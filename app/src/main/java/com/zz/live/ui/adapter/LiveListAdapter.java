package com.zz.live.ui.adapter;

import android.app.Activity;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.LiveEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.ScreenUtils;
import com.zz.live.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.scwang.smartrefresh.layout.util.DensityUtil.dp2px;

public class LiveListAdapter extends BaseQuickAdapter<LiveEntity.DataBean.RecordsBean, BaseViewHolder> {
    Fragment fragment;
    Activity activity;

    public LiveListAdapter(int layoutResId, @Nullable List<LiveEntity.DataBean.RecordsBean> data,Fragment fragment) {
        super(layoutResId, data);
        this.fragment=fragment;
    }
    public LiveListAdapter(int layoutResId, @Nullable List<LiveEntity.DataBean.RecordsBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity=activity;
    }

    private int getWidth(){
        int mScreenWidth = ScreenUtils.getWight(activity==null?fragment.getContext():activity);
        return (mScreenWidth-dp2px(0)-ScreenUtils.dip2px(activity==null?fragment.getContext():activity, 10))/2;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LiveEntity.DataBean.RecordsBean recordsBean) {
     ImageView live_iv=  baseViewHolder.getView(R.id.live_iv);
        live_iv.getLayoutParams().width=getWidth();
        live_iv.getLayoutParams().height=getWidth();
        GlideLoadViewUtil.FLoadCornersView(fragment,Utils.checkImageUrl(recordsBean.getCover()),12,live_iv);
        baseViewHolder.setText(R.id.live_title_tv,recordsBean.getTitle());
//        baseViewHolder.setText(R.id.live_lottery_name_tv)
    }
}
