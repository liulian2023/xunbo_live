package com.zz.live.ui.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.MineEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MineAdapter extends BaseQuickAdapter<MineEntity, BaseViewHolder> {
    public MineAdapter(int layoutResId, @Nullable List<MineEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MineEntity mineEntity) {
        baseViewHolder.setText(R.id.typename_tv,mineEntity.getTypeName());
        baseViewHolder.setImageResource(R.id.type_iv,mineEntity.getImageId());
        int itemPosition = getItemPosition(mineEntity);
        List<MineEntity> data = getData();
        TextView curtifition_status_tv =baseViewHolder.getView(R.id.curtifition_status_tv);
        int status = mineEntity.getStatus();
        //  memberStatus 0 审核中  1 已认证  2认证失败 3 未认证
        if(status==0){
            curtifition_status_tv.setText("审核中");
        }else if(status==1){
            curtifition_status_tv.setText("已认证");
        }else if(status==2){
            curtifition_status_tv.setText("认证失败");
        }else if(status==3){
            curtifition_status_tv.setText("未认证");
        }else {
            curtifition_status_tv.setText("");
        }
        if(itemPosition==0){
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_top_8radios_ripple);
        }else if(itemPosition==data.size()-1){
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_bottom_8radios_ripple);
        }else {
            baseViewHolder.itemView.setBackgroundResource(R.drawable.white_normal_ripple);
        }
    }
}
