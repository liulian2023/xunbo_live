package com.zz.live.ui.activity.mine_fragment_activity.agent_center_activity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
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
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.AnchorDataEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.AnchorDataAdapter;
import com.zz.live.ui.pop.ChooseDatePop;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AnchorDataActivity extends BaseActivity implements BasePopupWindow.OnPopClickListener {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.anchor_toolbar_back_iv)
    ImageView anchor_toolbar_back_iv;
    @BindView(R.id.anchor_date_tv)
    TextView anchor_date_tv;
    @BindView(R.id.anchor_toolbar_right_iv)
    ImageView anchor_toolbar_right_iv;
    @BindView(R.id.gift_amount_tv)
    TextView gift_amount_tv;
    @BindView(R.id.draw_in_amount_tv)
    TextView draw_in_amount_tv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.anchor_recycler)
    RecyclerView anchor_recycler;
    AnchorDataAdapter anchorDataAdapter;
    ArrayList<AnchorDataEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    int current=1;
    int type=5;
    ChooseDatePop chooseDatePop;
    @Override
    public int getLayoutId() {
        return R.layout.activity_anchor_data;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
     initRefresh();
     initRecycler();
     requestAnchorHeadData();
     requestAnchorListData(false,false);
    }

    private void requestAnchorListData(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        HttpApiUtils.pathShowLoadRequest(this, null, RequestUtils.ANCHOR_LIST_DATA, type + "", data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AnchorDataEntity anchorDataEntity = JSONObject.parseObject(result, AnchorDataEntity.class);
                List<AnchorDataEntity.DataBean.RecordsBean> records = anchorDataEntity.getData().getRecords();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear, records.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(records);
                anchorDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refresh);
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestAnchorListData(false,false);
    }

    private void requestAnchorHeadData() {
        HttpApiUtils.pathRequest(this, RequestUtils.ANCHOR_DATA, type + "", new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject data = jsonObject.getJSONObject("data");
                String totalCommission = data.getString("totalCommission");
                String giftTotalAmount = data.getString("giftTotalAmount");
                gift_amount_tv.setText(giftTotalAmount);
                draw_in_amount_tv.setText(totalCommission);

            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void initRecycler() {
        anchorDataAdapter=new AnchorDataAdapter(R.layout.anchor_recycler_item_, recordsBeanArrayList,this);
        anchor_recycler.setLayoutManager(new LinearLayoutManager(this));
        anchor_recycler.setAdapter(anchorDataAdapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.anchor_head_layout,null);
        TextView draw_in_amount_tip_tv = headView.findViewById(R.id.draw_in_amount_tip_tv);
        draw_in_amount_tip_tv.setText("抽成金额");
        anchorDataAdapter.addHeaderView(headView);
    }

    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(this, refresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestAnchorListData(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestAnchorListData(false,true);
            }
        });
    }
    @OnClick({R.id.anchor_toolbar_back_iv,R.id.anchor_date_tv,R.id.anchor_toolbar_right_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.anchor_toolbar_back_iv:
                finish();
                break;
            case R.id.anchor_date_tv:
            case R.id.anchor_toolbar_right_iv:
                if(chooseDatePop==null){
                    chooseDatePop = new ChooseDatePop(AnchorDataActivity.this);
                    chooseDatePop.setOnPopClickListener(this);
                }
                Utils.darkenBackground(AnchorDataActivity.this,0.7f);
                chooseDatePop.showAsDropDown(anchor_date_tv,0,20, Gravity.BOTTOM);
                break;
                default:
                    break;

        }
    }

    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            case R.id.today_tv:
                initDate(1,"今日");
                break;
            case R.id.yesterday_tv:
                initDate(2,"昨日");
                break;
            case R.id.week_tv:
                initDate(3,"七日");
                break;
            case R.id.month_tv:
                initDate(4,"本月");
                break;
        }
    }
    private void initDate(int type,String dateStr) {
        this.type=type;
        anchor_date_tv.setText(dateStr);
        chooseDatePop.dismiss();
        requestAnchorListData(false,false);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.anchor_toolbar))
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

}
