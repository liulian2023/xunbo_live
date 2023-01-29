package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.LimitTextChange;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AddAnchorActitity extends BaseActivity {
    @BindView(R.id.account_etv)
    EditText account_etv;
    @BindView(R.id.login_password_etv)
    EditText login_password_etv;
    @BindView(R.id.sure_password_etv)
    EditText sure_password_etv;
    @BindView(R.id.add_anchor_btn)
    Button add_anchor_btn;
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_anchor_actitity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LimitTextChange.StringWatcher(account_etv);
        LimitTextChange.StringWatcher(login_password_etv);
        LimitTextChange.StringWatcher(sure_password_etv);
    }
    @OnClick({R.id.add_anchor_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_anchor_btn:
                if(checkEtv()&&Utils.isNotFastClick()){
                    String password = login_password_etv.getText().toString();
                    if(!sure_password_etv.getText().toString().equals(password)){
                        showtoast("两次输入的密码不一致");
                    }else {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put("username",account_etv.getText().toString());
                        data.put("password", password);
                        HttpApiUtils.request(AddAnchorActitity.this, null, RequestUtils.ADD_ANCHOR, data, new HttpApiUtils.OnRequestLintener() {
                            @Override
                            public void onSuccess(String result) {
                                showtoast("注册成功");
                                account_etv.setText("");
                                login_password_etv.setText("");
                                sure_password_etv.setText("");
                            }

                            @Override
                            public void onFail(String msg) {
                            }
                        });
                    }

                }
                break;
               default:
                   break;
        }
    }
    private boolean checkEtv() {
        String loginPsd = login_password_etv.getText().toString();
        String surePsd = sure_password_etv.getText().toString();
        String account = account_etv.getText().toString();
        if(StringMyUtil.isEmptyString(account)){
            showtoast("请输入主播账号");
            return  false;
        }
        if(StringMyUtil.isEmptyString(loginPsd)){
            showtoast("请输入主播密码");
            return  false;
        }
        if(StringMyUtil.isEmptyString(surePsd)){
            showtoast("请再次输入主播密码");
            return  false;
        }
        if(account.length()<6||account.length()>11||!Utils.checkAccount(account)){
            showtoast("账号应为6-11位字母加数字的组合");
            return false;
        }
        if (loginPsd.length()<8||loginPsd.length()>20||!Utils.checkPsw(loginPsd)){
            showtoast("密码应为8-20字母加数字的组合");
            return false;
        }
        if (surePsd.length()<8||surePsd.length()>20||!Utils.checkPsw(surePsd)){
            showtoast("确认密码密码应为8-20字母加数字的组合");
            return false;
        }
        if(!loginPsd.equals(surePsd)){
            showtoast("两次密码输入不一致");
        }
        return true;
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"添加主播");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
