package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class CommonTipPop  extends BasePopupWindow {
    TextView comm_tip_title_tv;
    TextView common_tip_content_tv;
    TextView common_tip_sure_tv;
    String title;
    String tipContent;
    public CommonTipPop(Context context,String title,String tipContent) {
        super(context);
        this.title=title;
        this.tipContent=tipContent;
        setAnimationStyle(R.style.popAlphaanim0_3);
        setViewContent();
    }

    private void setViewContent() {
        comm_tip_title_tv.setText(title);
        common_tip_content_tv.setText(tipContent);
    }

    @Override
    public void initView() {
        super.initView();
        rootView= LayoutInflater.from(mContext).inflate(R.layout.common_tip_pop,null);
        comm_tip_title_tv=rootView.findViewById(R.id.comm_tip_title_tv);
        common_tip_content_tv=rootView.findViewById(R.id.common_tip_content_tv);
        common_tip_sure_tv=rootView.findViewById(R.id.common_tip_sure_tv);

        common_tip_sure_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonTipPop.this.dismiss();
            }
        });
    }

}
