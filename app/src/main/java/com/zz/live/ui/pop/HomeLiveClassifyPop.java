package com.zz.live.ui.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.zz.live.R;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.ui.adapter.CommonAdapter;
import com.zz.live.ui.adapter.HomeClassfyAdapter;
import com.zz.live.ui.fragment.home_fragment.HomeLiveFragment;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeLiveClassifyPop extends PopupWindow {
    Fragment fragment;
    View rootView;
    RecyclerView mRecyclerView;
    HomeClassfyAdapter homeClassfyAdapter;
    ArrayList<HomeClassfyEntity.DataBean> homeClassfyEntityArrayList = new ArrayList<>();
    public HomeLiveClassifyPop(Context context, Fragment fragment) {
        super(context);
        this.fragment = fragment;
        initView();
        initPop();
        initRecycler();
    }

    private void initRecycler() {
        homeClassfyAdapter= new HomeClassfyAdapter(homeClassfyEntityArrayList,fragment);
        mRecyclerView.setLayoutManager(new GridLayoutManager(fragment.getContext(),5));
        mRecyclerView.setAdapter(homeClassfyAdapter);
        HomeClassfyEntity.DataBean dataBean = new HomeClassfyEntity.DataBean();
        if(fragment instanceof HomeLiveFragment){
            HomeLiveFragment homeLiveFragment = (HomeLiveFragment) fragment;
            HomeClassfyEntity homeClassfyEntity = JSONObject.parseObject(homeLiveFragment.classfyData, HomeClassfyEntity.class);
            List<HomeClassfyEntity.DataBean> data = homeClassfyEntity.getData();
            homeClassfyEntityArrayList.addAll(data);
            homeClassfyAdapter.notifyDataSetChanged();
        }
        homeClassfyAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    private void initView() {
        rootView = LayoutInflater.from(fragment.getContext()).inflate(R.layout.home_live_classify_pop_layout, null);
        mRecyclerView=rootView.findViewById(R.id.home_live_classify_pop_recyclerView);
    }
    private void initPop() {
        this.setContentView(rootView);
        this.setAnimationStyle(R.style.up_to_down_150);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00FFFFFF);
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                Utils.darkenBackground(fragment.getActivity(),1f);
            }
        });
    }
}
