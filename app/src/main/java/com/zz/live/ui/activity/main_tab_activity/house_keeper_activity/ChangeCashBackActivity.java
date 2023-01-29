package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.ChangeCachBackEntity;
import com.zz.live.bean.UserMoneyEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.mine_fragment_activity.income_live_activity.IncomeLiveActivity;
import com.zz.live.ui.adapter.ChangeCashbackAdapter;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class ChangeCashBackActivity extends BaseActivity {
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
    @BindView(R.id.extract_amount_tv)
    TextView extract_amount_tv;
    @BindView(R.id.cash_back_amount_tv)
    TextView cash_back_amount_tv;
    @BindView(R.id.income_btn)
    Button cashbash_btn;
    @BindView(R.id.extract_btn)
    Button extract_btn;
    @BindView(R.id.refresh_iv)
    ImageView refresh_iv;
    @BindView(R.id.change_cashback_toolbar_relativeLayout)
    RelativeLayout change_cashback_toolbar_relativeLayout;
    @BindView(R.id.change_cashback_back_iv)
    ImageView change_cashback_back_iv;
    @BindView(R.id.change_cashBack_recycler)
    RecyclerView change_cashBack_recycler;
    ChangeCashbackAdapter changeCashbackAdapter;
    ArrayList<ChangeCachBackEntity.DataBean.VoListBean>voListBeanArrayList = new ArrayList<>();
    private String clickBalance;
    private BigDecimal extractAmount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_cash_back;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestAnchorList(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestMoney(false);
    }

    private void requestAnchorList(boolean isRefresh) {
        HttpApiUtils.wwwShowLoadRequest(this, null, RequestUtils.ANCHOR_AMOUNT_LIST, new HashMap<String, Object>(), loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
                    @Override
                    public void onSuccess(String result) {
                        ChangeCachBackEntity changeCachBackEntity = JSONObject.parseObject(result, ChangeCachBackEntity.class);
                        List<ChangeCachBackEntity.DataBean.VoListBean> voList = changeCachBackEntity.getData().getVoList();
                        String totalAmount = changeCachBackEntity.getData().getTotalAmount();
                        extract_amount_tv.setText(totalAmount);
                        extractAmount= new BigDecimal(totalAmount);
                        RefreshUtils.succse(1,refresh,loading_linear,nodata_linear,voList.size(),false,isRefresh,voListBeanArrayList);
                        voListBeanArrayList.addAll(voList);
                        changeCashbackAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(String msg) {
                        RefreshUtils.fail(isRefresh,false,1,refresh);
                    }
                });
    }

    /**
     * 顶部用户资金(可提现资金)
     */
    private void requestMoney(boolean isRefrsh) {
        HttpApiUtils.wwwRequest(this, null,RequestUtils.USER_MONEY, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                UserMoneyEntity userMoneyEntity = JSONObject.parseObject(result, UserMoneyEntity.class);
                String balance = userMoneyEntity.getData().getBalance();
                cash_back_amount_tv.setText(balance);
                if(isRefrsh){
                    showtoast("刷新成功");
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    private void initRecycler() {
        changeCashbackAdapter = new ChangeCashbackAdapter(R.layout.change_cash_back_recycler_item,voListBeanArrayList);
        change_cashBack_recycler.setLayoutManager(new LinearLayoutManager(this));
        change_cashBack_recycler.setAdapter(changeCashbackAdapter);
        changeCashbackAdapter.addChildClickViewIds(R.id.extract_btn);
        changeCashbackAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter  adapter, @NonNull View view, int position) {
                extractAmount(position,false);
            }
        });
    }

    /**
     * 提取主播金额
     * @param position 点击的item position 点击全部提取时随便传
     * @param isAll 是否是全部提取
     */
    private void extractAmount(int position,boolean isAll) {
        HashMap<String, Object> data = new HashMap<>();
        if(voListBeanArrayList.size()==0){
            return;
        }
        final ChangeCachBackEntity.DataBean.VoListBean voListBean = voListBeanArrayList.get(position);
        if(!isAll){
            clickBalance  = voListBean.getBalance();//当前提取的金额
            data.put("userId", voListBean.getId());
        }
        HttpApiUtils.wwwRequest(ChangeCashBackActivity.this, null, RequestUtils.EXTRACT_AMOUNT, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                //提取前可提现金额
                String cashBackAmount = cash_back_amount_tv.getText().toString();
                cashBackAmount = cashBackAmount.contains("-")?"0":cashBackAmount;
                if(isAll){
                    //可提现金额加上提取前的所有总金额
                    showtoast("全部提取成功");
                    //提取前的提取总金额
                    BigDecimal extractAmount = new BigDecimal(extract_amount_tv.getText().toString());
                    cash_back_amount_tv.setText(new BigDecimal(cashBackAmount).add(extractAmount)+"");
                    refresh.autoRefresh();

                }else {
                    showtoast("提取成功");
                    voListBean.setBalance("0.00");
                    //提取后的提取总金额需减去上当前提取的金额
                     extractAmount=extractAmount.subtract(new BigDecimal(clickBalance));
                    //单个提取后的可提现总金额需要加上当前提取的金额
                    cash_back_amount_tv.setText(new BigDecimal(cashBackAmount).add(new BigDecimal(clickBalance))+"");
                    extract_amount_tv.setText(extractAmount+"");

                    changeCashbackAdapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    @OnClick({R.id.income_btn,R.id.extract_btn,R.id.refresh_iv,R.id.change_cashback_back_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.income_btn:
                //提现
                startActivity(new Intent(ChangeCashBackActivity.this, IncomeLiveActivity.class));
                break;
            case R.id.extract_btn:
                //一键提取
                extractAmount(0,true);
                break;
            case R.id.refresh_iv:
                //刷新余额
                requestMoney(true);
                break;
            case R.id.change_cashback_back_iv:
                finish();
                break;
                default:
                    break;
        }
    }
    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestAnchorList(false);
    }

    private void initRefresh() {
        RefreshUtils.initRefresh(this, refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestAnchorList(true);
            }
        });
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBarMarginTop(findViewById(R.id.change_cashback_toolbar_relativeLayout))
                .init();
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
