package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class ChooseDatePop extends BasePopupWindow {
    TextView today_tv;
    TextView yesterday_tv;
    TextView week_tv;
    TextView month_tv;
    public ChooseDatePop(Context context) {
        super(context);
        setAnimationStyle(R.style.popAlphaanim0_3);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.choose_time_pop,null);
        today_tv = rootView.findViewById(R.id.today_tv);
        yesterday_tv = rootView.findViewById(R.id.yesterday_tv);
        week_tv = rootView.findViewById(R.id.week_tv);
        month_tv = rootView.findViewById(R.id.month_tv);
        today_tv.setOnClickListener(this);
        yesterday_tv.setOnClickListener(this);
        week_tv.setOnClickListener(this);
        month_tv.setOnClickListener(this);
    }
}
