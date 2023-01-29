package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.BaseUrlEntity;

import java.util.List;

public class BaseUrlAdapter extends BaseQuickAdapter<BaseUrlEntity.DataBean, BaseViewHolder> {
    public BaseUrlAdapter(int layoutResId, @Nullable List<BaseUrlEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseUrlEntity.DataBean item) {
        helper.setText(R.id.line_num_tv,"线路"+(helper.getAdapterPosition()+1));
        helper.setText(R.id.url_tv,item.getDomain());
      ImageView selector_iv= helper.getView(R.id.selector_iv);
       if(item.isCheck()){
           selector_iv.setVisibility(View.VISIBLE);
       }else {
           selector_iv.setVisibility(View.GONE);
       }

    }

}
