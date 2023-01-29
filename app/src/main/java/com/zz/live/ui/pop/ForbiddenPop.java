package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.LiveMessageModel;

public class ForbiddenPop extends BasePopupWindow {
    public    TextView forbidden_name_tv;
    TextView forbidden_cancel_tv;
    TextView forbidden_sure_tv;
   public LiveMessageModel liveMessageModel;
    public ForbiddenPop(Context context, LiveMessageModel liveMessageModel) {
        super(context);
        this.liveMessageModel = liveMessageModel;
        setAnimationStyle(R.style.down_to_up150);
        setViewData();
    }

    private void setViewData() {
        forbidden_name_tv.setText(liveMessageModel.getUserName());
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.forbidden_pop,null);
        forbidden_name_tv = rootView.findViewById(R.id.forbidden_name_tv);
        forbidden_cancel_tv = rootView.findViewById(R.id.forbidden_cancel_tv);
        forbidden_sure_tv = rootView.findViewById(R.id.forbidden_sure_tv);

        forbidden_sure_tv.setOnClickListener(this);
        forbidden_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForbiddenPop.this.dismiss();
            }
        });
    }
}
