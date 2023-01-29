package com.zz.live.ui.activity.mine_fragment_activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.ui.adapter.FragmentAdapter;
import com.zz.live.ui.fragment.feedback_fragment.MineFeedbackFragment;
import com.zz.live.ui.fragment.feedback_fragment.WantToFeedbackFragment;
import com.zz.live.utils.CommonToolbarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.feedback_fragment_content)
    ViewPager mViewPager;
    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> titles=new ArrayList<>();
    public int movieId;
    @BindView(R.id.feedback_tablayout)
    TabLayout tabLayout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_face_back;

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        CommonToolbarUtil.initToolbar(this,"意见反馈");
        getIntentData();
        initTabLayout();
    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
    private void getIntentData() {
        movieId=getIntent().getIntExtra("movieId",0);
    }

    private void initTabLayout() {
        titles.add("我要反馈");
        titles.add("我的反馈");
        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(titles.get(i));
        }
        fragmentArrayList=new ArrayList<>();
        fragmentArrayList.add(new WantToFeedbackFragment());
        fragmentArrayList.add(new MineFeedbackFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragmentArrayList);
        mViewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        if(movieId!=0){
            mViewPager.setCurrentItem(1);
        }else {
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fragmentArrayList.clear();
        //华为inputMethodManager内存泄漏
    }
}
