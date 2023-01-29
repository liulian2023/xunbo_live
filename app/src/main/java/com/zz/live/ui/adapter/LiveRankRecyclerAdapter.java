package com.zz.live.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.Const;
import com.zz.live.bean.LiveRankEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.Utils;

import java.util.List;

public class LiveRankRecyclerAdapter extends BaseQuickAdapter<LiveRankEntity.DataBean.RecordsBean, BaseViewHolder> {
    Fragment fragment;
    public LiveRankRecyclerAdapter(int layoutResId, @Nullable List<LiveRankEntity.DataBean.RecordsBean> data, Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveRankEntity.DataBean.RecordsBean item) {
        int adapterPosition = helper.getAdapterPosition();
        TextView rank_num_tv= helper.getView(R.id.rank_num_tv);
        ImageView title_iv= helper.getView(R.id.title_iv);
        ImageView grade_iv= helper.getView(R.id.grade_iv);
        if(adapterPosition==0){
            rank_num_tv.setBackgroundResource(R.drawable.rank_1);
            rank_num_tv.setText("");
        }else if(adapterPosition==1){
            rank_num_tv.setBackgroundResource(R.drawable.rank_2);
            rank_num_tv.setText("");
        }else if(adapterPosition==2){
            rank_num_tv.setBackgroundResource(R.drawable.rank_3);
            rank_num_tv.setText("");
        }else {
            rank_num_tv.setText(adapterPosition+1+"");
            rank_num_tv.setBackground(null);
        }
        GlideLoadViewUtil.fLoadTitleView(fragment, Utils.checkImageUrl(item.getGiverUserImage()),title_iv);
        helper.setText(R.id.nick_name_tv,item.getGiverName());
        helper.setText(R.id.gift_amount_tv,item.getTotalAmount());
//        grade_iv.setImageResource(getLevelDrawable(Integer.parseInt(item.GET())+1));
    }
    private int getLevelDrawable(int level) {
        int drawType;
        int[] levelIcon = Const.getLevelIcon(MyApplication.getInstance());
        drawType = levelIcon[level - 1];
        return drawType;
    }
}
