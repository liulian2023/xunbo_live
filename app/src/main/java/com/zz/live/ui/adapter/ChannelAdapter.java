package com.zz.live.ui.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChannelAdapter extends BaseQuickAdapter<HomeClassfyEntity.DataBean, BaseViewHolder> {
    Activity activity;
    public ChannelAdapter(int layoutResId, @Nullable List<HomeClassfyEntity.DataBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeClassfyEntity.DataBean dataBean) {
        GlideLoadViewUtil.LoadTitleView(activity,dataBean.getImgUrl(),baseViewHolder.getView(R.id.lottery_litile_iv));
        baseViewHolder.setText(R.id.channel_name_tv,dataBean.getName());
        baseViewHolder.setText(R.id.channel_tip_tv,dataBean.getDescription());
    }
}
