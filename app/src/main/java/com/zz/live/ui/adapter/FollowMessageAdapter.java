package com.zz.live.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.FollowMessageEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FollowMessageAdapter extends BaseQuickAdapter<FollowMessageEntity.DataBean.RecordsBean, BaseViewHolder> {


    public FollowMessageAdapter(int layoutResId, @Nullable List<FollowMessageEntity.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, FollowMessageEntity.DataBean.RecordsBean recordsBean) {
        baseViewHolder.setText(R.id.follow_name_tv,recordsBean.getPlatformUserName());
        baseViewHolder.setText(R.id.follow_date_tv,recordsBean.getCreateTime());
        int type = recordsBean.getType();
        if(type==0){
            baseViewHolder.setText(R.id.follow_content_tv,"取消关注");
        }else {
            baseViewHolder.setText(R.id.follow_content_tv,"关注了你");
        }
        int isRead = recordsBean.getIsRead();
        if(isRead==1){
            baseViewHolder.getView(R.id.un_read_tv).setVisibility(View.INVISIBLE);
        }else {
            baseViewHolder.getView(R.id.un_read_tv).setVisibility(View.VISIBLE);

        }
    }
}
