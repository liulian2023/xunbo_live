package com.zz.live.ui.activity.mine_fragment_activity.wallet_activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.bean.WalletEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.mine_fragment_activity.income_live_activity.IncomeLiveActivity;
import com.zz.live.ui.pop.CommonTipPop;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class MineWalletActivity extends BaseActivity {

    @BindView(R.id.wallet_right_iv)
    ImageView wallet_right_iv;
    @BindView(R.id.rule_tv)
    TextView rule_tv;
    @BindView(R.id.mine_amount_tv)
    TextView mine_amount_tv;
    @BindView(R.id.cash_back_btn)
    Button cash_back_btn;
    @BindView(R.id.month_follow_tv)
    TextView month_follow_tv;
    @BindView(R.id.month_gift_tv)
    TextView month_gift_tv;
    @BindView(R.id.month_cash_back_tv)
    TextView month_cash_back_tv;
    @BindView(R.id.last_month_follow_tv)
    TextView last_month_follow_tv;
    @BindView(R.id.last_month_gift_tv)
    TextView last_month_gift_tv;
    @BindView(R.id.last_month_cash_back_tv)
    TextView last_month_cash_back_tv;
    @BindView(R.id.today_tv)
    RadioButton today_tv;
    @BindView(R.id.yestoday_tv)
    RadioButton yestoday_tv;
    @BindView(R.id.fans_count_tv)
    TextView fans_count_tv;
    @BindView(R.id.follow_count_tv)
    TextView follow_count_tv;
    @BindView(R.id.gift_account_tv)
    TextView gift_account_tv;
    @BindView(R.id.cash_back_account_tv)
    TextView cash_back_account_tv;
    @BindView(R.id.other_account_tv)
    TextView other_account_tv;
    private WalletEntity.DataBean dataBean;
    CommonTipPop commonTipPop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_wallet;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        today_tv.performClick();
        requestWalletData();
    }

    private void requestWalletData() {
        HttpApiUtils.wwwNormalRequest(this, null, RequestUtils.MINE_WALLET, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                WalletEntity walletEntity = JSONObject.parseObject(result, WalletEntity.class);
                dataBean = walletEntity.getData();
                mine_amount_tv.setText(dataBean.getBalance());
                month_follow_tv.setText(dataBean.getTmSubscribe());
                last_month_follow_tv.setText(dataBean.getLmSubscribe());
                month_gift_tv.setText(dataBean.getTmGiftAmount());
                last_month_gift_tv.setText(dataBean.getLmGiftAmount());
                month_cash_back_tv.setText(dataBean.getTmCashBack());
                last_month_cash_back_tv.setText(dataBean.getLmCashBack());
                initTodayYesTodayData();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void initTodayYesTodayData() {
        if(dataBean==null){
            return;
        }
        if(today_tv.isChecked()){
            fans_count_tv.setText(dataBean.getTdFans()+"");
            follow_count_tv.setText(dataBean.getTdSubscribe());
            gift_account_tv.setText(dataBean.getTdGiftAmount());
            cash_back_account_tv.setText(dataBean.getTdCashBack());
            other_account_tv.setText(dataBean.getTdOther());
        }else {
            fans_count_tv.setText(dataBean.getYtdFans()+"");
            follow_count_tv.setText(dataBean.getYtdSubscribe());
            gift_account_tv.setText(dataBean.getYtdGiftAmount());
            cash_back_account_tv.setText(dataBean.getYtdCashBack());
            other_account_tv.setText(dataBean.getYtdOther());
        }
    }

    @OnClick({R.id.wallet_right_iv,R.id.rule_tv,R.id.cash_back_btn,R.id.today_tv,R.id.yestoday_tv})
    public void onClick(View v){
        switch (v.getId()){
            // 规则弹窗
            case R.id.wallet_right_iv:
            case R.id.rule_tv:
                initRulePop();
                break;
                //跳转充值
            case R.id.cash_back_btn:
                UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
                int userType = userInfoBean.getUserType();
                if(userType==3){
                    startActivity(new Intent(MineWalletActivity.this, CashBackTipAcitivity.class));
                }else {
                    startActivity(new Intent(MineWalletActivity.this, IncomeLiveActivity.class));
                }
                break;
                //点击今日昨日切换
            case R.id.today_tv:
            case R.id.yestoday_tv:
                initTodayYesTodayData();
                break;
                default:
                    break;
        }
    }

    private void initRulePop() {
        if(commonTipPop==null){
            String ruleContent="1.订阅收入为收费房间的收入\n2.观看时间不足都会产生订阅收入数据变动\n3.取消注单都会产生返现收入的数据变动";
            commonTipPop= new CommonTipPop(MineWalletActivity.this,"规则说明",ruleContent);
        }
        commonTipPop.showAtLocation(rule_tv, Gravity.CENTER,0,0);
        Utils.darkenBackground(MineWalletActivity.this,0.7f);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.wallet_toolbar_linear))
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
        CommonToolbarUtil.initToolbar(this,"我的钱包");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
