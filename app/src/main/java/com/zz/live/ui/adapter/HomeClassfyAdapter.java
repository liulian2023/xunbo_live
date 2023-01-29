package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.zz.live.R;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.Utils;

import java.util.ArrayList;

public class HomeClassfyAdapter extends CommonAdapter<HomeClassfyAdapter.MyHodler, HomeClassfyEntity.DataBean> {
    Fragment fragment;

    public HomeClassfyAdapter(ArrayList<HomeClassfyEntity.DataBean> list, Fragment fragment) {
        super(list);
        this.fragment = fragment;
    }

    @Override
    public void handleViewHolder(MyHodler commonHolder, int position) {
        HomeClassfyEntity.DataBean itemModel = getItemModel(position);
        GlideLoadViewUtil.FLoadCircleView(fragment, Utils.checkImageUrl(itemModel.getImgUrl()),commonHolder.imageView);
        commonHolder.textView.setText(itemModel.getName());
        commonHolder.itemView.setTag(position);
        commonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
    }

    @Override
    public int getLayOutRes() {
        return R.layout.home_live_classfy_recycler_item_layout;
    }

    public static class MyHodler extends CommonHolder {
        ImageView imageView;
        TextView textView;
        public MyHodler(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.home_classify_iv);
            textView=itemView.findViewById(R.id.home_classify_tv);
        }
    }
}
