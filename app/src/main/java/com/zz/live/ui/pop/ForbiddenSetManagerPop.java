package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.LiveMessageModel;

public class ForbiddenSetManagerPop extends BasePopupWindow {
    TextView forbidden_tv;
    TextView set_manager_tv;
    TextView forbidden_cancel_tv;
    public LiveMessageModel liveMessageModel;
    int isRoomManager;

    /**
     * 0:取消房管;1:设为房管
     * @param context
     * @param liveMessageModel
     * @param isRoomManager
     */
    public ForbiddenSetManagerPop(Context context,LiveMessageModel liveMessageModel,int isRoomManager) {
        super(context);
        this.liveMessageModel = liveMessageModel;
        this.isRoomManager = isRoomManager;
        setAnimationStyle(R.style.down_to_up150);
        initData();
    }

    private void initData() {
        if(isRoomManager==0){
            set_manager_tv.setText("取消房管");
            forbidden_tv.setVisibility(View.GONE);
        }else {
            set_manager_tv.setText("设为房管");
            forbidden_tv.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.forbidden_set_manager_pop, null);
        forbidden_tv =rootView.findViewById(R.id.forbidden_tv);
        set_manager_tv =rootView.findViewById(R.id.set_manager_tv);
        forbidden_cancel_tv =rootView.findViewById(R.id.forbidden_cancel_tv);
        forbidden_tv.setOnClickListener(this);
        set_manager_tv.setOnClickListener(this);
        forbidden_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForbiddenSetManagerPop.this.dismiss();
            }
        });
    }

    public int getIsRoomManager() {
        return isRoomManager;
    }
}
