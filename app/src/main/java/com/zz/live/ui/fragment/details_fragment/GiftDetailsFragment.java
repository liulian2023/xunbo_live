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
import com.zz.live.bean.GiftDetailsEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.GiftDetailsAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class GiftDetailsFragment extends BaseFragment {
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
    @BindView(R.id.gift_details__recycler)
    RecyclerView gift_details__recycler;

    GiftDetailsAdapter giftDetailsAdapter;
    ArrayList<GiftDetailsEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();

    int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_gift_details;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
        requestDetailsList(false,false);
    }

    private void initRecycler() {
        giftDetailsAdapter = new GiftDetailsAdapter(R.layout.gift_details_recycler_item, recordsBeanArrayList);
        gift_details__recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        gift_details__recycler.setAdapter(giftDetailsAdapter);
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
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.GIFT_DETATILS_LIST, data, loading_linear, error_linear, reload_tv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                GiftDetailsEntity giftDetailsEntity = JSONObject.parseObject(result, GiftDetailsEntity.class);
                List<GiftDetailsEntity.DataBean.RecordsBean> recordsBeanList = giftDetailsEntity.getData().getRecords();
                RefreshUtils.succse(current,refreshLayout,loading_linear,nodata_linear,recordsBeanList.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeanList);
                giftDetailsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refreshLayout);
            }
        });
    }

    public static GiftDetailsFragment newInstance(int positon){
        GiftDetailsFragment giftDetailsFragment = new GiftDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,positon);
        giftDetailsFragment.setArguments(bundle);
        return giftDetailsFragment;
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
