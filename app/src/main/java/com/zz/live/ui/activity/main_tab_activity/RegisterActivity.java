package com.zz.live.ui.activity.main_tab_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.LimitTextChange;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StatusBarUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;


/*
注册页面
 */
public class RegisterActivity extends BaseActivity /*implements View.OnClickListener*/ {
    @BindView(R.id.toolbar_back_iv)
    ImageView toolbar_back_iv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbar_title_tv;
    @BindView(R.id.invite_code_etv)
    EditText invite_code_etv;
    @BindView(R.id.register_account_etv)
    EditText register_account_etv;
    @BindView(R.id.register_password_etv)
    EditText register_password_etv;
    @BindView(R.id.register_sure_password_etv)
    EditText register_sure_password_etv;
    @BindView(R.id.register_next_btn)
    Button register_next_btn;
    @BindView(R.id.account_presence_tip_tv)
    TextView account_presence_tip_tv;
    @BindView(R.id.account_success_tip_iv)
    ImageView account_success_tip_iv;
    @BindView(R.id.invite_code_tip_tv)
    TextView invite_code_tip_tv;
    private String yqm;


    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
        CommonToolbarUtil.initToolbar(this,"注册");
        showView();
        LimitTextChange.StringWatcher(register_password_etv);
        LimitTextChange.StringWatcher(register_sure_password_etv);
        LimitTextChange.StringWatcher(invite_code_etv);
    }

    private void showView() {
        yqm = SharePreferencesUtil.getString(this,"yqm","");
        if(yqm.equals("0")){
            invite_code_etv.setHint("请输入邀请码6-8位数字(必填)");
        }else {
            invite_code_etv.setHint("请输入邀请码6-8位数字(选填)");
        }
    }

    @OnClick({R.id.register_next_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register_next_btn:
                if (checkEtv()) {
                    HashMap<String, Object> data = new HashMap<>();
                    String nickname = register_account_etv.getText().toString();
                    String inviteStr = invite_code_etv.getText().toString();
                    String password = register_password_etv.getText().toString();
                    data.put("password",password);
//                    data.put("invitationCode","18405766");
                    data.put("invitationCode",inviteStr);
                    data.put("sex","0");
                    data.put("username",nickname);
                    HttpApiUtils.request(RegisterActivity.this, null,RequestUtils.REGISTER, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result) {
                            System.out.println(result);
                            showtoast("注册成功");
                            //跳转确定认证页面
                            startActivity(new Intent(RegisterActivity.this,RegisterSussessActivity.class));
                            RegisterSussessActivity.startAty(RegisterActivity.this,nickname,password);
                        }

                        @Override
                        public void onFail(String msg) {
                            System.out.println(msg);
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
    @OnTextChanged(value = R.id.register_account_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChange(Editable editable){
        //限制中文输入
        if (editable.length() > 0) {
            for (int i = 0; i < editable.length(); i++) {
                char c = editable.charAt(i);
                if (c >= 0x4e00 && c <= 0X9fff) {
                    editable.delete(i,i+1);
                }
            }
        }
        if(account_presence_tip_tv.getVisibility()!= View.GONE){
            account_presence_tip_tv.setVisibility(View.GONE);
        }
        if(account_success_tip_iv.getVisibility()!= View.GONE){
            account_success_tip_iv.setVisibility(View.GONE);
        }
    }
    private boolean checkEtv() {
        if(yqm.equals("0")){
            String inviteStr = invite_code_etv.getText().toString();
            if(StringMyUtil.isEmptyString(inviteStr)){
                showtoast("邀请码不能为空");
                return false;
            }
            if(inviteStr.length()<6||inviteStr.length()>8){
                showtoast("邀请码应为6-8位");
                return false;
            }
        }

        String accountStr = register_account_etv.getText().toString();
        int accountLength = accountStr.length();
        if(accountLength<6||accountLength>10||!Utils.checkAccount(accountStr)){
            showtoast("账号应为6-10位字母加数字的组合");
            return false;
        }
        String passwordStr = register_password_etv.getText().toString();
        int passwordLength = passwordStr.length();
        if(passwordLength<8||passwordLength>20||!Utils.checkPsw(passwordStr)){
            showtoast("密码应为8-20位字母和数字的组合");
            return false;
        }
        if(accountStr.equals(passwordStr)){
            showtoast("账号和密码不能一样");
            return  false;
        }
        String surePasswirdStr = register_sure_password_etv.getText().toString();
        if(!passwordStr.equals(surePasswirdStr)){
            showtoast("两次输入的密码不一致");
            return false;
        }

        return true;
    }

/*    private void requestIp(){
        NetworkUtil.GetNetIp("http://ipinfo.io/ip", new NetworkUtil.Listener() {
            @Override
            public void success(String content) {
                ip = content;
                ip = ip.replace("\n","");
                Message msg = Message.obtain();
                msg.what =1;
                msg.obj = ip;
//                    handler.sendMessage(msg);
            }

            @Override
            public void failed(String content) {
                ip = "127.0.0.1";
                Message msg = Message.obtain();
                msg.what =1;
                msg.obj = ip;
//                    handler.sendMessage(msg);
            }
        });





    }*/


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}