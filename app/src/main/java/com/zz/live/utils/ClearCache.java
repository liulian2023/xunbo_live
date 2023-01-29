
package com.zz.live.utils;

import android.content.Context;
import android.content.Intent;


import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.zz.live.MyApplication;
import com.zz.live.ui.activity.main_tab_activity.LoginActivity;

import io.rong.imlib.RongIMClient;

public class ClearCache {


    public static void clearCache(Context context) {
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        RongIMClient.getInstance().disconnect();//断开融云连接
        sp.setToken("");
        sp.setLoginStatus(false);
        sp.setUrlList("");
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.CHOOSE_LOTTERY_BEAN);//开播时选择的彩种
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.LIVE_TITLE);//房间标题
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.CHOOSE_CHANNEL_ID);//直播频道id
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.CHOOSE_CHANNEL_NAME);//直播频道名
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.CHOOSE_LIVE_COVER);//直播封面
        SharePreferencesUtil.remove(MyApplication.getInstance(),CommonStr.CHOOSE_NATURE);//直播性质
        context.startActivity(intent);
    }


}
