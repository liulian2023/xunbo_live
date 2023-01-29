package com.zz.live.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zz.live.R;
import com.zz.live.bean.HomeNoticeEntity;


import java.util.ArrayList;

public class HomeNoticeAdapter extends RecyclerView.Adapter<HomeNoticeAdapter.MyHolder>/* implements View.OnClickListener*/ {
    private ArrayList<HomeNoticeEntity.DataBean.RecordsBean> homeNoticeEntityArrayList =new ArrayList<>();
    Context context;
    public HomeNoticeAdapter(ArrayList<HomeNoticeEntity.DataBean.RecordsBean> homeNoticeEntityArrayList, Context context) {
        this.homeNoticeEntityArrayList = homeNoticeEntityArrayList;
        this.context = context;
    }


    public  static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view, int position);
    }
    private HomeNoticeAdapter.OnRecyclerViewItemClickListener mOnItemClickListener =null;

    public void setOnItemClickListener(HomeNoticeAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public HomeNoticeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_pop_recycle_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeNoticeAdapter.MyHolder myHolder, int position) {
        HomeNoticeEntity.DataBean.RecordsBean homeNoticeEntity = homeNoticeEntityArrayList.get(position);
        if(position==0){
            myHolder.linearLayoutHide.setVisibility(View.VISIBLE);
            myHolder.redSplite.setVisibility(View.VISIBLE);
            myHolder.linearLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));
        }
        myHolder.title.setText(homeNoticeEntity.getTitle());
        WebSettings settings = myHolder.content.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
//        myHolder.content.loadData(homePopMedol.getContent(),"text/html;charset=utf-8", "utf-8");
        myHolder.content.loadDataWithBaseURL(null,"<style> img{ max-width:100%; height:auto;} </style>"+ homeNoticeEntity.getContent(),"text/html", "utf-8",null);

        myHolder.titleSmall.setText(homeNoticeEntity.getTitle());
//        if(homeNoticeEntity.getImgUrl().equals("null")){
/*        if(homePopMedol.getImgUrl().equals(FirstImageUrl+"null")){
            myHolder.imageView.setVisibility(View.GONE);
        }
        else {
            Glide.with(context)
                    .load(homeNoticeEntity.getImgUrl())
                    .into(myHolder.imageView);
        }*/
        myHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myHolder.linearLayoutHide.getVisibility()== View.GONE){
                myHolder.linearLayoutHide.setVisibility(View.VISIBLE);
                myHolder.linearLayout.setBackgroundColor(Color.parseColor("#f2f2f2"));
                myHolder.redSplite.setVisibility(View.VISIBLE);
                }
                else{
                    myHolder.linearLayoutHide.setVisibility(View.GONE);
                    myHolder.linearLayout.setBackgroundColor(Color.WHITE);
                    myHolder.redSplite.setVisibility(View.GONE);
                }
            }

        });
        myHolder.linearLayout.setTag(position);
    }
    @Override
    public int getItemCount() {
        return homeNoticeEntityArrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView titleSmall;
        WebView content;
        ImageView imageView;
        LinearLayout linearLayoutHide;
        LinearLayout linearLayout;
        TextView redSplite;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.home_pop_title);
            titleSmall= itemView.findViewById(R.id.home_tiele_small);
            content= itemView.findViewById(R.id.home_pop_content);
            imageView= itemView.findViewById(R.id.home_pop_image);
            linearLayout= itemView.findViewById(R.id.show_more);
            linearLayoutHide= itemView.findViewById(R.id.home_pop_more_linear);
            redSplite= itemView.findViewById(R.id.red_splite);
        }
    }
}
