package com.zz.live.ui.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.zz.live.R;
import com.zz.live.utils.Utils;

public class CommonChoosePop extends PopupWindow implements View.OnClickListener {
    private String title;
    private String tip;
    Activity activity;
    View rootView;
    TextView exit_pop_title_tv;
    TextView exit_cancel_tv;
    TextView exit_sure_tv;
    TextView common_tip_tv;
    private static final int FULL_SCREEN_FLAG =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
    public CommonChoosePop(Context context, Activity activity, String title, String tip) {
        super(context);
        this.activity = activity;
        this.title = title;
        this.tip = tip;
        initView();
        initPop();
    }
    public CommonChoosePop(Context context, Activity activity, String title, String tip, String sureStr) {
        super(context);
        this.activity = activity;
        this.title = title;
        this.tip = tip;
        initView();
        initPop();
    }
    private void initPop() {
        this.setContentView(rootView);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.pop_scale_animation);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if(activity!=null){
                    Utils.darkenBackground(activity,1f);
                }
            }
        });
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
//        this.setFocusable(focusable);
        //软键盘不会挡着popupwindow
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        this.update();
        this.getContentView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                CommonChoosePop.this.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
            }
        });
    }

    private void initView() {
        rootView = LayoutInflater.from(activity).inflate(R.layout.exit_pop,null);
        exit_pop_title_tv=rootView.findViewById(R.id.exit_pop_title_tv);
        exit_cancel_tv=rootView.findViewById(R.id.exit_cancel_tv);
        exit_sure_tv=rootView.findViewById(R.id.exit_sure_tv);
        common_tip_tv=rootView.findViewById(R.id.common_tip_tv);
        common_tip_tv.setText(tip);
        exit_pop_title_tv.setText(title);
        exit_sure_tv.setOnClickListener(this);
        exit_cancel_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_cancel_tv:
                CommonChoosePop.this.dismiss();
                break;
            case R.id.exit_sure_tv:
                if(onClickLintener!=null){
                    onClickLintener.onSureClick(exit_cancel_tv);
                }
                break;
                default:
                    break;
        }
    }
    public interface  OnClickLintener{
        void onSureClick(View view);
    }
    OnClickLintener onClickLintener;

    public void setOnClickLintener(OnClickLintener onClickLintener) {
        this.onClickLintener = onClickLintener;
    }
}
