package com.zz.live.ui.activity.main_tab_activity;

import android.content.Context;
import android.content.Intent;

import com.zz.live.base.BaseWebActivity;

public class OnLineCustomerActivity extends BaseWebActivity {

    @Override
    public String getToolBarTitle() {
        return "在线客服";
    }

    public static void startAty(Context context,String url){
        Intent intent = new Intent(context, OnLineCustomerActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

}
