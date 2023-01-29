package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

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
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AddCardEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.AddBankcardAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

public class ChooseBankcardActivity extends BaseActivity {
    ConstraintLayout loading_linear;
    LinearLayout error_linear;
    TextView reload_tv;
    LinearLayout nodata_linear;

    RecyclerView add_bankcard_recycler;
    AddBankcardAdapter addBankcardAdapter;
    public ArrayList<AddCardEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_bankcard;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initRecycler();
        requestBankList();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    private void initRecycler() {
        addBankcardAdapter = new AddBankcardAdapter(R.layout.add_bankcard_item, recordsBeanArrayList);
        add_bankcard_recycler.setLayoutManager(new LinearLayoutManager(this));
        add_bankcard_recycler.setAdapter(addBankcardAdapter);
        addBankcardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                AddCardEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
                String name = recordsBean.getName();
                Intent intent = new Intent();
                intent.putExtra("name",name);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    private void requestBankList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",100);
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.BANK_LIST, data, loading_linear, error_linear, reload_tv, add_bankcard_recycler, false, false, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AddCardEntity addCardEntity = JSONObject.parseObject(result, AddCardEntity.class);
                List<AddCardEntity.DataBean.RecordsBean> recordsBeans = addCardEntity.getData().getRecords();
                RefreshUtils.succse(1,null,loading_linear,nodata_linear,recordsBeans.size(),false,false,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeans);
                addBankcardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(false,false,1,null);
            }
        });
    }
    
    public void initView() {
        add_bankcard_recycler = findViewById(R.id.add_bankcard_recycler);
        nodata_linear = findViewById(R.id.nodata_linear);
        loading_linear = findViewById(R.id.loading_linear);
        error_linear = findViewById(R.id.error_linear);
        reload_tv = findViewById(R.id.reload_tv);
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"选择银行卡");
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestBankList();
    }
}
