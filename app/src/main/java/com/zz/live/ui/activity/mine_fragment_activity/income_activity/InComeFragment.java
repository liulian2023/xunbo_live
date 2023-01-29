package com.zz.live.ui.activity.mine_fragment_activity.income_activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.InComeEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity.ChildAnchorActivity;
import com.zz.live.ui.adapter.InComeAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

/**
 *家族长财务对账  主播管理查看下级 主播/家族主播收入详情 公用的tabFragment
 */
public class InComeFragment extends BaseFragment {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.income_refresh)
    RefreshLayout refresh;
    @BindView(R.id.income_recycler)
    RecyclerView income_recycler;
    ArrayList<InComeEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    InComeAdapter inComeAdapter;
    int current=1;
    int type=0;
    String tabName;
    InComeActivity inComeActivity;
    ChildAnchorActivity childAnchorActivity;
    //主播管理页面跳转时传入的主播id
    String userId;
    private String title;

    @Override
    protected void init(Bundle savedInstanceState) {
        getArgumentsData();
        initRecycler();
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_in_come;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        requestIncomeList(false,false);
    }

    /**
     * 族长财务对账  主播收入详情跳转
     * @param position
     * @param tabName
     * @return
     */
    public static InComeFragment newInstance(int position,String tabName){
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        bundle.putString("tabName",tabName);
        InComeFragment inComeFragment = new InComeFragment();
        inComeFragment.setArguments(bundle);
        return inComeFragment;
    }

    /**
     * 主播管理 查看下级时跳转
     * @param position tab position
     * @param tabNmae tabName 用于初始化type
     * @param userId 主播查看下级时,接口需要传user_id
     * @return
     */
    public static InComeFragment newInstance(int position,String tabNmae,String userId){
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        bundle.putString("userId",userId);
        bundle.putString("tabName",tabNmae);
        InComeFragment inComeFragment = new InComeFragment();
        inComeFragment.setArguments(bundle);
        return inComeFragment;
    }
    private void getArgumentsData() {
       int  position=  getArguments().getInt(CommonStr.POSITION);
        userId =   getArguments().getString("userId");
        tabName =   getArguments().getString("tabName");
        if(tabName.equals("全部")){
            type=0;
        }else if(tabName.equals("订阅")){
            type=5;
        }else if(tabName.equals("礼物")){
            type=1;
        }else if(tabName.equals("返现")){
            type=6;
        }else if(tabName.equals("提现")){
            //1族长2主播3家族主播
            boolean isIncomeActivity = getActivity() instanceof InComeActivity;
            if(isIncomeActivity) {
                inComeActivity = (InComeActivity) getActivity();
                title = inComeActivity.getIntent().getStringExtra("title");
            }else {
                title="主播管理";
            }
            if(!title.equals("财务对账")){
                int userType = Utils.getUserInfoBean().getUserType();
                if(userType==1||userType==3){
                    type=4;
                }else {
                    type=2;
                }
            }else {
                type=2;
            }

        }else if(tabName.equals("转入")) {
            type=3;
        }else {
            //其他
            type=7;
        }
    }
    public void requestIncomeList(boolean isRefresh,boolean isLoadMore) {
        boolean isIncomeActivity = getActivity() instanceof InComeActivity;
        boolean isChildAnchorActivity = getActivity() instanceof ChildAnchorActivity;
        HashMap<String, Object> data = new HashMap<>();
        if(isIncomeActivity || isChildAnchorActivity){
            if(isIncomeActivity){
                inComeActivity = (InComeActivity) getActivity();
                data.put("dateType", inComeActivity.getDateType());
            }else {
                childAnchorActivity = (ChildAnchorActivity) getActivity();
                data.put("dateType", childAnchorActivity.getDateType());
            }
//            if(type!=0){
                data.put("type", type);
//            }
            if(StringMyUtil.isNotEmpty(userId)){
                data.put("userId",userId);
            }
//            data.put("dateType",4);
            data.put("current",current);
            data.put("size",20);
            HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.INCOME_LIST, data, loading_linear, error_linear, reload_tv, (View) refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    InComeEntity inComeEntity = JSONObject.parseObject(result, InComeEntity.class);
                    List<InComeEntity.DataBean.RecordsBean> records = inComeEntity.getData().getRecords();
                    RefreshUtils.succse(current,refresh,loading_linear,nodata_linear,records.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                    recordsBeanArrayList.addAll(records);
                    inComeAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFail(String msg) {
                }
            });
        }
    }

    private void initRecycler() {
        inComeAdapter = new InComeAdapter(R.layout.income_item_recycler, recordsBeanArrayList);
        income_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        income_recycler.setAdapter(inComeAdapter);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestIncomeList(false,false);
    }

    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefreshLoadMore(getContext(), refresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestIncomeList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestIncomeList(false,true);
            }
        });
    }


}
