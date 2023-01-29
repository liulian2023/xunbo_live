package com.zz.live.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.bean.AnchorReportEntity;
import com.zz.live.utils.DateUtil;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnchorReportAdapter extends BaseQuickAdapter<AnchorReportEntity.DataBean.RecordsBean, BaseViewHolder> {
    public AnchorReportAdapter(int layoutResId, @Nullable List<AnchorReportEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AnchorReportEntity.DataBean.RecordsBean recordsBean) {
        ImageView anchor_manage_title_iv= baseViewHolder.getView(R.id.anchor_report_title_iv);
        TextView anchor_report_time_tv= baseViewHolder.getView(R.id.anchor_report_time_tv);
        GlideLoadViewUtil.LoadTitleView(getContext(),recordsBean.getImage(),anchor_manage_title_iv);
        baseViewHolder.setText(R.id.anchor_report_nickname_tv,recordsBean.getNickname());
        baseViewHolder.setText(R.id.anchor_report_username_tv,recordsBean.getUsername());
        anchor_report_time_tv.setText(DateUtil.secToTime(Integer.parseInt(recordsBean.getPlayTime())));


    }
}
