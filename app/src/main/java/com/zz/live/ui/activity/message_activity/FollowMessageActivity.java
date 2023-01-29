package com.zz.live.ui.activity.message_activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.FollowMessageEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.FollowMessageAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

import static com.zz.live.ui.activity.mine_fragment_activity.MessageActivity.TO_FOLLOW_MESSAGE;

public class FollowMessageActivity extends BaseActivity {

    @BindView(R.id.follow_message_refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.follow_message_recycler)
    RecyclerView follow_message_recycler;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    FollowMessageAdapter followMessageAdapter;
    ArrayList<FollowMessageEntity.DataBean.RecordsBean>recordsBeanArrayList = new ArrayList<>();
    private int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_follow_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestMessageList(false,false);
        CommonToolbarUtil.initToolbar(this,"关注消息");

    }
    public static void startAty(Activity activity){
        Intent intent = new Intent(activity, FollowMessageActivity.class);
        activity.startActivityForResult(intent,TO_FOLLOW_MESSAGE);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestMessageList(false,false);
    }

    private void requestMessageList(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",20);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.FOLLOW_MESSAGE_LIST, data, loading_linear,error_linear,reload_tv,refreshLayout,isLoadMore,isRefresh,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                FollowMessageEntity followMessageEntity = JSONObject.parseObject(result, FollowMessageEntity.class);
                List<FollowMessageEntity.DataBean.RecordsBean> recordsBeans = followMessageEntity.getData().getRecords();
                RefreshUtils.succse(current,refreshLayout,loading_linear,nodata_linear,recordsBeans.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeans);
                followMessageAdapter.notifyDataSetChanged();
                setResult(RESULT_OK);
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refreshLayout);
            }
        });
    }

    private void initRecycler() {
        followMessageAdapter = new FollowMessageAdapter(R.layout.follow_message_item,recordsBeanArrayList);
        follow_message_recycler.setLayoutManager(new LinearLayoutManager(this));
        follow_message_recycler.setAdapter(followMessageAdapter);
        followMessageAdapter.addChildClickViewIds(R.id.message_slide_constraint);
        followMessageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

            }
        });
    }
    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(this, refreshLayout, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestMessageList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestMessageList(false,true);
            }
        });
    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
     CommonToolbarUtil.initStatusBarColor(this);
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
