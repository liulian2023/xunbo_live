/*
 * Copyright (c) 2019.  ganzhe
 */

package com.zz.live.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zz.live.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class GlideLoadViewUtil {

    //Glide加载圆角矩形图片
    public static void LoadRetcCirView(Context context,String imgUrl,int Corner,ImageView imageView){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(Corner);
        //通过RequestOptions扩展功能
        RequestOptions options= RequestOptions.bitmapTransform(roundedCorners)/*.placeholder(R.drawable.ucrop_ic_next)*/;
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void LoadRetcCirView(Context context,int drawable,int Corner,ImageView imageView){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(Corner);
        //通过RequestOptions扩展功能
        RequestOptions options= RequestOptions.bitmapTransform(roundedCorners)/*.placeholder(R.drawable.ucrop_ic_next)*/;
        Glide.with(context)
                .load(drawable)
                .apply(options)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //glide加载圆形图
    public static void loadCircleView(Context context, String imgUrl, ImageView imageView){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .circleCrop()
                .skipMemoryCache(false)
     //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
    //glide加载圆形图
    public static void cpLoadCircleView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context)
                .load(Utils.cpCheckImageUrl(imgUrl))
                .circleCrop()
                .skipMemoryCache(false)
                //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


    }
    public static void cpLoadNormalView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context)
                .load(Utils.cpCheckImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
    //glide加载圆形图
    public static void LoadTitleView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .circleCrop()
                .skipMemoryCache(false)
                .error(R.drawable.system_default_title)
                           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


    }
    //glide加载圆形图
    public static void FLoadCircleView(Fragment fragment, String imgUrl, ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .circleCrop()
                .skipMemoryCache(false)
                //           .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    //glide加载圆形图
    public static void fLoadTitleView(Fragment fragment, String imgUrl, ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .circleCrop()
                .skipMemoryCache(false)
                .error(R.drawable.system_default_title)
                .into(imageView);
    }
//    ----------------------------------------------------------------------------------------------------------------------------------------------



    public static void LoadCornersView(Context context,String imgUrl,int corners,ImageView imageView){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(corners)))
                .into(imageView);
    }


    public static void FLoadCornersView(Fragment fragment, String imgUrl, int corners, ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(corners)))
                .into(imageView);
    }

    public static void LoadNormalView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
    public static void LoadNormalView(Context context,String imgUrl,ImageView imageView,int error){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(error)
                .into(imageView);
    }

    public static void FLoadNormalView(Fragment fragment, String imgUrl, ImageView imageView){
        Glide.with(fragment)
                .load(Utils.checkImageUrl(imgUrl))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void LoadMaoBoliNormalView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context)
                .load(Utils.checkImageUrl(imgUrl))
                .skipMemoryCache(true)
                .apply(RequestOptions.bitmapTransform(new GlideBlurTransformation(context)))
                .into(imageView);

    }
    //毛玻璃效果的背景图片
    public static void loadBlurView(Context context,String imgUrl,ImageView imageView){
        Glide.with(context).load(Utils.checkImageUrl(imgUrl))
                .apply(bitmapTransform(new BlurTransformation(10,3)))
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

    }


}
