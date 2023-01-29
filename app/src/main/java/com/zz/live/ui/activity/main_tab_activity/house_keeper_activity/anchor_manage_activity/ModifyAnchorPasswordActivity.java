package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.LimitTextChange;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class ModifyAnchorPasswordActivity extends BaseActivity {
    @BindView(R.id.title_iv)
    ImageView title_iv;
    @BindView(R.id.nickname_tv)
    TextView nickname_tv;
    @BindView(R.id.username_tv)
    TextView username_tv;
    @BindView(R.id.login_password_etv)
    EditText login_password_etv;
    @BindView(R.id.sure_login_password_etv)
    EditText sure_login_password_etv;
    @BindView(R.id.family_password_etv)
    EditText family_password_etv;
    @BindView(R.id.commit_btn)
    Button commit_btn;
    AnchorManageEntity.DataBean.RecordsBean recordsBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_anchor_password_actitity;
    }
    public static void startAty(Context context, AnchorManageEntity.DataBean.RecordsBean recordsBean){
        Intent intent = new Intent(context, ModifyAnchorPasswordActivity.class);
        intent.putExtra("recordsBean", (Serializable) recordsBean);
        context.startActivity(intent);
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        LimitTextChange.StringWatcher(login_password_etv);
        LimitTextChange.StringWatcher(sure_login_password_etv);
        LimitTextChange.StringWatcher(family_password_etv);
        getIntentData();
    }

    private void getIntentData() {
        recordsBean= (AnchorManageEntity.DataBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        nickname_tv.setText(recordsBean.getNickname());
        username_tv.setText(recordsBean.getUsername());
        GlideLoadViewUtil.LoadTitleView(this,recordsBean.getImage(),title_iv);
    }
    @OnClick({R.id.commit_btn})
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.commit_btn:
                if(checkEtv()&&Utils.isNotFastClick()){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("memberId",recordsBean.getId());
                    data.put("newPassword",login_password_etv.getText().toString());
                    data.put("patriarchPassword",family_password_etv.getText().toString());
                    data.put("type",1);
                    HttpApiUtils.request(ModifyAnchorPasswordActivity.this, null, RequestUtils.MODIFY_CHILD_ANCHOR_PASSWORD, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result) {
                            showtoast("修改成功");
                            finish();
                        }

                        @Override
                        public void onFail(String msg) {
                        }
                    });
                }

                break;
        }
    }

    private boolean checkEtv() {
        String loginPsd = login_password_etv.getText().toString();
        String surePsd = sure_login_password_etv.getText().toString();
        String familyPsd = family_password_etv.getText().toString();
        if(StringMyUtil.isEmptyString(loginPsd)){
            showtoast("请输入新密码");
            return  false;
        }
        if(StringMyUtil.isEmptyString(surePsd)){
            showtoast("请再次输入新密码");
            return  false;
        }
        if(StringMyUtil.isEmptyString(familyPsd)){
            showtoast("请输入家族密码");
            return  false;
        }

        if (loginPsd.length()<8||loginPsd.length()>20||!Utils.checkPsw(loginPsd)){
            showtoast("密码应为8-20字母加数字的组合");
            return false;
        }
        if (surePsd.length()<8||surePsd.length()>20||!Utils.checkPsw(surePsd)){
            showtoast("确认密码应为8-20字母加数字的组合");
            return false;
        }
        if(!loginPsd.equals(surePsd)){
            showtoast("两次密码输入不一致");
            return false;
        }
        if (familyPsd.length()<8||familyPsd.length()>20||!Utils.checkPsw(familyPsd)){
            showtoast("家族密码应为8-20字母加数字的组合");
            return false;
        }
        return true;
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"修改密码");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
