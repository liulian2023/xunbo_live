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
import com.zz.live.bean.PrivateMessageEntity;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.message_activity.MessageDetailsActivity;
import com.zz.live.ui.adapter.CommonAdapter;
import com.zz.live.ui.adapter.PrivateMessageAdapter;
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

public class PrivateMessageFragment extends BaseFragment {
    @BindView(R.id.private_message_refresh)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.private_message_recycler)
    RecyclerView private_message_recycler;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    public  PrivateMessageAdapter privateMessageAdapter;
    public ArrayList<PrivateMessageEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    private int current=1;

    @Override
    protected void init(Bundle savedInstanceState) {
        initRefresh();
        initRecycler();
        requestMessageList(false,false);
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        requestMessageList(false,false);
    }

    private void requestMessageList(boolean isRefresh, boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        HttpApiUtils.wwwShowLoadRequest(getActivity(), null, RequestUtils.PRIVATE_MESSAGE_LIST, data, loading_linear,error_linear,reload_tv,refreshLayout,isLoadMore,isRefresh,new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                PrivateMessageEntity privateMessageEntity = JSONObject.parseObject(result, PrivateMessageEntity.class);
                List<PrivateMessageEntity.DataBean.RecordsBean> recordsBeans = privateMessageEntity.getData().getRecords();
                RefreshUtils.succse(current,refreshLayout,loading_linear,nodata_linear,recordsBeans.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeans);
                privateMessageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refreshLayout);
            }
        });
    }

    private void initRecycler() {
        privateMessageAdapter = new PrivateMessageAdapter(recordsBeanArrayList,getContext());
        private_message_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        private_message_recycler.setAdapter(privateMessageAdapter);
        //设置WeSwipe。(侧滑删除)
        WeSwipe.attach(private_message_recycler);
        privateMessageAdapter.setOnDeleteClickListener(new CommonAdapter.OnDeleteClickListener() {
            @Override
            public void onDelete(int position) {
                PrivateMessageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
                String id = recordsBean.getId();
                HttpApiImpl.getInstance()
                        .deletePrivateMessage(id)
                        .compose(RxTransformerUtils.io_main())
                        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) getContext())))
                        .subscribe(new BaseStringObserver<ResponseBody>() {
                            @Override
                            public void onSuccess(String result) {
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                showtoast(jsonObject.getString("msg"));
                                privateMessageAdapter.removeDataByPosition(position);
                                if(recordsBeanArrayList.size()==0){
                                    nodata_linear.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onFail(String msg) {

                            }

                        });

            }
        });
        privateMessageAdapter.setOnItemClickListener(new CommonAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                /*
                getLayoutPosition 和 getAdapterPosition 通常情况下是一样的，只有当 Adapter 里面的内容改变了，
                而 Layout 还没来得及绘制的这段时间之内才有可能不一样，这个时间小于16ms

               延时200ms 避免侧滑删除后notifyItemRemoved()导致位置发生变化,未来得及刷新到布局中就点击另一个item时发生的下标越界问题
                 */
                if(position>recordsBeanArrayList.size()){
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            skipMessageDetails(position);
                        }
                    },200);
                }else {
                    skipMessageDetails(position);
                }


            }
        });
    }

    private void skipMessageDetails(int position) {
        PrivateMessageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(position);
        MessageDetailsActivity.startAty(getActivity(), recordsBean.getContent(), recordsBean.getTitle(), recordsBean.getId(), false, MessageDetailsActivity.PRIVATE_MESSAGE_REQUEST_CODE);
    }

    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(getContext(), refreshLayout, new RefreshUtils.OnRefreshLoadMoreLintener() {
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

    /*    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode== MessageDetailsAcrivity.SYSTEM_MESSAGE_REQUEST_CODE){
                if(resultCode==RESULT_OK){
                    String id = data.getStringExtra("id");
                    for (int i = 0; i < recordsBeanArrayList.size(); i++) {

                    }
                    //更改了消息已读状态时,才通知上级页面重新请求消息列表
                    getActivity().setResult(RESULT_OK);
                }
            }
        }*/
    @Override
    public int getLayoutId() {
        return R.layout.fragment_private_message;
    }
}
