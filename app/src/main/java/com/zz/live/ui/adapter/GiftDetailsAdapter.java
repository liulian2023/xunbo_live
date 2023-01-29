package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.GiftDetailsEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiftDetailsAdapter extends BaseQuickAdapter<GiftDetailsEntity.DataBean.RecordsBean, BaseViewHolder> {
    public GiftDetailsAdapter(int layoutResId, @Nullable List<GiftDetailsEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, GiftDetailsEntity.DataBean.RecordsBean recordsBean) {
            baseViewHolder.setText(R.id.platform_id_tv,recordsBean.getPlatformCode());
            baseViewHolder.setText(R.id.send_username_tv,recordsBean.getGiverName());
            baseViewHolder.setText(R.id.gift_name_tv,recordsBean.getGiftName());
            baseViewHolder.setText(R.id.gift_amount_tv,recordsBean.getGiftAmount());
            baseViewHolder.setText(R.id.gift_num_tv,recordsBean.getGiftCount()+"");
    }
}
