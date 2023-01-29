package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

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
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.ForbiddenEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.ForbiddenAdapter;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.ForbiddenMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class ForbiddenFragment extends BaseFragment implements OnItemChildClickListener {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.forbidden_recycler)
    RecyclerView forbidden_recycler;
    ForbiddenAdapter forbiddenAdapter;
    ArrayList<ForbiddenEntity.DataBean> dataBeanArrayList = new ArrayList<>();
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forbidden;
    }

    public static ForbiddenFragment newInstance(int position){
        ForbiddenFragment forbiddenFragment = new ForbiddenFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        forbiddenFragment.setArguments(bundle);
        return forbiddenFragment;
    }
    private void initRecycler() {
        forbiddenAdapter = new ForbiddenAdapter(R.layout.forbidden_list_recycler_item, dataBeanArrayList);
        forbidden_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        forbidden_recycler.setAdapter(forbiddenAdapter);

        forbiddenAdapter.addChildClickViewIds(R.id.operating_tv);
        forbiddenAdapter.setOnItemChildClickListener(this);
        requestForbiddenList(false);
    }

    /**
     * 禁言列表
     * @param isRefresh
     */
    private void requestForbiddenList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("chatRoomId",sp.getRoomId());
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.FORBIDDEN_LIST, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                ForbiddenEntity forbiddenEntity = JSONObject.parseObject(result, ForbiddenEntity.class);
                List<ForbiddenEntity.DataBean> forbiddenEntityData = forbiddenEntity.getData();
                RefreshUtils.succse(1,refresh,loading_linear,null,forbiddenEntityData.size(),false,isRefresh,dataBeanArrayList);
                dataBeanArrayList.addAll(forbiddenEntityData);
                forbiddenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestForbiddenList(false);
    }

    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefresh(getContext(), refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestForbiddenList(true);
            }
        });
    }


    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        ForbiddenEntity.DataBean dataBean = dataBeanArrayList.get(position);
        switch (view.getId()){
            case R.id.operating_tv:
                requestUnForbidden(position, dataBean);
                break;
                default:
                    break;
        }
    }

    /**
     * 解禁
     * @param position
     * @param dataBean
     */
    private void requestUnForbidden(int position, ForbiddenEntity.DataBean dataBean) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("chatRoomId",sp.getRoomId());
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(dataBean.getRongId());
        data.put("rongMemberId",jsonArray);
        HttpApiUtils.request(getActivity(), ForbiddenFragment.this, RequestUtils.UN_FORBIDDEN_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                showtoast("解禁成功");
                dataBeanArrayList.remove(position);
                forbiddenAdapter.notifyItemRemoved(position);
                ForbiddenMessage forbiddenMessage = new ForbiddenMessage("0", dataBean.getNickName(), "");
                forbiddenMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(sp.getRoomId(),forbiddenMessage);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
}
