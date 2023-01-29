package com.zz.live.ui.activity.message_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.SystemMessageEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.SystemMessageAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

import static com.zz.live.ui.activity.mine_fragment_activity.MessageActivity.TO_SYSTEM_MESSAGE;

public class SystemMessageActivity extends BaseActivity {
    @BindView(R.id.system_message_refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.system_message_recycler)
    RecyclerView system_message_recycler;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    SystemMessageAdapter systemMessageAdapter;
    ArrayList<SystemMessageEntity.DataBean.RecordsBean>recordsBeanArrayList = new ArrayList<>();
    private int current=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_message;
    }
    public static void startAty(Activity activity){
        Intent intent = new Intent(activity, SystemMessageActivity.class);
        activity.startActivityForResult(intent,TO_SYSTEM_MESSAGE);
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestMessageList(false,false);
        CommonToolbarUtil.initToolbar(this,"系统消息");
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestMessageList(false,false);
    }

    private void requestMessageList(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        HttpApiUtils.wwwShowLoadRequest(this, null,RequestUtils.SYSTEM_MESSAGE_LIST, data, loading_linear,error_linear,reload_tv,refreshLayout,isLoadMore,isRefresh,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                SystemMessageEntity systemMessageEntity = JSONObject.parseObject(result, SystemMessageEntity.class);
                List<SystemMessageEntity.DataBean.RecordsBean> recordsBeanList = systemMessageEntity.getData().getRecords();
                RefreshUtils.succse(current,refreshLayout,loading_linear,nodata_linear,recordsBeanList.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeanList);
                systemMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refreshLayout);
            }
        });
    }

    private void initRecycler() {
        systemMessageAdapter = new SystemMessageAdapter(R.layout.message_recycler_item,recordsBeanArrayList,this);
        system_message_recycler.setLayoutManager(new LinearLayoutManager(this));
        system_message_recycler.setAdapter(systemMessageAdapter);
        systemMessageAdapter.addChildClickViewIds(R.id.message_slide_constraint);
        systemMessageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                SystemMessageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
                MessageDetailsActivity.startAty(SystemMessageActivity.this,recordsBean.getContent(),recordsBean.getTitle(),recordsBean.getId(),true, MessageDetailsActivity.SYSTEM_MESSAGE_REQUEST_CODE);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MessageDetailsActivity.SYSTEM_MESSAGE_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                String id = data.getStringExtra("id");
                for (int i = 0; i < recordsBeanArrayList.size(); i++) {
                    SystemMessageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(i);
                    if(recordsBean.getId().equals(id)){
                        recordsBean.setIsRead(1);
                        systemMessageAdapter.notifyItemChanged(i);
                    }
                }
                //更改了消息已读状态时,才通知上级页面重新请求消息列表
                setResult(RESULT_OK);
            }
        }
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

