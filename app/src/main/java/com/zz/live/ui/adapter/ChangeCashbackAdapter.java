package com.zz.live.ui.adapter;

import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.ChangeCachBackEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.math.BigDecimal;
import java.util.List;

public class ChangeCashbackAdapter extends BaseQuickAdapter<ChangeCachBackEntity.DataBean.VoListBean, BaseViewHolder> {
    public ChangeCashbackAdapter(int layoutResId, @Nullable List<ChangeCachBackEntity.DataBean.VoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ChangeCachBackEntity.DataBean.VoListBean voListBean) {
        ImageView title_iv = baseViewHolder.getView(R.id.title_iv);
        GlideLoadViewUtil.LoadTitleView(getContext(),voListBean.getImage(),title_iv);
        baseViewHolder.setText(R.id.username_tv,voListBean.getNickname());
        baseViewHolder.setText(R.id.id_tv,voListBean.getUsername());
        String balance = voListBean.getBalance();
        baseViewHolder.setText(R.id.amount_tv, balance);
        Button extract_btn= baseViewHolder.getView(R.id.extract_btn);
        if(new BigDecimal(balance).compareTo(BigDecimal.ZERO)>=1){
            //金额大于0
            extract_btn.setBackgroundResource(R.drawable.commit_btn_selector);
            extract_btn.setTextColor(Color.WHITE);
            extract_btn.setClickable(true);
        }else {
            //余额小于0
            extract_btn.setBackgroundResource(R.drawable.change_cashback_extract_cannot_click_shape);
            extract_btn.setTextColor(Color.parseColor("#333333"));
            extract_btn.setClickable(false);
        }
    }
}
