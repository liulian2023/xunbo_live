package com.zz.live.ui.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.AgentCenterEntity;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class AgentAdapter extends BaseQuickAdapter<AgentCenterEntity.DataBean.RecordsBean, BaseViewHolder> {
    Activity activity;
    public AgentAdapter(int layoutResId, @Nullable List<AgentCenterEntity.DataBean.RecordsBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity  = activity;
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AgentCenterEntity.DataBean.RecordsBean recordsBean) {
        baseViewHolder.setText(R.id.num_tv,getItemPosition(recordsBean)+1+"");
        GlideLoadViewUtil.LoadTitleView(activity, Utils.checkImageUrl(recordsBean.getImage()),baseViewHolder.getView(R.id.title_iv));
        baseViewHolder.setText(R.id.username_tv,recordsBean.getNickname());
        baseViewHolder.setText(R.id.fans_num_tv,recordsBean.getFansNumber()+"");
        TextView certification_tv =  baseViewHolder.getView(R.id.certification_tv);
        TextView is_live_tv =  baseViewHolder.getView(R.id.is_live_tv);
        int memberStatus = recordsBean.getMemberStatus();
        int isLive = recordsBean.getIsLive();
//        memberStatus 0 审核中  1 已认证  2认证失败 3 未认证
        if(memberStatus==0){
            certification_tv.setText("审核中");
            certification_tv.setTextColor(Color.parseColor("#0FBA14"));
        }else if(memberStatus==1){
            certification_tv.setText("已认证");
            certification_tv.setTextColor(Color.parseColor("#315FFF"));
        }else if(memberStatus==2){
            certification_tv.setText("已拒绝");
            certification_tv.setTextColor(Color.parseColor("#FF3D75"));
        }else {
            certification_tv.setText("未认证");
            certification_tv.setTextColor(Color.parseColor("#666666"));
        }
        if(isLive==0){
            is_live_tv.setText("未开");
            is_live_tv.setTextColor(Color.parseColor("#FF3D75"));
        }else {
            is_live_tv.setText("直播中");
            is_live_tv.setTextColor(Color.parseColor("#0FBA14"));
        }
    }
}
