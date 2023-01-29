package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.BankcardEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BankcardAdapter extends BaseQuickAdapter<BankcardEntity.DataBean, BaseViewHolder> {
    public BankcardAdapter(int layoutResId, @Nullable List<BankcardEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, BankcardEntity.DataBean dataBean) {
        baseViewHolder.setText(R.id.bank_name_tv,dataBean.getBankName());
        baseViewHolder.setText(R.id.bank_num_tv,dataBean.getCardNumber());
        ImageView selector_iv = baseViewHolder.getView(R.id.selector_iv);
        ImageView bankcard_big_iv = baseViewHolder.getView(R.id.bankcard_big_iv);
        if(dataBean.getStatus()==1){
            selector_iv.setVisibility(View.VISIBLE);
        }else {
            selector_iv.setVisibility(View.GONE);
        }
        int itemPosition = getItemPosition(dataBean);
        if(itemPosition%6==0){
            bankcard_big_iv.setBackgroundResource(R.drawable.hongka);
        }else if(itemPosition%6==1){
            bankcard_big_iv.setBackgroundResource(R.drawable.lanka);
        }else if(itemPosition%6==2){
            bankcard_big_iv.setBackgroundResource(R.drawable.blka);
        }else if(itemPosition%6==3){
            bankcard_big_iv.setBackgroundResource(R.drawable.lka);
        }else if(itemPosition%6==4){
            bankcard_big_iv.setBackgroundResource(R.drawable.zka);
        }else {
            bankcard_big_iv.setBackgroundResource(R.drawable.lanka2);

        }
    }
}
