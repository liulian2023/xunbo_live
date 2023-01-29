package com.zz.live.ui.activity.start_live_activity.open_result_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.SixResultModel;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.SixOpenResultAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.ErrorUtil;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Headers;

public class SixOpenActivity extends BaseActivity {
        private RecyclerView mRecy;
        private SixOpenResultAdapter sixOpenResultAdapter;
        private ArrayList<SixResultModel> sixResultModelArrayList =new ArrayList<>();
        private RefreshLayout refreshLayout;
        private TextView backTv;
        private int pageNum=1;
        private int typeId;
        private LinearLayout nodataLienar;
        private ConstraintLayout loadingLinear;
        private LinearLayout errorLinear;
        private TextView reloadTv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_six_open;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        typeId=  getIntent().getIntExtra("type_id",0);
        bindView();
        initRecycle();
        initRefesh();
    }


    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getData(1,typeId,false,false,true);
    }

    private void initRefesh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(SixOpenActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(SixOpenActivity.this));
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            //上拉加载
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getData(pageNum,typeId,true,false,false);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=1;
                getData(pageNum,typeId,false,true,false);
            }
        });
    }

    private void bindView() {
        errorLinear=findViewById(R.id.error_linear);
        reloadTv=findViewById(R.id.reload_tv);
        nodataLienar=findViewById(R.id.nodata_linear);
        loadingLinear=findViewById(R.id.loading_linear);
        refreshLayout=findViewById(R.id.refresh);
        backTv=findViewById(R.id.open_result_action_bar_return);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
    }

    private void initRecycle() {
        mRecy=findViewById(R.id.six_open_result_pop_recycle);
        sixOpenResultAdapter=new SixOpenResultAdapter(sixResultModelArrayList);
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mRecy.setAdapter(sixOpenResultAdapter);
        getData(pageNum,typeId,false,false,true);
    }

    private void getData(int pageNo, int type_id, final boolean isLoadMore,boolean isRefresh,boolean showLoad) {
        if(showLoad){
            nodataLienar.setVisibility(View.GONE);
            loadingLinear.setVisibility(View.VISIBLE);
        }
        ErrorUtil.hideErrorLayout(mRecy,errorLinear);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",pageNo);
        data.put("pageSize",15);
        HttpApiUtils.cpNormalRequest(this, null, RequestUtils.REQUEST_05lhc, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                loadingLinear.setVisibility(View.GONE);

                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray marksixLotterylist = jsonObject1.getJSONArray("marksixLotterylist");
                int size = marksixLotterylist.size();
                if(!isLoadMore){
                    sixResultModelArrayList.clear();
                    if(size==0){
                        nodataLienar.setVisibility(View.VISIBLE);
                    }else {
                        nodataLienar.setVisibility(View.GONE);
                    }
                    if(isRefresh){
                        refreshLayout.finishRefresh();
                    }
                }else {
                    if(size==0){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        refreshLayout.finishLoadMore();
                    }
                }


                for (int i = 0; i < size; i++) {
                    JSONObject jsonObject = marksixLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String color = jsonObject.getString("color");//颜色
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String animalAll = jsonObject.getString("animalAll");
                    sixResultModelArrayList.add(new SixResultModel(lotteryqishu,lotterytime,lotteryNo,animalAll,color));
                    sixOpenResultAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String msg) {
                loadingLinear.setVisibility(View.GONE);
                if(isRefresh){
                    refreshLayout.finishRefresh(false);
                }
                if (isLoadMore){
                    pageNum--;
                    refreshLayout.finishLoadMore(false);
                }
                ErrorUtil.showErrorLayout(SixOpenActivity.this,mRecy,errorLinear,reloadTv);
            }
        });
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
