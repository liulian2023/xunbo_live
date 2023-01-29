package com.zz.live.ui.fragment.main_tab_fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.ui.adapter.HomeTabAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeLiveFragment extends BaseFragment implements XTabLayout.OnTabSelectedListener {
    @BindView(R.id.home_action_linear)
     RelativeLayout actionBarLinear;
    @BindView(R.id.home_search_iv)
     ImageView searchIv;
    @BindView(R.id.home_on_line_kf_iv)
     ImageView onlineKfIv;
    @BindView(R.id.home_tab)
    public XTabLayout mTab;
    @BindView(R.id.home_fragment_viewpager)
    public ViewPager mViewPager;
    @BindView(R.id.starusbar_view)
    View starusbar_view;
    public ArrayList<String> titleList  = new ArrayList<>();

    public HomeTabAdapter homeTabAdapter;
    public int hotTabCount=1;

    public XTabLayout getmTab() {
        return mTab;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ImmersionBar.with(this)
                .statusBarView(starusbar_view)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
        initTab();
    }
    public static HomeLiveFragment newInstance() {
        Bundle args = new Bundle();
        HomeLiveFragment fragment = new HomeLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @OnClick({R.id.home_search_iv,R.id.home_on_line_kf_iv})
    public void OnClick(View v){

    }
    public void initTab() {
        titleList.add("直播");
//        titleList.add("关注");
        homeTabAdapter=new HomeTabAdapter(getChildFragmentManager(),titleList,getContext());
        mViewPager.setAdapter(homeTabAdapter);
        mTab.setupWithViewPager(mViewPager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = mTab.getTabAt(i);
            tabAt.setCustomView(homeTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
//                textView.setTextColor(Color.parseColor("#FF2F81"));
                textView.setTextColor(Color.parseColor("#333333"));
                textView.setTextSize(20);
            }
        }
        mTab.addOnTabSelectedListener(this);
        mViewPager.setOffscreenPageLimit(2);
    }
    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(16);
        textView.setTextColor(Color.parseColor("#FF2F81"));
        boolean isHotTab = tab.getPosition() == 0;
        if(isHotTab){
            hotTabCount++;
        }

    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        TextView textView=  tab.getCustomView().findViewById(R.id.home_tab_title_tv);
        textView.setTextColor(Color.parseColor("#767676"));
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }
}
