package com.zz.live.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zz.live.R;
import com.zz.live.bean.PrivateMessageEntity;
import com.zz.live.utils.Utils;

import java.util.ArrayList;

import cn.we.swipe.helper.WeSwipeHelper;

public class PrivateMessageAdapter extends CommonAdapter<PrivateMessageAdapter.MyHolder,PrivateMessageEntity.DataBean.RecordsBean>{
    Context context;
    int clickCount=0;

    public PrivateMessageAdapter(ArrayList<PrivateMessageEntity.DataBean.RecordsBean> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public void handleViewHolder(MyHolder commonHolder, int position) {
        PrivateMessageEntity.DataBean.RecordsBean itemModel = getItemModel(position);
        commonHolder.message_type_tv.setText(itemModel.getTitle());
        commonHolder.message_content_tv.setText(itemModel.getContentTxt());
        commonHolder.message_date_tv.setText(itemModel.getCreateTime());
        TextView message_num_tv = commonHolder.message_num_tv;

        message_num_tv.setText("");
        ViewGroup.LayoutParams layoutParams = message_num_tv.getLayoutParams();
        layoutParams.height= Utils.dip2px(context,5);
        layoutParams.width=Utils.dip2px(context,5);
        message_num_tv.setLayoutParams(layoutParams);
        //isRead 0未读 1已读
        int isRead = itemModel.getIsRead();
        if(isRead==0){
            message_num_tv.setVisibility(View.VISIBLE);
        }else {
            message_num_tv.setVisibility(View.INVISIBLE);
        }
        commonHolder.message_iv.setImageResource(R.drawable.sixinxiaox_tx);
        ConstraintLayout message_slide_constraint = commonHolder.message_slide_constraint;
        message_slide_constraint.setTag(position);
        message_slide_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        commonHolder.message_delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteClickListener!=null){
                    clickCount++;
                    if(clickCount%2==0){
                        return;
                    }
                    onDeleteClickListener.onDelete(commonHolder.getAdapterPosition());
                }
            }
        });
    }
    public void removeDataByPosition(int position){
        if(position>=0&&position<list.size()){
            list.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,list.size()-1);
        }
    }
    @Override
    public int getLayOutRes() {
        return R.layout.message_recycler_item;
    }

    public static class MyHolder extends CommonHolder implements WeSwipeHelper.SwipeLayoutTypeCallBack {
        TextView message_delete_tv;
        ConstraintLayout message_slide_constraint;
        ImageView message_iv;
        TextView message_type_tv;
        TextView message_content_tv;
        TextView message_date_tv;
        TextView message_num_tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            message_delete_tv=itemView.findViewById(R.id.message_delete_tv);
            message_slide_constraint=itemView.findViewById(R.id.message_slide_constraint);
            message_iv=itemView.findViewById(R.id.message_iv);
            message_type_tv=itemView.findViewById(R.id.message_type_tv);
            message_content_tv=itemView.findViewById(R.id.message_content_tv);
            message_date_tv=itemView.findViewById(R.id.message_date_tv);
            message_num_tv=itemView.findViewById(R.id.message_num_tv);
        }
        //返回删除按钮的宽度(即需要滑动按钮的宽度才会出现删除按钮)
        @Override
        public float getSwipeWidth() {
            return message_delete_tv.getWidth();
        }
        //需要滑动的view
        @Override
        public View needSwipeLayout() {
            return message_slide_constraint;
        }
        //未滑动之前需要显示的布局(此处就是需要滑动的view slideLinear)
        @Override
        public View onScreenView() {
            return message_slide_constraint;
        }
    }
}
