package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.NatureEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NatureAdapter extends BaseQuickAdapter<NatureEntity, BaseViewHolder> {
    public NatureAdapter(int layoutResId, @Nullable List<NatureEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, NatureEntity natureEntity) {
        baseViewHolder.setText(R.id.nature_content_tv,natureEntity.getContent());
    }
}
