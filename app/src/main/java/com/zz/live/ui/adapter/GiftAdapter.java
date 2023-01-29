package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.bean.GiftEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiftAdapter extends BaseQuickAdapter<GiftEntity, BaseViewHolder> {
    public GiftAdapter(int layoutResId, @Nullable List<GiftEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GiftEntity giftEntity) {

    }
}
