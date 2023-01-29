package com.zz.live.ui.activity.play_live_activity;

import android.os.Bundle;

import android.util.Log;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.evenBus.HideCoverEven;
import com.zz.live.bean.evenBus.LiveEvenEntity;
import com.zz.live.bean.evenBus.PlayFailEven;
import com.zz.live.utils.AESUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

import static com.tencent.rtmp.TXLiveConstants.PLAY_ERR_NET_DISCONNECT;
import static com.tencent.rtmp.TXLiveConstants.PLAY_EVT_PLAY_BEGIN;


public class LivePlayerFragment extends BaseFragment {
    @BindView(R.id.play_tx_cloud_view)
    TXCloudVideoView play_tx_cloud_view;
    private TXLivePlayer mLivePlayer;
    private String TAG="LivePlayerFragment";
    LiveEvenEntity liveEvenEntity;
    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mLivePlayer = new TXLivePlayer(getActivity());
        mLivePlayer.setPlayerView(play_tx_cloud_view);
        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {
                switch (i){
                    //开始播放
                    case PLAY_EVT_PLAY_BEGIN:
                        //延时300ms隐藏封面图(播放器收到此监听时,播放源切换存在延迟,马上隐藏封面可能出现闪屏)
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(new HideCoverEven(true,liveEvenEntity.getPosition()));
                            }
                        },300);
                        break;
                        //网络断连，且连续三次无法重新连接，需要自行重启推流(显示主播已下线)
                    case PLAY_ERR_NET_DISCONNECT:
                        EventBus.getDefault().post(new PlayFailEven());
                        break;
                        default:
                            break;
                }
                Log.e(TAG, "onPlayEvent:  "+i );
            }

            @Override
            public void onNetStatus(Bundle bundle) {

            }
        });
    }

    /**
     * 更新播放源(LiveFragment 可见时发送此通知)
     * @param liveEvenEntity
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void updatePlayer(LiveEvenEntity liveEvenEntity){
        if(liveEvenEntity!=null){
           this. liveEvenEntity=liveEvenEntity;
            if(mLivePlayer.isPlaying()){
                mLivePlayer.stopPlay(true);
            }
            String playUrl = liveEvenEntity.getPlayUrl();
            try {
                mLivePlayer.startPlay(AESUtil.decrypt(playUrl),TXLivePlayer.PLAY_TYPE_LIVE_RTMP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_live_player;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //退出当前页面必须销毁播放器, 避免内存泄漏以及下次startPlay()时的闪屏现象
        if(mLivePlayer!=null){
            mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        }
        if(play_tx_cloud_view!=null){
            play_tx_cloud_view.onDestroy();
        }
    }
}
