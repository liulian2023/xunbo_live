package com.zz.live.ui.fragment.details_fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.LiveDurationEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.LIveDurationAdaper;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class LiveDurationFragment extends BaseFragment {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    LIveDurationAdaper lIveDurationAdaper;
    ArrayList<LiveDurationEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_duration;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
        requestDetailsList(false,false);
    }
    private void initRecycler() {
        lIveDurationAdaper = new LIveDurationAdaper(R.layout.duration_details_recycler_item, recordsBeanArrayList);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(lIveDurationAdaper);
    }
    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestDetailsList(false,false);
    }

    private void requestDetailsList(boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",30);
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.LIVE_DURATION_LIST, data, loading_linear, error_linear, reload_tv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                LiveDurationEntity liveDurationEntity = JSONObject.parseObject(result, LiveDurationEntity.class);
                List<LiveDurationEntity.DataBean.RecordsBean> recordsBeanList = liveDurationEntity.getData().getRecords();
                RefreshUtils.succse(current,refreshLayout,loading_linear,nodata_linear,recordsBeanList.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeanList);
                lIveDurationAdaper.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refreshLayout);
            }
        });
    }

    public static LiveDurationFragment newInstance(int positon){
        LiveDurationFragment liveDurationFragment = new LiveDurationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,positon);
        liveDurationFragment.setArguments(bundle);
        return liveDurationFragment;
    }

    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefreshLoadMore(getContext(), refreshLayout, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestDetailsList(false,true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestDetailsList(true,false);
            }
        });
    }
}
