package com.zz.live.ui.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.AnchorDataEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AnchorDataAdapter extends BaseQuickAdapter<AnchorDataEntity.DataBean.RecordsBean, BaseViewHolder> {
    Activity activity;
    public AnchorDataAdapter(int layoutResId, @Nullable List<AnchorDataEntity.DataBean.RecordsBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity=activity;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AnchorDataEntity.DataBean.RecordsBean recordsBean) {
        baseViewHolder.setText(R.id.num_tv,getItemPosition(recordsBean)+1+"");
        GlideLoadViewUtil.LoadTitleView(activity, Utils.checkImageUrl(recordsBean.getImage()),baseViewHolder.getView(R.id.title_iv));
        baseViewHolder.setText(R.id.username_tv,recordsBean.getNickname());
        //礼物数量
        baseViewHolder.setText(R.id.gift_amount_tv,recordsBean.getGiftAmount()+"");
        //直播时长
        baseViewHolder.setText(R.id.live_date_tv,recordsBean.getPlayTime()+"");
        //抽成金额
        baseViewHolder.setText(R.id.draw_in_amount_tv,recordsBean.getCommission()+"");

    }
}
