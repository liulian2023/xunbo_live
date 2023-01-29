package com.zz.live.ui.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.LiveDurationEntity;
import com.zz.live.utils.DateUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class LIveDurationAdaper extends BaseQuickAdapter<LiveDurationEntity.DataBean.RecordsBean, BaseViewHolder> {
    public LIveDurationAdaper(int layoutResId, @Nullable List<LiveDurationEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LiveDurationEntity.DataBean.RecordsBean recordsBean) {
            baseViewHolder.setText(R.id.start_date_tv,recordsBean.getStartTime());
            baseViewHolder.setText(R.id.end_date_tv,recordsBean.getEndTime());
            baseViewHolder.setText(R.id.live_duration_tv, DateUtil.secToTime(recordsBean.getLiveTime()));
    }
}
