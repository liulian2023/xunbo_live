package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.RankEntity;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RankAdapter extends BaseQuickAdapter<RankEntity.DataBean.RecordsBean, BaseViewHolder> {
    Fragment fragment;

    public RankAdapter(int layoutResId, @Nullable List<RankEntity.DataBean.RecordsBean> data, Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RankEntity.DataBean.RecordsBean recordsBean) {
        int itemPosition = getItemPosition(recordsBean);
        baseViewHolder.setText(R.id.rank_num_tv,(itemPosition+4)+"");
        baseViewHolder.setText(R.id.rank_name_tv,recordsBean.getNickName());
        baseViewHolder.setText(R.id.rank_gift_amount_tv,recordsBean.getTotalAmount());
        ImageView rank_title_iv = baseViewHolder.getView(R.id.rank_title_iv);
        GlideLoadViewUtil.fLoadTitleView(fragment,recordsBean.getImage(),rank_title_iv);
        ImageView rank_sex_iv = baseViewHolder.getView(R.id.rank_sex_iv);
        rank_sex_iv.setVisibility(View.INVISIBLE);//性别不要, 直接隐藏
        if(recordsBean.getSex()==0){
            baseViewHolder.setImageResource(R.id.rank_sex_iv,R.drawable.xingbie_nan);
        }else {
            baseViewHolder.setImageResource(R.id.rank_sex_iv,R.drawable.xingbie_nv);

        }
    }
}
