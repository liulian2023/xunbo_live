package com.zz.live.ui.fragment.message_fragment;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.SystemMessageListEntity;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.message_activity.FollowMessageActivity;
import com.zz.live.ui.activity.message_activity.SystemMessageActivity;
import com.zz.live.ui.adapter.CommonAdapter;
import com.zz.live.ui.adapter.SystemMessageListAdapter;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import cn.we.swipe.helper.WeSwipe;
import okhttp3.Headers;
import okhttp3.ResponseBody;


public class SystemMessageFragment extends BaseFragment {
    @BindView(R.id.system_message_list_refresh)
    public SmartRefreshLayout system_message_recfresh;
    @BindView(R.id.system_message_list_recycler)
    RecyclerView system_message_recycler;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    SystemMessageListAdapter systemMessageListAdapter;
    ArrayList<SystemMessageListEntity.DataBean> systemMessageListEntiryArrayList = new ArrayList<>();
    int current=1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
        requestMessageList(false,false);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_system_message;
    }
    private void initRecycler() {
        systemMessageListAdapter = new SystemMessageListAdapter(systemMessageListEntiryArrayList);
        system_message_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        system_message_recycler.setAdapter(systemMessageListAdapter);
        //设置WeSwipe。(侧滑删除)
        WeSwipe.attach(system_message_recycler);
        systemMessageListAdapter.setOnDeleteClickListener(new CommonAdapter.OnDeleteClickListener() {
            @Override
            public void onDelete(int position) {
                SystemMessageListEntity.DataBean dataBean = systemMessageListEntiryArrayList.get(position);
                int type = dataBean.getType();
                HttpApiImpl.getInstance()
                        .deleteSystemMessage(type)
                        .compose(RxTransformerUtils.io_main())
                        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) getContext())))
                        .subscribe(new BaseStringObserver<ResponseBody>() {
                            @Override
                            public void onSuccess(String result) {
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                showtoast(jsonObject.getString("msg"));
                                systemMessageListAdapter.removeDataByPosition(position);
                                if(systemMessageListEntiryArrayList.size()==0){
                                    nodata_linear.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onFail(String msg) {

                            }

                        });

            }
        });
        systemMessageListAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position>systemMessageListEntiryArrayList.size()){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            skipMessgeListActivity(position);
                        }
                    },200);
                }else {
                    skipMessgeListActivity(position);
                }

            }
        });
    }

    private void skipMessgeListActivity(int position) {
        SystemMessageListEntity.DataBean dataBean = systemMessageListEntiryArrayList.get(position);
        //type: 1 系统消息 2 关注消息
        int type = dataBean.getType();
        switch (type) {
            case 1:
                SystemMessageActivity.startAty(getActivity());
                break;
            case 2:
                FollowMessageActivity.startAty(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestMessageList(false,false);
    }

    private void requestMessageList(boolean isRefresh, boolean isLoadMore) {
        HttpApiUtils.wwwShowLoadRequest(getActivity(), this, RequestUtils.SYSTEM_MESSAGE, new HashMap<String, Object>(), loading_linear, error_linear, reload_tv, system_message_recfresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                SystemMessageListEntity systemMessageListEntity = JSONObject.parseObject(result, SystemMessageListEntity.class);
                List<SystemMessageListEntity.DataBean> data = systemMessageListEntity.getData();
                int size = data.size();
                RefreshUtils.succse(current,system_message_recfresh,loading_linear,nodata_linear,size,isLoadMore,isRefresh,systemMessageListEntiryArrayList);
                systemMessageListEntiryArrayList.addAll(data);
                systemMessageListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,system_message_recfresh);
            }
        });
    }

    @Override
    protected void initrefresh() {
        super.initrefresh();
        RefreshUtils.initRefreshLoadMore(getContext(), system_message_recfresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestMessageList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestMessageList(false,true);
            }
        });
    }
}
