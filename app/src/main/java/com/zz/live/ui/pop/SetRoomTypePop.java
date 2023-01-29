package com.zz.live.ui.pop;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class SetRoomTypePop extends BasePopupWindow {
  public   EditText set_amount_etv;
    TextView set_room_type_sure_tv;
    TextView set_room_type_free_tv;
   public int roomType;
    public SetRoomTypePop(Context context ,int roomType) {
        super(context);
        this.roomType = roomType;
        setAnimationStyle(R.style.pop_scale_animation);
    }

    public SetRoomTypePop(Context context, boolean focusable) {
        super(context);
        setAnimationStyle(R.style.pop_scale_animation);
    }

    @Override
    public void initView() {
        super.initView();
        rootView= LayoutInflater.from(mContext).inflate(R.layout.set_room_type_layout,null);
        set_amount_etv= rootView.findViewById(R.id.set_amount_etv);
        set_room_type_sure_tv= rootView.findViewById(R.id.set_room_type_sure_tv);
        set_room_type_free_tv= rootView.findViewById(R.id.set_room_type_free_tv);
        set_amount_etv.setFocusable(true);
        set_room_type_sure_tv.setOnClickListener(this);
        set_room_type_free_tv.setOnClickListener(this);

    }

}
