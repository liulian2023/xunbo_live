package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.evenBus.CountDownEven;
import com.zz.live.utils.CommonStr;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static com.zz.live.utils.DateUtil.timeMode;

public class GameFragment extends BaseFragment   {
    @BindView(R.id.typename_tv)
    TextView typename_tv;
    @BindView(R.id.qishu_tv)
    TextView qishu_tv;
    @BindView(R.id.count_time_tv)
    TextView count_time_tv;


    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void hideCoverIv(CountDownEven countDownEven){
        long countTime = countDownEven.getCountTime();
        String qishu = countDownEven.getQishu();
        String typeName = countDownEven.getTypeName();
        typename_tv.setText(typeName);
        qishu_tv.setText("第"+qishu+"期");
        if(countTime>0){
            int mHour = (int) ((countTime / 1000) / (60 * 60));  //  对3600 取整  就是小时
            int mMin = (int) (((countTime / 1000) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
            int mSecond = (int) ((countTime / 1000) % 60); //  对60 取余  就是多出的秒
            String str_time;
            if (mHour == 0) {
                str_time = timeMode(mMin) + ":" + timeMode(mSecond);
            } else {
                str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
            }
            count_time_tv.setText(str_time);
        }else {
            count_time_tv.setText("已结束");
        }

    }
    public static GameFragment newInstance(int position){
        GameFragment gameFragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        gameFragment.setArguments(bundle);
        return gameFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_game;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
