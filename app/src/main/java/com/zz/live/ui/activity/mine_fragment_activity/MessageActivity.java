package com.zz.live.ui.activity.mine_fragment_activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.PrivateMessageEntity;
import com.zz.live.ui.activity.message_activity.MessageDetailsActivity;
import com.zz.live.ui.fragment.message_fragment.PrivateMessageFragment;
import com.zz.live.ui.fragment.message_fragment.SystemMessageFragment;
import com.zz.live.utils.CommonToolbarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.message_content_frameLayout)
    FrameLayout message_content_frameLayout;
    @BindView(R.id.system_message_iv)
    ImageView system_message_iv;
    @BindView(R.id.private_message_iv)
    ImageView private_message_iv;
    @BindView(R.id.system_message_tv)
    TextView system_message_tv;
    @BindView(R.id.private_message_tv)
    TextView private_message_tv;
    FragmentManager supportFragmentManager;
    SystemMessageFragment systemMessageFragment;
    PrivateMessageFragment privateMessageFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }
    public static int TO_SYSTEM_MESSAGE=100;
    public static int TO_FOLLOW_MESSAGE=300;
    @Override
    protected void init(Bundle savedInstanceState) {
        supportFragmentManager  = getSupportFragmentManager();
        CommonToolbarUtil.initToolbar(this,"消息");
        system_message_iv.performClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        更新系统消息
         */
        if(requestCode== MessageActivity.TO_SYSTEM_MESSAGE||requestCode==MessageActivity.TO_FOLLOW_MESSAGE){
            if(resultCode==RESULT_OK){
                //下级页面更改过消息已读状态时,自动刷新系统消息的列表(更新未读消息数)
                if(systemMessageFragment!=null){
                    systemMessageFragment. system_message_recfresh.autoRefresh();
                }
            }
        }
        /*
        更新私信消息
         */
        if(requestCode== MessageDetailsActivity.PRIVATE_MESSAGE_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                if(privateMessageFragment!=null){
                    ArrayList<PrivateMessageEntity.DataBean.RecordsBean> recordsBeanArrayList = privateMessageFragment.recordsBeanArrayList;
                    for (int i = 0; i < recordsBeanArrayList.size(); i++) {
                        PrivateMessageEntity.DataBean.RecordsBean recordsBean = recordsBeanArrayList.get(i);
                        String id= recordsBean.getId();
                        if(id.equals(data.getStringExtra("id"))){
                            recordsBean.setIsRead(1);
                            privateMessageFragment.privateMessageAdapter.notifyItemChanged(i);
                        }

                    }
                }
            }
        }
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        View viewById = findViewById(R.id.include);
        ImmersionBar.with(this)
                .titleBar(viewById)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();
    }

    @OnClick({R.id.system_message_iv,R.id.private_message_iv,R.id.system_message_tv,R.id.private_message_tv})
    public void onClick(View v){
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.system_message_iv:
            case R.id.system_message_tv:
                hideAllFragment(transaction);
                initViewStatus(1);
                if(systemMessageFragment==null){
                    systemMessageFragment= new SystemMessageFragment();
                    transaction.add(R.id.message_content_frameLayout,systemMessageFragment);
                }else {
                    transaction.show(systemMessageFragment);
                }
                break;
            case  R.id.private_message_iv:
            case  R.id.private_message_tv:
                hideAllFragment(transaction);
                initViewStatus(2);
                if(privateMessageFragment==null){
                    privateMessageFragment= new PrivateMessageFragment();
                    transaction.add(R.id.message_content_frameLayout,privateMessageFragment);
                }else {
                    transaction.show(privateMessageFragment);
                }

                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void initViewStatus(int type) {
        if(type==1){
            system_message_iv.setImageResource(R.drawable.xtxx_dliang);
            system_message_tv.setTextColor(getResources().getColor(R.color.default_color));
            private_message_iv.setImageResource(R.drawable.sxxx_mr);
            private_message_tv.setTextColor(Color.parseColor("#999999"));
        }else {
            system_message_iv.setImageResource(R.drawable.xtxx_mr);
            system_message_tv.setTextColor(Color.parseColor("#999999"));
            private_message_iv.setImageResource(R.drawable.sxxx_dliang);
            private_message_tv.setTextColor(getResources().getColor(R.color.default_color));
        }
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if(systemMessageFragment!=null){transaction.hide(systemMessageFragment);}
        if(privateMessageFragment!=null){transaction.hide(privateMessageFragment);}
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
