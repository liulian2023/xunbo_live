package com.zz.live.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.SystemMessageEntity;
import com.zz.live.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SystemMessageAdapter extends BaseQuickAdapter<SystemMessageEntity.DataBean.RecordsBean, BaseViewHolder> {
    Context context;
    public SystemMessageAdapter(int layoutResId, @Nullable List<SystemMessageEntity.DataBean.RecordsBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SystemMessageEntity.DataBean.RecordsBean recordsBean) {
        baseViewHolder.setText(R.id.message_type_tv,"系统消息");
        baseViewHolder.setText(R.id.message_date_tv,recordsBean.getCreateTime());
        baseViewHolder.setText(R.id.message_content_tv,recordsBean.getTitle());
        TextView message_num_tv=  baseViewHolder.getView(R.id.message_num_tv);
        message_num_tv.setText("");
        ViewGroup.LayoutParams layoutParams = message_num_tv.getLayoutParams();
        layoutParams.height=Utils.dip2px(context,5);
        layoutParams.width=Utils.dip2px(context,5);
        message_num_tv.setLayoutParams(layoutParams);
        //isRead 0未读 1已读
        int isRead = recordsBean.getIsRead();
        if(isRead==0){
            message_num_tv.setVisibility(View.VISIBLE);
        }else {
            message_num_tv.setVisibility(View.INVISIBLE);

        }
    }
}
