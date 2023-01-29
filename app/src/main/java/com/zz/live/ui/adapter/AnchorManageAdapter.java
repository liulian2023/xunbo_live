package com.zz.live.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnchorManageAdapter extends BaseQuickAdapter<AnchorManageEntity.DataBean.RecordsBean, BaseViewHolder> {
    public AnchorManageAdapter(int layoutResId, @Nullable List<AnchorManageEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AnchorManageEntity.DataBean.RecordsBean recordsBean) {
       ImageView anchor_manage_title_iv= baseViewHolder.getView(R.id.anchor_manage_title_iv);
        GlideLoadViewUtil.LoadTitleView(getContext(),recordsBean.getImage(),anchor_manage_title_iv);
        baseViewHolder.setText(R.id.anchor_manage_nickname_tv,recordsBean.getNickname());
        baseViewHolder.setText(R.id.anchor_manage_username_tv,recordsBean.getUsername());
    }
}
