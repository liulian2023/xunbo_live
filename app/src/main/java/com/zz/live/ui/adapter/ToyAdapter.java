package com.zz.live.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lovense.sdklibrary.Lovense;
import com.lovense.sdklibrary.LovenseToy;
import com.zz.live.R;
import com.zz.live.bean.ToyEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToyAdapter extends BaseQuickAdapter<LovenseToy, BaseViewHolder> {
    public ToyAdapter(int layoutResId, @Nullable List<LovenseToy> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LovenseToy toyEntity) {
        baseViewHolder.setText(R.id.oty_name_tv,toyEntity.getDeviceName());
        TextView oty_status_tv = baseViewHolder.getView(R.id.oty_status_tv);
       /* int status = toyEntity.getStatus();

        if(status==1){
            oty_status_tv.setText("已连接");
        }else {
            oty_status_tv.setText("未连接");
        }*/
        Activity activity = (Activity) getContext();
        boolean connected = Lovense.getInstance(activity.getApplication()).isConnected(toyEntity.getToyId());
        if(connected){
            oty_status_tv.setText("已连接");
        }else {
            oty_status_tv.setText("未连接");
        }
    }
}
