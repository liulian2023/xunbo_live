package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.WithdrawNoteEntity;
import com.zz.live.utils.CommonToolbarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawDetailsActivity extends BaseActivity {
    @BindView(R.id.withdraw_status_iv)
    ImageView withdraw_status_iv;
    @BindView(R.id.withdraw_status_tv)
    TextView withdraw_status_tv;
    @BindView(R.id.withdraw_tip_tv)
    TextView withdraw_tip_tv;
    @BindView(R.id.withdraw_bankName_tv)
    TextView withdraw_bankName_tv;
    @BindView(R.id.withdraw_amount)
    TextView withdraw_amount;
    @BindView(R.id.withdraw_return_btn)
    Button withdraw_return_btn;
    WithdrawNoteEntity.DataBean.RecordsBean recordsBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_details_acvitity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        initView();
    }

    private void initView() {
        //status 提现状态 0不通过1通过 2审核中
        int status = recordsBean.getStatus();
        if(status==0){
            withdraw_status_iv.setImageResource(R.drawable.shibai);
            withdraw_status_tv.setText("提现申请被拒绝");
            withdraw_tip_tv.setText("很抱歉，你提交的提现申请未通过。请重新提交，\n感谢您的支持");
            String cardNum = recordsBean.getCardNumber();
            withdraw_bankName_tv.setText(recordsBean.getBankName()+"("+cardNum.substring(cardNum.length()-4)+")");
            withdraw_amount.setText(recordsBean.getAmount()+"元");

        }else if(status==1){
            withdraw_status_iv.setImageResource(R.drawable.txxq_dzcg);
            withdraw_status_tv.setText("恭喜到账成功");
            withdraw_tip_tv.setText("提现已经到账快去账户看看吧~");
            String cardNum = recordsBean.getCardNumber();
            withdraw_bankName_tv.setText(recordsBean.getBankName()+"("+cardNum.substring(cardNum.length()-4)+")");//银行名+尾号
            withdraw_amount.setText(recordsBean.getAmount()+"元");
        }else {
            withdraw_status_iv.setImageResource(R.drawable.chengg);
            withdraw_status_tv.setText("提现申请正在审核中");
            withdraw_tip_tv.setText("提现后24小时内到账，若未及时到账\n请联系客服人员");
            String cardNum = recordsBean.getCardNumber();
            withdraw_bankName_tv.setText(recordsBean.getBankName()+"("+cardNum.substring(cardNum.length()-4)+")");
            withdraw_amount.setText(recordsBean.getAmount()+"元");
        }
    }

    private void getIntentData() {
        recordsBean = (WithdrawNoteEntity.DataBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"提现详情");
    }
    @OnClick({R.id.withdraw_return_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.withdraw_return_btn:
                finish();
                break;
                default:
                    break;
        }
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
