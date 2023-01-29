package com.zz.live.ui.pop;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;

public class ModifyNickNamePop extends BasePopupWindow {
  public EditText modify_nickname_etv;
    TextView modify_nickname_cancel_tv;
    TextView modify_nickname_sure_tv;
    public ModifyNickNamePop(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        super.initView();
        rootView= LayoutInflater.from(mContext).inflate(R.layout.modify_nickname_pop,null);
        modify_nickname_etv=rootView.findViewById(R.id.set_business_card_etv);
        modify_nickname_cancel_tv=rootView.findViewById(R.id.set_business_card_cancel_tv);
        modify_nickname_sure_tv=rootView.findViewById(R.id.set_business_card_sure_tv);
        modify_nickname_cancel_tv.setOnClickListener(this);
        modify_nickname_sure_tv.setOnClickListener(this);
        modify_nickname_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                String inputStr = clearLimitStr( str);
                modify_nickname_etv.removeTextChangedListener(this);
                // et.setText方法可能会引起键盘变化,所以用editable.replace来显示内容
                s.replace(0, s.length(), inputStr.trim());
                modify_nickname_etv.addTextChangedListener(this);
            }
        });
    }
    /**
     * 清除不是中文的内容
     *
     * @param
     * @return
     */
    private String clearLimitStr( String str) {
        return str.replaceAll("[^\u4E00-\u9FA5]", "");
    }

}
