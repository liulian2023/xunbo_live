package com.zz.live;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.model.SingleLoginEvent;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppUpdateInfoListener;
import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AppUpdateEntity;
import com.zz.live.bean.AppUpdateModel;
import com.zz.live.bean.HomeNoticeEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.LoginActivity;
import com.zz.live.ui.activity.start_live_activity.StartLiveActivity;
import com.zz.live.ui.fragment.main_tab_fragment.DynamicFragment;
import com.zz.live.ui.fragment.main_tab_fragment.HomeFragment;
import com.zz.live.ui.fragment.main_tab_fragment.HomeLiveFragment;
import com.zz.live.ui.fragment.main_tab_fragment.MineLiveFragment;
import com.zz.live.ui.fragment.main_tab_fragment.RankFragment;
import com.zz.live.ui.pop.HomeAtyPop;
import com.zz.live.utils.ActivityUtil;
import com.zz.live.utils.ClearCache;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.ProgressDialogUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.VersionUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_home_linear)
    LinearLayout tab_home_linear;
    @BindView(R.id.tab_dynamic_linear)
    LinearLayout tab_dynamic_linear;
    @BindView(R.id.tab_rank_linear)
    LinearLayout tab_rank_linear;
    @BindView(R.id.tab_mine_linear)
    LinearLayout tab_mine_linear;
    @BindView(R.id.tab_home_img)
    ImageView tab_home_img;
    @BindView(R.id.tab_dynamic_img)
    ImageView tab_dynamic_img;
    @BindView(R.id.tab_rank_img)
    ImageView tab_rank_img;
    @BindView(R.id.tab_mine_img)
    ImageView tab_mine_img;
    @BindView(R.id.tab_home_tv)
    TextView tab_home_tv;
    @BindView(R.id.tab_dynamic_tv)
    TextView tab_dynamic_tv;
    @BindView(R.id.tab_rank_tv)
    TextView tab_rank_tv;
    @BindView(R.id.tab_mine_tv)
    TextView tab_mine_tv;
    @BindView(R.id.tab_unread_tv)
    TextView tab_unread_tv;
    @BindView(R.id.start_live_iv)
    ImageView start_live_iv;
    @BindView(R.id.maim_fragment_content)
    FrameLayout maim_fragment_content;
    HomeLiveFragment homeLiveFragment;
    HomeFragment homeFragment;
    DynamicFragment dynamicFragment;
    RankFragment rankFragment;
    MineLiveFragment mineLiveFragment;
    private FragmentManager fragmentManager;
    public long firstTime = 0;
    private Integer versionCode;
    private String versionName;
    private String updateTip;
    private String TAG="MainActivity";
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private ArrayList<HomeNoticeEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    HomeAtyPop homeAtyPop;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        fragmentManager = getSupportFragmentManager();
        tab_home_linear.performClick();

        requestAppUpdate();
        requestBaseUrlList();
        requestNoticeData();
    }

    private void requestNoticeData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",20);
        HttpApiUtils.normalRequest(this, null, RequestUtils.HOME_NOTICE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                HomeNoticeEntity homeNoticeEntity = JSONObject.parseObject(result, HomeNoticeEntity.class);
                List<HomeNoticeEntity.DataBean.RecordsBean> records = homeNoticeEntity.getData().getRecords();
                recordsBeanArrayList.addAll(records);
                initNoticePop();
                if(recordsBeanArrayList.size()!=0){
                    showAtyPop();
                }
                homeAtyPop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void requestBaseUrlList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",100);
        HttpApiUtils.wwwNormalRequest(this, null, RequestUtils.BASE_URL_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                sp.setUrlList(result);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    /**
     * 版本更新
     */
    private void requestAppUpdate() {
        int appVersionCode = (int) VersionUtils.getAppVersionCode(this);
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.normalRequest(this, null, RequestUtils.APP_UPDATE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AppUpdateEntity appUpdateEntity = JSONObject.parseObject(result, AppUpdateEntity.class);
                List<AppUpdateEntity.DataBean> records = appUpdateEntity.getData();
                AppUpdateEntity.DataBean recordsBean = null;
                for (int i = 0; i < records.size(); i++) {
                    AppUpdateEntity.DataBean bean = records.get(i);
                    if(bean.getType()==1){
                        recordsBean=bean;
                        break;
                    }
                }
                if(recordsBean!=null){
                    versionCode = recordsBean.getVersionCode();
                    versionName = recordsBean.getVersionName();
                    boolean mustUpdate = recordsBean.isMustUpdate();
                    String downloadUrl = recordsBean.getDownloadUrl();
                    updateTip = recordsBean.getTips();
                    if(versionCode==appVersionCode){
                        Log.e(TAG, "onSuccess: 当前是最新版本" );
                    }else {
                        AppUpdateModel appUploadModel = new AppUpdateModel();
                        appUploadModel.setAutoUpdateBackground(false);
                        appUploadModel.setCheckFileMD5(false);
                        appUploadModel.setSourceTypeVaule(TypeConfig.DATA_SOURCE_TYPE_MODEL);
                        appUploadModel.setUiTypeValue(TypeConfig.UI_THEME_B);
                        if (mustUpdate) {//强制更新
                            appUploadModel.setForceUpdate(true);
                        } else {//非强制更新
                            appUploadModel.setForceUpdate(false);
                        }
                        try {
                            startUpdateApp(appUploadModel, downloadUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFail(String msg) {

            }

        });
    }
    /**
     活动公告pop相关配置
     */
    private void initNoticePop() {
        if(homeAtyPop==null){
            homeAtyPop = new HomeAtyPop(this,this,recordsBeanArrayList);
        }
    }
    /*
    弹出活动公告pop(延时弹出)
     */
    private void showAtyPop() {
        tab_home_linear.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!MainActivity.this.isFinishing()) {
                    if(tab_home_linear!=null){
                        homeAtyPop.showAtLocation(tab_home_linear, Gravity.CENTER, 0, 0); // 显示弹出窗口
                    }
                }
                ProgressDialogUtil.darkenBackground(MainActivity.this,0.7f);
            }
        },300);
    }
    /**
     * 弹出更新pop
     * @param appUploadModel
     * @param downloadUrl
     * @throws IOException
     */
    private void startUpdateApp(AppUpdateModel appUploadModel,String downloadUrl) throws IOException {
        DownloadInfo downloadInfo = new DownloadInfo().setApkUrl(downloadUrl)
                //EC42E7E1FE580F1E187C8E10355A13BB0
//                .setFileSize(getContentLength(downloadUrl))
                .setProdVersionCode(versionCode)
                .setProdVersionName(versionName)
                .setForceUpdateFlag(appUploadModel.isForceUpdate() ? 1 : 0)
                .setUpdateLog(updateTip);
        AppUpdateUtils appUpdateUtils = AppUpdateUtils.getInstance();
        appUpdateUtils.getUpdateConfig().setUiThemeType(appUploadModel.getUiTypeValue());
        //打开文件MD5校验
        appUpdateUtils.getUpdateConfig().setNeedFileMD5Check(appUploadModel.isCheckFileMD5());
        appUpdateUtils.getUpdateConfig().setDataSourceType(appUploadModel.getSourceTypeVaule());
        //开启或者关闭后台静默下载功能
        appUpdateUtils.getUpdateConfig().setAutoDownloadBackground(appUploadModel.isAutoUpdateBackground());
        if (appUploadModel.isAutoUpdateBackground()) {
            //开启静默下载的时候推荐关闭通知栏进度提示
            appUpdateUtils.getUpdateConfig().setShowNotification(false);
        } else {
            appUpdateUtils.getUpdateConfig().setShowNotification(true);
        }
        appUpdateUtils
                .addAppUpdateInfoListener(new AppUpdateInfoListener() {
                    @Override
                    public void isLatestVersion(boolean isLatest) {
                        Log.e("HHHHHHHHHHHHHHH", "isLatest:" + isLatest);
                    }
                })
                .addAppDownloadListener(new AppDownloadListener() {
                    @Override
                    public void downloading(int progress) {
                        Log.e("HHHHHHHHHHHHHHH", "progress:" + progress);
                    }

                    @Override
                    public void downloadFail(String msg) {
                        Log.e("HHHHHHHHHHHHHHH", "msg:" + msg);
                    }

                    @Override
                    public void downloadComplete(String path) {
                        Log.e("HHHHHHHHHHHHHHH", "path:" + path);

                    }

                    @Override
                    public void downloadStart() {
                        Log.e("HHHHHHHHHHHHHHH", "start");
//                        sendMessage2Fragment();
                    }

                    @Override
                    public void reDownload() {
                        Log.e("HHHHHHHHHHHHHHH", "reDownload");
                    }

                    @Override
                    public void pause() {
                        Log.e("HHHHHHHHHHHHHHH", "pause");
                    }
                })
                .checkUpdate(downloadInfo);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //单点登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void singleLogin(SingleLoginEvent singleLoginEvent) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClass(MainActivity.this, LoginActivity.class);
        intent.putExtra(CommonStr.SINGLE_LOGIN,singleLoginEvent.isSingleLogin());
        intent.putExtra("flag",singleLoginEvent.getFlag());
        ClearCache.clearCache(MyApplication.getInstance());
        startActivity(intent);
    }
    @OnClick({R.id.tab_home_linear,R.id.tab_dynamic_linear,R.id.tab_rank_linear,R.id.tab_mine_linear,R.id.start_live_iv})
    public void onClick(View v){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.tab_home_linear:
                hideAllFragment(transaction);
                initHomeTab();
/*                if(homeLiveFragment ==null){
                    homeLiveFragment =new HomeLiveFragment();
                    transaction.add(R.id.maim_fragment_content, homeLiveFragment);
                }else {
                    transaction.show(homeLiveFragment);
                }  */
                if(homeFragment ==null){
                    homeFragment =new HomeFragment();
                    transaction.add(R.id.maim_fragment_content, homeFragment);
                }else {
                    transaction.show(homeFragment);
                }
                break;
            case R.id.tab_dynamic_linear:
                hideAllFragment(transaction);
                initDynamicTab();
                if(dynamicFragment==null){
                    dynamicFragment=new DynamicFragment();
                    transaction.add(R.id.maim_fragment_content,dynamicFragment);
                }else {
                    transaction.show(dynamicFragment);
                }
                break;
            case R.id.tab_rank_linear:
                hideAllFragment(transaction);
                initRankTab();
                if(rankFragment==null){
                    rankFragment=new RankFragment();
                    transaction.add(R.id.maim_fragment_content,rankFragment);
                }else {
                    transaction.show(rankFragment);
                }
                break;
            case R.id.tab_mine_linear:
                hideAllFragment(transaction);
                initMineTab();
                if(mineLiveFragment ==null){
                    mineLiveFragment =new MineLiveFragment();
                    transaction.add(R.id.maim_fragment_content, mineLiveFragment);
                }else {
                    transaction.show(mineLiveFragment);
                }
                break;
            case R.id.start_live_iv:
                StartLiveActivity.startAty(MainActivity.this);
                break;
                default:
                    break;
        }
        transaction.commit();
    }

    private void initHomeTab() {
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(tab_home_img);
        tab_home_img.setImageResource(R.drawable.home_click);
        tab_dynamic_img.setImageResource(R.drawable.dynamic_unclick);
        tab_rank_img.setImageResource(R.drawable.rank_unclick);
        tab_mine_img.setImageResource(R.drawable.me_unclick);

        tab_home_tv.setTextColor(Color.parseColor("#FF574E"));
        tab_dynamic_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_rank_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_mine_tv.setTextColor(Color.parseColor("#A9A9A9"));

    }
    private void initDynamicTab() {
        YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(tab_dynamic_img);
        tab_home_img.setImageResource(R.drawable.home_unclick);
        tab_dynamic_img.setImageResource(R.drawable.dynamic_click);
        tab_rank_img.setImageResource(R.drawable.rank_unclick);
        tab_mine_img.setImageResource(R.drawable.me_unclick);

        tab_home_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_dynamic_tv.setTextColor(Color.parseColor("#FF574E"));
        tab_rank_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_mine_tv.setTextColor(Color.parseColor("#A9A9A9"));

    }
    private void initRankTab() {
                YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(tab_rank_img);
        tab_home_img.setImageResource(R.drawable.home_unclick);
        tab_dynamic_img.setImageResource(R.drawable.dynamic_unclick);
        tab_rank_img.setImageResource(R.drawable.rank_click);
        tab_mine_img.setImageResource(R.drawable.me_unclick);

        tab_home_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_dynamic_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_rank_tv.setTextColor(Color.parseColor("#FF574E"));
        tab_mine_tv.setTextColor(Color.parseColor("#A9A9A9"));


    }
    private void initMineTab() {
                YoYo.with(Techniques.Landing)
                .duration(700)
                .playOn(tab_mine_img);
        tab_home_img.setImageResource(R.drawable.home_unclick);
        tab_dynamic_img.setImageResource(R.drawable.dynamic_unclick);
        tab_rank_img.setImageResource(R.drawable.rank_unclick);
        tab_mine_img.setImageResource(R.drawable.me_click);

        tab_home_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_dynamic_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_rank_tv.setTextColor(Color.parseColor("#A9A9A9"));
        tab_mine_tv.setTextColor(Color.parseColor("#FF574E"));


    }
    /**
     * 每次点击底部tab都隐藏所有fragment
     * @param fragmentTransaction
     */
    private void  hideAllFragment( FragmentTransaction fragmentTransaction){
            if(homeLiveFragment !=null){fragmentTransaction.hide(homeLiveFragment);}
            if(dynamicFragment!=null){fragmentTransaction.hide(dynamicFragment);}
            if(rankFragment!=null){fragmentTransaction.hide(rankFragment);}
            if(mineLiveFragment !=null){fragmentTransaction.hide(mineLiveFragment);}
            if(homeFragment !=null){fragmentTransaction.hide(homeFragment);}
        }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                showtoast("再按一次退出程序");
                firstTime = secondTime;
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}