package com.zz.live.ui.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.BuildConfig;
import com.zz.live.MainActivity;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.BaseUrlEntity;
import com.zz.live.bean.LoginEntity;
import com.zz.live.bean.SystemParamsEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.AnchorHouseKeeperActivity;
import com.zz.live.ui.activity.mine_fragment_activity.certification_activity.CertificationActivity;
import com.zz.live.ui.pop.CommonChoosePop;
import com.zz.live.ui.pop.SingleLoginPop;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.utils.AESUtil;
import com.zz.live.utils.ActivityUtil;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StatusBarUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.zz.live.ui.activity.main_tab_activity.SplashActivity.pingSuccessTime;


/*
登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    @BindView(R.id.login_app_name_tv)
    TextView login_app_name_tv;
    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.login_account_etv)
    EditText login_account_etv;
    @BindView(R.id.login_password_etv)
    EditText login_password_etv;
    @BindView(R.id.to_register_tv)
    TextView to_register_tv;
    @BindView(R.id.login_account_clear_iv)
    ImageView login_account_clear_iv;
    @BindView(R.id.login_password_clear_iv)
    ImageView login_password_clear_iv;
    @BindView(R.id.platform_etv)
    EditText platform_etv;
    @BindView(R.id.login_version_name_tv)
    TextView login_version_name_tv;
    //触发融云和后台单点登录时都会传入的字段,判断是否需要弹出下线pop
    private boolean singleLogin;
    //出发后台单点登录时传入的字段,用于判断下线pop的显示内容
    int flag;
    public long firstTime = 0;

    //忘记密码,成功找回后传回的nickName,进入页面时显示
    private String nickName;
    /*final RxPermissions rxPermissions = new RxPermissions(this);
    String mImei;*/
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private String account;
    private String password;
    private String paypassword;
    private String isOpenPayPass;
    private String ip;

    String ali_sessionId;
    String ali_sig;
    String ali_token;
    private String appName;
    private String frontUrl;
    private String url;
    private String domain;

    private SingleLoginPop singleLoginPop;
    private static String TAG = "LoginActivity";

    String bestDomain="";
    private  int failCount=0;
    private JSONArray domainDatas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        toolbar_back_iv.setVisibility(View.GONE);
        CommonToolbarUtil.initToolbar(this,"登录");
        getIntentData();
        initSharePreferencesData();
        login_account_etv.setSelection(login_account_etv.getText().length());
        initSingleLogin();
        initCacheData();

        if(BuildConfig.DEBUG){
            login_version_name_tv.setText(String.format("测试版 版本号:%s",BuildConfig.VERSION_NAME));
        }else {
            login_version_name_tv.setText(String.format("版本号:%s",BuildConfig.VERSION_NAME));
        }
    }

    private void initCacheData() {
        String platform = sp.getPlatform();
        if(StringMyUtil.isNotEmpty(platform)){
            platform_etv.setText(platform);
        }
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        if(userInfoBean!=null){
            String username = userInfoBean.getUsername();
            login_account_etv.setText(username);
        }
    }

    private void initSingleLogin() {
        if(singleLogin){
            singleLoginPop = new SingleLoginPop(this);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(login_account_etv!=null){
                                singleLoginPop.showAtLocation(login_account_etv, Gravity.CENTER,0,0);
                                Utils.darkenBackground(LoginActivity.this,0.7f);
                            }
                        }
                    });

                }
            },400);

        }
    }

    private void initSharePreferencesData() {
        SystemParamsEntity.DataBean systemParam = Utils.getSystemParam();
        if(systemParam!=null){
            appName = systemParam.getWebsiteName();
/*            if(StringMyUtil.isNotEmpty(appName)){
                login_app_name_tv.setText(appName);
            }*/
        }
    }
    @OnTextChanged(value = R.id.login_password_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPasswordTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        String string = login_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(string)){
            login_password_clear_iv.setVisibility(View.GONE);
        }else {
            login_password_clear_iv.setVisibility(View.VISIBLE);
        }

    }
    @OnTextChanged(value = R.id.login_account_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAccountTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        String toString = login_account_etv.getText().toString();
        if(StringMyUtil.isEmptyString(toString)){
            login_account_clear_iv.setVisibility(View.GONE);
        }else {
            login_account_clear_iv.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.login_account_clear_iv,R.id.login_password_clear_iv, R.id.login_btn,R.id.to_register_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_btn:
                if (CheckEdit()){
                    bestDomain="";//每次点击登录都将之前选择的最佳线路置空.避免登录接口请求成功后不走onSuccess中的判断
                    requestPlatform();
                }
                break;
            case R.id.to_register_tv:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.login_account_clear_iv:
                login_account_etv.setText("");
                break;
            case R.id.login_password_clear_iv:
                login_password_etv.setText("");
                break;
            default:
                break;
        }
    }

    private void requestPlatform() {
        showLoading();
        HttpApiUtils.getAsync(this,platform_etv.getText().toString(), new HttpApiUtils.FormRequestResult() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: getAsync" );
                closeLoading();
                if(Utils.isJsonObject(result)){
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    String status = jsonObject.getString("status");
                    if(StringMyUtil.isNotEmpty(status)&&status.equals("500")){
                        String message = jsonObject.getString("message");
                        showtoast(message);
                        return;
                    }
                    chooseBestUrl(jsonObject);
                }else {
                    showtoast("数据错误,请重试!");
                }

            }

            @Override
            public void onFail(String failStr) {
                Log.e(TAG, "onFail: getAsync" );
                     closeLoading();
                     showtoast(failStr);
            }
        });
    }

    /**
     * 选择最优域名
     * @param jsonObject
     */
    private void chooseBestUrl(JSONObject jsonObject) {
        if(platform_etv!=null){
            sp.setPlatform(platform_etv.getText().toString());//设置平台id缓存
        }

        domainDatas = jsonObject.getJSONArray("data");
        if(domainDatas!=null&&domainDatas.size()!=0){
            for (int i = 0; i < domainDatas.size(); i++) {
                JSONObject jsonObject1 = domainDatas.getJSONObject(i);
                String  domainName = null;
                try {
                    domainName = AESUtil.decrypt(jsonObject1.getString("domainName"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "domainName: "+domainName );


                Observable<Response<ResponseBody>> compose = new HttpApiImpl(domainName)
                        .pingTest()
                        .timeout(2000, TimeUnit.MILLISECONDS)
                        .compose(RxTransformerUtils.io_main());
                String finalDomainName = domainName;
                compose
                        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) LoginActivity.this)))
                        .subscribe(new BaseStringObserver<ResponseBody>() {
                            @Override
                            public void onSuccess(String result) {
                                if(StringMyUtil.isEmptyString(bestDomain)){
                                    bestDomain = finalDomainName;
                                    sp.setNewBaseUrl(bestDomain);
                                    try {
                                        requestLogin();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFail(String msg) {
                                try {
                                    failCount++;
                                    //主控返回的url全都请求失败时,放弃选择最优线裤, 直接用默认的baseUrl进行登录
                                    if(domainDatas!=null&&failCount==domainDatas.size()){
                                        requestLogin();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }
    }

    /**
     * 请求登录接口
     */
    private void requestLogin( ) throws IOException {
        Log.e(TAG, "onSuccess: requestLogin" );
        if(login_account_etv == null || StringMyUtil.isEmptyString(login_password_etv.getText().toString()) ){
            return;
        }
        showLoading();
        HashMap<String, Object> data = new HashMap<>();
        data.put("username",login_account_etv.getText().toString());
        String encrypt = AESUtil.encrypt(login_password_etv.getText().toString());
        data.put("password", encrypt.replace("\n",""));
        HttpApiUtils.doFormRequest(LoginActivity.this,RequestUtils.LOGIN,data, new HttpApiUtils.FormRequestResult() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: doFormRequest" );
                closeLoading();
                LoginEntity loginEntity = JSONObject.parseObject(result, LoginEntity.class);
                LoginEntity.DataBean loginEntityData = loginEntity.getData();
                String access_token = loginEntityData.getAccess_token();
                sp.setToken(access_token);
                sp.setUserId(loginEntityData.getId());
                //请求用户信息&融云相关
                closeLoading();
//               startActivity(new Intent(LoginActivity.this,SplashActivity.class));

                getUserInfo();

            }

            @Override
            public void onFail(String failStr) {
                bestDomain="";
                closeLoading();
            }
        });

    }
    /**
     * 获取用户信息,融云相关
     */
    private void getUserInfo() {
        HashMap<String, Object> data = new HashMap<>();
//      data.put("type",1);
        HttpApiUtils.request(LoginActivity.this,null, RequestUtils.USER_INFO, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: getUserInfo" );
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                UserInfoEntity.DataBean userInfoEntityData = userInfoEntity.getData();
                SharePreferencesUtil.putString(LoginActivity.this, CommonStr.USER_INFO,result);
//                userStatus 0 审核中  1 已认证  2认证失败 3 未认证
                int userStatus = userInfoEntityData.getUserStatus();//主播认证
                int userType = userInfoEntityData.getUserType();
                if(userType==1){
                    //家族长
                    try {
                        initUserStatus(userInfoEntityData, userStatus, "认证通过后才能登录",new Intent(LoginActivity.this, AnchorHouseKeeperActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    //主播和家族主播
                    try {
                        initUserStatus(userInfoEntityData, userStatus, "主播认证通过后才能登录",new Intent(LoginActivity.this,MainActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                bestDomain="";
            }
        });
    }

    /**
     * 获取用户信息后对应处理
     * @param userInfoEntityData 用户信息
     * @param userStatus 用户类型
     * @param toastText pop中的提示文字
     * @param intent 需要跳转的intent
     */
    private void initUserStatus(UserInfoEntity.DataBean userInfoEntityData, int userStatus, String toastText,Intent intent) throws Exception {
        if (userStatus == 0) {
            showtoast(toastText);
        } else if (userStatus == 1) {
            sp.setLoginStatus(true);
            String rongCloudKey = userInfoEntityData.getRongCloudKey();
            //融云key和token需要AES解密处理
            String decryptKey = AESUtil.decrypt(rongCloudKey);
            //融云key
            sp.setRongKey(decryptKey);
            String userToken = userInfoEntityData.getUserToken();
            String decryptToken = AESUtil.decrypt(userToken);
            //融云token
            sp.setRongToken(decryptToken);
            String roomId = AESUtil.decrypt(userInfoEntityData.getRoomId());
            sp.setRoomId(roomId);
            //初始化融云
            RongLibUtils.initRongYun(decryptKey);
            //连接融云服务器
            RongLibUtils.connectRongYun(decryptToken);
         /*   startActivity(intent);
            showtoast("登录成功");*/
            startActivity(new Intent(LoginActivity.this,SplashActivity.class));
            Log.e(TAG, "onSuccess: initUserStatus" );

        } else if (userStatus == 2) {
            CommonChoosePop commonChoosePop = new CommonChoosePop(LoginActivity.this, LoginActivity.this, "主播认证", "您的审核未通过，是否重新提交认证资料?", "重新提交");
            commonChoosePop.showAtLocation(to_register_tv,Gravity.CENTER,0,0);
            Utils.darkenBackground(LoginActivity.this,0.6f);
            commonChoosePop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    startActivity(new Intent(LoginActivity.this, CertificationActivity.class));
                }
            });
        } else if (userStatus == 3) {
            CommonChoosePop commonChoosePop = new CommonChoosePop(LoginActivity.this, LoginActivity.this, "主播认证", toastText, "立即认证");
            commonChoosePop.showAtLocation(to_register_tv,Gravity.CENTER,0,0);
            Utils.darkenBackground(LoginActivity.this,0.6f);
            commonChoosePop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    startActivity(new Intent(LoginActivity.this, CertificationActivity.class));
                }
            });
        }
    }

    private void getIntentData() {
        nickName=getIntent().getStringExtra("nickName");
        singleLogin=getIntent().getBooleanExtra(CommonStr.SINGLE_LOGIN,false);
        flag=getIntent().getIntExtra("flag",0);
        isOpenPayPass= SharePreferencesUtil.getString(LoginActivity.this,"isOpenPayPass","");
        if( !StringMyUtil.isEmptyString(nickName)){
            login_account_etv.setText(nickName);
        }else {
            login_account_etv.setText(SharePreferencesUtil.getString(LoginActivity.this, "account", ""));
        }

    }


    private boolean CheckEdit(){
        account = login_account_etv.getText().toString();
        password = login_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showtoast("账号不能为空");
            return false;
        }else if(StringMyUtil.isEmptyString(password)){
            showtoast("密码不能为空");
            return false;
        }
        if (account.length()<6||account.length()>11){
            showtoast("账号长度应为6-11位");
            return false;
        } else  if (password.length()<8||password.length()>20){
            showtoast("密码长度应为8-20位");
            return false;
        } /*else if (isOpenPayPass.equals("0")){
            if (paypassword.length()<6||paypassword.length()>16){
                showtoast("提款密码长度不符");
                return false;
            }
        }*/
        else if(StringMyUtil.isEmptyString(platform_etv.getText().toString())){
            showtoast("请输入平台ID");
            return false;
        }
        return true;
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
    /*
    用户登录成功后,需要调用application中连接融云服务器的方法,并创建消息监听
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Long user_id = SharePreferencesUtil.getLong(LoginActivity.this, "user_id", 0l);
            switch (msg.what) {
                //请求融云信息
                case 1:

                    break;
                case 2:


                    break;
            }

        }




    };

    @Override
    public void onNetChange(boolean netWorkState) {

    }


    /**
     *
     *测试ping成功的时间
     *
     * @param doMain
     * @return
     */
    public static long pingSuccessTime(String doMain) {
        String delay = new String();
        Process p = null;
        try {
            URL url = new URL(doMain);
            String host = url.getHost();
            p = Runtime.getRuntime().exec("ping -c 1 " + host);//1代表的是每次尝试的次数
//            p = Runtime.getRuntime().exec("curl -c 1 " + host);
            Log.e(TAG, "pingSuccessTime: 延迟测试:"+host );
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = new String();
            while ((str = buf.readLine()) != null) {
                if (str.contains("avg")) {
                    int i = str.indexOf("/", 20);
                    int j = str.indexOf(".", i);
                    Log.e(TAG, "pingSuccessTime: 延迟结果:"+str.substring(i + 1, j));
                    delay = str.substring(i + 1, j);
                    return Long.parseLong(StringMyUtil.isEmptyString(delay)?"0":delay);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.parseLong(StringMyUtil.isEmptyString(delay)?"100000":delay);
    }
}