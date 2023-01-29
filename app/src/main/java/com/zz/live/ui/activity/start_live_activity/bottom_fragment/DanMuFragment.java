package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.LiveChatAdapter;
import com.zz.live.ui.pop.ForbiddenSetManagerPop;
import com.zz.live.ui.pop.ForbiddenTimePop;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.ForbiddenMessage;
import com.zz.live.ui.rongyun.message.RoomManageMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class DanMuFragment extends BaseFragment implements LiveChatAdapter.MyOnItemClickLintener, BasePopupWindow.OnPopClickListener {

    @BindView(R.id.danmu_recycler)
    RecyclerView danmu_recycler;
    @BindView(R.id.un_read_tv)
    TextView un_read_tv;
    LiveChatAdapter liveChatAdapter;
    ArrayList<LiveMessageModel>messageHistoryList = new ArrayList<>();
    ArrayList<LiveMessageModel>liveMessageModelArrayList = new ArrayList<>();
    private int unReadCount=0;
    int position;
    private LiveMessageModel currentLiveMessageModel;
    private ForbiddenTimePop forbiddenTimePop;
    private SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private ForbiddenSetManagerPop forbiddenSetManagerPop;

    public DanMuFragment(ArrayList<LiveMessageModel> liveMessageModelArrayList) {
        messageHistoryList = liveMessageModelArrayList;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_dan_mu;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        getArgumentsData();
        initRecycler();
    }

    private void getArgumentsData() {
        position=getArguments().getInt(CommonStr.POSITION);
    }

    private void initRecycler() {
        liveChatAdapter = new LiveChatAdapter(this,liveMessageModelArrayList) ;
        danmu_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        danmu_recycler.setAdapter(liveChatAdapter);
        danmu_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isSlideToBottom(danmu_recycler)){
                    unReadCount=0;
                    un_read_tv.setVisibility(View.GONE);
                }
            }
        });

            for (int i = 0; i < messageHistoryList.size(); i++) {
                LiveMessageModel liveMessageModel = messageHistoryList.get(i);
                String objectName = liveMessageModel.getObjectName();
//                if(objectName.equals(CommonStr.TEXT_MESSAGE)){
                    addItem(liveMessageModel);
//                }
            }

        liveChatAdapter.setMyOnItemClickLintener(this);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTextMessage(LiveMessageModel liveMessageModel){
        addItem(liveMessageModel);
    }

    public static DanMuFragment newInstance(int position,ArrayList<LiveMessageModel> liveMessageModelArrayList){
        DanMuFragment danMuFragment = new DanMuFragment(liveMessageModelArrayList);
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        danMuFragment.setArguments(bundle);
        return danMuFragment;
    }

    public void addItem(LiveMessageModel liveMessageModel){
        boolean b = danmu_recycler.canScrollVertically(1);//是否能向上滚动，false表示已经滚动到底部
        if(!b){
            if(position==1){
                if(liveMessageModel.getObjectName().equals(CommonStr.TEXT_MESSAGE)){
                    liveMessageModelArrayList.add(liveMessageModel);
                }
            }else {
                if(liveMessageModel.getObjectName().equals(CommonStr.GIFT_MESSAGE)){
                    liveMessageModelArrayList.add(liveMessageModel);
                }
            }
            danmu_recycler.scrollToPosition(liveMessageModelArrayList.size()-1);
        }else {
            //当前列表不在底部时, 显示未读消息
            unReadCount++;
            if(position==1){
                if(liveMessageModel.getObjectName().equals(CommonStr.TEXT_MESSAGE)){
                    liveMessageModelArrayList.add(liveMessageModel);
                }
            }else {
                if(liveMessageModel.getObjectName().equals(CommonStr.GIFT_MESSAGE)){
                    liveMessageModelArrayList.add(liveMessageModel);
                }
            }
            un_read_tv.setVisibility(View.VISIBLE);
            un_read_tv.setText("未读消息:"+unReadCount);
        }
    }
    @OnClick({R.id.un_read_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.un_read_tv:
                danmu_recycler.scrollToPosition(liveMessageModelArrayList.size()-1);
                break;
                default:
                    break;
        }
    }
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * recycleView 点击事件
     * @param view
     * @param position
     */
    @Override
    public void onItemClickListener(View view, int position) {
        currentLiveMessageModel = liveMessageModelArrayList.get(position);
        switch (view.getId()){
            case R.id.text_name_content_tv:
                if(!currentLiveMessageModel.getManagerType().equals("2")){
                    initForbiddenPop();
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 禁言pop
     * @param
     */
    private void initForbiddenPop() {
        String managerType = currentLiveMessageModel.getManagerType();
        int isRoomManager;
        if(managerType.equals("3")){
            isRoomManager=0;
        }else {
            isRoomManager=1;
        }
        forbiddenSetManagerPop = new ForbiddenSetManagerPop(getContext(),currentLiveMessageModel, isRoomManager);
        forbiddenSetManagerPop.setOnPopClickListener(this);
        forbiddenSetManagerPop.showAtLocation(un_read_tv, Gravity.BOTTOM,0,0);
        Utils.darkenBackground(getActivity(),0.7f);
    }
    /**
     * 禁言时间选择pop
     */
    private void initForbiddenTimePop() {
        forbiddenTimePop = new ForbiddenTimePop(getContext());
        forbiddenTimePop.setOnPopClickListener(this);
        forbiddenTimePop.showAtLocation(un_read_tv, Gravity.BOTTOM,0,0);
        Utils.darkenBackground(getActivity(),0.7f);
    }
    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            //禁言确定按钮(点击选择禁言时间)
            case R.id.forbidden_sure_tv:
                if(forbiddenSetManagerPop!=null){
                    forbiddenSetManagerPop.dismiss();
                }
                initForbiddenTimePop();
                break;
            //设置房管
            case R.id.set_manager_tv:
                requestSetManage();
                break;
            case R.id.one_hour_tv:
                requestForbidden(1);
                break;
            case R.id.six_hour_tv:
                requestForbidden(2);
                break;
            case R.id.twelve_tv:
                requestForbidden(3);
                break;
            case R.id.one_day_tv:
                requestForbidden(4);
                break;
            case R.id.one_week_tv:
                requestForbidden(5);
                break;
            case R.id.one_month_tv:
                requestForbidden(6);
                break;
            default:
                break;
        }
    }
    private void requestSetManage() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorId", Utils.getUserInfoBean().getId());
        data.put("chatroomId",sp.getRoomId());
        data.put("image",currentLiveMessageModel.getPortrait());
        data.put("isRoomManager",forbiddenSetManagerPop.getIsRoomManager());
        data.put("nickName",currentLiveMessageModel.getUserName());
        data.put("platformCode",currentLiveMessageModel.getZkCode());
        data.put("rcUserId",currentLiveMessageModel.getRcUserId());
        HttpApiUtils.request(getActivity(), this, RequestUtils.SET_MANAGE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                if(forbiddenSetManagerPop.getIsRoomManager()==1){
                    showtoast("房管设置成功");
                    /*
                    设置列表中该用户的所有manageType
                     */
                    for (int i = 0; i < liveMessageModelArrayList.size(); i++) {
                        LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(i);
                        if(liveMessageModel.getRcUserId().equals(currentLiveMessageModel.getRcUserId())){
                            liveMessageModel.setManagerType("3");
                        }
                    }
                }else {
                    showtoast("房管取消成功");
                    for (int i = 0; i < liveMessageModelArrayList.size(); i++) {
                        LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(i);
                        if(liveMessageModel.getRcUserId().equals(currentLiveMessageModel.getRcUserId())){
                            liveMessageModel.setManagerType("0");
                        }
                    }
                }
                liveChatAdapter.notifyDataSetChanged();
                forbiddenSetManagerPop.dismiss();
                JSONObject jsonObject= JSONObject.parseObject(result).getJSONObject("data");
                String isRoomManager = jsonObject.getString("isRoomManager");
                RoomManageMessage roomManageMessage = new RoomManageMessage(isRoomManager, currentLiveMessageModel.getUserName(), currentLiveMessageModel.getRcUserId(),"");
                roomManageMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(sp.getRoomId(),roomManageMessage);
            }
            @Override
            public void onFail(String msg) {
            }
        });
    }
    private void requestForbidden(int gagType) {
        JSONObject userInfoJson = JSONObject.parseObject(currentLiveMessageModel.getUserInfoJson());
        HashMap<String, Object> data = new HashMap<>();
        data.put("chatRoomId",sp.getRoomId());
        data.put("gagType",gagType);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("image", userInfoJson.getString("portrait"));
        jsonObject.put("nickName",userInfoJson.getString("name"));
        jsonObject.put("remark",currentLiveMessageModel.getTextContent());
        jsonObject.put("userSpeakContent",currentLiveMessageModel.getTextContent());
        jsonObject.put("rongId",currentLiveMessageModel.getRcUserId());
        jsonObject.put("platformCode",currentLiveMessageModel.getZkCode());
        jsonObject.put("userName",userInfoJson.getString("platUserId"));
/*        jsonObject.put("anchorNickName",Utils.getUserInfoBean().getNickname());
        jsonObject.put("roomManageUserId",Utils.getUserInfoBean().getId());*/
        jsonArray.add(jsonObject);
        data.put("members",jsonArray);
        HttpApiUtils.request(getActivity(), DanMuFragment.this, RequestUtils.FORBIDDEN, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                String msg = JSONObject.parseObject(result).getString("msg");
                showtoast(msg);
                forbiddenTimePop.dismiss();
                ForbiddenMessage forbiddenMessage = new ForbiddenMessage("1", userInfoJson.getString("name"), "");
                forbiddenMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(sp.getRoomId(),forbiddenMessage);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
}
