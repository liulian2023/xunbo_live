package com.zz.live.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.ForbiddenEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ForbiddenAdapter extends BaseQuickAdapter<ForbiddenEntity.DataBean, BaseViewHolder> {
    public ForbiddenAdapter(int layoutResId, @Nullable List<ForbiddenEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ForbiddenEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.platform_name_tv,dataBean.getPlatformCode());
        baseViewHolder.setText(R.id.username_tv,dataBean.getNickName());
        baseViewHolder.setText(R.id.end_time_tv,dataBean.getUnGagTime());
    }
}
