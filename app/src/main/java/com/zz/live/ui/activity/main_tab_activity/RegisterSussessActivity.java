package com.zz.live.ui.activity.main_tab_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.LoginEntity;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.ui.activity.mine_fragment_activity.certification_activity.CertificationActivity;
import com.zz.live.utils.AESUtil;
import com.zz.live.utils.CommonToolbarUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
import okhttp3.ResponseBody;

public class RegisterSussessActivity extends BaseActivity {

    @BindView(R.id.certification_btn)
    Button Certification_btn;
    @BindView(R.id.no_certification_btn)
    Button no_Certification_btn;
    private SharedPreferenceHelperImpl sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_sussess;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        requestLogin();
    }

    /**
     * 请求登录接口
     */
    private void requestLogin() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("username",getIntent().getStringExtra("username"));
        data.put("password", AESUtil.encrypt(getIntent().getStringExtra("password")));
//        data.put("decode",1);
//        data.put("grant_type","password");
        HttpApiImpl.getInstance()
                .login(data)
//                .login("mobile:"+getIntent().getStringExtra("username"),AESUtil.encrypt(getIntent().getStringExtra("password")),"password")
//                .login("mobile:"+getIntent().getStringExtra("username"),getIntent().getStringExtra("password"))
                .compose(RxTransformerUtils.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                .subscribe(new BaseStringObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(String result) {
                        LoginEntity loginEntity = JSONObject.parseObject(result, LoginEntity.class);
                        String access_token = loginEntity.getData().getAccess_token();
                        sp.setToken(access_token);
                    }

                    @Override
                    public void onFail(String msg) {
                    }

                    @Override
                    protected void onRequestStart() {
                        super.onRequestStart();
                        showLoading();
                    }

                    @Override
                    protected void onRequestEnd() {
                        super.onRequestEnd();
                        closeLoading();
                    }
                });
    }
    public static void startAty(Context context,String username, String password){
        Intent intent = new Intent(context, RegisterSussessActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        context.startActivity(intent);
    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"注册成功");
        CommonToolbarUtil.initStatusBarColor(this);
    }
    @OnClick({R.id.certification_btn,R.id.no_certification_btn})
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.certification_btn:
                //主播认证
                startActivity(new Intent(RegisterSussessActivity.this, CertificationActivity.class));
                break;
            case R.id.no_certification_btn:
                startActivity(new Intent(RegisterSussessActivity.this,LoginActivity.class));
                break;
                default:
                    break;

        }
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
