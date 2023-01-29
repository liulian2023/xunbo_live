package com.zz.live.ui.activity.play_live_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.LiveEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.LiveExitAndJoinMessage;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

public class PlayLiveActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_play_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        FrameLayout viewById = (FrameLayout) findViewById(R.id.flmain);
        SharePreferencesUtil.remove(MyApplication.getInstance(), CommonStr.JOIN_CHATROOM_ID );
        /*
        底部放置播放器(LivePlayerFragment), MainDialogFragment显示在上方(MainDialogFragment中处理viePager+fragment的逻辑, 实现上下滑动切换直播间)
         */
        LivePlayerFragment liveViewFragment = new LivePlayerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flmain, liveViewFragment).commit();
        new MainDialogFragment(viewById).show(getSupportFragmentManager(),"MainDialogFragment");
    }
    public static void startAty(Context context, LiveEntity.DataBean.RecordsBean recordsBean){
        Intent intent = new Intent(context, PlayLiveActivity.class);
        intent.putExtra("recordsBean",recordsBean);
        context.startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        String joinChatroomId = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.JOIN_CHATROOM_ID, "");
        LiveExitAndJoinMessage liveExitAndJoinMessage = new LiveExitAndJoinMessage(userInfoBean.getNickname(), "1");
        liveExitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(this));
        if (StringMyUtil.isNotEmpty(joinChatroomId)) {
            liveExitAndJoinMessage.setUserInfoJson(RongLibUtils.setUserInfo(PlayLiveActivity.this));
            RongLibUtils.sendMessage(joinChatroomId, liveExitAndJoinMessage);
        }
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
