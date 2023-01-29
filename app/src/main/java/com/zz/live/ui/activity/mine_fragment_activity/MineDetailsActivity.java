package com.zz.live.ui.activity.mine_fragment_activity;

import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.ui.adapter.DetailsTabAdapter;
import com.zz.live.utils.CommonToolbarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class MineDetailsActivity extends BaseActivity {
    @BindView(R.id.details_tabLayout)
    TabLayout details_tabLayout;
    @BindView(R.id.details_viewPager)
    ViewPager details_viewPager;
    ArrayList<String>titleList = new ArrayList<>();
    DetailsTabAdapter detailsTabAdapter;
    //version 1 ,旧版我的明细页面(tab添加两个页面) 2 ,新版直播时长(tab只添加直播时长,并隐藏tab)  3 新版收礼明细(tab只添加收礼明细,并隐藏tab)
   int version;
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_details;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        if(version==1){
            CommonToolbarUtil.initToolbar(this,"我的明细");
        }else if(version==2){
            CommonToolbarUtil.initToolbar(this,"开播历史");
        }else {
            CommonToolbarUtil.initToolbar(this,"收礼明细");
        }
        intiTab();
    }

    private void getIntentData() {
        version = getIntent().getIntExtra("version",1);
    }

    /**
     *
     * @param context
     * @param version version 1 ,旧版我的明细页面(tab添加两个页面) 2 ,新版直播时长(tab只添加直播时长,并隐藏tab)  3 新版收礼明细(tab只添加收礼明细,并隐藏tab)
     */
    public static void startAty (Context context,int version){
        Intent intent = new Intent(context, MineDetailsActivity.class);
        intent.putExtra("version",version);
        context.startActivity(intent);
    }
    private void intiTab() {
        if(version==1){
            titleList.add("收礼明细");
            titleList.add("直播时长明细");
        }else if(version==2) {
            titleList.add("直播时长明细");
            details_tabLayout.setVisibility(View.GONE);
        }else {
            titleList.add("收礼明细");
            details_tabLayout.setVisibility(View.GONE);
        }

        for (int i = 0; i < titleList.size(); i++) {
            details_tabLayout.addTab(details_tabLayout.newTab());
            details_tabLayout.getTabAt(i).setText(titleList.get(i));
        }
        detailsTabAdapter= new DetailsTabAdapter(getSupportFragmentManager(),titleList,version);
        details_viewPager.setAdapter(detailsTabAdapter);
        details_tabLayout.setupWithViewPager(details_viewPager);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
