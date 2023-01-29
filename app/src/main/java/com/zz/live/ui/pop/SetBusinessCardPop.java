package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

public class SetBusinessCardPop extends BasePopupWindow {
  public   EditText set_business_card_etv;
    TextView set_business_card_cancel_tv;
    TextView set_business_card_sure_tv;
    public SetBusinessCardPop(Context context) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
    }

    @Override
    public void initView() {
        super.initView();
        try{
            rootView= LayoutInflater.from(mContext).inflate(R.layout.set_business_card_pop,null);
            set_business_card_etv =rootView.findViewById(R.id.set_business_card_etv);
            set_business_card_cancel_tv =rootView.findViewById(R.id.set_business_card_cancel_tv);
            set_business_card_sure_tv =rootView.findViewById(R.id.set_business_card_sure_tv);
            UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
            String callingCard = userInfoBean.getCallingCard();
            set_business_card_etv.setText(StringMyUtil.isEmptyString(callingCard)?"":callingCard);
            set_business_card_cancel_tv.setOnClickListener(this);
            set_business_card_sure_tv.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
