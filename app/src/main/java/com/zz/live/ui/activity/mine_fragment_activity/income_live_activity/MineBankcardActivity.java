package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.zz.live.bean.BankcardEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.BankcardAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class MineBankcardActivity extends BaseActivity {
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
    @BindView(R.id.bankcard_recycle)
    RecyclerView bankcard_recycle;
    BankcardAdapter bankcardAdapter;
    ArrayList<BankcardEntity.DataBean> bankcardEntityArrayList = new ArrayList<>();
    ConstraintLayout bankcard_foot_wrap_view;
    int current=1;
    static int SKIP_ADD_CARD=1;
    BankcardEntity.DataBean currentDataBean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_bankcard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        initRefresh();
        initRecycler();
        requestBankList(false,false);
    }

    private void getIntentData() {
        currentDataBean= (BankcardEntity.DataBean) getIntent().getSerializableExtra("currentDataBean");
    }

    private void initRecycler() {
        bankcardAdapter = new BankcardAdapter(R.layout.bankcard_recycler_item,bankcardEntityArrayList);
        bankcard_recycle.setLayoutManager(new LinearLayoutManager(this));
        bankcard_recycle.setAdapter(bankcardAdapter);
        View footView = LayoutInflater.from(this).inflate(R.layout.bankcard_recycker_foot,null);
        bankcardAdapter.addFooterView(footView);
        bindFootView(footView);
        bankcardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                BankcardEntity.DataBean checkedDataBean=null;
                for (int i = 0; i < bankcardEntityArrayList.size(); i++) {
                    BankcardEntity.DataBean dataBean   = bankcardEntityArrayList.get(i);
                    if(i==position){
                        dataBean.setStatus(1);
                        checkedDataBean=dataBean;
                    }else {
                        dataBean.setStatus(0);
                    }
                }
                bankcardAdapter.notifyDataSetChanged();
                Intent intent = new Intent();
                intent.putExtra("dataBean",checkedDataBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void bindFootView(View footView) {
        bankcard_foot_wrap_view = footView.findViewById(R.id.bankcard_foot_wrap_view);
        bankcard_foot_wrap_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineBankcardActivity.this, AddBankcardActivity.class);
                startActivityForResult(intent,SKIP_ADD_CARD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SKIP_ADD_CARD&&resultCode==RESULT_OK){
            refresh.autoRefresh();
        }
    }

    private void requestBankList(boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",100);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.USER_BANK_CARD_LIST, data, loading_linear,error_linear,reload_tv,refresh,isLoadMore,isRefresh,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                BankcardEntity bankcardEntity = JSONObject.parseObject(result, BankcardEntity.class);
                List<BankcardEntity.DataBean> dataBeans = bankcardEntity.getData();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear,dataBeans.size(),isLoadMore,isRefresh,bankcardEntityArrayList);
                bankcardEntityArrayList.addAll(dataBeans);
                boolean haveDufaultCheck=false;
                for (int i = 0; i < bankcardEntityArrayList.size(); i++) {
                    BankcardEntity.DataBean dataBean = bankcardEntityArrayList.get(i);
                    if(dataBean!=null){
                        if(dataBean.getId().equals(currentDataBean.getId())){
                            dataBean.setStatus(1);
                            haveDufaultCheck=true;
                            break;
                        }
                    }

                }
                if(!haveDufaultCheck){
                    if(bankcardEntityArrayList.size()!=0){
                        bankcardEntityArrayList.get(0).setStatus(1);
                    }
                }

                bankcardAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFail(String msg) {
               RefreshUtils.fail(isRefresh,isLoadMore,current,refresh);
            }
        });
    }
    private void initRefresh() {
        RefreshUtils.initRefresh(this, refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestBankList(false,true);
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestBankList(false,false);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"我的银行卡");
        CommonToolbarUtil.initStatusBarColor(this);
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
