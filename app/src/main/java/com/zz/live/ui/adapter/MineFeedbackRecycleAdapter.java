package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.zz.live.R;
import com.zz.live.bean.MineFeedbackModel;
import com.zz.live.utils.StringMyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MineFeedbackRecycleAdapter extends CommonAdapter<MineFeedbackRecycleAdapter.MyHolder, MineFeedbackModel.DataBean.RecordsBean> {
    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        MineFeedbackModel.DataBean.RecordsBean itemModel = getItemModel(position);
        String time = itemModel.getCreateTime();
/*        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(Long.parseLong(time)));*/
        commonHolder.timeTv.setText("反馈时间:"+ time);
        commonHolder.typeTv.setText("反馈类型:"+itemModel.getOpinionTypeExplain());
        commonHolder.contentTv.setText("反馈内容:"+itemModel.getOpinionContent());
        String reply = itemModel.getReplyContent();
        if(StringMyUtil.isEmptyString(reply)){
            commonHolder.replyTv.setText("官方回复:暂无回复");
        }else {
            commonHolder.replyTv.setText("官方回复:"+reply);

        }
    }

    public MineFeedbackRecycleAdapter(ArrayList<MineFeedbackModel.DataBean.RecordsBean> list) {
        super(list);
    }

    @Override
    public int getLayOutRes() {
        return R.layout.mine_feedback_recycle_item;
    }

    public static class MyHolder extends CommonHolder {
        private TextView timeTv;
        private TextView typeTv;
        private TextView contentTv;
        private TextView replyTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            timeTv=itemView.findViewById(R.id.feedback_time_tv);
            typeTv=itemView.findViewById(R.id.feedback_type);
            contentTv=itemView.findViewById(R.id.feedback_content);
            replyTv=itemView.findViewById(R.id.gf_Reply_tv);
        }
    }
}
