package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.WithdrawNoteEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WithdrawAdapter  extends BaseQuickAdapter<WithdrawNoteEntity.DataBean.RecordsBean, BaseViewHolder> {
    public WithdrawAdapter(int layoutResId, @Nullable List<WithdrawNoteEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, WithdrawNoteEntity.DataBean.RecordsBean recordsBean) {
    baseViewHolder.setText(R.id.withdraw_amount_tv,recordsBean.getAmount());
    baseViewHolder.setText(R.id.withdraw_date_tv,recordsBean.getCreateTime());
    //提现状态 0不通过1通过 2审核中
        int status = recordsBean.getStatus();
        String remark;
        if(status==0){
            remark="已拒绝";
        }else if(status==1){
            remark="到账成功";
        }else {
            remark="审核中";
        }
        baseViewHolder.setText(R.id.application_status_tv,remark);
    }
}
