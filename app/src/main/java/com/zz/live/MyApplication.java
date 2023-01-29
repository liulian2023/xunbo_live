package com.zz.live;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.ArrayMap;

import com.cambodia.zhanbang.rxhttp.net.common.ApiConfig;
import com.cambodia.zhanbang.rxhttp.net.utils.CommonModule;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.model.UpdateConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.cretin.www.cretinautoupdatelibrary.utils.SSLUtils;
import com.previewlibrary.ZoomMediaLoader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.zz.live.bean.AppUpdateModel;
import com.zz.live.bean.evenBus.leaveAppEvenModel;
import com.zz.live.ui.adapter.TestImageLoader;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.OwnUncaughtExceptionHandler;
import com.zz.live.utils.SharePreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private static Context instance;
    public static Context getInstance() {
        return instance;
    }
    private int appCount=0;
    private boolean isRunInBackground=false;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        CommonModule.init(this);
        //rxhttp网络请求
        initRxhttp();
        //融云
        initRongYun();
        //大图预览
        ZoomMediaLoader.getInstance().init(new TestImageLoader());
        //友盟统计
//        initUmeng();
        //版本更新
        initAppUpdate();
        //app后台监听
        initBackgroundCallBack();
//        MHSDK.getInstance().init(this,"b162086e4e077d15341a4ce7ab56cd9e");
        // 以下用来捕获程序崩溃异常  程序崩溃时触发线程
        Thread.setDefaultUncaughtExceptionHandler(new OwnUncaughtExceptionHandler());
        initHttpCache();
    }

    /**
     * HttpResponseCache 处理缓存 (SVGA缓存处理)
     */
    private void    initHttpCache() {
        File httpCacheDir=new File(instance.getCacheDir(),"http");
        long httpCacheSize=1024 *1024*1000;
        try {
            HttpResponseCache.install(httpCacheDir,httpCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         * */
        if(!BuildConfig.DEBUG){
            UMConfigure.init(this,  UMConfigure.DEVICE_TYPE_PHONE, null);
            // 选用AUTO页面采集模式
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
            // 打开统计SDK调试模式
            UMConfigure.setLogEnabled(BuildConfig.DEBUG?false:true);
            //错误统计功能，
            MobclickAgent.setCatchUncaughtExceptions(BuildConfig.DEBUG?false:true);
        }

    }

    private void initRongYun() {
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        boolean loginStatus = sp.getLoginStatus();
        if(loginStatus){
            String rongKey = sp.getRongKey();
            RongLibUtils.initRongYun(rongKey);
            String rongToken = sp.getRongToken();
            RongLibUtils.connectRongYun(rongToken);
        }
    }


    private void initRxhttp(){
        ArrayMap<String, String> headMap = new ArrayMap<String, String>();
        //retrofit2 工厂类初始化
        ApiConfig build = new ApiConfig.Builder()
                .setBaseUrl1(BuildConfig.API_HOST1)//BaseUrl，这个地方加入后项目中默认使用该url
                .setBaseUrl2(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_BASE_URL,BuildConfig.API_HOST2))
                .setSucceedCode(200)//成功返回码  200
                .setDefaultTimeout(1000*30)//响应时间，可以不设置，默认为2000毫秒
                .setOpenHttps(true)
                //        .setHeads(headMap)
                .build();
        build.init(this);
    }
    private void initAppUpdate() {
        //如果你想使用okhttp作为下载的载体，那么你需要自己依赖okhttp，更新库不强制依赖okhttp！可以使用如下代码创建一个OkHttpClient 并在UpdateConfig中配置setCustomDownloadConnectionCreator start
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30_000, TimeUnit.SECONDS)
                .readTimeout(30_000, TimeUnit.SECONDS)
                .writeTimeout(30_000, TimeUnit.SECONDS)
                //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 start
                .sslSocketFactory(SSLUtils.createSSLSocketFactory())
                .hostnameVerifier(new SSLUtils.TrustAllHostnameVerifier())
                //如果你需要信任所有的证书，可解决根证书不被信任导致无法下载的问题 end
                .retryOnConnectionFailure(true);

        //更新库配置
        UpdateConfig updateConfig = new UpdateConfig()
                .setDebug(true)//是否是Debug模式
                .setBaseUrl("http://www.cretinzp.com/system/versioninfo")//当dataSourceType为DATA_SOURCE_TYPE_URL时，配置此接口用于获取更新信息
                .setMethodType(TypeConfig.METHOD_GET)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的方法
                .setDataSourceType(TypeConfig.DATA_SOURCE_TYPE_URL)//设置获取更新信息的方式
                .setShowNotification(true)//配置更新的过程中是否在通知栏显示进度
                .setNotificationIconRes(R.drawable.ic_launch)//配置通知栏显示的图标
                .setUiThemeType(TypeConfig.UI_THEME_AUTO)//配置UI的样式，一种有12种样式可供选择
                .setRequestHeaders(null)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的请求头
                .setRequestParams(null)//当dataSourceType为DATA_SOURCE_TYPE_URL时，设置请求的请求参数
                .setAutoDownloadBackground(false)//是否需要后台静默下载，如果设置为true，则调用checkUpdate方法之后会直接下载安装，不会弹出更新页面。当你选择UI样式为TypeConfig.UI_THEME_CUSTOM，静默安装失效，您需要在自定义的Activity中自主实现静默下载，使用这种方式的时候建议setShowNotification(false)，这样基本上用户就会对下载无感知了
//                .setCustomActivityClass(CustomActivity.class)//如果你选择的UI样式为TypeConfig.UI_THEME_CUSTOM，那么你需要自定义一个Activity继承自RootActivity，并参照demo实现功能，在此处填写自定义Activity的class
                .setNeedFileMD5Check(false)//是否需要进行文件的MD5检验，如果开启需要提供文件本身正确的MD5校验码，DEMO中提供了获取文件MD5检验码的工具页面，也提供了加密工具类Md5Utils
//                .setCustomDownloadConnectionCreator(new OkHttp3Connection.Creator(builder))//如果你想使用okhttp作为下载的载体，可以使用如下代码创建一个OkHttpClient，并使用demo中提供的OkHttp3Connection构建一个ConnectionCreator传入，在这里可以配置信任所有的证书，可解决根证书不被信任导致无法下载apk的问题
                .setModelClass(new AppUpdateModel());
        //初始化
        AppUpdateUtils.init(this, updateConfig);
    }

    private void initBackgroundCallBack() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
                if (isRunInBackground) {
                    //应用从后台回到前台 需要做的操作
                    back2App(activity);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
                if (appCount == 0) {
                    //应用进入后台 需要做的操作
                    leaveApp(activity);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    /**
     * 从后台回到前台需要执行的逻辑
     *
     * @param activity
     */
    private void back2App(Activity activity) {
        isRunInBackground = false;
        EventBus.getDefault().postSticky(new leaveAppEvenModel(true));
    }

    /**
     * 离开应用 压入后台或者退出应用
     *
     * @param activity
     */
    private void leaveApp(Activity activity) {
        isRunInBackground = true;
        EventBus.getDefault().postSticky(new leaveAppEvenModel(false));
    }
}
