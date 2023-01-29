package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.bean.AnchorReportEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.AnchorReportAdapter;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.DateUtil;
import com.zz.live.utils.RefreshUtils;
import com.zz.live.utils.RequestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class AnchorReportActivity extends BaseActivity {
    @BindView(R.id.start_time_tv)
    TextView start_time_tv;
    @BindView(R.id.end_time_tv)
    TextView end_time_tv;
    @BindView(R.id.inquire_btn)
    Button inquire_btn;
    @BindView(R.id.loading_linear)
    ConstraintLayout loading_linear;
    @BindView(R.id.error_linear)
    LinearLayout error_linear;
    @BindView(R.id.reload_tv)
    TextView reload_tv;
    @BindView(R.id.nodata_linear)
    LinearLayout nodata_linear;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.anchor_report_recycler)
    RecyclerView anchor_report_recycler;
    AnchorReportAdapter anchorReportAdapter;
    ArrayList<AnchorReportEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    private Date selectedDateLeft;
    private Date selectedDateRight;
    private String startDate;
    private String endDate;
    private Calendar mixDate;
    private Calendar maxDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//请求接口时的格式
    private SimpleDateFormat sdfShow = new SimpleDateFormat("yyyy-MM-dd");

    private int current=1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_anchor_report;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initDate();

        initRecycler();
        requestList(false,false);
        initRefresh();
    }

    private void initDate() {
        Calendar instance = Calendar.getInstance();
        mixDate=Calendar.getInstance();
        mixDate.set(instance.get(Calendar.YEAR)-100,0,30);
        maxDate=Calendar.getInstance();
        Calendar calendarMax = Calendar.getInstance();
        calendarMax.setTime(new Date());

        maxDate.set(calendarMax.get(Calendar.YEAR),calendarMax.get(Calendar.MONTH),calendarMax.get(Calendar.DAY_OF_MONTH));
        selectedDateLeft  = new Date();
        selectedDateRight = new Date();

        Calendar todayCalendar = DateUtil.getTodayCalendar(new Date());
        endDate =sdf.format(DateUtil.getEndTime(todayCalendar));
        startDate=sdf.format(DateUtil.getStartTime(todayCalendar));

        start_time_tv.setText(sdfShow.format(new Date()));
        end_time_tv.setText(sdfShow.format(new Date()));
    }

    private void initRefresh() {
        RefreshUtils.initRefreshLoadMore(this, refresh, new RefreshUtils.OnRefreshLoadMoreLintener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                current=1;
                requestList(true,false);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                current++;
                requestList(false,true);
            }
        });
    }

    @Override
    public void errorRefresh() {
        super.errorRefresh();
        current=1;
        requestList(false,false);
    }
    private void requestList(boolean isRefresh,boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        data.put("startTime",startDate);
        data.put("endTime",endDate);
        HttpApiUtils.showLoadRequest(this, null, RequestUtils.ANCHOR_REPORT, data, loading_linear, error_linear, reload_tv, (View) refresh, isLoadMore, isRefresh, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AnchorReportEntity anchorReportEntity = JSONObject.parseObject(result, AnchorReportEntity.class);
                AnchorReportEntity.DataBean reportEntityData = anchorReportEntity.getData();
                List<AnchorReportEntity.DataBean.RecordsBean> recordsBeanList = reportEntityData.getRecords();
                RefreshUtils.succse(current,refresh,loading_linear,nodata_linear,recordsBeanList.size(),isLoadMore,isRefresh,recordsBeanArrayList);
                recordsBeanArrayList.addAll(recordsBeanList);
                anchorReportAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                RefreshUtils.fail(isRefresh,isLoadMore,current,refresh);
            }
        });
    }

    private void initRecycler() {
        anchorReportAdapter = new AnchorReportAdapter(R.layout.anchor_report_recycler_item,recordsBeanArrayList);
        anchor_report_recycler.setLayoutManager(new LinearLayoutManager(this));
        anchor_report_recycler.setAdapter(anchorReportAdapter);

    }
    @OnClick({R.id.start_time_tv,R.id.end_time_tv,R.id.inquire_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.start_time_tv:
                showPickerTimeLeft();
                break;
            case R.id.end_time_tv:
                showPickerTimeRight();
                break;
            case R.id.inquire_btn:
                requestList(false,false);
                break;
            default:
                break;
        }
    }

    private void showPickerTimeLeft(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDateLeft);
        TimePickerView timePickerView= new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                selectedDateLeft =date;
                Calendar todayCalendar = DateUtil.getTodayCalendar(date);
                Date startTime = DateUtil.getStartTime(todayCalendar);
                startDate=sdf.format(startTime);
                start_time_tv.setText(sdfShow.format(startTime));
                current=1;
                refresh.resetNoMoreData();
                requestList(false,false);
            }
        })
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTextXOffset(10,10,10,10,10,10)
//                        .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("开始时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(mixDate,maxDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDecorView(findViewById(R.id.content))
                .setDate(calendar)
                .build();
        timePickerView.show();
    }
    private void showPickerTimeRight(){
        //每次点击右侧时间选择都配置默认选中时间(默认选中时间需要传入当天开始时间的Calendar对象)
        Calendar calendar = Calendar.getInstance();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(selectedDateRight);
        calendar.setTime(DateUtil.getStartTime(calendarStart));
        String format = sdf.format(selectedDateRight);
//        calendarEnd.setTime(selectedDateRight);
//        calendar.setTime(DateUtil.getEndTime(calendarEnd));
        com.bigkoo.pickerview.view.TimePickerView  timePickerView =new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
   /*
                因为每次选择都要请求接口,所以初始化pageNo 和refreLaout的加载状态
                每次选择时间,都把选择的时间以接口要求的格式赋值给结束时间
                 */

                selectedDateRight =date;
                Calendar todayCalendar = DateUtil.getTodayCalendar(date);
                Date endTime = DateUtil.getEndTime(todayCalendar);//当天的结束时间
                endDate=sdf.format(endTime);
                end_time_tv.setText(sdfShow.format(endTime));
                current=1;
                refresh.resetNoMoreData();
                requestList(false,false);
            }
        })
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTextXOffset(10,10,10,10,10,10)
//                        .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("结束时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(mixDate,maxDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDecorView( findViewById(R.id.content))//设置pickView需要显示的父元素(解决pickView遮盖虚拟按键的问题)
                .setDate(calendar)
                .build();
        timePickerView.show();

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"主播报表");
        CommonToolbarUtil.initStatusBarColor(this);
    }
}