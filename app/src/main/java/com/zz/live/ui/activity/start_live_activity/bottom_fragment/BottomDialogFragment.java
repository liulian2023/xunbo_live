package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import com.zz.live.base.BaseDialogFragment;
import com.zz.live.bean.LiveMessageModel;

import java.util.ArrayList;


public class BottomDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private View view;
    TabLayout bottom_tab;
    ViewPager bottom_viewpager;
    TextView transition_bg_tv;
    ArrayList<String> titleList = new ArrayList<>();
    BottomTabAdapter bottomTabAdapter;
    ArrayList<LiveMessageModel>liveMessageModelArrayList;

    public BottomDialogFragment(ArrayList<LiveMessageModel> liveMessageModelArrayList) {
        this.liveMessageModelArrayList = liveMessageModelArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                getDialog(). getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                fullScreen( getDialog().getWindow());
            }
        });
        view = inflater.inflate(R.layout.fragment_bottom_dialog, container, false);
        initView();
        initTab();
        return view;
    }
    public  void fullScreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }

    public void initView() {
        bottom_tab= view.findViewById(R.id.bottom_tab);
        bottom_viewpager= view.findViewById(R.id.bottom_viewpager);
        transition_bg_tv= view.findViewById(R.id.transition_bg_tv);
        transition_bg_tv.setOnClickListener(this);
    }

    private void initTab() {
        titleList.add("游戏");
        titleList.add("弹幕");
        titleList.add("礼物");
        titleList.add("禁言");
        titleList.add("房管");
        bottomTabAdapter = new BottomTabAdapter(getChildFragmentManager());
        bottom_viewpager.setAdapter(bottomTabAdapter);
        bottom_tab.setupWithViewPager(bottom_viewpager);
    }
    @Override
    public void onStart() {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
            dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            lp.windowAnimations = android.R.style.Animation_InputMethod;
            dialogWindow.setAttributes(lp);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.transition_bg_tv:
                dismiss();
                break;
                default:
                    break;
        }
    }

    class BottomTabAdapter extends FragmentStatePagerAdapter {
        public BottomTabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return GameFragment.newInstance(position);
            }else if(position==3){
                return ForbiddenFragment.newInstance(position);
            }else if(position == 4){
                return RoomManageFragment.newInstance(position);
            }else {

                return DanMuFragment.newInstance(position,liveMessageModelArrayList);
            }
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);

        }
    }
}
