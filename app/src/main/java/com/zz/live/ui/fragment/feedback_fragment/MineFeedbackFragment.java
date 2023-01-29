package com.zz.live.ui.fragment.feedback_fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.MineFeedbackModel;
import com.zz.live.bean.UpdateMineFeedback;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.MineFeedbackRecycleAdapter;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

/*
我的反馈
 */
public class MineFeedbackFragment extends BaseFragment {
    @BindView(R.id.mine_feedback_recycle)
    RecyclerView mRecy;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.error_linear)
    LinearLayout errorLinear;
    @BindView(R.id.reload_tv)
    TextView reloadTv;
    @BindView(R.id.loading_linear)
    ConstraintLayout loadingLinear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodataLinear;
    private MineFeedbackRecycleAdapter mineFeedbackRecycleAdapter;
    private ArrayList<MineFeedbackModel.DataBean.RecordsBean> mineFeedbackModelArrayList = new ArrayList<>();

    private int pageNum = 1;



    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        initRecycle();
        initRefresh();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_feedback;
    }


    @Override
    public void errorRefresh() {
        super.errorRefresh();
        getMineFeedback(pageNum,false,false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updateMineFeedback(UpdateMineFeedback updateMineFeedback){
        if(updateMineFeedback.isUpdate()){
            getMineFeedback(1,false,false);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(refreshLayout!=null){
            refreshLayout.closeHeaderOrFooter();
        }
        EventBus.getDefault().unregister(this);
    }


    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(getContext(), refreshLayout, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum=1;
                getMineFeedback(pageNum,false,true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                getMineFeedback(pageNum,true,false);
            }
        });
    }

    private void initRecycle() {
        mineFeedbackRecycleAdapter = new MineFeedbackRecycleAdapter(mineFeedbackModelArrayList);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecy.setAdapter(mineFeedbackRecycleAdapter);

        getMineFeedback(1, false, false);
    }

    private void getMineFeedback(int pageNo, boolean isLoadMore, boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current", pageNo);
        data.put("size", 20);
//        data.put("orderField", "createdDate");
        HttpApiUtils.showLoadRequest(getActivity(), this, RequestUtils.MINE_FEEDBACK_LIST, data, loadingLinear, errorLinear, reloadTv, refreshLayout, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                MineFeedbackModel mineFeedbackModel = JSONObject.parseObject(result, MineFeedbackModel.class);
                List<MineFeedbackModel.DataBean.RecordsBean> records = mineFeedbackModel.getData().getRecords();
                RefreshUtils.succse(pageNum, refreshLayout, loadingLinear, nodataLinear,records.size(), isLoadMore, isRefresh, mineFeedbackModelArrayList);
                mineFeedbackModelArrayList.addAll(records);
                mineFeedbackRecycleAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,pageNo,refreshLayout);
            }
        });
    }
}

