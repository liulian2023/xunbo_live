package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class SingleLoginPop extends BasePopupWindow {
    TextView bottom_text_tv;
    TextView top_text_tv;
    TextView single_login_pop_sure_tv;

    public SingleLoginPop(Context context) {
        super(context);
        setAnimationStyle(R.style.popAlphaanim0_3);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.single_login_pop,null);
        bottom_text_tv= rootView.findViewById(R.id.bottom_text_tv);
        top_text_tv= rootView.findViewById(R.id.top_text_tv);
        single_login_pop_sure_tv= rootView.findViewById(R.id.single_login_pop_sure_tv);
        single_login_pop_sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleLoginPop.this.dismiss();
            }
        });
    }
}
