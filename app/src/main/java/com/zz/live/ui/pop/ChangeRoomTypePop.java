package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class ChangeRoomTypePop extends BasePopupWindow {
    ImageView change_room_type_iv;
    TextView change_room_type_cancel_tv;
    TextView change_room_type_tv;
    int roomType;
    public ChangeRoomTypePop(Context context, boolean hideNavicate,int roomType) {
        super(context);
        this.roomType = roomType;
        setAnimationStyle(R.style.down_to_up150);
        if(roomType==1){
            //当前为普通房间,显示切换收费
            change_room_type_iv.setImageResource(R.drawable.qiehuanshouf_icon);
            change_room_type_tv.setText("切换收费");
        }else {
            change_room_type_iv.setImageResource(R.drawable.gongkai_icon);
            change_room_type_tv.setText("切换免费");
        }
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.change_room_type_pop_layout,null);
        change_room_type_iv = rootView.findViewById(R.id.change_room_type_iv);
        change_room_type_cancel_tv = rootView.findViewById(R.id.change_room_type_cancel_tv);
        change_room_type_tv = rootView.findViewById(R.id.change_room_type_tv);
        change_room_type_tv.setOnClickListener(this);
        change_room_type_iv.setOnClickListener(this);
        change_room_type_cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeRoomTypePop.this.dismiss();
            }
        });
    }
}
