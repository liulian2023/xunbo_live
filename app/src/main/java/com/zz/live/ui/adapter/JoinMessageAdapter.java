package com.zz.live.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zz.live.R;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.myView.MyImageSpan;
import com.zz.live.myView.UrlImageSpan;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class JoinMessageAdapter extends BaseQuickAdapter<LiveMessageModel, BaseViewHolder> {
    Fragment fragment;
    public JoinMessageAdapter(int layoutResId, @Nullable List<LiveMessageModel> data, Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LiveMessageModel liveMessageModel) {
    LinearLayout forbidden_wrap_linear =  baseViewHolder.getView(R.id.forbidden_wrap_linear);
            forbidden_wrap_linear.setVisibility(View.VISIBLE);
            String userName = liveMessageModel.getUserName();
            String textContent = liveMessageModel.getTextContent();

            String managerType= liveMessageModel.getManagerType();
            String levelIcon = liveMessageModel.getLevelIcon();
            String medalIcon = liveMessageModel.getMedalIcon();
            String titleIcon = liveMessageModel.getTitleIcon();
            TextView exitJoinCotentTv = baseViewHolder.getView(R.id.exit_join_content_tv);
            /**
             * 消息内容colorSpan
             */
            ForegroundColorSpan defaultColorSpan = new ForegroundColorSpan(Color.WHITE);
            /**
             * 用户名colorSpan
             */
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FFCB14"));

            SpannableStringBuilder joinSpanBuilder = initLevelEquipmentIcon( managerType, levelIcon, medalIcon, titleIcon, exitJoinCotentTv);
            String s ;
            if(liveMessageModel.getStatus().equals("0")){
                s="进入直播间";
            }else {
                s="退出直播间";
            }
            joinSpanBuilder.append(liveMessageModel.getUserName(),foregroundColorSpan, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            joinSpanBuilder.append(s,defaultColorSpan, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                SpannableString exitSpan = new SpannableString("    "+liveMessageModel.getUserName() + s);
//                initLevelIcon(level, exitSpan,userName,managerType,true);
            exitJoinCotentTv.setTextColor(Color.WHITE);
            exitJoinCotentTv.setText(joinSpanBuilder);
        }


    @NotNull
    private SpannableStringBuilder initLevelEquipmentIcon(String managerType, String levelIcon, String medalIcon, String titleIcon, TextView textNameContentTv) {
        managerType = StringMyUtil.isEmptyString(managerType)?"":managerType;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int drawType = R.drawable.add;
        if(managerType.equals("1")){
            //主播没有等级icon
            drawType=R.drawable.jinyan_zb_icon;
        }else if(managerType.equals("2")){
//            drawType=R.drawable.chaoguang;//超管取消
        }else if(managerType.equals("3")){
            drawType=R.drawable.fangguang;
        }else {
            //普通用户没有身份icon
        }
        if(drawType!=R.drawable.add){
            //添加身份icon
            Bitmap bitmapType = BitmapFactory.decodeResource(fragment.getResources(), drawType);
            MyImageSpan typeSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmapType, 35, 15));
            spannableStringBuilder.append(" ",typeSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if(!managerType.equals("1")){
            //不是主播, 添加等级icon 勋章icon medalIcon
            if(StringMyUtil.isNotEmpty(levelIcon)){
                /**
                 * 等级icon
                 */
                UrlImageSpan levelUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(levelIcon), textNameContentTv,35,15,R.drawable.touming);
                spannableStringBuilder.append(" ", levelUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }else {
                //生成的假数据直接取本地数据
                Bitmap bitmapType = BitmapFactory.decodeResource(fragment.getResources(), R.drawable.vip1);
                MyImageSpan typeSpan = new MyImageSpan(fragment.getContext(), Utils.getNewBitmap(bitmapType, 35, 15));
                spannableStringBuilder.append(" ",typeSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }
            if(StringMyUtil.isNotEmpty(medalIcon)){
                /**
                 * 勋章icon
                 */
                UrlImageSpan medalUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(medalIcon), textNameContentTv,15,15,R.drawable.touming2);
                spannableStringBuilder.append(" ", medalUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(" ");
            }

            if(StringMyUtil.isNotEmpty(titleIcon)){
                /**
                 * 称号牌icon
                 */
                List<String> titleIconList = Arrays.asList(titleIcon.split(","));
                for (int i = 0; i < titleIconList.size(); i++) {
                    UrlImageSpan titleUrlImageSpan = new UrlImageSpan(fragment.getContext(), Utils.checkImageUrl(titleIconList.get(i)), textNameContentTv,35,15,R.drawable.touming);
                    spannableStringBuilder.append(" ", titleUrlImageSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//称号牌icon
                    spannableStringBuilder.append(" ");
                }
            }
        }


        return spannableStringBuilder;
    }
}
