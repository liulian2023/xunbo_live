package com.zz.live.ui.activity.start_live_activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.FinishLiveEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.bean.evenBus.StopPushEvenModel;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.pop.CommonTipPop;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.CloseLiveMessage;
import com.zz.live.utils.DateUtil;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class FinishLiveActivity extends BaseActivity {
    @BindView(R.id.big_bg_iv)
    ImageView big_bg_iv;
    @BindView(R.id.title_iv)
    ImageView title_iv;
    @BindView(R.id.return_home_btn)
    Button return_home_btn;
    @BindView(R.id.live_time_tv)
    TextView live_time_tv;
    @BindView(R.id.gift_amount_tv)
    TextView gift_amount_tv;
    @BindView(R.id.watch_num_tv)
    TextView watch_num_tv;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.toll_amount_tv)
    TextView toll_amount_tv;
    @BindView(R.id.toll_linear)
    LinearLayout toll_linear;
    int watchRadio;
    String streamName;
    int onLine;
    String roomAmount;
    Activity activity;
    String tipContent="";
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    @Override
    public int getLayoutId() {
        return R.layout.activity_finish_live;
    }
    Handler handler = new Handler();
    /**
     *
     * @param context
     * @param streamName 直播流地址
     * @param mostHighMemberCount 最高峰时的在线人数(真实人数)
     * @param watchRadio  直播间人数的倍数
     * @param roomAmount 是否开启了付费房间(roomAmount为0未开启)
     */
    public static void startAty(Context context, String streamName,int mostHighMemberCount,int watchRadio ,String roomAmount,String tipContent){
        Intent intent = new Intent(context, FinishLiveActivity.class);
        intent.putExtra("streamName",streamName);
        intent.putExtra("onLine",mostHighMemberCount);
        intent.putExtra("watchRadio",watchRadio);
        intent.putExtra("roomAmount",roomAmount);
        intent.putExtra("tipContent",tipContent);
        context.startActivity(intent);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        initView();
        requestStopLiveData();
        initTipPop();
    }

    private void initTipPop() {
        if(StringMyUtil.isNotEmpty(tipContent)){
            CommonTipPop commonTipPop = new CommonTipPop(this, "温馨提示", tipContent);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    commonTipPop.showAtLocation(big_bg_iv, Gravity.CENTER,0,0);
                    Utils.darkenBackground(FinishLiveActivity.this,0.7f);
                }
            },300);
        }
    }

    private void initView() {
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        String title = Utils.getTitle();
        GlideLoadViewUtil.loadBlurView(this,title,big_bg_iv);
        GlideLoadViewUtil.LoadTitleView(this,title,title_iv);
        name_tv.setText(userInfoBean.getNickname());
    }

    private void requestStopLiveData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("onLine",onLine);
        data.put("streamName",streamName );
        HttpApiUtils.wwwRequest(this,null, RequestUtils.FINISH_LIVE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                FinishLiveEntity finishLiveEntity = JSONObject.parseObject(result, FinishLiveEntity.class);
                FinishLiveEntity.DataBean data = finishLiveEntity.getData();
                String liveTime = data.getLiveTime();
                String giftAmount = data.getGiftAmount();
                String subAmount = data.getSubAmount();
                String time = DateUtil.secToTime(Integer.parseInt(liveTime));
                live_time_tv.setText(time);
                gift_amount_tv.setText(giftAmount);
                watch_num_tv.setText((watchRadio*onLine)+"");
                //没开启付费房间时隐藏订阅金额
                if(!roomAmount.equals("0")&& StringMyUtil.isNotEmpty(subAmount)){
                    toll_amount_tv.setText(subAmount);
                }else {
                    toll_linear.setVisibility(View.GONE);
                }

                CloseLiveMessage closeLiveMessage = new CloseLiveMessage("");
                closeLiveMessage.setUserInfoJson(RongLibUtils.setUserInfo(FinishLiveActivity.this));
                RongLibUtils.sendMessage(sp.getRoomId(),closeLiveMessage);

                StopPushEvenModel event = new StopPushEvenModel();
                event.setStopPush(true);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onFail(String msg) {
                StopPushEvenModel event = new StopPushEvenModel();
                event.setStopPush(true);
                EventBus.getDefault().post(event);
            }
        });
    }

    private void getIntentData() {
        streamName = getIntent().getStringExtra("streamName");
        onLine= getIntent().getIntExtra("onLine",1);
        watchRadio= getIntent().getIntExtra("watchRadio",1);
        roomAmount= getIntent().getStringExtra("roomAmount");
        tipContent= getIntent().getStringExtra("showTipPop");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .navigationBarColor(R.color.transparent)
                .init();
    }

    @OnClick({R.id.return_home_btn})
    public void onClick(View  v){
        switch (v.getId()){
            case R.id.return_home_btn:
                finish();
                break;
            default:
                break;

        }
    }
}
