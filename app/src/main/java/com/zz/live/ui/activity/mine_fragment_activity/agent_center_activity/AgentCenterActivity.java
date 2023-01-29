package com.zz.live.ui.activity.mine_fragment_activity.agent_center_activity;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AgentCenterEntity;
import com.zz.live.bean.AgentInfoEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.AgentAdapter;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AgentCenterActivity extends BaseActivity {
    @BindView(R.id.agent_nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.agent_error_linear)
    LinearLayout error_linear;
    @BindView(R.id.agent_reload_tv)
    TextView reload_tv;
    @BindView(R.id.agent_loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.agent_center_toolbar)
    Toolbar agent_center_toolbar;
    @BindView(R.id.agent_center_toolbar_back_iv)
    ImageView agent_center_toolbar_back_iv;
    @BindView(R.id.agent_center_toolbar_right_tv)
    TextView agent_center_toolbar_right_tv;
    @BindView(R.id.draw_into_proportion_tv)
    TextView draw_into_proportion_tv;
    @BindView(R.id.draw_into_amount_tv)
    TextView draw_into_amount_tv;
    @BindView(R.id.mine_member_tv)
    TextView mine_member_tv;
    @BindView(R.id.live_data_tv)
    TextView live_data_tv;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.id_tv)
    TextView id_tv;
    @BindView(R.id.title_iv)
    ImageView title_iv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.agent_recycler)
    RecyclerView agent_recycler;
    AgentAdapter agentAdapter;
    ArrayList<AgentCenterEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_agent_center;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestAgentInfo();
        requestAnchorList(false,false);
    }

    private void requestAgentInfo() {
        HttpApiUtils.wwwRequest(this, null,RequestUtils.AGENT_INFO, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AgentInfoEntity agentInfoEntity = JSONObject.parseObject(result, AgentInfoEntity.class);
                AgentInfoEntity.DataBean data = agentInfoEntity.getData();
                String parentExtraRake = data.getParentExtraRake();
                //用户名
                name_tv.setText(data.getNickname());
                //头像
                GlideLoadViewUtil.LoadTitleView(AgentCenterActivity.this, Utils.checkImageUrl(data.getImage()),title_iv);
                //id
                id_tv.setText("ID:"+data.getAccountId());
                //抽成比例
                draw_into_proportion_tv.setText(parentExtraRake+"%");
                //抽成金额
                draw_into_amount_tv.setText(data.getCommission()+"元");
                //我的成员
                mine_member_tv.setText(data.getMemberCount()+"个");
                //主播数据
                live_data_tv.setText(data.getGiftAmount()+"元");
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void initRecycler() {
        agentAdapter = new AgentAdapter(R.layout.agent_center_recycler_item_, recordsBeanArrayList,this);
        agent_recycler.setLayoutManager(new LinearLayoutManager(this));
        agent_recycler.setAdapter(agentAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.agent_center_head_layout,null);
        agentAdapter.addHeaderView(headView);


    }

    private void requestAnchorList(boolean isRefresh,boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.AGENT_ANCHOR_LIST, data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AgentCenterEntity agentCenterEntity = JSONObject.parseObject(result, AgentCenterEntity.class);
                List<AgentCenterEntity.DataBean.RecordsBean> records = agentCenterEntity.getData().getRecords();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear,records.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(records);
                agentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refresh);
            }
        });
    }

    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(this, refresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestAnchorList(true,false);

            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestAnchorList(false,true);
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestAnchorList(false,false);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBar(agent_center_toolbar)
                .init();
    }
    @OnClick({R.id.agent_center_toolbar_back_iv,R.id.agent_center_toolbar_right_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.agent_center_toolbar_back_iv:
                finish();
                break;
            case R.id.agent_center_toolbar_right_tv:
                startActivity(new Intent(AgentCenterActivity.this,AnchorDataActivity.class));
                break;
                default:
                    break;
        }
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
