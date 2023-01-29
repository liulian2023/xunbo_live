package com.zz.live.utils;

import android.content.Context;
import android.view.View;

import com.zz.live.R;
import com.zz.live.bean.BannerData;
import com.zz.live.ui.adapter.BannerViewHolder;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;

import java.util.List;

/**
 * created  by ganzhe on 2019/11/20.
 */
public class BannerLoadViewUtil {
    /**
     * 创建广告轮播图
     * @param context
     * @param mBannerViewPager
     * @param bannerDataList
     * @param showIndicator
     * @param Interval
     * @param canLoop
     * @param autoPlay
     */
    public static void createBannerView(Context context, BannerViewPager<BannerData.DataBean.RecordsBean, BannerViewHolder> mBannerViewPager,
                                        List<BannerData.DataBean.RecordsBean> bannerDataList, boolean showIndicator, int Interval, boolean canLoop, boolean autoPlay,OnPageClickListener onPageClickListener){
        mBannerViewPager
                .setIndicatorVisibility(showIndicator == true?View.VISIBLE:View.INVISIBLE)
                //轮播切换时间
                .setInterval(Interval)
                //是否开启循环
                .setCanLoop(canLoop)
                //是否开启自动轮播
                .setAutoPlay(autoPlay)
                //设置圆角
                .setRoundCorner(12)
                //指示器颜色
                .setIndicatorColor(context.getResources().getColor(R.color.white), context.getResources().getColor(R.color.black))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                //指示器位置
                .setIndicatorGravity(IndicatorGravity.END)
                //设置指示器的宽度/直径
                .setIndicatorWidth(10)
                //设置指示器样式( DASH:矩形  CIRCLE:圆点)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                //页面滚动时间
                .setScrollDuration(1000)
                //绑定banner视图,以及数据的加载方式
                .setHolderCreator(BannerViewHolder::new)
                //点击事件
                .setOnPageClickListener(position -> {
                    // BannerData bannerData = bannerDataList.get(position);
                    //页面跳转
                //    String url = bannerDataList.get(position).getHref();
                //    String id = bannerDataList.get(position).getId();
                //    RouteUtils.start2WebViewActivity(context,url,id);
                if(onPageClickListener!=null){
                    onPageClickListener.onPageClickListener(position);
                }
                })
                //绑定数据源
                .create(bannerDataList);
    }


    public interface  OnPageClickListener{
        void onPageClickListener(int position);
    }
}
