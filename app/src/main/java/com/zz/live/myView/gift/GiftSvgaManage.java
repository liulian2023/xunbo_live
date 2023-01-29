package com.zz.live.myView.gift;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.zz.live.R;
import com.zz.live.bean.LiveGiftSVGAEntity;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * svga 动画管理
 */
public class GiftSvgaManage {
    private String TAG="SvgaManage";
    private ArrayList<LiveGiftSVGAEntity> svgaList;//当前等待播放的动画集合
    private SVGAImageView svgaImageView; // 播放控件
    private Context context;
    SVGAParser svgaParser;
    public GiftSvgaManage(Context context, SVGAImageView svgaImageView) {
        this.context = context;
        this.svgaImageView = svgaImageView;
        svgaList = new ArrayList<>();
        svgaParser = new SVGAParser(context);
        initSvgaImageView();
    }

    /**
     * 动画播放监听
     */
    private void initSvgaImageView() {
        svgaImageView.setLoops(1);
        svgaImageView.setFillMode(SVGAImageView.FillMode.Backward);
        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            /**
             * 播放结束后从集合中移出当前播放的动画,然后接着播放集合中第一个动画
             */
            @Override
            public void onFinished() {
                if(svgaList!=null && svgaList.size()>0){
                    svgaList.remove(0);
                    if(svgaList!=null && svgaList.size()>0){
                        decodeSvgaFromUrl(svgaList.get(0));
                    }else {
                        stopSVGA();
                    }
                }else {
                    stopSVGA();
                }
            }

            @Override
            public void onRepeat() {

            }
            @Override
            public void onStep(int i, double v) {

            }
        });
    }


    public void startAnimator(LiveGiftSVGAEntity liveGiftSVGAEntity) {
        svgaList.add(svgaList.size(), liveGiftSVGAEntity);
        if(svgaList.size()==1){
            decodeSvgaFromUrl(svgaList.get(0));
        }
    }

    private void decodeSvgaFromUrl(LiveGiftSVGAEntity liveGiftSVGAEntity) {
        if(svgaList.size()>0){
            try {
                svgaParser.decodeFromURL(new URL(liveGiftSVGAEntity.getUrl()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        try{
                            LiveMessageModel liveMessageModel = liveGiftSVGAEntity.getLiveMessageModel();
                            SVGADrawable drawable;
                            if(liveMessageModel!=null){
                                /**
                                 * 坐骑特效时调用会传liveMessageModel,需要加昵称 头像 ,其他礼物特效时liveMessageModel为空
                                 */
                                SVGADynamicEntity dynamicEntity= new SVGADynamicEntity();
                                String userName = liveMessageModel.getUserName();
                                String level = liveMessageModel.getLevel();
                                String mountName = liveMessageModel.getMountName();
                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Lv."+level +"  "+ userName);
                                //等级字体大小
                                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(23),0,level.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                //等级字体样式(斜体 加粗)
                                spannableStringBuilder.setSpan( new StyleSpan(Typeface.BOLD_ITALIC),0,level.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                //昵称颜色span
                                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFCB14")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                                TextPaint textPaint = new TextPaint();
                                textPaint.setColor(Color.WHITE);
                                textPaint.setTextSize(25);
                                dynamicEntity.setDynamicText(new StaticLayout(
                                        spannableStringBuilder,//文本内容
                                        0,//开始位置
                                        spannableStringBuilder.length(),//结束位置,
                                        textPaint,//文本画笔对象
                                        0,//布局高度.超出宽度换行显示
                                        Layout.Alignment.ALIGN_NORMAL,//对齐方式
                                        1.0f,//行间距倍数，默认是1
                                        0.0f,//行距增加值，默认是0
                                        false//文本顶部和底部是否留白
                                ), "content1");
                                SpannableStringBuilder joinStringBuilder = new SpannableStringBuilder("乘坐 【"+mountName+"】 来了");
//                            joinStringBuilder.setSpan(new ForegroundColorSpan(Color.WHITE), 0, joinStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                TextPaint joinTextPaint = new TextPaint();
                                joinTextPaint.setColor(Color.WHITE);
                                joinTextPaint.setTextSize(20);
                                dynamicEntity.setDynamicText(new StaticLayout(
                                        joinStringBuilder,//文本内容
                                        0,//开始位置
                                        joinStringBuilder.length(),//结束位置,
                                        joinTextPaint,//文本画笔对象
                                        0,//布局高度.超出宽度换行显示
                                        Layout.Alignment.ALIGN_NORMAL,//对齐方式
                                        1.0f,//行间距倍数，默认是1
                                        0.0f,//行距增加值，默认是0。
                                        false//文本顶部和底部是否留白
                                ), "content2");

                                String portrait = liveMessageModel.getPortrait();
                                if(StringMyUtil.isEmptyString(portrait)){
                                    //没有设置过头像时portrait为空,设置默认头像
                                    Bitmap bitmap = decodeResource(context.getResources(), R.drawable.system_default_title);
                                    dynamicEntity.setDynamicImage(bitmap,"avatar");
                                }else {
                                    dynamicEntity.setDynamicImage(portrait,"avatar");
                                }
                                drawable = new SVGADrawable(svgaVideoEntity,dynamicEntity);
                            }else {
                                /**
                                 * 礼物特效直接解析
                                 */
                                drawable = new SVGADrawable(svgaVideoEntity);
                            }
                            svgaImageView.setImageDrawable(drawable);
                            svgaImageView.startAnimation();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError() {
                        //如果动画数组列表大于0,移除第一位的动画,继续循环解析
                        if (svgaList.size() > 0) {
                            svgaList.remove(0);
                           if(svgaList.size()>0){
                               decodeSvgaFromUrl(svgaList.get(0));
                           }
                        } else {
                            stopSVGA();
                        }
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 停止动画
     */
    private void stopSVGA() {
        if (svgaImageView.isAnimating() && svgaList.size() == 0) {
            svgaImageView.stopAnimation();
        }
    }


}
