package com.zz.live.ui.rongyun;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zz.live.MyApplication;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.ui.rongyun.message.CloseLiveMessage;
import com.zz.live.ui.rongyun.message.ForbiddenMessage;
import com.zz.live.ui.rongyun.message.LiveExitAndJoinMessage;
import com.zz.live.ui.rongyun.message.LiveFollowMessage;
import com.zz.live.ui.rongyun.message.LiveGiftMessage;
import com.zz.live.ui.rongyun.message.LiveNormalRedMessage;
import com.zz.live.ui.rongyun.message.LiveRedMessage;
import com.zz.live.ui.rongyun.message.LiveRewardMessage;
import com.zz.live.ui.rongyun.message.LiveShareBetMessage;
import com.zz.live.ui.rongyun.message.LiveSystemMessage;
import com.zz.live.ui.rongyun.message.LiveTextMessage;
import com.zz.live.ui.rongyun.message.RoomManageMessage;
import com.zz.live.ui.rongyun.message.SwichMoneyMessage;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

public class RongLibUtils {
    public static String TAG = "RongLibUtils";
    /**
     * 事件代码
     */
    public static final int MESSAGE_ARRIVED = 0;
    public static final int MESSAGE_SENT = 1;

    /**
     * 事件错误代码
     */
    public static final int MESSAGE_SEND_ERROR = -1;
    /**
     * 事件监听者列表
     */
    private static ArrayList<Handler> eventHandlerList = new ArrayList<>();


    public static void initRongYun(String appKey) {
        //融云全局初始化 (只需初始化一次)
       RongIMClient.init(MyApplication.getInstance(), appKey);
        //消息接受监听
        RongIMClient.setOnReceiveMessageListener(onReceiveMessageListener);
        //连接状态监听
        RongIMClient.setConnectionStatusListener(new ConnectionStatusListene(MyApplication.getInstance()));
        try {
            RongIMClient.registerMessageType(LiveTextMessage.class);
            RongIMClient.registerMessageType(LiveShareBetMessage.class);
            RongIMClient.registerMessageType(LiveRedMessage.class);
            RongIMClient.registerMessageType(LiveGiftMessage.class);
            RongIMClient.registerMessageType(LiveExitAndJoinMessage.class);
            RongIMClient.registerMessageType(LiveFollowMessage.class);
            RongIMClient.registerMessageType(ForbiddenMessage.class);
            RongIMClient.registerMessageType(RoomManageMessage.class);
            RongIMClient.registerMessageType(SwichMoneyMessage.class);
            RongIMClient.registerMessageType(CloseLiveMessage.class);
            RongIMClient.registerMessageType(LiveSystemMessage.class);
            RongIMClient.registerMessageType(LiveNormalRedMessage.class);
            RongIMClient.registerMessageType(LiveRewardMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void connectRongYun(String token) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "rongLog:  连接融云成功");
            }

            @Override
            public void onError(RongIMClient.ConnectionErrorCode connectionErrorCode) {
                Log.e(TAG, "rongLog:  连接融云失败"+connectionErrorCode.getValue());
            }

            @Override
            public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus databaseOpenStatus) {

            }
        });
    }

    /**
     * 添加IMLib 事件接收者。
     *
     * @param handler
     */
    public static void addEventHandler(Handler handler) {
        if (!eventHandlerList.contains(handler)) {
            eventHandlerList.add(handler);
        }
    }

    /**
     * 消息接收监听者
     */
    private static RongIMClient.OnReceiveMessageListener onReceiveMessageListener = new RongIMClient.OnReceiveMessageListener() {
        @Override
        public boolean onReceived(Message message, int i) {
            handleEvent(MESSAGE_ARRIVED, message);
            return false;
        }
    };

    /**
     * 移除IMLib 事件接收者。
     *
     * @param handler
     */
    public static void removeEventHandler(Handler handler) {
        eventHandlerList.remove(handler);
        handler.removeCallbacksAndMessages(null);
    }


    /**
     * 断开与融云服务器的连接，并且不再接收 Push 消息。
     */
    public void logout() {
        RongIMClient.getInstance().logout();
    }

    /**
     * 加入聊天室。如果聊天室不存在，sdk 会创建聊天室并加入，如果已存在，则直接加入。加入聊天室时，可以选择拉取聊天室消息数目。
     *
     * @param
     * @param defMessageCount 默认开始时拉取的历史记录条数
     * @param currentRoomId   聊天室id
     * @param callback        状态回调
     */
    public static void joinChatRoom(int defMessageCount, String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().joinChatRoom(currentRoomId, defMessageCount, callback);
    }

    /**
     * 退出聊天室，不在接收其消息。
     */
    public static void quitChatRoom(String currentRoomId, final RongIMClient.OperationCallback callback) {
        RongIMClient.getInstance().quitChatRoom(currentRoomId, callback);
    }

    /**
     * 向当前聊天室发送消息。
     * </p>
     * <strong>注意：</strong>此函数为异步函数，发送结果将通过handler事件返回。
     *
     * @param msgContent 消息对象
     */
    public static void sendMessage(String currentRoomId, final MessageContent msgContent) {
        Message msg = Message.obtain(currentRoomId, Conversation.ConversationType.CHATROOM, msgContent);
        //没有获取到聊天室id
        if (StringMyUtil.isEmptyString(currentRoomId)) {
            Toast.makeText(MyApplication.getInstance(), "直播间初始化失败,请退出重试", Toast.LENGTH_SHORT).show();
            return;
        }
        RongIMClient.getInstance().sendMessage(msg, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
            }

            @Override
            public void onSuccess(Message message) {
                handleEvent(MESSAGE_SENT, message);
                Log.e(TAG, "rongLog 消息发送成功  id= " + currentRoomId);
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                handleEvent(MESSAGE_SEND_ERROR, errorCode.getValue(), 0, message);

                Log.e(TAG, "rongLog 消息发送失败   id =" + currentRoomId + "  code=  " + errorCode.getValue());
            }
        });
    }

    private static void handleEvent(int what, Object obj) {
        for (Handler handler : eventHandlerList) {
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.obj = obj;
            handler.sendMessage(m);
        }
    }

    private static void handleEvent(int what, int arg1, int arg2, Object obj) {
        for (Handler handler : eventHandlerList) {
            android.os.Message m = android.os.Message.obtain();
            m.what = what;
            m.arg1 = arg1;
            m.arg2 = arg2;
            m.obj = obj;
            handler.sendMessage(m);
        }
    }

    public static String setUserInfo(Context context) {
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        String userNickName = userInfoBean.getNickname();
        //主播端没有等级
        int pointGrade = SharePreferencesUtil.getInt(context, "pointGrade", 1);
        long user_id = userInfoBean.getId();
        HashMap<String, Object> userInfoMap = new HashMap<>();

        userInfoMap.put("medalIcon", "");
        userInfoMap.put("mountSVGA", "");//坐骑
        userInfoMap.put("titleIcon", "");//称号牌
        userInfoMap.put("levelIcon","");//等级icon
        userInfoMap.put("levelSVGA", "");//等级特效
        userInfoMap.put("mountName", "");//坐骑名


        userInfoMap.put("userId", user_id);
        userInfoMap.put("name", userNickName);
        userInfoMap.put("level", pointGrade);
        userInfoMap.put("platUserId",userNickName);
        userInfoMap.put("managerType",1);//1 主播 2超管 3房管
        String userInfoJson = JSONObject.toJSONString(userInfoMap);
        return userInfoJson;
    }
}
