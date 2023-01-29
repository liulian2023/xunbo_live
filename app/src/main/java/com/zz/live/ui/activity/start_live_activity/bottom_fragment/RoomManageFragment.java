package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.ManagerEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.ManageRecyclerAdapter;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.RoomManageMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Headers;

public class RoomManageFragment extends BaseFragment {
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.manager_recycler)
    RecyclerView manager_recycler;
    ManageRecyclerAdapter manageRecyclerAdapter;
    ArrayList<ManagerEntity.DataBean>managerEntityArrayList = new ArrayList<>();
    private ManagerEntity.DataBean currentDataBean;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
    }
    public static RoomManageFragment newInstance(int position){
        RoomManageFragment roomManageFragment = new RoomManageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        roomManageFragment.setArguments(bundle);
        return roomManageFragment;
    }
    @Override
    public int getLayoutId() {
        return (R.layout.fragment_room_manage);
    }
    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefresh(getContext(), refresh, new RefreshUtils.OnRefreshLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                requestMangeList(true);
            }
        });
    }

    private void initRecycler() {
        manageRecyclerAdapter = new ManageRecyclerAdapter(R.layout.manager_recycler_item_layout,managerEntityArrayList,this);
        manager_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        manager_recycler.setAdapter(manageRecyclerAdapter);
        requestMangeList(false);
        manageRecyclerAdapter.addChildClickViewIds(R.id.manage_cancel_tv);
        manageRecyclerAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                 currentDataBean = managerEntityArrayList.get(position);
                requestSetManager();
            }
        });
    }

    private void requestMangeList(boolean isRefresh) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("roomId",sp.getRoomId());
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.MANAGE_LIST, data, loading_linear, error_linear, reload_tv, refresh, false, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                ManagerEntity managerEntity = JSONObject.parseObject(result, ManagerEntity.class);
                List<ManagerEntity.DataBean> managerEntityData = managerEntity.getData();
                RefreshUtils.succse(1,refresh,loading_linear,null,managerEntityData.size(),false,isRefresh,managerEntityArrayList);
                managerEntityArrayList.addAll(managerEntityData);
                manageRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,false,1,refresh);
            }
        });
    }
    private void requestSetManager() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorId", Utils.getUserInfoBean().getId());
        data.put("chatroomId",sp.getRoomId());
        data.put("image",currentDataBean.getImage());
        data.put("isRoomManager","0");
        data.put("nickName",currentDataBean.getNickName());
        data.put("platformCode",currentDataBean.getPlatformCode());
        data.put("rcUserId",currentDataBean.getRcUserId());
        HttpApiUtils.request(getActivity(), this, RequestUtils.SET_MANAGE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                showtoast("房管取消成功");
                JSONObject jsonObject= JSONObject.parseObject(result).getJSONObject("data");
                String isRoomManager = jsonObject.getString("isRoomManager");
                RoomManageMessage roomManageMessage = new RoomManageMessage(isRoomManager, currentDataBean.getNickName(), currentDataBean.getRcUserId(),"");
                roomManageMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(sp.getRoomId(),roomManageMessage);
                managerEntityArrayList.remove(currentDataBean);
                manageRecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFail(String msg) {
                System.out.println(msg);
            }
        });
    }


}
