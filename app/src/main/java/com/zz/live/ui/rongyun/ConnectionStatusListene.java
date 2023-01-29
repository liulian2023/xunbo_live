
package com.zz.live.ui.rongyun;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.zz.live.ui.activity.main_tab_activity.LoginActivity;
import com.zz.live.utils.ClearCache;
import com.zz.live.utils.CommonStr;

import io.rong.imlib.RongIMClient;

/*
融云连接状态监听
*/
public class ConnectionStatusListene implements RongIMClient.ConnectionStatusListener {
Context context;
public static String TAG="ConnectionStatusListener ";
public ConnectionStatusListene(Context context) {
this.context = context;
}

@Override
public void onChanged(ConnectionStatus connectionStatus) {
switch (connectionStatus){
    case CONNECTED://连接成功
        Log.e(TAG, "rongIM 连接成功 " );
        break;
    case UNCONNECTED://断开连接。
        Log.e(TAG, "rongIM 断开连接 " );
        break;
    case CONNECTING://连接中。

        break;
    case NETWORK_UNAVAILABLE://网络不可用。
        Log.e(TAG, "rongIM 网络不可用 " );
//                MyApplication.getInstance().connectRongYun(SharePreferencesUtil.getString(MyApplication.getInstance(),"chatroomToken",""));

        break;
    case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "rongIM 多处登录,当前账号被强制下线 " );
                if(context!=null){
                    Intent intent=new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    intent.putExtra(CommonStr.SINGLE_LOGIN,true);
                    ClearCache.clearCache(context);
                    context.startActivity(intent);
                }
            }
        });

}
}
}
