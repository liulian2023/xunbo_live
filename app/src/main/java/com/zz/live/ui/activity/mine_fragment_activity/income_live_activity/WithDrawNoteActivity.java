package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.WithdrawNoteEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.WithdrawAdapter;
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

public class WithDrawNoteActivity extends BaseActivity implements BasePopupWindow.OnPopClickListener {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.withDraw_toolbar_back_iv)
    ImageView withDraw_toolbar_back_iv;
    @BindView(R.id.withDraw_toolbar_right_iv)
    ImageView withDraw_toolbar_right_iv;
    @BindView(R.id.withDraw_date_tv)
    TextView withDraw_date_tv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.withDraw_recycler)
    RecyclerView withDraw_recycler;
    WithdrawAdapter withdrawAdapter;
    ArrayList<WithdrawNoteEntity.DataBean.RecordsBean>recordsBeanArrayList = new ArrayList<>();
    int current=1;
    int type=1;
    ChooseDatePop chooseDatePop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_with_draw_note;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestNoteList(false,false);
    }

    private void initRecycler() {
        withdrawAdapter = new WithdrawAdapter(R.layout.withdraw_recycler_item,recordsBeanArrayList);
        withDraw_recycler.setLayoutManager(new LinearLayoutManager(this));
        withDraw_recycler.setAdapter(withdrawAdapter);
        withdrawAdapter.addChildClickViewIds(R.id.withdraw_note_item_wrap_view);
        withdrawAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                WithdrawNoteEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
                Intent intent = new Intent(WithDrawNoteActivity.this, WithdrawDetailsActivity.class);
                intent.putExtra("recordsBean",recordsBean);
                startActivity(intent);
            }
        });
    }

    private void requestNoteList(boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        HttpApiUtils.pathShowLoadRequest(this, null, RequestUtils.WITHDRAW_LIST, type + "", data, loading_linear, error_linear, reload_tv, refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                WithdrawNoteEntity withdrawNoteEntity = JSONObject.parseObject(result, WithdrawNoteEntity.class);
                List<WithdrawNoteEntity.DataBean.RecordsBean> recordsBeans = withdrawNoteEntity.getData().getRecords();
                int size = recordsBeans.size();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear, size,isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeans);
                withdrawAdapter.notifyDataSetChanged();
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
                requestNoteList(false,true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestNoteList(true,false);
            }
        });
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.withDraw_toolbar))
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
    }
    @OnClick({R.id.withDraw_toolbar_back_iv,R.id.withDraw_toolbar_right_iv,R.id.withDraw_date_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.withDraw_toolbar_back_iv:
                finish();
                break;
                /*
                选择时间
                 */
            case R.id.withDraw_toolbar_right_iv:
            case R.id.withDraw_date_tv:
                if(chooseDatePop==null){
                    chooseDatePop = new ChooseDatePop(WithDrawNoteActivity.this);
                    chooseDatePop.setOnPopClickListener(this);
                }
                Utils.darkenBackground(WithDrawNoteActivity.this,0.7f);
                chooseDatePop.showAsDropDown(withDraw_date_tv,0,20, Gravity.BOTTOM);
                break;
            default:
                break;
        }
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }

    /**
     * pop点击回调
     * @param view
     */
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
                default:
                    break;
        }
    }

    private void initDate(int type,String dateStr) {
        this.type=type;
        withDraw_date_tv.setText(dateStr);
        chooseDatePop.dismiss();
        requestNoteList(false,false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_OK);
    }
}
