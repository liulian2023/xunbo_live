package com.zz.live;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.bean.LiveEntity;
import com.zz.live.utils.GlideLoadViewUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<LiveEntity, BaseViewHolder> {
    Context context;
    public TestAdapter(int layoutResId,Context context,  @Nullable List<LiveEntity> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LiveEntity liveEntity) {
//        baseViewHolder.setText(R.id.live_title_tv,liveEntity.getName());
        ImageView imageView =baseViewHolder.findView(R.id.live_iv);
        String url="http://pics4.baidu.com/feed/2cf5e0fe9925bc31bfe6323c3f89ddb7ca1370b3.jpeg?token=8c0dbbd8c71510e420916599992f67bc&s=4BA438626AD163E94D1421DE0000C0E2";
        GlideLoadViewUtil.LoadCornersView(context,url,12,imageView);
    }

}
