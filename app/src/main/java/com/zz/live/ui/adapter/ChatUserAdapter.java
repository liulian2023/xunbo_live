package com.zz.live.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.ChatUserEntity;

import java.util.List;

public class ChatUserAdapter extends BaseQuickAdapter<ChatUserEntity, BaseViewHolder> {
    Context context;
    public ChatUserAdapter(int layoutResId, @Nullable List<ChatUserEntity> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatUserEntity item) {
        ImageView iv = helper.getView(R.id.iv_item_chatuser);
        Glide.with(context)
                .load(item.getImageId())
                .circleCrop()
                .skipMemoryCache(false)
                //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
    }
}
