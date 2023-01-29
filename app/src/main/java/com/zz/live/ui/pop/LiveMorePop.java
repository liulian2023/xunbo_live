package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class LiveMorePop extends BasePopupWindow {
    public    ImageView  swich_camare_iv;
    public   ImageView  swich_video_iv;
    public  ImageView  swich_microphone_iv;
    public   ImageView  swich_light_iv;

    public   TextView  swich_camare_tv;
    public  TextView  swich_video_tv;
    public TextView  swich_microphone_tv;
    public TextView  swich_light_tv;
    public LiveMorePop(Context context) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
    }
    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.live_more_pop_layout,null);
        swich_camare_iv=rootView.findViewById(R.id.swich_camare_iv);
        swich_video_iv=rootView.findViewById(R.id.swich_video_iv);
        swich_microphone_iv=rootView.findViewById(R.id.swich_microphone_iv);
        swich_light_iv=rootView.findViewById(R.id.swich_light_iv);
        swich_camare_tv=rootView.findViewById(R.id.swich_camare_tv);
        swich_video_tv=rootView.findViewById(R.id.swich_video_tv);
        swich_microphone_tv=rootView.findViewById(R.id.swich_microphone_tv);
        swich_light_tv=rootView.findViewById(R.id.swich_light_tv);
        swich_camare_iv.setOnClickListener(this);
        swich_video_iv.setOnClickListener(this);
        swich_microphone_iv.setOnClickListener(this);
        swich_light_iv.setOnClickListener(this);
        swich_camare_tv.setOnClickListener(this);
        swich_video_tv.setOnClickListener(this);
        swich_microphone_tv.setOnClickListener(this);
        swich_light_tv.setOnClickListener(this);
    }
}
