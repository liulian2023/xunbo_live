/*
 * Copyright (c) 2019.  ganzhe
 */

package com.zz.live.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zz.live.R;
import com.zz.live.bean.BannerData;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.Utils;
import com.zhpan.bannerview.holder.ViewHolder;

public class BannerViewHolder implements ViewHolder<BannerData.DataBean.RecordsBean> {
    private ImageView mImageView;
    @Override
    public View createView(ViewGroup viewGroup, Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, viewGroup, false);
        mImageView = view.findViewById(R.id.banner_imageView);
        return view;
    }

    @Override
    public void onBind(Context context, BannerData.DataBean.RecordsBean data, int position, int size) {
        GlideLoadViewUtil.LoadCornersView(context, Utils.checkImageUrl(data.getHeadImage()),12,mImageView);
//        GlideLoadViewUtil.LoadCornersView(context,"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=111713540,615806613&fm=26&gp=0.jpg",12,mImageView);
    }



}
