package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class ModifySexPop extends BasePopupWindow {
    TextView man_tv;
    TextView woman_tv;
    TextView modify_sex_cancel_tv;

    public ModifySexPop(Context context) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.modify_sex_pop_layout, null);
        man_tv=rootView.findViewById(R.id.man_tv);
        woman_tv =rootView.findViewById(R.id.woman_tv);
        modify_sex_cancel_tv =rootView.findViewById(R.id.modify_sex_cancel_tv);
        man_tv.setOnClickListener(this);
        woman_tv.setOnClickListener(this);
        modify_sex_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifySexPop.this.dismiss();
            }
        });
    }

}
