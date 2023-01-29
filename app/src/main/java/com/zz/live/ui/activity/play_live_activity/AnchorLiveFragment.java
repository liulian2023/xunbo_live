package com.zz.live.ui.activity.play_live_activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.opensource.svgaplayer.SVGAImageView;
import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.LiveGiftSVGAEntity;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.myView.gift.GiftSendModel;
import com.zz.live.myView.gift.GiftView;
import com.zz.live.myView.gift.GiftSvgaManage;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.LiveExitAndJoinMessage;
import com.zz.live.ui.rongyun.message.LiveGiftMessage;
import com.zz.live.ui.rongyun.message.LiveRedMessage;
import com.zz.live.ui.rongyun.message.LiveShareBetMessage;
import com.zz.live.ui.rongyun.message.LiveTextMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.DrawerLeftEdgeSize;
import com.zz.live.utils.SharePreferencesStr;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.MessageContent;

import static android.view.View.GONE;

public class AnchorLiveFragment extends BaseFragment implements Handler.Callback{
    public static String TAG="LiveFragment";
    @BindView(R.id.live_drawerLayout)
    DrawerLayout live_drawerLayout;
    @BindView(R.id.live_cover_iv)
    ImageView live_cover_iv;
    @BindView(R.id.play_fail_linear)
    LinearLayout play_fail_linear;
    @BindView(R.id.clear_screen_iv)
    ImageView clear_screen_iv;
    @BindView(R.id.linearLayout7)
    public LinearLayout linearLayout7;
    @BindView(R.id.live_edit_panel)
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
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private LiveChatFragment liveChatFragment;
    private GiftSvgaManage giftSvgaManage;
    UserInfoEntity.DataBean userDataBean;
    private Handler handler = new Handler(this);
    private int totalMemberCount;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_anchor;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        userDataBean= Utils.getUserInfoBean();
        /**
         * 初始化 drawlayout
         */
//        editPanel.setAnchorLiveFragment(this);
        live_cover_iv.setVisibility(View.VISIBLE);
        live_drawerLayout.setScrimColor(0x00000000);
        DrawerLeftEdgeSize.setRightEdge(getActivity(),live_drawerLayout, 1f);
        live_drawerLayout.openDrawer(GravityCompat.END);
        initLiveChatFragment();
        initSvgaImageView();
        editPanel.setRoomId(sp.getRoomId());
        initRongYun();
        editPanel.setInputLinstener();
    }

    /**
     *
     * @param position
     * @param url
     * @param coverUrl
     * @return
     */
    public static AnchorLiveFragment newInstence(int position, String url, String coverUrl ){
        AnchorLiveFragment anchorLiveFragment = new AnchorLiveFragment();
        Bundle newBundle = new Bundle();
        newBundle.putInt(CommonStr.POSITION,position);
        newBundle.putString("url",url);
        newBundle.putString("coverUrl",coverUrl);
        anchorLiveFragment.setArguments(newBundle);
        return  anchorLiveFragment;
    }

    private void initRongYun() {
        RongLibUtils.addEventHandler(handler);
        joinChatRoom();
    }
    private void joinChatRoom() {
        RongLibUtils.joinChatRoom(0, sp.getRoomId(), new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                /*
                加入聊天室成功
                 */
                Log.e(TAG, "加入聊天室成功" );
//            发送一条进入直播间的message
                LiveExitAndJoinMessage msgContent = new LiveExitAndJoinMessage(userDataBean.getNickname(), "0");
                msgContent.setUserInfoJson(RongLibUtils.setUserInfo(getContext()));
                RongLibUtils.sendMessage(sp.getRoomId(), msgContent);
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
        RongIMClient.getInstance().getChatRoomInfo(sp.getRoomId(), 20, ChatRoomInfo.ChatRoomMemberOrder.RC_CHAT_ROOM_MEMBER_ASC, new RongIMClient.ResultCallback<ChatRoomInfo>() {
            @Override
            public void onSuccess(ChatRoomInfo chatRoomInfo) {
                //真实在线人数
                 totalMemberCount = chatRoomInfo.getTotalMemberCount();
                //机器人人数
                if(tv_num!=null){
                    tv_num.setText(totalMemberCount+"人");
                }
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
    /**
     * 页面可见
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }
        private void initSvgaImageView() {
        giftSvgaManage = new GiftSvgaManage(getContext(),svgaImageView);
    }
    private void initLiveChatFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        liveChatFragment= (LiveChatFragment) fragmentManager.findFragmentById(R.id.live_chat_fragment);
    }
      @OnTouch(R.id.drawwe_linear)
        public void onTouch(View view, MotionEvent event){
            editPanel.editLinear.setVisibility(GONE);
            editPanel.inputClickLinear.setVisibility(View.VISIBLE);
            linearLayout7.setVisibility(View.VISIBLE);
        }
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
            if(targetId.equals(sp.getRoomId())){
                MessageContent content = obj.getContent();
                switch (message.what) {
                    //发送和接受消息
                    case RongLibUtils.MESSAGE_ARRIVED:
                    case RongLibUtils.MESSAGE_SENT:

                        /*普通文本消息*/

                        LiveMessageModel messageModel = new LiveMessageModel();
                        if (content instanceof LiveTextMessage) {
                            LiveTextMessage liveTextMessage = (LiveTextMessage) content;
                            Utils. initUserInfo(messageModel, liveTextMessage.getUserInfoJson());
                            messageModel.setObjectName(CommonStr.TEXT_MESSAGE);
                            messageModel.setTextContent(liveTextMessage.getContent());
                            liveChatFragment.addItem(messageModel);

                        /*    跟投消息*/

                        } else if (content instanceof LiveShareBetMessage) {
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

                         /*   红包消息*/

                        } else if (content instanceof LiveRedMessage) {
                            LiveRedMessage liveRedMessage = (LiveRedMessage) content;
                            int redType = liveRedMessage.getRedType();
                            //3的用户信息放在 userInfoJson中  1 2 的用户信息在qpUserName中
                            if (redType == 3) {
                                Utils.initUserInfo(messageModel, liveRedMessage.getUserInfoJson());
                            } else {
                                messageModel.setUserName(liveRedMessage.getQbUserName());
                            }
                            messageModel.setObjectName(CommonStr.RED_MESSAGE);
                            messageModel.setRedType(redType);
                            messageModel.setRedPrice(liveRedMessage.getRedPrice());
                            messageModel.setRedId(Integer.parseInt(liveRedMessage.getRedId()));
                            liveChatFragment.addItem(messageModel);

                   /*         礼物消息*/

                        } else if (content instanceof LiveGiftMessage) {
//                            添加到聊天列表  开始   ---------------------
                                    LiveGiftMessage liveGiftMessage = (LiveGiftMessage) content;
                            String userInfoJson = liveGiftMessage.getUserInfoJson();
                            Utils.  initUserInfo(messageModel, userInfoJson);
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
                            if(!toString.equals("- - - -")){
                                int oldNum = Integer.parseInt(toString);
                                money_tv.setText((oldNum+oldPrice)+"");
                            }
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
        RongLibUtils.removeEventHandler(handler);
    }
}
