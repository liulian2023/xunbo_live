package com.zz.live.ui.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;

import java.util.List;

public class GiftZSQAdapter extends BaseQuickAdapter<Boolean, BaseViewHolder> {
    public enum  SelectorColor{
        BULE,
        WHITE;
    };
    SelectorColor selectorColor;
    public GiftZSQAdapter(int layoutResId, @Nullable List<Boolean> data, SelectorColor selectorColor) {
        super(layoutResId, data);
        this.selectorColor = selectorColor;
    }

    @Override
    protected void convert(BaseViewHolder helper, Boolean item) {
        TextView tv = helper.getView(R.id.tv_zsq);
        if(selectorColor == SelectorColor.WHITE){
            if (item){
                tv.setBackground(getContext().getResources().getDrawable(R.drawable.seleter_check));
            }else {
                tv.setBackground(getContext().getResources().getDrawable(R.drawable.seleter_uncheck));
            }
        }else {
            if (item){
                tv.setBackground(getContext().getResources().getDrawable(R.drawable.seleter_bule_check));
            }else {
                tv.setBackground(getContext().getResources().getDrawable(R.drawable.seleter_uncheck));
            }
        }

    }
}
