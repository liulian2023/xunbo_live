package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.HouseKeeperEnity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HouseKeeperAdapter extends BaseQuickAdapter<HouseKeeperEnity, BaseViewHolder> {
    public HouseKeeperAdapter(int layoutResId, @Nullable List<HouseKeeperEnity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HouseKeeperEnity houseKeeperEnity) {
        baseViewHolder.setImageResource(R.id.house_title_tv,houseKeeperEnity.getDrawableId());
        baseViewHolder.setText(R.id.remark_tv,houseKeeperEnity.getRemark());
        baseViewHolder.setText(R.id.house_keeper_amount_tv,houseKeeperEnity.getAmount());
    }
}
