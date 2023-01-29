package com.zz.live.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zz.live.R;
import com.zz.live.bean.ChatUserEntity;
import com.zz.live.ui.adapter.ChatUserAdapter;

import java.util.ArrayList;

public class OnlineNumUtil {
    ArrayList<Integer>imageIdlist = new ArrayList<>();
    private static OnlineNumUtil instance;
    public static OnlineNumUtil newInstance(){
        if(instance==null){
            instance = new OnlineNumUtil();
        }
        return instance;
    }
    public void initOnlineRecycler(Context context, ChatUserAdapter chatUserAdapter, RecyclerView recy_renshu, ArrayList<ChatUserEntity>chatUserEntityArrayList) {
        imageIdlist.add(R.drawable.pic_1);
        imageIdlist.add(R.drawable.pic_2);
        imageIdlist.add(R.drawable.pic_3);
        imageIdlist.add(R.drawable.pic_4);
        imageIdlist.add(R.drawable.pic_5);
        imageIdlist.add(R.drawable.pic_6);
        imageIdlist.add(R.drawable.pic_7);
        imageIdlist.add(R.drawable.pic_8);
        imageIdlist.add(R.drawable.pic_9);
        imageIdlist.add(R.drawable.pic_10);
        imageIdlist.add(R.drawable.pic_11);
        imageIdlist.add(R.drawable.pic_12);
        imageIdlist.add(R.drawable.pic_13);
        imageIdlist.add(R.drawable.pic_14);
        imageIdlist.add(R.drawable.pic_15);
        imageIdlist.add(R.drawable.pic_16);
        imageIdlist.add(R.drawable.pic_17);
        imageIdlist.add(R.drawable.pic_18);
        imageIdlist.add(R.drawable.pic_19);
        imageIdlist.add(R.drawable.pic_20);
        chatUserAdapter = new ChatUserAdapter(R.layout.item_chatuser,chatUserEntityArrayList,context);
        recy_renshu.setLayoutManager(new LinearLayoutManager(context));
        recy_renshu.setAdapter(chatUserAdapter);
    }

}
