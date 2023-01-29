package com.zz.live;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.LiveEntity;

import java.util.ArrayList;

import butterknife.BindView;

public class TestActitivy extends BaseActivity {
    @BindView(R.id.test_recycler)
    RecyclerView recyclerView;
    ArrayList<LiveEntity> liveEntityArrayList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_actitivy;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        TestAdapter testAdapter = new TestAdapter(R.layout.live_list_recycle_item, this, liveEntityArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(testAdapter);
        testAdapter.setAnimationEnable(true);
        testAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        testAdapter.setAnimationFirstOnly(false);
        View headView = LayoutInflater.from(this).inflate(R.layout.beauty_pop_layout,null);
//        testAdapter.addHeaderView(headView);
        View view  = LayoutInflater.from(this).inflate(R.layout.item_beauty,null);
        testAdapter.setEmptyView(view);

    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
