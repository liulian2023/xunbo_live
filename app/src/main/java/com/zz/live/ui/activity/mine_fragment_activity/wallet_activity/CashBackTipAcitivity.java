package com.zz.live.ui.activity.mine_fragment_activity.wallet_activity;


import android.os.Bundle;

import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.utils.CommonToolbarUtil;

public class CashBackTipAcitivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.cash_back_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onNetChange(boolean netWorkState) {
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"提现");
    }
}
