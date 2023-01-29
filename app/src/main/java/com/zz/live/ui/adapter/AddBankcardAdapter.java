package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.AddCardEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AddBankcardAdapter extends BaseQuickAdapter<AddCardEntity.DataBean.RecordsBean, BaseViewHolder> {
    public AddBankcardAdapter(int layoutResId, @Nullable List<AddCardEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AddCardEntity.DataBean.RecordsBean recordsBean) {
        baseViewHolder.setText(R.id.bank_name_tv,recordsBean.getName());
    }
}
