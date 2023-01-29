package com.zz.live.ui.activity.start_live_activity.rank_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.LiveRankEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.LiveRankRecyclerAdapter;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Headers;


public class LiveRankFragment extends BaseFragment {
    @BindView(R.id.live_rank_recycler)
    RecyclerView live_rank_recycler;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    private Unbinder mUnbinder;
    LiveRankRecyclerAdapter liveRankRecyclerAdapter ;
    ArrayList<LiveRankEntity.DataBean.RecordsBean> liveRankEntityArrayList = new ArrayList<>();
    LiveRankDialogFragment liveRankDialogFragment;
    int type=0;
    private int position;
    String anchorAccount;


    @Override
    protected void init(Bundle savedInstanceState) {
        getArgumentsData();
        initRefresh();
        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_rank;
    }

/*    public LiveRankFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }*/

    public LiveRankFragment() {
    }

    private void initRecycler() {
        liveRankRecyclerAdapter = new LiveRankRecyclerAdapter(R.layout.live_rank_recycler_item,liveRankEntityArrayList,this);
        live_rank_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        live_rank_recycler.setAdapter(liveRankRecyclerAdapter);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        requestRankList(false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    private void requestRankList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
            data.put("current",1);
            data.put("type",type);
            data.put("size",50);
            HttpApiUtils.showLoadRequest(getActivity(), this, RequestUtils.LIVE_RANK_LIST, data, loading_linear, error_linear, reload_tv, (View) refresh, false, false, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    LiveRankEntity liveRankEntity = JSONObject.parseObject(result, LiveRankEntity.class);
                    List<LiveRankEntity.DataBean.RecordsBean> records = liveRankEntity.getData().getRecords();
                    RefreshUtils.succse(1,refresh,loading_linear,null,records.size(),false,true,liveRankEntityArrayList);
                    liveRankEntityArrayList.addAll(records);
                    liveRankRecyclerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(String msg) {
                    RefreshUtils.fail(isRefresh,false,1,refresh);
                }
            });
          /*  HttpApiUtils.cpShowLoadRequest(getActivity(), this, RequestUtil.GIGT_RANK, data, loading_linear, error_linear, reload_tv, (View) refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    LiveRankEntity liveRankEntity = JSONObject.parseObject(result, LiveRankEntity.class);
                    String anchorGift = liveRankEntity.getAnchorGift();//自身礼物金额
                    List<LiveRankEntity.ListBean> list = liveRankEntity.getList();
                    RefreshUtil.succse(1,refresh,loading_linear,null,list.size(),false,true,liveRankEntityArrayList);
                    liveRankEntityArrayList.addAll(list);
                    liveRankRecyclerAdapter.notifyDataSetChanged();
                    boolean isInRank=false;
                    LiveRankEntity.ListBean mineBean=new LiveRankEntity.ListBean();
                    mineBean.setAnchorGift(anchorGift);
                    String imageUrl = SharePreferencesUtil.getString(getContext(), "image", "");
                    mineBean.setImage(imageUrl);
                    mineBean.setPointGrade(SharePreferencesUtil.getInt(MyApplication.getInstance(),"pointGrade",0)+"");
                    mineBean.setUserNickName(SharePreferencesUtil.getString(MyApplication.getInstance(),"userNickName",""));
                    int position=0;
                    for (int i = 0; i < liveRankEntityArrayList.size(); i++) {
                        LiveRankEntity.ListBean listBean = liveRankEntityArrayList.get(i);
                        if(listBean.getUser_id().equals(SharePreferencesUtil.getLong(MyApplication.getInstance(),"user_id",0l)+"")){
                            isInRank=true;
                            mineBean.setImage(listBean.getImage());
                            mineBean.setUserNickName(listBean.getUserNickName());
                            mineBean.setPointGrade(listBean.getPointGrade());
                            position = i;
                            break;
                        }
                    }
                    Fragment parentFragment = getParentFragment();
                    if(parentFragment instanceof LiveRankDialogFragment){
                        liveRankDialogFragment = (LiveRankDialogFragment) parentFragment;
                        if(liveRankDialogFragment!=null){
                            liveRankDialogFragment.initMineInfo(isInRank,position,mineBean);
                        }
                    }
                }

                @Override
                public void onFailed(String msg) {
                    RefreshUtil.fail(isRefresh,false,1,refresh);
                }
            });*/

    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestRankList(false);
    }

    private void initRefresh() {


//        header.setAccentColor(getActivity().getResources().getColor(R.color.alpha_70_black));
        refresh.setEnableLoadMoreWhenContentNotFull(false);
        refresh.setEnableLoadMore(false);
        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        classicsHeader.setPrimaryColor(getActivity().getResources().getColor(R.color.black_70));
        classicsHeader.setAccentColor(Color.WHITE);
        refresh.setRefreshHeader(classicsHeader);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestRankList(true);
            }
        });


    }

    private void getArgumentsData() {
         position = getArguments().getInt("position");
        anchorAccount = getArguments().getString("anchorAccount");
        switch (position){
            case 0:
                type=1;
                break;
            case 1:
                type=2;
                break;
            case 2:
                type =3;
                break;
                default:
                break;
        }
    }

    public static LiveRankFragment newInstance(int position, String anchorAccount){
       LiveRankFragment liveRankFragment = new LiveRankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("anchorAccount",anchorAccount);
        bundle.putInt("position",position);
        liveRankFragment.setArguments(bundle);
        return liveRankFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
