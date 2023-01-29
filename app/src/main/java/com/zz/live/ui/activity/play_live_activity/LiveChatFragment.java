package com.zz.live.ui.activity.play_live_activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.enmu.RedType;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.start_live_activity.StartLiveActivity;
import com.zz.live.ui.adapter.JoinMessageAdapter;
import com.zz.live.ui.adapter.LiveChatAdapter;
import com.zz.live.ui.pop.ForbiddenSetManagerPop;
import com.zz.live.ui.pop.ForbiddenTimePop;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.ForbiddenMessage;
import com.zz.live.ui.rongyun.message.RoomManageMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imlib.model.Message;
import okhttp3.Headers;

public class LiveChatFragment extends BaseFragment implements BasePopupWindow.OnPopClickListener {
    @BindView(R.id.live_chat_recycle)
     RecyclerView liveChatRecycle;
    @BindView(R.id.un_read_tv)
     TextView un_read_tv;
    @BindView(R.id.join_chat_room_recycler)
    RecyclerView join_chat_room_recycler;
    public LiveChatAdapter liveChatAdapter;
    public ArrayList<LiveMessageModel> liveMessageModelArrayList = new ArrayList<>();
    private LiveFragment liveFragment;
    private int unReadCount=0;
    private LiveMessageModel currentLiveMessageModel;
    public JoinMessageAdapter joinMessageAdapter;
    public  ArrayList<LiveMessageModel>joinMessageList = new ArrayList<>();

    ForbiddenSetManagerPop forbiddenSetManagerPop;
    ForbiddenTimePop forbiddenTimePop;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();

    @OnClick({R.id.un_read_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.un_read_tv:
                liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);
                break;
                default:
                    break;
        }
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycle();
        initJoinRecycle();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_chat;
    }
    private void initJoinRecycle() {
        joinMessageAdapter = new JoinMessageAdapter(R.layout.live_exit_join_message_item,joinMessageList,this);
        join_chat_room_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        joinMessageAdapter.setAnimationEnable(true);
        joinMessageAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        joinMessageAdapter.setAnimationFirstOnly(false);
        join_chat_room_recycler.setAdapter(joinMessageAdapter);
    }

    private void initRecycle() {
        liveChatAdapter = new LiveChatAdapter(this, liveMessageModelArrayList);
        liveChatRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        liveChatRecycle.setAdapter(liveChatAdapter);

        liveChatRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isSlideToBottom(liveChatRecycle)) {
                    unReadCount = 0;
                    un_read_tv.setVisibility(View.GONE);
                }
            }
        });
        /**
         * 富文本按钮点击事件
         */
        liveChatAdapter.setOnClickSpanLintener(new LiveChatAdapter.OnClickSpanLintener() {
            @Override
            public void onClickSpanLintener(View view, int position) {
                LiveMessageModel liveMessageModel = liveMessageModelArrayList.get(position);
                long redId = liveMessageModel.getRedId();
                String objectName = liveMessageModel.getObjectName();
                if (objectName.equals(CommonStr.RED_MESSAGE)) {
                    int redType = liveMessageModel.getRedType();
                    RedType redTypeEnum = null;
                    switch (redType) {
                        case 1:
                            redTypeEnum = RedType.QY;
                            break;
                        case 2:
                            redTypeEnum = RedType.ZX;
                            break;
                        case 3:
                            redTypeEnum = RedType.PT;
                            break;
                        default:
                            break;
                    }
                    //点击趣约红包时,需要先判断是否有领取次数,没有次数时直接提示次数已用完
                    if (redTypeEnum == RedType.QY) {
/*                        boolean aBoolean = SharePreferencesUtil.getBoolean(MyApplication.getInstance(), CommonStr.HAVE_GET_RED_NUM, true);
                        if(aBoolean){
                            liveFragment.OpenPackPop(redId+"", currentLiveMessageModel.getRedStatus(),currentLiveMessageModel.getQbPrice(),redTypeEnum,liveFragment.ROOM_ID,currentLiveMessageModel.getFailInfo());
                        }else {
                            ToastUtils.showToast("趣约红包领取次数已用完");
                        }*/
//                        InviteAndMakeMoneyActivity.startAty(getContext(),liveFragment.ROOM_ID);
                    } else if (redTypeEnum == RedType.ZX) {
                        //专享

//                        ZhuangXiangRedActivity.startAty(getContext(),liveFragment.ROOM_ID);
                    } else {
                        //普通红包
//                   liveFragment.OpenPackPop(redId+"", currentLiveMessageModel.getRedStatus(),currentLiveMessageModel.getQbPrice(),redTypeEnum,liveFragment.ROOM_ID,currentLiveMessageModel.getFailInfo());
                    }
                    /*
                    点击跟投
                     */
                } else if (objectName.equals(CommonStr.SHARE_MESSAGE)) {
//                    new FollowBetPop(getContext(),liveFragment,currentLiveMessageModel);

                }
            }
        });
        /**
         * recycleView item点击事件(目前只有文本消息点击弹出禁言框)
         */
        liveChatAdapter.setMyOnItemClickLintener(new LiveChatAdapter.MyOnItemClickLintener() {
            @Override
            public void onItemClickListener(View view, int position) {
                currentLiveMessageModel = liveMessageModelArrayList.get(position);
                boolean isNormalUser = currentLiveMessageModel.getManagerType().equals("0");
                boolean isRoomManager = currentLiveMessageModel.getManagerType().equals("3");
                String objectName = currentLiveMessageModel.getObjectName();
                if(currentLiveMessageModel.getMessageDirection() == Message.MessageDirection.RECEIVE&&objectName.equals(CommonStr.TEXT_MESSAGE) ){
                    if(isNormalUser||isRoomManager){
                        initForbiddenPop();
                    }
                }

            }
        });
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
        forbiddenSetManagerPop.setOnPopClickListener(LiveChatFragment.this);
        forbiddenSetManagerPop.showAtLocation(liveChatRecycle, Gravity.BOTTOM,0,0);
        Utils.darkenBackground(getActivity(),0.7f);
    }


    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

        public void addItem(LiveMessageModel liveMessageModel){
        //自己发的消息,直接滚动到底部
        if(liveMessageModel.getMessageDirection() == Message.MessageDirection.SEND){
            liveMessageModelArrayList.add(liveMessageModel);
            liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);
        }else {
            //收到其他用户发的消息  判断列表当前时候在底部
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) liveChatRecycle.getLayoutManager();
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            //当前在底部时, 添加消息后再滚动到底部(并清空未读消息数)
            if(lastVisibleItemPosition==liveMessageModelArrayList.size()-1){
                //滚动到底部
    /*            unReadCount=0;
                un_read_tv.setVisibility(View.GONE);*/
                liveMessageModelArrayList.add(liveMessageModel);
                liveChatRecycle.scrollToPosition(liveMessageModelArrayList.size()-1);

            }else {
                //当前列表不在底部时, 显示未读消息
                unReadCount++;
                liveMessageModelArrayList.add(liveMessageModel);
                un_read_tv.setVisibility(View.VISIBLE);
                un_read_tv.setText("未读消息:"+unReadCount);
            }
        }
//        liveChatAdapter.notifyDataSetChanged();
            liveChatAdapter.notifyItemInserted(liveMessageModelArrayList.size()-1);
    }
    public void addJoinItem(LiveMessageModel liveMessageModel){
        if(joinMessageList.size()==0){
            LiveMessageModel liveMessageModelTest = new LiveMessageModel();
            liveMessageModelTest.setLevel("2");
            liveMessageModelTest.setUserName("test");
            liveMessageModelTest.setPortrait("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1819216937,2118754409&fm=26&gp=0.jpg");
            if(getActivity() instanceof StartLiveActivity){
                StartLiveActivity startLiveActivity = (StartLiveActivity) getActivity();
                startLiveActivity.assetsSvgaManage.startAnimator(liveMessageModelTest);
            }
        }
        joinMessageList.add(liveMessageModel);
        joinMessageAdapter.notifyItemInserted(joinMessageList.size()-1);
        if(join_chat_room_recycler!=null){
            join_chat_room_recycler.scrollToPosition(joinMessageList.size()-1);
        }
//        joinMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * pop点击事件
     * @param view
     */
    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            //禁言按钮(点击选择禁言时间)
            case R.id.forbidden_tv:
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

    /**
     * 设置房管
     */
    private void requestSetManage() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("anchorId", Utils.getUserInfoBean().getId());
        data.put("chatroomId",sp.getRoomId());
        data.put("image",currentLiveMessageModel.getPortrait());
        data.put("isRoomManager",forbiddenSetManagerPop.getIsRoomManager());
        data.put("nickName",currentLiveMessageModel.getUserName());
        data.put("platformCode",currentLiveMessageModel.getZkCode());
        data.put("rcUserId",currentLiveMessageModel.getRcUserId());
        HttpApiUtils.request(getActivity(), LiveChatFragment.this, RequestUtils.SET_MANAGE, data, new HttpApiUtils.OnRequestLintener() {
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
        HttpApiUtils.request(getActivity(), LiveChatFragment.this, RequestUtils.FORBIDDEN, data, new HttpApiUtils.OnRequestLintener() {
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

    /**
     * 禁言时间选择pop
     */
    private void initForbiddenTimePop() {
        forbiddenTimePop = new ForbiddenTimePop(getContext());
        forbiddenTimePop.setOnPopClickListener(LiveChatFragment.this);
        forbiddenTimePop.showAtLocation(liveChatRecycle, Gravity.BOTTOM,0,0);
        Utils.darkenBackground(getActivity(),0.7f);
    }
}
