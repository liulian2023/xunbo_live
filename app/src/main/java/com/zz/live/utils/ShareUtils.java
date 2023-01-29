package com.zz.live.utils;

import android.content.Context;
import android.content.Intent;

public class ShareUtils {
    //本地分享
    public static void start2Share(Context context, String url) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        //     share_intent.putExtra(Intent.EXTRA_SUBJECT, title);//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, url);//添加分享链接内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "选择分享应用");
        context.startActivity(share_intent);
    }
}
