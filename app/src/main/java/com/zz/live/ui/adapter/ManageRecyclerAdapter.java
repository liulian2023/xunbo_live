package com.zz.live.ui.adapter;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.ManagerEntity;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ManageRecyclerAdapter extends BaseQuickAdapter<ManagerEntity.DataBean, BaseViewHolder> {
    Fragment fragment;
    public ManageRecyclerAdapter(int layoutResId, @Nullable List<ManagerEntity.DataBean> data,Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ManagerEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.manage_name_tv,dataBean.getNickName());
        GlideLoadViewUtil.fLoadTitleView(fragment,dataBean.getImage(),baseViewHolder.getView(R.id.manage_iv));
    }
}
