package com.zz.live.ui.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.InComeEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InComeAdapter extends BaseQuickAdapter<InComeEntity.DataBean.RecordsBean, BaseViewHolder> {
    public InComeAdapter(int layoutResId, @Nullable List<InComeEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, InComeEntity.DataBean.RecordsBean recordsBean) {
       TextView amount_tv= baseViewHolder.getView(R.id.cash_back_amount_tv);
        int type = recordsBean.getType();
        if(type==0){//收入
            amount_tv.setTextColor(Color.parseColor("#F92F51"));
            baseViewHolder.setText(R.id.cash_back_amount_tv,"+"+recordsBean.getAmount());
        }else {//支出
            amount_tv.setTextColor(Color.parseColor("#0A9F0D"));
            baseViewHolder.setText(R.id.cash_back_amount_tv,"-"+recordsBean.getAmount());
        }
        baseViewHolder.setText(R.id.date_tv,recordsBean.getRemark());
        baseViewHolder.setText(R.id.type_tv,"");
        baseViewHolder.setText(R.id.expenses_amount_tv,recordsBean.getCreateTime());

    }
}
