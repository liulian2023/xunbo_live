package com.zz.live.ui.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zz.live.R;
import com.zz.live.ui.beauty.BeautyPanel;
import com.zz.live.ui.beauty.PusherBeautyKit;

public class BeautyPop extends PopupWindow {
    Activity activity;
    View rootView;
    BeautyPanel beautyPanel;
    PusherBeautyKit pusherBeautyKit;
    LinearLayout beauty_pop_bg_linear;
    private static final int FULL_SCREEN_FLAG =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;

    public BeautyPop(Context context, Activity activity, PusherBeautyKit pusherBeautyKit) {
        super(context);
        this.activity = activity;
        this.pusherBeautyKit = pusherBeautyKit;

        initView();
        initPop();
    }

    private void initPop() {
        this.setContentView(rootView);
        this.setAnimationStyle(R.style.down_to_up150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(false);
        this.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
        this.setFocusable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
    }

    private void initView() {
        rootView = LayoutInflater.from(activity).inflate(R.layout.beauty_pop_layout, null);
        beautyPanel = rootView.findViewById(R.id.pop_beauty_panel);
        beauty_pop_bg_linear = rootView.findViewById(R.id.beauty_pop_bg_linear);
        //关联美颜管理
        beautyPanel.setProxy(pusherBeautyKit);
        beauty_pop_bg_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyPop.this.dismiss();
            }
        });
    }
}
