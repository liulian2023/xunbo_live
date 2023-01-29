package com.zz.live.ui.activity.play_live_activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.opensource.svgaplayer.SVGAImageView;
import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.ChatUserEntity;
import com.zz.live.bean.LiveEntity;
import com.zz.live.bean.LiveGiftSVGAEntity;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.bean.UserMoneyEntity;
import com.zz.live.bean.evenBus.HideCoverEven;
import com.zz.live.bean.evenBus.LiveEvenEntity;
import com.zz.live.bean.evenBus.PlayFailEven;
import com.zz.live.bean.evenBus.ShowCoverEven;
import com.zz.live.myView.gift.GiftSendModel;
import com.zz.live.myView.gift.GiftView;
import com.zz.live.myView.gift.GiftSvgaManage;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.ChatUserAdapter;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.LiveExitAndJoinMessage;
import com.zz.live.ui.rongyun.message.LiveGiftMessage;
import com.zz.live.ui.rongyun.message.LiveTextMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.DrawerLeftEdgeSize;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.OnlineNumUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesStr;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.MessageContent;
import okhttp3.Headers;
import pl.droidsonroids.gif.GifImageView;
import static android.view.View.GONE;

public class LiveFragment extends BaseFragment implements Handler.Callback {
    public static String TAG="LiveFragment";
    @BindView(R.id.live_drawerLayout)
    DrawerLayout live_drawerLayout;
    @BindView(R.id.live_cover_iv)
    ImageView live_cover_iv;
    @BindView(R.id.live_loading_iv)
    GifImageView live_loading_iv;
    @BindView(R.id.play_fail_linear)
    LinearLayout play_fail_linear;
    @BindView(R.id.clear_screen_iv)
    ImageView clear_screen_iv;
    @BindView(R.id.linearLayout7)
    public LinearLayout linearLayout7;
//    @BindView(R.id.live_edit_panel)
    EditPanel editPanel;
    @BindView(R.id.drawwe_linear)
    ConstraintLayout drawwe_linear;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.giftView)
    public GiftView giftView;
    @BindView(R.id.money_tv)
    TextView money_tv;
    @BindView(R.id.svga_imageview)
    public SVGAImageView svgaImageView;
    @BindView(R.id.recy_renshu)
    RecyclerView recy_renshu;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private LiveChatFragment liveChatFragment;
    private GiftSvgaManage giftSvgaManage;
    private UserInfoEntity.DataBean userDataBean;
    private Handler handler= new Handler(this);
    private int totalMemberCount;
    private LiveEntity.DataBean.RecordsBean recordsBean;
    public int visibleCount =0;
    public String roomId;
    ChatUserAdapter chatUserAdapter;
    ArrayList<ChatUserEntity>chatUserEntityArrayList= new ArrayList<>();
    ArrayList<Integer>imageIdlist = new ArrayList<>();
    private PublishSubject onlineNumObservable=PublishSubject.create();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_live;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        getArgumentsData();
        userDataBean=Utils.getUserInfoBean();
        /**
         * 初始化 drawlayout
         */
        GlideLoadViewUtil.loadBlurView(getContext(), getArguments().getString("coverUrl"),live_cover_iv);
        live_loading_iv.setVisibility(View.VISIBLE);
        live_cover_iv.setVisibility(View.VISIBLE);
        live_loading_iv.setVisibility(View.VISIBLE);
        live_drawerLayout.setScrimColor(0x00000000);
        DrawerLeftEdgeSize.setRightEdge(getActivity(),live_drawerLayout, 1f);
        live_drawerLayout.openDrawer(GravityCompat.END);
        OnlineNumUtil onlineNumUtil = OnlineNumUtil.newInstance();
        onlineNumUtil.initOnlineRecycler(getContext(),chatUserAdapter,recy_renshu,chatUserEntityArrayList);
        initOnlineRecycler();
        /**
         * 订阅人数变化
         */
        observableOnlineNum();
    }

    private void initOnlineRecycler() {
        imageIdlist.add(R.drawable.pic_1);
        imageIdlist.add(R.drawable.pic_2);
        imageIdlist.add(R.drawable.pic_3);
        imageIdlist.add(R.drawable.pic_4);
        imageIdlist.add(R.drawable.pic_5);
        imageIdlist.add(R.drawable.pic_6);
        imageIdlist.add(R.drawable.pic_7);
        imageIdlist.add(R.drawable.pic_8);
        imageIdlist.add(R.drawable.pic_9);
        imageIdlist.add(R.drawable.pic_10);
        imageIdlist.add(R.drawable.pic_11);
        imageIdlist.add(R.drawable.pic_12);
        imageIdlist.add(R.drawable.pic_13);
        imageIdlist.add(R.drawable.pic_14);
        imageIdlist.add(R.drawable.pic_15);
        imageIdlist.add(R.drawable.pic_16);
        imageIdlist.add(R.drawable.pic_17);
        imageIdlist.add(R.drawable.pic_18);
        imageIdlist.add(R.drawable.pic_19);
        imageIdlist.add(R.drawable.pic_20);
        chatUserAdapter = new ChatUserAdapter(R.layout.item_chatuser,chatUserEntityArrayList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_renshu.setLayoutManager(layoutManager);
        recy_renshu.setAdapter(chatUserAdapter);
/*        for (int i = 0; i < imageIdlist.size(); i++) {
            chatUserEntityArrayList.add(new ChatUserEntity(imageIdlist.get(i)));
        }
        chatUserAdapter.notifyDataSetChanged();*/
    }
    private void observableOnlineNum() {
        onlineNumObservable
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if(integer<20){
                            for (int i = 0; i <integer ; i++) {
                                int nextInt = new Random().nextInt(20);
                                if(chatUserEntityArrayList.size()<integer){
                                    chatUserEntityArrayList.add(new ChatUserEntity(imageIdlist.get(nextInt)));
                                }
                            }
                        }else {
                            for (int i = 0; i <40 ; i++) {
                                int nextInt = new Random().nextInt(20);
                                if(chatUserEntityArrayList.size()<integer){
                                    chatUserEntityArrayList.add(new ChatUserEntity(imageIdlist.get(nextInt)));
                                }
                            }
                        }
                        chatUserAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getArgumentsData() {
        recordsBean = (LiveEntity.DataBean.RecordsBean) getArguments().getSerializable("recordsBean");
    }
    public static LiveFragment newInstence(int position, LiveEntity.DataBean.RecordsBean recordsBean){
        LiveFragment liveFragment = new LiveFragment();
        Bundle newBundle = new Bundle();
        newBundle.putInt(CommonStr.POSITION,position);
        newBundle.putSerializable("recordsBean",recordsBean);
        liveFragment.setArguments(newBundle);
        return  liveFragment;
    }

    /**
     * 隐藏封面图片(LivePlayerFragment 在开始播放时发送此通知)
     * 封面显示过程 : viewPager选中>>>>显示所有fragment中的封面>>>>>视频加载成功>>>>>隐藏当前fragment的封面>>>>>viewPager选中  .........
     * @param hideCoverEven
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void hideCoverIv(HideCoverEven hideCoverEven){
        if(hideCoverEven.isHideCover()){
            //每次收到广播只隐藏当前fragment中封面
            if(getArguments().getInt(CommonStr.POSITION)== hideCoverEven.getPosition()){
                if(live_cover_iv!=null){
                    live_cover_iv.setVisibility(GONE);
                    live_loading_iv.setVisibility(GONE);
                }
            }
        }
    }

    /**
     * 显示封面图片, 隐藏失败信息(MainDialogFragment 中viewPager选中时发送此通知) ----- 相当于初始化操作, 保证每次滑动到页面时,该页面的各种提示ui都是默认的样式
     * 封面显示过程 : viewPager默认选中>>>>显示所有fragment中的封面>>>>>视频加载成功>>>>>隐藏当前fragment的封面>>>>>上下滑动>>viewPager选中  .........
     * @param showCoverEven
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void showCoverIv(ShowCoverEven showCoverEven){
        if(live_cover_iv!=null){
            GlideLoadViewUtil.loadBlurView(getContext(),recordsBean.getCover(),live_cover_iv);
            live_cover_iv.setVisibility(View.VISIBLE);
            live_loading_iv.setVisibility(View.VISIBLE);
        }
        if(play_fail_linear!=null){
            play_fail_linear.setVisibility(GONE);
        }
    }

    /**
     * 播放失败 显示失败信息 隐藏加载图,(LivePlayerFragment收到网络断连监听时发送此通知)
     * 并显示封面(已下播图片和垫片推流图片重叠)
     * @param playFailEven
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void playFail(PlayFailEven playFailEven){
        if(play_fail_linear!=null){
            play_fail_linear.setVisibility(View.VISIBLE);
        }

        if(live_cover_iv!=null){
            GlideLoadViewUtil.loadBlurView(getContext(),recordsBean.getCover(),live_cover_iv);
            live_cover_iv.setVisibility(View.VISIBLE);
        }
        if(live_loading_iv!=null){
            live_loading_iv.setVisibility(GONE);
        }
    }
    /**
     * 页面可见
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        System.out.println("visivisi");
        visibleCount++;
        if(visibleCount !=1){
            return;
        }
        initLiveChatFragment();
        initSvgaImageView();
        initGiftView();
        requestMoney();
        initRongYun();
        getFocus();
        //发送更换播放源的通知
        EventBus.getDefault().post(new LiveEvenEntity(recordsBean.getPlayUrl(),recordsBean.getCover(),getArguments().getInt(CommonStr.POSITION)));
        //是否显示右滑清屏(安装一次app只显示一次,在点击clear_screen_iv将缓存设为false)
        boolean showClearScreen = SharePreferencesUtil.getBoolean(getContext(), SharePreferencesStr.SHOW_CLEAR_SCREEN, true);
        if(showClearScreen){
            clear_screen_iv.setVisibility(View.VISIBLE);
        }else {
            clear_screen_iv.setVisibility(GONE);
        }

    }
    private void initRongYun() {
        roomId=recordsBean.getRoomId();
        editPanel = getRootView().findViewById(R.id.live_edit_panel);
        editPanel.setLiveFragment(this);
        editPanel.setRoomId(roomId);
        RongLibUtils.addEventHandler(handler);
        joinChatRoom();

    }

    private void requestMoney() {
        HttpApiUtils.wwwNormalRequest(getActivity(),this, RequestUtils.USER_MONEY, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                UserMoneyEntity userMoneyEntity = JSONObject.parseObject(result, UserMoneyEntity.class);
                String balance = userMoneyEntity.getData().getBalance();
                money_tv.setText(balance);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void joinChatRoom() {
        String joinChatroomId = SharePreferencesUtil.getString(getContext(), CommonStr.JOIN_CHATROOM_ID, "");
        LiveExitAndJoinMessage liveExitAndJoinMessage = new LiveExitAndJoinMessage(userDataBean.getNickname(), "1");
        liveExitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
        if (StringMyUtil.isNotEmpty(joinChatroomId)&&!joinChatroomId.equals(roomId)) {
            RongLibUtils.sendMessage(joinChatroomId, liveExitAndJoinMessage);
            RongLibUtils.quitChatRoom(joinChatroomId, new RongIMClient.OperationCallback() {
                @Override
                public void onSuccess() {
                    Log.e(TAG, " rongLog onSuccess:  退出聊天室成功 + roomId: " + joinChatroomId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e(TAG, "rongLog  onError:  退出聊天室失败 + roomId: " + joinChatroomId);
                }
            });
        }
        ArrayList<LiveMessageModel> liveMessageModelArrayList = liveChatFragment.liveMessageModelArrayList;
        liveMessageModelArrayList.clear();
        liveChatFragment.liveChatAdapter.notifyDataSetChanged();

                /*
                请求直播间公告
                 */

        RongLibUtils.joinChatRoom(-1, roomId, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                /*
                加入聊天室成功
                 */
                Log.e(TAG, "加入聊天室成功" );
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.JOIN_CHATROOM_ID, roomId);
//            发送一条进入直播间的message
                LiveExitAndJoinMessage msgContent = new LiveExitAndJoinMessage(userDataBean.getNickname(), "0");
                msgContent.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(roomId, msgContent);
//             获取聊天室人数
                getChatRoomNum();

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                //加入聊天室失败
                Log.e(TAG, "加入聊天室失败" );
            }
        });
    }
    /*
融云聊天室在线人数
*/
    private void getChatRoomNum() {
        RongIMClient.getInstance().getChatRoomInfo(roomId, 20, ChatRoomInfo.ChatRoomMemberOrder.RC_CHAT_ROOM_MEMBER_ASC, new RongIMClient.ResultCallback<ChatRoomInfo>() {
            @Override
            public void onSuccess(ChatRoomInfo chatRoomInfo) {
                //真实在线人数
                totalMemberCount = chatRoomInfo.getTotalMemberCount();
                //机器人人数
                if(tv_num!=null){
                    tv_num.setText(totalMemberCount+"人");
                }
                onlineNumObservable.onNext(totalMemberCount);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    private void initGiftView() {
        giftView.setViewCount(3);
        giftView.init();
    }
    private void initSvgaImageView() {
        giftSvgaManage = new GiftSvgaManage(getContext(),svgaImageView);
    }
    private void initLiveChatFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        liveChatFragment= (LiveChatFragment) fragmentManager.findFragmentById(R.id.live_chat_fragment);
    }
    /**
     * 消息展示处理
     *
     * @param message
     * @return
     */
    @Override
    public boolean handleMessage(Message message) {
        if(message.obj instanceof io.rong.imlib.model.Message ){
            io.rong.imlib.model.Message obj = (io.rong.imlib.model.Message) message.obj;
            String targetId = obj.getTargetId();//消息是发往哪个直播间的
            //过滤不是本直播间的消息
            System.out.println("targetId  ="+targetId+"  roomId  ="+roomId);
            if(targetId.equals(roomId)){
                MessageContent content = obj.getContent();
                switch (message.what) {
                    //发送和接受消息
                    case RongLibUtils.MESSAGE_ARRIVED:
                    case RongLibUtils.MESSAGE_SENT:
                        /*普通文本消息*/
                        LiveMessageModel messageModel = new LiveMessageModel();
                        if (content instanceof LiveTextMessage) {
                            LiveTextMessage liveTextMessage = (LiveTextMessage) content;
                            Utils.  initUserInfo(messageModel, liveTextMessage.getUserInfoJson());
                            messageModel.setObjectName(CommonStr.TEXT_MESSAGE);
                            messageModel.setTextContent(liveTextMessage.getContent());
                            liveChatFragment.addItem(messageModel);

                            /*
                                跟投消息*/

                        } /*else if (content instanceof LiveShareBetMessage) {
                            LiveShareBetMessage liveShareBetMessage = (LiveShareBetMessage) content;
                            messageModel.setObjectName(CommonStr.SHARE_MESSAGE);
                            messageModel.setLevel(liveShareBetMessage.getPointGrade());
                            messageModel.setUserName(liveShareBetMessage.getNickname());
                            String betMoney = liveShareBetMessage.getLotmoney();
                            List<String> amountAmountList = Arrays.asList(betMoney.split(","));
                            messageModel.setBetAmount((Integer.parseInt(amountAmountList.get(0))*amountAmountList.size())+"");
                            messageModel.setTypeName(liveShareBetMessage.getTypename());
                            messageModel.setBetQiShu(liveShareBetMessage.getLotteryqishu());
                            messageModel.setBetName(liveShareBetMessage.getName());
                            messageModel.setBetGroupName(liveShareBetMessage.getGroupname());
                            messageModel.setType_id(liveShareBetMessage.getType_id());
                            messageModel.setGame(liveShareBetMessage.getGame());
                            messageModel.setReluId(liveShareBetMessage.getRule_id());
                            liveChatFragment.addItem(messageModel);

                            *//*   红包消息*//*

                        } else if (content instanceof LiveRedMessage) {
                            LiveRedMessage liveRedMessage = (LiveRedMessage) content;
                            int redType = liveRedMessage.getRedType();
                            //3的用户信息放在 userInfoJson中  1 2 的用户信息在qpUserName中
                            if (redType == 3) {
                                Utils.      initUserInfo(messageModel, liveRedMessage.getUserInfoJson());
                            } else {
                                messageModel.setUserName(liveRedMessage.getQbUserName());
                            }
                            messageModel.setObjectName(CommonStr.RED_MESSAGE);
                            messageModel.setRedType(redType);
                            messageModel.setRedPrice(liveRedMessage.getRedPrice());
                            messageModel.setRedId(Integer.parseInt(liveRedMessage.getRedId()));
                            liveChatFragment.addItem(messageModel);

                            *//*         礼物消息*//*

                        }*/
                        else if (content instanceof LiveGiftMessage) {
//                            添加到聊天列表  开始   ---------------------
                            LiveGiftMessage liveGiftMessage = (LiveGiftMessage) content;
                            String userInfoJson = liveGiftMessage.getUserInfoJson();
                            Utils. initUserInfo(messageModel, userInfoJson);
                            messageModel.setObjectName(CommonStr.GIFT_MESSAGE);
                            messageModel.setGiftId(liveGiftMessage.getGiftId());
                            String giftNum = liveGiftMessage.getGiftNum();
                            messageModel.setGiftNum(giftNum);
                            messageModel.setGiftName(liveGiftMessage.getGiftName());
                            String giftPrice = liveGiftMessage.getGiftPrice();
                            int singlePrice = Integer.parseInt(giftPrice);
                            int oldPrice = singlePrice * Integer.parseInt(giftNum);
                            liveChatFragment.addItem(messageModel);
                            String toString = money_tv.getText().toString();
                            double oldNum = Double.parseDouble(toString.equals("- - -")?"0":toString);
                            money_tv.setText((oldNum+oldPrice)+"");
//                            添加到聊天列表  结束   ---------------------
//                                    礼物特效播放   开始  -----------------------
                            String giftUrl = liveGiftMessage.getGiftUrl();//礼物特效地址
                            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);//用户信息
                            if (StringMyUtil.isEmptyString(giftUrl)) {//没有礼物特效地址,表示为小礼物. 添加到聊天室上方播放飞入特效
                                int giftCount = 0;
                                try {
                                    giftCount = Integer.parseInt(giftNum);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                GiftSendModel giftSendModel = new GiftSendModel(giftCount);
                                giftSendModel.setGiftRes(liveGiftMessage.getGiftIcon());
                                giftSendModel.setNickname(jsonObject.getString("name"));
                                giftSendModel.setSig("送出" + liveGiftMessage.getGiftName());
                                String portrait = jsonObject.getString("portrait");
                                giftSendModel.setUserAvatarRes(portrait);
                                giftView.addGift(giftSendModel);

                            }else {//有礼物特效表示为大礼物, 播放svga动画(拦截掉自己发的大礼物消息,因为在调用发送接口成功的时候已经播放了)
                                if(!jsonObject.getString("userId").equals(userDataBean.getId())){
                                    LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.getFirstImgurl(MyApplication.getInstance())+giftUrl, null);
                                    giftSvgaManage.startAnimator(liveGiftSVGAEntity);
                                }
                            }
//                            礼物特效播放   结束  -----------------------
                            //进入/退出直播间消息
                        } else if (content instanceof LiveExitAndJoinMessage) {
                            LiveExitAndJoinMessage liveExitAndJoinMessage = (LiveExitAndJoinMessage) content;
                            messageModel.setObjectName(CommonStr.EXIT_JOIN_MESSAGE);
                            Utils.initUserInfo(messageModel,liveExitAndJoinMessage.getUserInfoJson());
                            String userName = liveExitAndJoinMessage.getUserName();
                            messageModel.setUserName(userName);
                            String status = liveExitAndJoinMessage.getStatus();
                            messageModel.setStatus(status);
                            String userNickName = SharePreferencesUtil.getString(MyApplication.getInstance(), "userNickName", "");
                            if(status.equals("0")){//进入直播间,在线人数增加1
                                if(!userName.equals(userNickName)){
                                    totalMemberCount++;
                                    tv_num.setText(totalMemberCount+"人");
                                }
                                liveChatFragment.addItem(messageModel);
                            }else {//退出直播间在线人数-1
                                if(!userName.equals(userNickName)){
                                    totalMemberCount--;
                                    tv_num.setText(totalMemberCount+"人");
                                    liveChatFragment.addItem(messageModel);
                                }
                            }
                            onlineNumObservable.onNext(totalMemberCount);
                        }
                        break;
                    //消息发送失败
                    case RongLibUtils.MESSAGE_SEND_ERROR:
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }
/*    @OnTouch(R.id.drawwe_linear)
    public void onTouch(View view, MotionEvent event){
        editPanel.editLinear.setVisibility(GONE);
        editPanel.inputClickLinear.setVisibility(View.VISIBLE);
        linearLayout7.setVisibility(View.VISIBLE);
    }*/
    @OnClick({R.id.clear_screen_iv,R.id.drawwe_linear})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.clear_screen_iv:
                clear_screen_iv.setVisibility(GONE);
                SharePreferencesUtil.putBoolean(getContext(),SharePreferencesStr.SHOW_CLEAR_SCREEN,false);
                break;
            case R.id.drawwe_linear:
                editPanel.editLinear.setVisibility(GONE);
                editPanel.inputClickLinear.setVisibility(View.VISIBLE);
                linearLayout7.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    /**
     使主界面获取焦点,并监听返回事件(解决在抽屉打开时按下返回键是收回抽屉的问题)
     当页面有其他控件抢夺焦点时 需要对获得焦点的view的setOnKeyListener进行处理

     nickname.setOnKeyListener(new View.OnKeyListener() {
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK
    && event.getAction() == KeyEvent.ACTION_UP) {
    //关闭软键盘
    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(nickname.getWindowToken(), 0);
    //使得根View重新获取焦点，以监听返回键
    getFocus();
    }0
    return false;
    }
    });
     */
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        RongLibUtils.removeEventHandler(handler);
    }
}
