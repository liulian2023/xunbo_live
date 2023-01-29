package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class ChooseTimePop extends BasePopupWindow {
    TextView today_tv;
    TextView yestoday_tv;
    TextView week_tv;
    TextView month_tv;
    TextView last_month_tv;
    public ChooseTimePop(Context context) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.choose_date_pop,null);
        today_tv=rootView.findViewById(R.id.today_tv);
        yestoday_tv=rootView.findViewById(R.id.yestoday_tv);
        week_tv=rootView.findViewById(R.id.week_tv);
        month_tv=rootView.findViewById(R.id.month_tv);
        last_month_tv=rootView.findViewById(R.id.last_month_tv);
        today_tv.setOnClickListener(this);
        yestoday_tv.setOnClickListener(this);
        week_tv.setOnClickListener(this);
        month_tv.setOnClickListener(this);
        last_month_tv.setOnClickListener(this);
        today_tv.performClick();
    }
}
