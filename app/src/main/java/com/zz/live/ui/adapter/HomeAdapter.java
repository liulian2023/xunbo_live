package com.zz.live.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.HomeEntity;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeEntity, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeEntity homeEntity) {
        baseViewHolder.setText(R.id.typename_tv,homeEntity.getTypeName());
        baseViewHolder.setImageResource(R.id.type_iv,homeEntity.getImageId());
        int itemPosition = getItemPosition(homeEntity);
        List<HomeEntity> data = getData();
        if(itemPosition==0){
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_top_8radios_ripple);
        }else if(itemPosition==data.size()-1){
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_bottom_8radios_ripple);
        }else {
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_normal_ripple);
        }
    }
}
