package com.zz.live.ui.fragment.rank_fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.RankEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.RankAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
public class InComeRankFragment extends BaseFragment {
    @BindView(R.id.rank_refresh)
    SmartRefreshLayout rank_refresh;
    @BindView(R.id.rank_recycler)
    RecyclerView rank_recycler;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.day_rank_rbt)
    RadioButton day_rank_rbt;
    @BindView(R.id.week_rank_rbt)
    RadioButton week_rank_rbt;
    @BindView(R.id.month_rank_rbt)
    RadioButton month_rank_rbt;
    @BindView(R.id.all_rank_rbt)
    RadioButton all_rank_rbt;
    private ConstraintLayout loadingLinear;
    private LinearLayout nodataLinear;
    private LinearLayout errorLinear;
    private TextView reloadTv;

    ImageView one_title_iv;
    TextView one_name_tv;
    ImageView one_sex_iv;
    ImageView one_level_iv;
    TextView one_follow_tv;
    TextView one_gift_amount_tv;

    ImageView two_title_iv;
    TextView two_name_tv;
    ImageView two_sex_iv;
    ImageView two_level_iv;
    TextView two_follow_tv;
    TextView two_gift_amount_tv;

    ImageView three_title_iv;
    TextView three_name_tv;
    ImageView three_sex_iv;
    ImageView three_level_iv;
    TextView three_follow_tv;
    TextView three_gift_amount_tv;
    int position;
    RankAdapter rankAdapter ;
    ArrayList<RankEntity.DataBean.RecordsBean> rankEntityArrayList = new ArrayList<>();
    int current=1;
    int type=1;
    int dayRbtClickCount=0;
    private List<RankEntity.DataBean.RecordsBean> recordsBeans;


    @Override
    protected void init(Bundle savedInstanceState) {
        position = getArguments().getInt(CommonStr.POSITION);

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_in_come_rank;
    }

    public static InComeRankFragment newInstance(int positon){
        InComeRankFragment inComeRankFragment = new InComeRankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,positon);
        inComeRankFragment.setArguments(bundle);
        return inComeRankFragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        initRecycler();
    }
    private void initRecycler() {
        rankAdapter = new RankAdapter(R.layout.rank_recycler_item,rankEntityArrayList,this);
        rank_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        rank_recycler.setAdapter(rankAdapter);
/*        rankAdapter.setAnimationEnable(true);
        rankAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        rankAdapter.setAnimationFirstOnly(false);*/
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.rank_recycler_head,null);
        bindHeadView(headView);
        rankAdapter.addHeaderView(headView);
        day_rank_rbt.performClick();
        RequestRankList(true,false);
        rank_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //头部视图消失时,改变顶部radioGroup的背景色(头部的position为0)
                RecyclerView.LayoutManager layoutManager = rank_recycler.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if(firstVisibleItemPosition!=0){
                        radioGroup.setBackgroundColor(Color.parseColor("#FD5F82"));
                    }else {
                        radioGroup.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
    }

    private void bindHeadView(View headView) {

        one_title_iv = headView.findViewById(R.id.one_title_iv);
        one_name_tv = headView.findViewById(R.id.one_name_tv);
        one_sex_iv = headView.findViewById(R.id.one_sex_iv);
        one_level_iv = headView.findViewById(R.id.one_level_iv);
        one_follow_tv = headView.findViewById(R.id.one_follow_tv);
        one_gift_amount_tv = headView.findViewById(R.id.one_gift_amount_tv);

        two_title_iv = headView.findViewById(R.id.two_title_iv);
        two_name_tv = headView.findViewById(R.id.two_name_tv);
        two_sex_iv = headView.findViewById(R.id.two_sex_iv);
        two_level_iv = headView.findViewById(R.id.two_level_iv);
        two_follow_tv = headView.findViewById(R.id.two_follow_tv);
        two_gift_amount_tv = headView.findViewById(R.id.two_gift_amount_tv);

        three_title_iv = headView.findViewById(R.id.three_title_iv);
        three_name_tv = headView.findViewById(R.id.three_name_tv);
        three_sex_iv = headView.findViewById(R.id.three_sex_iv);
        three_level_iv = headView.findViewById(R.id.three_level_iv);
        three_follow_tv = headView.findViewById(R.id.three_follow_tv);
        three_gift_amount_tv = headView.findViewById(R.id.three_gift_amount_tv);
    }

    private void bindFootView(View footView) {
        loadingLinear=footView.findViewById(R.id.loading_linear);
        errorLinear=footView.findViewById(R.id.error_linear);
        nodataLinear=footView.findViewById(R.id.nodata_linear);
        reloadTv=footView.findViewById(R.id.reload_tv);
        //layoutParam的作用是子控件告诉父控件自己要如何布局
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,150,0,0);
        //nodataLinear的父控件是footView的根布局LinearLayout,将自定义的LinearLayout.layoutParams设置存入,表示noDataLinear 希望按照layoutParam中的设置布局
        nodataLinear.setLayoutParams(layoutParams);
        errorLinear.setLayoutParams(layoutParams);
    }

    @OnClick({R.id.day_rank_rbt,R.id.week_rank_rbt,R.id.month_rank_rbt,R.id.all_rank_rbt})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.day_rank_rbt:
                dayRbtClickCount++;
                if(dayRbtClickCount!=1){
                    initType(1);
                }
                break;
            case R.id.week_rank_rbt:
                initType(2);
                break;
            case R.id.month_rank_rbt:
                initType(3);
                break;
            case R.id.all_rank_rbt:
                initType(0);
                break;
            default:
                break;
        }
    }

    private void initType(int type) {
        this.type = type;
        current=1;
        RequestRankList(true,false);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        RequestRankList(false,false);
    }

    private void RequestRankList(boolean isRefresh, boolean isLoadMore) {
        if(!rankAdapter.hasFooterLayout()){
            View footView = LayoutInflater.from(getContext()).inflate(R.layout.status_layout,null);
            bindFootView(footView);
            rankAdapter.addFooterView(footView);
        }
        if(!isLoadMore){
            RankEntity.DataBean.RecordsBean recordsBean = new RankEntity.DataBean.RecordsBean();
            recordsBean.setImage("");
            recordsBean.setUsername("- - - ");
            recordsBean.setSex(1);
            recordsBean.setTotalAmount("- - - ");
            initRankOne(recordsBean);
            initRankTwo(recordsBean);
            initRankThree(recordsBean);
        }
        HashMap<String, Object> data= new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        data.put("type",type);
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.RANK_LIST, data, loadingLinear, errorLinear, reloadTv, rank_refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                RankEntity rankEntity = JSONObject.parseObject(result, RankEntity.class);
                recordsBeans = rankEntity.getData().getRecords();
                int size = recordsBeans.size();
                RefreshUtils.succse(current,rank_refresh,loadingLinear,nodataLinear, size,isLoadMore,isRefresh,rankEntityArrayList);
                if(!isLoadMore){
                    if(size<=3){
                        //数据在3个以内时.初始化head的数据
                        initHeadData();

                    }else {
                        //大于3,同时设置头部和列表的数据
                        initAllData(recordsBeans, size, isLoadMore);
                    }
                }else {
                    for (int i = 0; i < recordsBeans.size(); i++) {
                        rankEntityArrayList.add(recordsBeans.get(i));
                    }
                }

                if(rankEntityArrayList.size()!=0){
                    rankAdapter.removeAllFooterView();
                }
                rankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,rank_refresh);
            }
        });
    }

    private void initAllData(List<RankEntity.DataBean.RecordsBean> recordsBeans, int size, boolean isLoadMore) {
        if(!isLoadMore){
            RankEntity.DataBean.RecordsBean oneBeen = recordsBeans.get(0);
            RankEntity.DataBean.RecordsBean twobean = recordsBeans.get(1);
            RankEntity.DataBean.RecordsBean threeBeen = recordsBeans.get(2);
            initRankOne(oneBeen);
            initRankTwo(twobean);
            initRankThree(threeBeen);

        }
        for (int i = 3; i < size; i++) {
            rankEntityArrayList.add(recordsBeans.get(i));
        }

    }
    private void initRankThree(RankEntity.DataBean.RecordsBean twobean) {
        //头像
        GlideLoadViewUtil.fLoadTitleView(InComeRankFragment.this, twobean.getImage(), three_title_iv);
        //用户名
        three_name_tv.setText(twobean.getNickName());
        //性别
        if (twobean.getSex() == 0) {
            three_sex_iv.setImageResource(R.drawable.xingbie_nan);
        } else {
            three_sex_iv.setImageResource(R.drawable.xingbie_nv);
        }
        three_gift_amount_tv.setText(twobean.getTotalAmount());
    }
    private void initRankTwo(RankEntity.DataBean.RecordsBean twobean) {
        //头像
        GlideLoadViewUtil.fLoadTitleView(InComeRankFragment.this, twobean.getImage(), two_title_iv);
        //用户名
        two_name_tv.setText(twobean.getNickName());
        //性别
        if (twobean.getSex() == 0) {
            two_sex_iv.setImageResource(R.drawable.xingbie_nan);
        } else {
            two_sex_iv.setImageResource(R.drawable.xingbie_nv);
        }
        two_gift_amount_tv.setText(twobean.getTotalAmount() );
    }

    private void initRankOne(RankEntity.DataBean.RecordsBean oneBeen) {
        //头像
        GlideLoadViewUtil.fLoadTitleView(InComeRankFragment.this, oneBeen.getImage(), one_title_iv);
        //用户名
        one_name_tv.setText(oneBeen.getNickName());
        int sex = oneBeen.getSex();
        //性别
        if (sex == 0) {
            one_sex_iv.setImageResource(R.drawable.xingbie_nan);
        } else {
            one_sex_iv.setImageResource(R.drawable.xingbie_nv);
        }
        one_gift_amount_tv.setText(oneBeen.getTotalAmount());
    }

    private void initHeadData( ) {
        if(recordsBeans.size()==1){
            initRankOne(recordsBeans.get(0));
        }else if(recordsBeans.size()==2){
            initRankOne(recordsBeans.get(0));
            initRankTwo(recordsBeans.get(1));
        }else if (recordsBeans.size()==3){
            initRankOne(recordsBeans.get(0));
            initRankTwo(recordsBeans.get(1));
            initRankThree(recordsBeans.get(2));
        }
    }

    @Override
    protected void initrefresh() {
        super.initrefresh();
        rank_refresh.setEnableLoadMore(false);
        rank_refresh.setPrimaryColors(Color.WHITE,Color.BLACK);
        rank_refresh.setEnableHeaderTranslationContent(false);//内容不偏移
        rank_refresh.setRefreshHeader(new WaveSwipeHeader(getContext()));
        rank_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                current=1;
                RequestRankList(true,false);
            }
        });
    }

}
