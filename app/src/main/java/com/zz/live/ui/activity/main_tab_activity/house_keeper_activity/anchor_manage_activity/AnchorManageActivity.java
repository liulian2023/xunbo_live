package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.AnchorManageAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AnchorManageActivity extends BaseActivity {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.anchor_manage_recycler)
    RecyclerView anchor_manage_recycler;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.toolbar_right_iv)
    ImageView toolbar_right_iv;
    @BindView(R.id.anchor_manage_search_etv)
    EditText anchor_manage_search_etv;
    @BindView(R.id.search_tv)
    TextView search_tv;
    ArrayList<AnchorManageEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    AnchorManageAdapter anchorManageAdapter;
    int currenr=1;
    String keyWords="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_anchor_manage;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        toolbar_right_iv.setVisibility(View.VISIBLE);
        toolbar_right_iv.setImageResource(R.drawable.add);
        initRecycler();
        initRefresh();

    }

    @Override
    protected void onStart() {
        super.onStart();
        refresh.autoRefresh();
    }

    private void initRecycler() {
        anchorManageAdapter = new AnchorManageAdapter(R.layout.anchor_manage_recycler_item, recordsBeanArrayList);
        anchor_manage_recycler.setLayoutManager(new LinearLayoutManager(this));
        anchor_manage_recycler.setAdapter(anchorManageAdapter);
        anchorManageAdapter.addChildClickViewIds(R.id.anchor_manage_item_wrap_constrainLayout);
        anchorManageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                AnchorManageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
                ChildAnchorActivity.startAty(AnchorManageActivity.this,recordsBean);
            }
        });
    }

    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(this, refresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                currenr=1;
                requestAnchorList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currenr++;
                requestAnchorList(false,true);
            }
        });
    }

    private void requestAnchorList(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",currenr);
        data.put("size",20);
        data.put("keyWords",keyWords);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.HOUSE_ANCHOR_LIST, data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AnchorManageEntity anchorManageEntity = JSONObject.parseObject(result, AnchorManageEntity.class);
                List<AnchorManageEntity.DataBean.RecordsBean> records = anchorManageEntity.getData().getRecords();
                RefreshUtils.succse(currenr,refresh,loading_linear,nodata_linear,records.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(records);
                anchorManageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,currenr,refresh);
            }
        });
    }
    @OnClick({R.id.search_tv,R.id.toolbar_right_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.search_tv:
                keyWords=anchor_manage_search_etv.getText().toString();
                currenr=1;
                requestAnchorList(false,false);
                break;
            case R.id.toolbar_right_iv:
                startActivity(new Intent(AnchorManageActivity.this,AddAnchorActitity.class));
                break;
                default:
                    break;
        }
    }


    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestAnchorList(false,false);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"主播管理");
        CommonToolbarUtil.initStatusBarColor(this);
    }
}
