package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AddBankcardActivity extends BaseActivity {
    @BindView(R.id.choose_bank_relativeLayout)
    RelativeLayout choose_bank_relativeLayout;
    @BindView(R.id.bank_second_name_etv)
    EditText bank_second_name_etv;
    @BindView(R.id.card_num_etv)
    EditText card_num_etv;
    @BindView(R.id.card_username_etv)
    EditText card_username_etv;
    @BindView(R.id.add_card_sure_btn)
    Button bind_card_sure_btn;
    @BindView(R.id.bank_name_tv)
    TextView bank_name_tv;
    public int SKIP_CHPOOSE_CARD=123;
    @Override
    public int getLayoutId() {
        return  R.layout.activity_add_bankcard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"添加银行卡");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @OnClick({R.id.add_card_sure_btn,R.id.choose_bank_relativeLayout})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_card_sure_btn:
                if( checkEtv()){
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("bankName",bank_name_tv.getText().toString());
                    data.put("branchName",bank_second_name_etv.getText().toString());
                    data.put("cardNumber",card_num_etv.getText().toString());
                    data.put("realName",card_username_etv.getText().toString());
                    HttpApiUtils.request(AddBankcardActivity.this,null, RequestUtils.ADD_BANKCORD, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result) {
                            showtoast(JSONObject.parseObject(result).getString("msg"));
                            /*
                            回传消息 (返回IncomeActivity 和 MineBankcardActivity时重新请求银行卡列表)
                             */
                            setResult(RESULT_OK);
                            finish();
                        }

                        @Override
                        public void onFail(String msg) {
                        }
                    });

                }
                break;
            case R.id.choose_bank_relativeLayout:
                startActivityForResult(new Intent(AddBankcardActivity.this,ChooseBankcardActivity.class),SKIP_CHPOOSE_CARD);
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SKIP_CHPOOSE_CARD&&resultCode==RESULT_OK){
            //选择银行卡返回
            String name = data.getStringExtra("name");
            bank_name_tv.setText(name);
        }
    }



    private boolean checkEtv() {
        String bankName = bank_name_tv.getText().toString();
        String bankSecondName = bank_second_name_etv.getText().toString();
        String cardNum = card_num_etv.getText().toString();
        String userName = card_username_etv.getText().toString();

        if(bankName.equals("请选择银行")){
            showtoast("请选择银行");
            return false;
        }
        if(StringMyUtil.isEmptyString(bankSecondName)){
            showtoast("请输入支行名称");
            return false;
        }
        if(StringMyUtil.isEmptyString(cardNum)){
            showtoast("请输入银行卡号");
            return false;
        }
        if(cardNum.length()>19){
            showtoast("银行卡长度不正确");
            return  false;
        }
        if(StringMyUtil.isEmptyString(userName)){
            showtoast("请输入持卡人姓名");
            return false;
        }
        return true;
    }
}
