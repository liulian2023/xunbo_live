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
import com.zz.live.bean.LotteryEntitiy;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.ChooseLotteryAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import okhttp3.Headers;

public class ChooseLotteryActivity extends BaseActivity {
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
    @BindView(R.id.choose_lottery_recycler)
    RecyclerView choose_lottery_recycler;
    ChooseLotteryAdapter chooseLotteryAdapter;
    ArrayList<LotteryEntitiy.GameClasslistBean>dataBeanArrayList = new ArrayList<>();
    int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_lottery;
    }
    @Override

    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"选择彩票");
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestLotteryList(false);
    }

    private void requestLotteryList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("isAnchorUse",1);
        HttpApiUtils.cpShowLoadRequest(this, null, RequestUtils.CP_LOTTERY_LIST, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                LotteryEntitiy lotteryEntitiy = JSONObject.parseObject(result, LotteryEntitiy.class);
                List<LotteryEntitiy.GameClasslistBean> data = lotteryEntitiy.getGameClasslist();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear, data.size(),false,isRefresh,dataBeanArrayList);
//                dataBeanArrayList.addAll(data);
                for (int i = 0; i < data.size(); i++) {
                    dataBeanArrayList.add(data.get(i));

                }
                chooseLotteryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,false,current,refresh);
            }
        });
    }

    private void initRecycler() {
        chooseLotteryAdapter = new ChooseLotteryAdapter(R.layout.choose_lottery_recycler_item,dataBeanArrayList,this);
        choose_lottery_recycler.setLayoutManager(new LinearLayoutManager(this));
        choose_lottery_recycler.setAdapter(chooseLotteryAdapter);
        chooseLotteryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                LotteryEntitiy.GameClasslistBean dataBean = dataBeanArrayList.get(position);
                Intent intent = new Intent();
                intent.putExtra("dataBean",dataBean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestLotteryList(false);
    }

    private void initRefresh() {
        RefreshUtils.initRefresh(this, refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestLotteryList(true);
            }
        });
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
