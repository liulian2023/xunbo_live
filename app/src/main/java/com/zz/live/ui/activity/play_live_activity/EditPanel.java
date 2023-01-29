package com.zz.live.ui.activity.play_live_activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.zz.live.R;
import com.zz.live.ui.activity.start_live_activity.StartLiveActivity;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.LiveTextMessage;
import com.zz.live.utils.HeightProvider;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

public class EditPanel extends LinearLayout {
    public TextView inputTv;
    public TextView sendTv;
    public EditText editText;
    public LinearLayout editLinear;
    public LinearLayout inputClickLinear;
    public LinearLayout edit_root_linear;
    public Context context;
    String roomId;
    public LiveFragment liveFragment;
    public StartLiveActivity startLiveActivity;
    private HeightProvider heightProvider;

    public LiveFragment getLiveFragment() {
        return liveFragment;
    }

    public StartLiveActivity getStartLiveActivity() {
        return startLiveActivity;
    }

    public void setStartLiveActivity(StartLiveActivity startLiveActivity) {
        this.startLiveActivity = startLiveActivity;
    }

    public HeightProvider getHeightProvider() {
        return heightProvider;
    }

    public void setLiveFragment(LiveFragment liveFragment) {
        this.liveFragment = liveFragment;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

        public EditPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }


    /**
     * 监听键盘高度, 将输入框顶到键盘上方
     */
    public  void setInputLinstener() {
         heightProvider = new HeightProvider(liveFragment == null ? startLiveActivity : liveFragment.getActivity()).init().setHeightListener(new HeightProvider.HeightListener() {
            @Override
            public void onHeightChanged(int height, int navigateHeight) {
                ObjectAnimator animator;
                if (height == 0) {
                    //收回键盘时,不需要动画时长
                    animator = ObjectAnimator.ofFloat(edit_root_linear, "translationY", -height);
                    animator.setDuration(0);
                    //每次收回键盘时都隐藏输入框,显示 来聊聊天吧 按钮, 并显示activity中右下角的linearLayout
                    editLinear.setVisibility(GONE);
                    inputClickLinear.setVisibility(View.VISIBLE);
                    LinearLayout linearLayout7;
                    linearLayout7 = liveFragment == null ? startLiveActivity.linearLayout7 : liveFragment.linearLayout7;
                    if (linearLayout7 != null) {
                        linearLayout7.setVisibility(View.VISIBLE);
                    }
                } else {
//                    animator = ObjectAnimator.ofFloat(edit_root_linear, "translationY", -(height + navigateHeight));
                    animator = ObjectAnimator.ofFloat(edit_root_linear, "translationY", -height);
                    animator.setDuration(100);
                }
                animator.start();
/*                ObjectAnimator animator = ObjectAnimator.ofFloat(edit_root_linear, "translationY", -height);
                if(height==0){
                    //收回键盘时,不需要动画时长
                    animator.setDuration(0);
                    editLinear.setVisibility(GONE);
                    inputClickLinear.setVisibility(View.VISIBLE);
                    LinearLayout linearLayout7;
                    linearLayout7 = liveFragment ==null?startLiveActivity.linearLayout7:liveFragment.linearLayout7;
                    linearLayout7.setVisibility(View.VISIBLE);
                }else {
                    animator.setDuration(200);
                }
                animator.start();*/


            }
        });
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chat_edit_layout, this);
        bindView(view);
        inputTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showHideInputView(editLinear);
            }
        });
        sendTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringMyUtil.isEmptyString(editText.getText().toString())) {
                    return;
                }
                //隐藏显示输入框
                showHideInputView(editLinear);
                LiveTextMessage msgContent = new LiveTextMessage(RongLibUtils.setUserInfo(context), editText.getText().toString());
                msgContent.setUserInfoJson(RongLibUtils.setUserInfo(context));
                RongLibUtils.sendMessage(roomId, msgContent);
                //清空输入框
                editText.setText("");
                //收回软键盘
                Utils.hideSoftKeyBoard((Activity) context,editText);

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (StringMyUtil.isNotEmpty(editText.getText().toString())) {
                    sendTv.setBackground(getResources().getDrawable(R.drawable.login_button_can_click_shape));
                    sendTv.setClickable(true);
                } else {
                    sendTv.setBackground(getResources().getDrawable(R.drawable.login_button_can_not_click_shape));
                    sendTv.setClickable(false);
                }
            }
        });
        /**
         * 软键盘右下角action 按钮事件
         */
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //点击右下角按钮, 默认发送
                sendTv.performClick();
                return false;
            }
        });
    }

    private void bindView(View view) {
        inputClickLinear = view.findViewById(R.id.input_click_linear);
        inputTv = view.findViewById(R.id.live_chat_tv);
        sendTv = view.findViewById(R.id.send_text_message_tv);
        editText = view.findViewById(R.id.live_chat_edit);
        editLinear = view.findViewById(R.id.edit_linear);
        edit_root_linear = view.findViewById(R.id.edit_root_linear);
    }

    /**
     *输入框和上方"来聊聊天"按钮之间的显示隐藏
     * @param editLinear
     */
    public void showHideInputView(LinearLayout editLinear) {
        if (editLinear.getVisibility() == VISIBLE) {
            editLinear.setVisibility(GONE);
            inputClickLinear.setVisibility(VISIBLE);
 /*           if(null!=liveFragment){
                liveFragment.bottomLinear.setVisibility(VISIBLE);
            }*/
            if(null!=liveFragment){
                liveFragment.linearLayout7.setVisibility(VISIBLE);
            }else {
                startLiveActivity.linearLayout7.setVisibility(VISIBLE);
            }
            Utils.hideSoftKeyBoard((Activity) context);
        } else {
            editLinear.setVisibility(VISIBLE);
            Utils.showSoftInputFromWindow((Activity) context, editText);
            inputClickLinear.setVisibility(GONE);
            if(null!=liveFragment){
                liveFragment.linearLayout7.setVisibility(INVISIBLE);
            }else {
                startLiveActivity.linearLayout7.setVisibility(INVISIBLE);
            }
        }
    }
}
