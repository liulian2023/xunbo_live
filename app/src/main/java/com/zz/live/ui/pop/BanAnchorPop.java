package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class BanAnchorPop extends BasePopupWindow {
    TextView cancel_tv;
    TextView ban_anchor_tv;
    public BanAnchorPop(Context context,String title) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
        ban_anchor_tv.setText(title);
    }

    @Override
    public void initView() {
        super.initView();
        rootView= LayoutInflater.from(mContext).inflate(R.layout.ban_anchor_pop,null);
        cancel_tv = rootView.findViewById(R.id.cancel_tv);
        ban_anchor_tv = rootView.findViewById(R.id.ban_anchor_tv);
        ban_anchor_tv.setOnClickListener(this);
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BanAnchorPop.this.dismiss();
            }
        });
    }
}
