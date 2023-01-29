package com.zz.live.ui.fragment.home_fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.BannerData;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.bean.LiveEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.play_live_activity.PlayLiveActivity;
import com.zz.live.ui.adapter.BannerViewHolder;
import com.zz.live.ui.adapter.CommonAdapter;
import com.zz.live.ui.adapter.HomeClassfyAdapter;
import com.zz.live.ui.adapter.LiveListAdapter;
import com.zz.live.ui.pop.HomeLiveClassifyPop;
import com.zz.live.utils.BannerLoadViewUtil;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.ScreenUtils;
import com.zhpan.bannerview.BannerViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class HomeLiveFragment extends BaseFragment {

    @BindView(R.id.home_live_refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.home_live_recycle)
    RecyclerView mRecycler;
    /*
    直播列表recyclerView
     */
    LiveListAdapter liveListAdapter;
    ArrayList<LiveEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    /*
    直播分类recyclerView
     */
    RecyclerView classifyRecycle;
    HomeClassfyAdapter homeClassfyAdapter;
    ArrayList<HomeClassfyEntity.DataBean> classfyEntityArrayList = new ArrayList<>();

    /*
    banner轮播
     */
    LinearLayout home_live_banner_linear;
    BannerViewPager<BannerData.DataBean.RecordsBean,BannerViewHolder>mBannerViewPager;
    ArrayList<BannerData.DataBean.RecordsBean>bannerDataArrayList  = new ArrayList<>();
    //点击全部分类弹出的分类pop
    HomeLiveClassifyPop homeLiveClassifyPop;
    int current=1;
    public String classfyData;
    public static HomeLiveFragment newInstance (int positon){
        HomeLiveFragment homeLiveFragment = new HomeLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,positon);
        homeLiveFragment.setArguments(bundle);
        return homeLiveFragment;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
        initClassifyRecycler();
        initBanner();
        requestLiveListData(false,false);
        requestClassfyData();
        requestBannerData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_live;
    }
    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefresh(getContext(), refreshLayout, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestLiveListData(false,true);
            }
        });
    }

    private void initRecycler() {
        liveListAdapter = new LiveListAdapter(R.layout.live_list_recycle_item,recordsBeanArrayList,this);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.home_live_head_layout,null);
        liveListAdapter.addHeaderView(headView);
        bindHeadView(headView);
        mRecycler.setAdapter(liveListAdapter);
        View nodataView = LayoutInflater.from(getContext()).inflate(R.layout.nodata_layout,null);
        nodataView.setVisibility(View.VISIBLE);
        liveListAdapter.setEmptyView(nodataView);
        liveListAdapter.setUseEmpty(true);
        liveListAdapter.setHeaderWithEmptyEnable(true);
        liveListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                PlayLiveActivity.startAty(getContext(),recordsBeanArrayList.get(position));
            }
        });
    }

    private void requestLiveListData(boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        data.put("categoryId","");
        HttpApiUtils.wwwNormalRequest(getActivity(),this, RequestUtils.LIVE_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                if(isRefresh){
                    recordsBeanArrayList.clear();
                    refreshLayout.finishRefresh();
                }
                LiveEntity liveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.DataBean.RecordsBean> records = liveEntity.getData().getRecords();
                recordsBeanArrayList.addAll(records);
                liveListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
            }
        });
    }

    private void initBanner() {
        //banner轮播  宽高比:100:375
        int mScreenWidth = ScreenUtils.getWight(_mActivity);
        home_live_banner_linear.getLayoutParams().height = (mScreenWidth - ScreenUtils.dip2px(_mActivity, 0)) * 150/ 375;

    }

    private void requestBannerData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",20);
        HttpApiUtils.wwwNormalRequest(getActivity(), this,RequestUtils.BANNER, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                BannerData bannerData = JSONObject.parseObject(result, BannerData.class);
                List<BannerData.DataBean.RecordsBean> records = bannerData.getData().getRecords();
                bannerDataArrayList.addAll(records);
                BannerLoadViewUtil.createBannerView(getContext(), mBannerViewPager, bannerDataArrayList, true, 2000, true, true, new BannerLoadViewUtil.OnPageClickListener() {
                    @Override
                    public void onPageClickListener(int position) {
                        showtoast("点击:"+position);
                    }
                });
            }

            @Override
            public void onFail(String msg) {
            }
        });

    }

    private void initClassifyRecycler() {
        homeClassfyAdapter=new HomeClassfyAdapter(classfyEntityArrayList,this);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 6);
        classifyRecycle.setLayoutManager(layout);
        classifyRecycle.setAdapter(homeClassfyAdapter);
        homeClassfyAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HomeClassfyEntity.DataBean dataBean = classfyEntityArrayList.get(position);
                if(dataBean.getName().equals("全部")){
                    if(null==homeLiveClassifyPop){
                        homeLiveClassifyPop = new HomeLiveClassifyPop(getContext(), HomeLiveFragment.this);
                    }
                    Fragment parentFragment = getParentFragment();
                    if(parentFragment instanceof com.zz.live.ui.fragment.main_tab_fragment.HomeLiveFragment){
                        com.zz.live.ui.fragment.main_tab_fragment.HomeLiveFragment homeLiveFragment = (com.zz.live.ui.fragment.main_tab_fragment.HomeLiveFragment) parentFragment;
                        homeLiveClassifyPop.showAsDropDown(homeLiveFragment.getmTab(),0,0, Gravity.BOTTOM);
                    }
                }
            }
        });
    }

    private void requestClassfyData() {
        HttpApiUtils.wwwNormalRequest(getActivity(),this, RequestUtils.LIVE_CLASSFY, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                classfyData= result;
                HomeClassfyEntity homeClassfyEntity = JSONObject.parseObject(result, HomeClassfyEntity.class);
                List<HomeClassfyEntity.DataBean> data = homeClassfyEntity.getData();
                int size = data.size();
                if(size<6){
                    classfyEntityArrayList.addAll(data);
                }else {
                    for (int i = 0; i < 5; i++) {
                        classfyEntityArrayList.add(data.get(i));
                    }

                }
                HomeClassfyEntity.DataBean dataBean = new HomeClassfyEntity.DataBean();
                dataBean.setName("全部");
                classfyEntityArrayList.add(dataBean);
                homeClassfyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void bindHeadView(View headView) {
        classifyRecycle=headView.findViewById(R.id.home_live_classfy_recycle);
        home_live_banner_linear=headView.findViewById(R.id.home_live_banner_linear);
        mBannerViewPager=headView.findViewById(R.id.home_live_banner);
    }
}
