package com.zz.live.ui.activity.user_info_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.LoginActivity;
import com.zz.live.utils.ClearCache;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.LimitTextChange;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class ModifyPasswordActivity extends BaseActivity {
    @BindView(R.id.old_password_etv)
    EditText old_password_etv;
    @BindView(R.id.new_password_etv)
    EditText new_password_etv;
    @BindView(R.id.sure_new_password_etv)
    EditText sure_new_password_etv;
    @BindView(R.id.find_psd_btn)
    Button find_psd_btn;
    int type;
    @Override
    public int getLayoutId() {
        return R.layout.activity_find_password;
    }

    /**
     *
     * @param context
     * @param type 修改的密码类型 1登录密码2支付密码
     */
    public static void  startAty(Context context,int type){
        Intent intent = new Intent(context, ModifyPasswordActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        type=getIntent().getIntExtra("type",1);
        if(type==1){
            CommonToolbarUtil.initToolbar(this,"修改登录密码");
        }else {
            CommonToolbarUtil.initToolbar(this,"修改支付密码");

        }
        LimitTextChange.StringWatcher(old_password_etv);
        LimitTextChange.StringWatcher(new_password_etv);
        LimitTextChange.StringWatcher(sure_new_password_etv);
    }

    @OnClick({R.id.find_psd_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.find_psd_btn:
                if(checkEtv()){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("oldPassword", old_password_etv.getText().toString());
                    data.put("newPassword", new_password_etv.getText().toString());
                    data.put("type", type);
                    HttpApiUtils.request(ModifyPasswordActivity.this, null,RequestUtils.FIND_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result) {
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            String msg = jsonObject.getString("msg");
                            showtoast(msg);
                            /*
                            修改登录密成功后,跳转登录
                             */
                            if(type==1){
                                Intent intent=new Intent(ModifyPasswordActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                intent.putExtra("singleLogin",true);
                                ClearCache.clearCache(ModifyPasswordActivity.this);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFail(String msg) {
                        }
                    });

                }
                break;
                default:
                    break;
        }
    }
    private boolean checkEtv() {
        String nowPsdStr = old_password_etv.getText().toString();
        String newPsdStr = new_password_etv.getText().toString();
        String surePsdStr = sure_new_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(nowPsdStr)){
            showtoast("请输入当前密码");
            return false;
        }else if(!Utils.checkPsw(nowPsdStr)){
            showtoast("密码为8-20位字母和数字的组合");
            return  false;
        }
        if(StringMyUtil.isEmptyString(newPsdStr)){
            showtoast("请输入新密码");
            return false;
        }else if(!Utils.checkPsw(newPsdStr)){
            showtoast("新密码为8-20位字母和数字的组合");
            return  false;
        }
        if(StringMyUtil.isEmptyString(surePsdStr)){
            showtoast("请再次输入新密码");
            return false;
        }else if(!Utils.checkPsw(surePsdStr)){
            showtoast("确认密码为8-20位字母和数字的组合");
            return  false;
        }
        if(!newPsdStr.equals(surePsdStr)){
            showtoast("两次输入的密码不一致");
            return  false;
        }
        return  true;
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
    CommonToolbarUtil.initStatusBarColor(this);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
