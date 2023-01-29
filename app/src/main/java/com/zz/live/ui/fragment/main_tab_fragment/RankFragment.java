package com.zz.live.ui.fragment.main_tab_fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.ui.adapter.RankTabAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class RankFragment extends BaseFragment implements XTabLayout.OnTabSelectedListener {
    @BindView(R.id.statusbar_view)
    ImageView starusbar_view;
    @BindView(R.id.rank_viewpager)
    ViewPager rank_viewpager;
    @BindView(R.id.rank_tab)
    XTabLayout rank_tab;
    private ArrayList<String> titleList=new ArrayList<>();
    RankTabAdapter rankTabAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {
        ImmersionBar.with(this)
                .navigationBarColor(R.color.transparent)
                .init();
        initTab();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }
    public void initTab() {
        titleList.add("收益榜");
//        titleList.add("贡献榜");
        rankTabAdapter=new RankTabAdapter(getChildFragmentManager(),titleList,getContext());
        rank_viewpager.setAdapter(rankTabAdapter);
        rank_tab.setupWithViewPager(rank_viewpager);
        for (int i = 0; i < titleList.size(); i++) {
            XTabLayout.Tab tabAt = rank_tab.getTabAt(i);
            tabAt.setCustomView(rankTabAdapter.getTabView(i));
            if(i==0){
                TextView textView= tabAt.getCustomView().findViewById(R.id.home_tab_title_tv);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
                textView.setTextSize(20);
            }
        }
        rank_tab.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        if(tab==null){
            return;
        }
        View customView = tab.getCustomView();
        if(customView==null){
            return;
        }
        TextView textView=  customView.findViewById(R.id.home_tab_title_tv);
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {
        if(tab==null){
            return;
        }
        View customView = tab.getCustomView();
        if(customView==null){
            return;
        }
        TextView textView=  customView.findViewById(R.id.home_tab_title_tv);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setTextSize(16);
    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }
}
