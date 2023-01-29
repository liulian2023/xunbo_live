package com.zz.live.ui.activity.start_live_activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.ChannelAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class ChooseChannelActivity extends BaseActivity {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.channel_recycler)
    RecyclerView channel_recycler;
    ChannelAdapter channelAdapter;
    ArrayList<HomeClassfyEntity.DataBean> dataBeanArrayList = new ArrayList<>();

    int current=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_channel;
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"选择直播频道");
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestChannelList(false,false);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestChannelList(false,false);
    }

    private void requestChannelList(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",100);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.LIVE_CLASSFY, data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                HomeClassfyEntity homeClassfyEntity = JSONObject.parseObject(result, HomeClassfyEntity.class);
                List<HomeClassfyEntity.DataBean> dataBeans = homeClassfyEntity.getData();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear,dataBeans.size(),isLoadMore,isRefresh,dataBeanArrayList);
                dataBeanArrayList.addAll(dataBeans);
                channelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refresh);
            }
        });
    }

    private void initRecycler() {
        channelAdapter = new ChannelAdapter(R.layout.channel_recycler_item,dataBeanArrayList,this);
        channel_recycler.setLayoutManager(new LinearLayoutManager(this));
        channel_recycler.setAdapter(channelAdapter);
        channelAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                HomeClassfyEntity.DataBean dataBean = dataBeanArrayList.get(position);
                Intent intent = new Intent();
                intent.putExtra("dataBean",dataBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void initRefresh() {
        RefreshUtils.initRefresh(this, refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestChannelList(true,false);
            }
    });
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

}
