package com.zz.live.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.gson.Gson;
import com.zz.live.R;
import com.zz.live.bean.Happy10OpenResultMedol;
import com.zz.live.bean.Happy8OpenResultMedol;
import com.zz.live.bean.KuaiSanTodayResultModel;
import com.zz.live.bean.MarksixLottery;
import com.zz.live.bean.PcddTodayResultModel;
import com.zz.live.bean.RaceLottery;
import com.zz.live.bean.SscTodayOpenMedol;
import com.zz.live.myView.MyCornerTextView;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.Happy8OpentActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.KuaiSanOpenActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.LuckFarmOpenActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.PcDanOpenActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.RaceOpenActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.SixOpenActivity;
import com.zz.live.ui.activity.start_live_activity.open_result_activity.SscOpenActivity;
import com.zz.live.ui.adapter.Happy10OPenResultAdapter;
import com.zz.live.ui.adapter.Happy8OPenResultAdapter;
import com.zz.live.ui.adapter.KuaiSanOpenResultAdapter;
import com.zz.live.ui.adapter.MarksixOpenresultAdapter;
import com.zz.live.ui.adapter.PcddOpentTodayResultAdapter;
import com.zz.live.ui.adapter.RaceOpenresultAdapter;
import com.zz.live.ui.adapter.SscTodayOpenAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;

/**
 *
 */
public class CustomPopupWindow {
   /*
   右侧菜单pop
    */
    public PopupWindow menuPop;
    public TextView betNote;//右侧菜单中 "投注记录"
    public TextView openResult;//右侧菜单中 "开奖结果"
    public TextView gameRule;//右侧菜单中 "游戏规则"
    public TextView twoCahngLongTv;//右侧菜单中 "两面长龙"
    public TextView investTv;//右侧菜单中 "充值中心"
    public TextView mineCenterTv;//右侧菜单中 "会员中心"
    public TextView todayWinLoseTv;//右侧菜单中 "今日输赢"
    public TextView onlineKf;//右侧菜单中"在线客服"

    /*
    快三投注页面当天的开奖结果pop
     */

    public PopupWindow kuaiSanTodayOpenResultPop;
    public MyCornerTextView kuaiSanshowMore; //查看更多按钮
    public RecyclerView todayResultRecy;//recycleView
    private KuaiSanOpenResultAdapter kuaiSanKuaiSanOpenResultAdapter;//适配器
    private ArrayList<KuaiSanTodayResultModel> kuaiSanTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器
    private ConstraintLayout kuaisanLoadIngLinear;

/*        *//*
    六合彩投注页面当天的开奖结果pop
     *//*

    public  PopupWindow sixTodayOpenResultPop;
    public  MyCornerTextView sixshowMore; //查看更多按钮
    public  RecyclerView sixTodayResultRecy;//recycleView
    private  KuaiSanOpenResultAdapter sixKuaiSanOpenResultAdapter;//适配器
    private  ArrayList<KuaiSanTodayResultModel> sixTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器*/


        /*
    pcdd投注页面当天的开奖结果pop
     */

    public PopupWindow pcddTodayOpenResultPop;
    public  MyCornerTextView pcddshowMore; //查看更多按钮
    public RecyclerView pcddtodayResultRecy;//recycleView
    private ConstraintLayout pcddLoadingLinear;
    private PcddOpentTodayResultAdapter pcddOpenResultAdapter;//适配器
    private ArrayList<PcddTodayResultModel> pcddTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器

    /*
    北京快乐8投注页面当天的开奖结果pop
     */

    public PopupWindow bjTodayOpenResultPop;
    public  MyCornerTextView bjshowMore; //查看更多按钮
    public RecyclerView bjtodayResultRecy;//recycleView
    private ConstraintLayout bjLoadingLinear;
    private Happy8OPenResultAdapter happy8OPenResultAdapter;//适配器
    private ArrayList<Happy8OpenResultMedol> bjTodayResultModelArrayList =new ArrayList<>();//recycleView数据容器
    /*
    北京快乐10投注页面当天的开奖结果pop
     */

    public PopupWindow happy10TodayOpenResultPop;
    public  MyCornerTextView happy10showMore; //查看更多按钮
    public RecyclerView happy10todayResultRecy;//recycleView
    private ConstraintLayout happy10LoadingLinear;
    private Happy10OPenResultAdapter happy10OPenResultAdapter;//适配器
    private ArrayList<Happy10OpenResultMedol> happy10TodayResultModelArrayList =new ArrayList<>();//recycleView数据容器


    /*
      ssc投注页面当天的开奖结果pop
     */

    public PopupWindow sscTodayOpenResultPop;
    public  MyCornerTextView sscShowMore; //查看更多按钮
    public RecyclerView sscTodayResultRecy;//recycleView
    private ConstraintLayout sscLoadingLinear;
    private SscTodayOpenAdapter sscTodayOpenAdapter;//适配器
    private ArrayList<SscTodayOpenMedol> sscTodayOpenMedolArrayList =new ArrayList<>();//recycleView数据容器

    public PopupWindow raceTodayOpenResultPop;
    public  MyCornerTextView raceShowMore; //查看更多按钮
    public RecyclerView raceTodayResultRecy;//recycleView
    private ConstraintLayout raceLoadingLinear;
    private RaceOpenresultAdapter raceOpenresultAdapter;//适配器
    private RaceLottery raceLottery;
    private List<RaceLottery.RaceLotteryBean> raceTodayOpenList =  new ArrayList<>();

    public PopupWindow marksixTodayOpenResultPop;
    public  MyCornerTextView marksixShowMore; //查看更多按钮
    public RecyclerView marksixTodayResultRecy;//recycleView
    private ConstraintLayout sixLoadingLinear;
    private MarksixOpenresultAdapter marksixOpenresultAdapter;//适配器
    private MarksixLottery marksixLottery;
    private List<MarksixLottery.marksixLotteryBean> marksixTodayOpenList =  new ArrayList<>();


    /*
    投注页面右侧菜单中  "游戏规则" popwindow
     */
    public PopupWindow gameRulePop; //pop视图
    public TextView gameRuleCancel; //取消按钮
    public TextView gameRuleSure;  //确定按钮
    public TextView lotteryNameText; //顶部的typename
    public TextView gameRuleTextView; //第二行的规则说明

    private static final int FULL_SCREEN_FLAG =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;



    /**
     * 快三投注页面当日开奖结果pop
     * @param contextWeakReference 上下文
//     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initKuaiSanTodayResult(final WeakReference<Context> contextWeakReference/* Context context*//*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){
        Context context = contextWeakReference.get();
        if(null==context){
            return;
        }
        Context applicationContext = context;
        View contentView = LayoutInflater.from(applicationContext).inflate(R.layout.kuaisan_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) applicationContext);
            int height = (int) (i * 0.65);
            kuaiSanTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow

        }else {
            kuaiSanTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(kuaiSanTodayOpenResultPop);
        kuaiSanTodayOpenResultPop.setAnimationStyle(R.style.down_to_up150);//设置进出动画
        //recycleView配置
        todayResultRecy=contentView.findViewById(R.id.today_open_result_pop_recycle);
        kuaisanLoadIngLinear =contentView.findViewById(R.id.live_loading_linear);
        kuaiSanKuaiSanOpenResultAdapter =new KuaiSanOpenResultAdapter(kuaiSanTodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        todayResultRecy.setLayoutManager(linearLayoutManager);
        todayResultRecy.setAdapter(kuaiSanKuaiSanOpenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        kuaiSanTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        kuaiSanshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        kuaiSanshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        kuaiSanshowMore.setCornerSize(10);//设置圆角

        //点击 查看更多 跳转到快三开奖结果activity
        kuaiSanshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuaiSanTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, KuaiSanOpenActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    private void setPopFlag(PopupWindow popupWindow) {
        popupWindow.setOutsideTouchable(true);
        popupWindow.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
        popupWindow.update();
    }

    /**
     * pcdd投注页面当日开奖结果pop
     * @param context 上下文
    //     * @param onMenuPopClickListener 点击事件的回调
     */
    public  void initPcddTodayResult(final Context context/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){

        View contentView = LayoutInflater.from(context).inflate(R.layout.pcdd_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            pcddTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow

        }else {
            pcddTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(pcddTodayOpenResultPop);
        pcddTodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        pcddtodayResultRecy=contentView.findViewById(R.id.pcdd_open_result_pop_recycle);
        pcddLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        pcddOpenResultAdapter =new PcddOpentTodayResultAdapter(pcddTodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        pcddtodayResultRecy.setLayoutManager(linearLayoutManager);
        pcddtodayResultRecy.setAdapter(pcddOpenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        pcddTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        pcddshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        pcddshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        pcddshowMore.setCornerSize(10);//设置圆角

        //点击 查看更多 跳转到快三开奖结果activity
        pcddshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcddTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, PcDanOpenActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /*
    初始化北京快乐8 开奖结果pop
     */
    public  void initHappy8TodayResult(final Context context/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.happy8_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            bjTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow

        }else {
            bjTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(bjTodayOpenResultPop);
        bjTodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        bjtodayResultRecy=contentView.findViewById(R.id.bj_today_open_result_pop_recycle);
        happy8OPenResultAdapter =new Happy8OPenResultAdapter(bjTodayResultModelArrayList);
        bjLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        bjtodayResultRecy.setLayoutManager(linearLayoutManager);
        bjtodayResultRecy.setAdapter(happy8OPenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        bjTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        bjshowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        bjshowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        bjshowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到快三开奖结果activity
        bjshowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bjTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, Happy8OpentActivity.class);
                intent.putExtra("type_id", type_id);
                context.startActivity(intent);
            }
        });
    }

    /*
   初始化广东快乐10开奖结果pop
    */
    public  void initHappy10TodayResult(final Context context, final int game/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.happy10_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            happy10TodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow

        }else {
            happy10TodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(happy10TodayOpenResultPop);
        happy10TodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        happy10todayResultRecy=contentView.findViewById(R.id.happy10_today_open_result_pop_recycle);
        happy10LoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        happy10OPenResultAdapter =new Happy10OPenResultAdapter(happy10TodayResultModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        happy10todayResultRecy.setLayoutManager(linearLayoutManager);
        happy10todayResultRecy.setAdapter(happy10OPenResultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        happy10TodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        happy10showMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        happy10showMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        happy10showMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到开奖结果activity
        happy10showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, LuckFarmOpenActivity.class);
                    intent.putExtra("type_id", type_id);
                    intent.putExtra("game", game);
                    context.startActivity(intent);
                happy10TodayOpenResultPop.dismiss();
            }
        });
    }
    /*
初始化时时彩 开奖结果pop
 */
    public  void initSscTodayResultPop(final Context context, final  int game/*,final CustomPopupWindow.OnMenuPopClickListener onMenuPopClickListener*/, final int type_id, Boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.ssc_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow

        }else {
            sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(sscTodayOpenResultPop);
//        sscTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);//实例化popupWindow
        sscTodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        sscTodayResultRecy=contentView.findViewById(R.id.ssc_today_open_result_pop_recycle);
        sscLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        sscTodayOpenAdapter =new SscTodayOpenAdapter(sscTodayOpenMedolArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        sscTodayResultRecy.setLayoutManager(linearLayoutManager);
        sscTodayResultRecy.setAdapter(sscTodayOpenAdapter);

        //右侧菜单pop的disMIss监听
        sscTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        sscShowMore=contentView.findViewById(R.id.show_more);//查看更多按钮
        sscShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        sscShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到ssc开奖结果activity
        sscShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sscTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, SscOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }
    public  void initRaceTodayResultPop(final Context context, final  int game, final int type_id, boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.race_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            raceTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow
        }else {
            raceTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(raceTodayOpenResultPop);
        raceTodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        raceTodayResultRecy=contentView.findViewById(R.id.race_pop_recycle);
        raceLoadingLinear =contentView.findViewById(R.id.live_loading_linear);
        if (raceOpenresultAdapter==null){
            raceOpenresultAdapter =new RaceOpenresultAdapter(context,raceTodayOpenList);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        raceTodayResultRecy.setLayoutManager(linearLayoutManager);
        raceTodayResultRecy.setAdapter(raceOpenresultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        raceTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        raceShowMore=contentView.findViewById(R.id.race_pop_showmore);//查看更多按钮
        raceShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        raceShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到ssc开奖结果activity
        raceShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raceTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, RaceOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }
    public  void initMarksixTodayResultPop(final Context context, final  int game, final int type_id, boolean isLive){
        View contentView = LayoutInflater.from(context).inflate(R.layout.marksix_today_open_result_popwindow, null);
        if(isLive){
            int i = Utils.intgetWinndowHeight((Activity) context);
            int height = (int) (i * 0.65);
            marksixTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height==0?1000:height, false);//实例化popupWindow
        }else {
            marksixTodayOpenResultPop = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);//实例化popupWindow
        }
        setPopFlag(marksixTodayOpenResultPop);
        marksixTodayOpenResultPop.setAnimationStyle(R.style.popAlphaanim0_3);//设置进出动画
        //recycleView配置
        marksixTodayResultRecy=contentView.findViewById(R.id.marksix_pop_recycle);
        sixLoadingLinear=contentView.findViewById(R.id.live_loading_linear);
        if (marksixOpenresultAdapter==null){
            marksixOpenresultAdapter =new MarksixOpenresultAdapter(context,marksixTodayOpenList);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        marksixTodayResultRecy.setLayoutManager(linearLayoutManager);
        marksixTodayResultRecy.setAdapter(marksixOpenresultAdapter);
        //recycleView配置

        //右侧菜单pop的disMIss监听
        marksixTodayOpenResultPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground((Activity) context,1f);//恢复背景亮度
            }
        });

        marksixShowMore=contentView.findViewById(R.id.marksix_pop_showmore);//查看更多按钮
        marksixShowMore.setfilColor(Color.parseColor("#f8a52a"));//设置背景颜色
        marksixShowMore.setCornerSize(10);//设置圆角
        //点击 查看更多 跳转到六合彩开奖结果activity
        marksixShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marksixTodayOpenResultPop.dismiss();
                Intent intent = new Intent(context, SixOpenActivity.class);
                intent.putExtra("type_id", type_id);
                intent.putExtra("game", game);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 点击右侧菜单中"开奖结果", 跳转对应的开奖结果activity
     * @param context 上下文
     * @param type_id 彩票type_id
     * @param game 彩票分类的id(用于判断点击后,需要跳转哪一个彩种的开奖结果)
     */
    public  void toOpenResult(final Context context, final int type_id, final  int game) {
        openResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(game==3){//3 表示跳转赛车类
                    intent  = new Intent(context, RaceOpenActivity.class);
                }
                else if(game==1){//1 表示跳转快三类
                    intent  = new Intent(context, KuaiSanOpenActivity.class);
                }
                else if(game==6){//北京快乐8
                    intent  = new Intent(context, Happy8OpentActivity.class);
                }else if(game==5){//pc蛋蛋
                    intent  = new Intent(context, PcDanOpenActivity.class);
                }/*else if(game==8){//快乐10分
                    intent  = new Intent(context, Happy10OpentActivity.class);
                }*/else if(game==7||game==8){
                    intent  = new Intent(context, LuckFarmOpenActivity.class);
                }
                else if(game==2||game==9){//时时彩  11选5
                    intent  = new Intent(context, SscOpenActivity.class);
                }else if(game==4){//六合彩
                    intent  = new Intent(context, SixOpenActivity.class);
                }else {
//                    intent  = new Intent(context, XuanWuOpenActivity.class);
                }

                //测试代码 记得删
//                intent  = new Intent(context, KuaiSanOpenActivity.class);

                intent.putExtra("type_id",type_id);//跳转到开奖结果activity后,请求数据需要用的到参数
                intent.putExtra("game",game);//跳转到开奖结果activity后,请求数据需要用的到参数
                context.startActivity(intent);
                menuPop.dismiss();
            }
        });

    }

    /**
     * 弹出快三规则说明pop
     * @param activity
     */
    public  void showGameRulePop(final Activity activity){
        gameRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameRulePop !=null){
                    menuPop.dismiss();
                    if(!activity.isFinishing()){
                        gameRulePop.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER,0,0);
                    }
                    darkenBackground(activity,0.3f);
                }
            }
        });
    }

    
    /**
     * 显示快三当日开奖结果pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showKuaiSanTodayResultPop(View targetView, Context context) {
        if (kuaiSanTodayOpenResultPop != null) {
            kuaiSanTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            darkenBackground((Activity) context,0.3f);
        }
    }

    /**
     * 显示北京快乐8当日开奖结果pop到控件下方
     * @param targetView pop定位的元素(在targetView的下方显示pop)
     * @param context 上下文对象
     */
    public  void showHappy8TodayResultPop(View targetView, Context context) {
        if (bjTodayOpenResultPop != null) {
            bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            darkenBackground((Activity) context,0.3f);
        }
    }
    
    /**
     * 请求快三当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initKuaiSanTodayResultData(final Context context, final View targetView, final int type_id){
        if (kuaiSanTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                kuaiSanTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {
                kuaiSanTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        kuaisanLoadIngLinear.setVisibility(View.VISIBLE);
        kuaiSanshowMore.setVisibility(View.GONE);
        todayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_8r, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                kuaisanLoadIngLinear.setVisibility(View.GONE);
                kuaiSanshowMore.setVisibility(View.VISIBLE);
                todayResultRecy.setVisibility(View.VISIBLE);
                kuaiSanTodayResultModelArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray gameLotterylist = jsonObject1.getJSONArray("gameLotterylist");
                for (int i = 0; i < gameLotterylist.size(); i++) {
                    JSONObject jsonObject = gameLotterylist.getJSONObject(i);
                    String typeqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String remark = jsonObject.getString("remark");//大小
                    String createdDate = jsonObject.getString("lotterytime");//时间
                    kuaiSanTodayResultModelArrayList.add(new KuaiSanTodayResultModel(typeqishu,lotteryNo,remark,createdDate));
                }
                kuaiSanKuaiSanOpenResultAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                kuaisanLoadIngLinear.setVisibility(View.GONE);
                kuaiSanshowMore.setVisibility(View.VISIBLE);
                todayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }


    /**
     * 请求快三当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initPcddTodayResultData(final Context context, final View targetView, final int type_id){
        if (pcddTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                pcddTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                pcddTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        pcddLoadingLinear.setVisibility(View.VISIBLE);
        pcddshowMore.setVisibility(View.GONE);
        pcddtodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_05dd, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                pcddTodayResultModelArrayList.clear();
                pcddLoadingLinear.setVisibility(View.GONE);
                pcddshowMore.setVisibility(View.VISIBLE);
                pcddtodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray danLotterylist = jsonObject1.getJSONArray("danLotterylist");
                for (int i = 0; i < danLotterylist.size(); i++) {
                    JSONObject jsonObject = danLotterylist.getJSONObject(i);
                    String typeqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotterytime = jsonObject.getString("lotterytime");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String markdx = jsonObject.getString("markdx");//大小
                    String markds = jsonObject.getString("markds");//单双
                    pcddTodayResultModelArrayList.add(new PcddTodayResultModel(typeqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                pcddOpenResultAdapter.notifyDataSetChanged();
                if (pcddTodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        pcddTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0, Gravity.BOTTOM);
                    }else {

                        pcddTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void onFail(String msg) {
                pcddLoadingLinear.setVisibility(View.GONE);
                pcddshowMore.setVisibility(View.VISIBLE);
                pcddtodayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求北京快乐8当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initHappy8TodayResultData(final Context context, final LinearLayout targetView, final int type_id){
        if (bjTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                bjTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        bjLoadingLinear.setVisibility(View.VISIBLE);
        bjshowMore.setVisibility(View.GONE);
        bjtodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_04ha, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                bjTodayResultModelArrayList.clear();
                bjLoadingLinear.setVisibility(View.GONE);
                bjshowMore.setVisibility(View.VISIBLE);
                bjtodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray happyLotterylist = jsonObject1.getJSONArray("happyLotterylist");
                for (int i = 0; i < happyLotterylist.size(); i++) {
                    JSONObject jsonObject = happyLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    bjTodayResultModelArrayList.add(new Happy8OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,false,sum,"","","","","",""));
                }
                happy8OPenResultAdapter .notifyDataSetChanged();
                if (bjTodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        bjTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0, Gravity.BOTTOM);
                    }else {

                        bjTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void onFail(String msg) {
                bjLoadingLinear.setVisibility(View.GONE);
                bjshowMore.setVisibility(View.VISIBLE);
                bjtodayResultRecy.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 请求g广东快乐10当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initHappy10TodayResultData(final Context context, final View targetView, final int type_id){
        if (happy10TodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                happy10TodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        happy10LoadingLinear.setVisibility(View.VISIBLE);
        happy10showMore.setVisibility(View.GONE);
        happy10todayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_04happyten, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                happy10TodayResultModelArrayList.clear();
                happy10LoadingLinear.setVisibility(View.GONE);
                happy10showMore.setVisibility(View.VISIBLE);
                happy10todayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("happytenLotterylist");
                for (int i = 0; i < happytenLotterylist.size(); i++) {
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String markdx = jsonObject.getString("markdx");//和值大小
                    String markds = jsonObject.getString("markds");//和值单双
                    happy10TodayResultModelArrayList.add(new Happy10OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                happy10OPenResultAdapter .notifyDataSetChanged();
                if (happy10TodayOpenResultPop != null) {
                    if(null==targetView){
                        Activity activity =(Activity)context;
                        happy10TodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0, Gravity.BOTTOM);
                    }else{

                        happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                    }
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void onFail(String msg) {
                happy10LoadingLinear.setVisibility(View.GONE);
                happy10showMore.setVisibility(View.VISIBLE);
                happy10todayResultRecy.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 请求重庆幸运农场当日开奖结果的数据
     * @param type_id 彩票type_id
     */
    public  void initfarmTodayResultData(final Context context, final View targetView, final int type_id){
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_04farm, data, new HttpApiUtils.OnRequestLintener() {

            @Override
            public void onSuccess(String result) {
                happy10TodayResultModelArrayList.clear();
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray happytenLotterylist = jsonObject1.getJSONArray("farmLotterylist");
                for (int i = 0; i < happytenLotterylist.size(); i++) {
                    JSONObject jsonObject = happytenLotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String markdx = jsonObject.getString("markdx");//和值大小
                    String markds = jsonObject.getString("markds");//和值单双
                    happy10TodayResultModelArrayList.add(new Happy10OpenResultMedol(lotteryqishu,lotterytime,lotteryNo,sum,markdx,markds));
                }
                happy10OPenResultAdapter .notifyDataSetChanged();
                if (happy10TodayOpenResultPop != null) {
                    happy10TodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                    darkenBackground((Activity) context,0.3f);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }


    /**
     * 请求 时时彩开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initSscTodayResultData(final Context context, final int game, final int type_id , final View targetView){
        if (sscTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                sscTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {
                sscTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        sscLoadingLinear.setVisibility(View.VISIBLE);
        sscShowMore.setVisibility(View.GONE);
        sscTodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        String url="";
        if(game==9){
            url=RequestUtils.REQUEST_04xuanwu;
        }else{
            url=RequestUtils.REQUEST_19r;
        }
        HttpApiUtils.cpNormalRequest((Activity) context, null, url, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                sscTodayOpenMedolArrayList.clear();
                sscShowMore.setVisibility(View.VISIBLE);
                sscLoadingLinear.setVisibility(View.GONE);
                sscTodayResultRecy.setVisibility(View.VISIBLE);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONArray lotterylist=null;
                if(game==2){
                    lotterylist = jsonObject1.getJSONArray("sscaiLotterylist");
                }
                else {
                    lotterylist = jsonObject1.getJSONArray("xuanwuLotterylist");
                }
                for (int i = 0; i < lotterylist.size(); i++) {
                    JSONObject jsonObject = lotterylist.getJSONObject(i);
                    String lotteryqishu = jsonObject.getString("lotteryqishu");//期数
                    String lotteryNo = jsonObject.getString("lotteryNo");//开奖号码
                    String sum = jsonObject.getString("sum");//和值
                    String lotterytime = jsonObject.getString("lotterytime");//时间
                    String marklh = jsonObject.getString("marklh");//龙虎
                    String markdx = jsonObject.getString("markdx");//大小
                    String markds = jsonObject.getString("markds");//单双
                    SscTodayOpenMedol sscTodayOpenMedol = new SscTodayOpenMedol(lotteryqishu, lotteryNo, markdx, markds, marklh,lotterytime);
                    sscTodayOpenMedol.setGame(game);
                    sscTodayOpenMedolArrayList.add(sscTodayOpenMedol);
                }
                sscTodayOpenAdapter .notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                sscLoadingLinear.setVisibility(View.GONE);
                sscShowMore.setVisibility(View.VISIBLE);
                sscTodayResultRecy.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 请求 赛车开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initRaceTodayResultData(final Context context, final int game, final int type_id , final View targetView ){
        if (raceTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                raceTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                raceTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        raceLoadingLinear.setVisibility(View.VISIBLE);
        raceShowMore.setVisibility(View.GONE);
        raceTodayResultRecy.setVisibility(View.GONE);
        Activity activity=(Activity)context;
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_26r, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                raceTodayOpenList.clear();
                raceLoadingLinear.setVisibility(View.GONE);
                raceShowMore.setVisibility(View.VISIBLE);
                raceTodayResultRecy.setVisibility(View.VISIBLE);
                Gson gson = new Gson();

                raceLottery =    gson.fromJson(result,RaceLottery.class);
                List<RaceLottery.RaceLotteryBean> kjlist = new ArrayList<>();
                kjlist = raceLottery.getRaceLotterylist();
                raceTodayOpenList.addAll(kjlist);
                raceOpenresultAdapter .notifyDataSetChanged();

                if (raceTodayOpenResultPop != null) {

                    if(context!=null&&!activity.isFinishing()){
                        if(null==targetView){
                            Activity activity =(Activity)context;
                            raceTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), 0, 0, Gravity.BOTTOM);
                        }else {

                            raceTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                        }
                        darkenBackground((Activity) context,0.3f);
                    }

                }
            }

            @Override
            public void onFail(String msg) {
                raceLoadingLinear.setVisibility(View.GONE);
                raceShowMore.setVisibility(View.VISIBLE);
                raceTodayResultRecy.setVisibility(View.VISIBLE);
            }
        });


    }

    /**
     * 请求 六合彩开奖结果 并弹出pop
     * @param context   上下文
     * @param targetView 显示到该控件下方
     * @param type_id  type_id
     */
    public  void initMarksixTodayResultData(final Context context, final int game, final int type_id , final View targetView){
        if (marksixTodayOpenResultPop != null) {
            if(null==targetView){
                Activity activity =(Activity)context;
                marksixTodayOpenResultPop.showAtLocation(Utils.getContentView(activity), Gravity.BOTTOM, 0,0);
            }else {

                marksixTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
            }
            darkenBackground((Activity) context,0.3f);
        }
        sixLoadingLinear.setVisibility(View.VISIBLE);
        marksixShowMore.setVisibility(View.GONE);
        marksixTodayResultRecy.setVisibility(View.GONE);
        HashMap<String, Object> data = new HashMap<>();
        data.put("type_id",type_id);
        data.put("pageNo",1);
        data.put("pageSize",20);
        data.put("flag",1);
        HttpApiUtils.cpNormalRequest((Activity) context, null, RequestUtils.REQUEST_05lhc, data, new HttpApiUtils.OnRequestLintener() {
                    @Override
                    public void onSuccess(String result) {
                        marksixTodayOpenList.clear();
                        sixLoadingLinear.setVisibility(View.GONE);
                        marksixShowMore.setVisibility(View.VISIBLE);
                        marksixTodayResultRecy.setVisibility(View.VISIBLE);
                        Gson gson = new Gson();

                        marksixLottery =    gson.fromJson(result,MarksixLottery.class);
                        List<MarksixLottery.marksixLotteryBean> kjlist = new ArrayList<>();
                        kjlist = marksixLottery.getMarksixLotterylist();
                        marksixTodayOpenList.addAll(kjlist);
                        marksixOpenresultAdapter .notifyDataSetChanged();

                        if (marksixTodayOpenResultPop != null) {
                            marksixTodayOpenResultPop.showAsDropDown(targetView, 0, 0, Gravity.BOTTOM);
                            darkenBackground((Activity) context,0.3f);
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                        sixLoadingLinear.setVisibility(View.GONE);
                        marksixShowMore.setVisibility(View.VISIBLE);
                        marksixTodayResultRecy.setVisibility(View.VISIBLE);
                    }
                });


    }

    /**
     * 设置背景亮度
     * @param activity activity实例
     * @param bgcolor 亮度值(0f-1f)值越小,背景越暗
     */
    public  void darkenBackground(Activity activity, Float bgcolor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor==1f){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        }
        activity.getWindow().setAttributes(lp);
    }

}
