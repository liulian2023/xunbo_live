package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class TakeCameraPop extends BasePopupWindow {
    TextView take_camera_tv;
    TextView choose_photo_tv;
    TextView take_camare_cancel_tv;

    public TakeCameraPop(Context context) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
    }


    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.take_camera_pop_layout, null);
        take_camera_tv=rootView.findViewById(R.id.forbidden_tv);
        choose_photo_tv=rootView.findViewById(R.id.set_manager_tv);
        take_camare_cancel_tv=rootView.findViewById(R.id.forbidden_cancel_tv);
        take_camera_tv.setOnClickListener(this);
        choose_photo_tv.setOnClickListener(this);
        take_camare_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakeCameraPop.this.dismiss();
            }
        });
    }

}
