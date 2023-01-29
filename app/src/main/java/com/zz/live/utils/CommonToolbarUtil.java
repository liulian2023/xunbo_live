package com.zz.live.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;

public class CommonToolbarUtil {
    public static void initToolbar(Activity activity, String title){
        TextView titleTv = Utils.getContentView(activity).findViewById(R.id.toolbar_title_tv);
        ImageView backIv = Utils.getContentView(activity).findViewById(R.id.toolbar_back_iv);
        if(titleTv!=null){
            titleTv.setText(title);
        }

        if(backIv!=null){
            backIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activity!=null){
                        activity.finish();
                    }
                }
            });
        }
    }

    public static void initStatusBarColor(Activity activity){
        ImmersionBar.with(activity)
                .titleBar(activity.findViewById(R.id.include))
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
    }
}
