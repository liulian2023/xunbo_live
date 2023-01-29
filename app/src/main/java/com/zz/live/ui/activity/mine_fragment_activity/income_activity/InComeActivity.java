package com.zz.live.ui.activity.mine_fragment_activity.income_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.fastjson.JSONObject;
import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.pop.ChooseTimePop;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

/**
 * 主播/家族主播收入详情 + 族长财务对账页面
 */
public class InComeActivity extends BaseActivity implements BasePopupWindow.OnPopClickListener {
    @BindView(R.id.income_tab)
    TabLayout income_tab;
    @BindView(R.id.income_viewpager)
    ViewPager income_viewpager;
    @BindView(R.id.income_amount_tv)
    TextView income_amount_tv;
    @BindView(R.id.expenses_amount_tv)
    TextView expenses_amount_tv;
    @BindView(R.id.date_tv)
    TextView date_tv;
    @BindView(R.id.choose_time_iv)
    ImageView choose_time_iv;
    ArrayList<String>titleList = new ArrayList<>();
    IncomeTabAdapter incomeTabAdapter;
    //    类型 1礼物 2提现 3订阅 4返现 5其他 0  全部
    int type=0;
    //    时间类型 1当日 2昨日 3近7天 4本月 5上个月
    int dateType=1;
    ChooseTimePop chooseTimePop;
    ArrayList<InComeFragment>inComeFragments = new ArrayList<>();
    private int userType;
    String title;
    @Override
    public int getLayoutId() {
        return R.layout.activity_in_come;
    }
    /**
     * 跳转
     * @param context
     * @param title actionBar标题
     * @param
     */
    public static void startAty(Context context,String title){
        Intent intent = new Intent(context, InComeActivity.class);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        initView();
        requestIncomeHead();
    }

    private void getIntentData() {
        title = getIntent().getStringExtra("title");
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        userType = userInfoBean.getUserType();
    }


    private void initDate() {
        if(dateType==1){
            date_tv.setText("今日");
        }else if(dateType==2){
            date_tv.setText("昨日");
        }else if(dateType==3){
            date_tv.setText("近七日");
        }else if(dateType==4){
            date_tv.setText("本月");
        }else {
            date_tv.setText("上月");
        }
    }
    private void requestIncomeHead() {
        initDate();
        HashMap<String, Object> data = new HashMap<>();
        data.put("dateType",dateType);
//        if(type!=0){
            data.put("type",type);
//        }
        HttpApiUtils.wwwNormalRequest(this, null, RequestUtils.INCOME_HEAD, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
                String expensesAmount = jsonObject.getString("expensesAmount");
                String incomeAmount = jsonObject.getString("incomeAmount");
                expenses_amount_tv.setText("¥"+expensesAmount);
                income_amount_tv.setText("¥"+incomeAmount);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    @OnClick({R.id.choose_time_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.choose_time_iv:
                if(chooseTimePop==null){
                    chooseTimePop = new ChooseTimePop(InComeActivity.this);
                    chooseTimePop.setOnPopClickListener(this);
                }
                chooseTimePop.showAtLocation(choose_time_iv, Gravity.BOTTOM,0,0);
                Utils.darkenBackground(InComeActivity.this,0.7f);
                break;
                default:
                    break;
        }
    }
    private void initView() {

        if(title.equals("财务对账")){
            titleList.add("全部");
            titleList.add("转入");
            titleList.add("提现");
            titleList.add("其他");
        }else {
            titleList.add("全部");
            titleList.add("订阅");
            titleList.add("礼物");
            titleList.add("返现");
            titleList.add("提现");
            titleList.add("其他");
        }
        for (int i = 0; i < titleList.size(); i++) {
            inComeFragments.add(InComeFragment.newInstance(i,titleList.get(i)));
        }
        incomeTabAdapter=new IncomeTabAdapter(getSupportFragmentManager());
        income_viewpager.setAdapter(incomeTabAdapter);
        income_tab.setupWithViewPager(income_viewpager);
        income_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                if(tabName.equals("全部")){
                    type=0;//为0时传空
                }else if(tabName.equals("订阅")){
                    type=5;
                }else if(tabName.equals("礼物")){
                    type=1;
                }else if(tabName.equals("返现")){
                    type=6;
                }else if(tabName.equals("提现")){
                    //1族长2主播3家族主播
                    if(!title.equals("财务对账")){
                        if(userType==1||userType==3){
                            type=4;
                        }else {
                            type=2;
                        }
                    }else {
                        type=2;
                    }
                }else if(tabName.equals("转入")) {
                    type=3;
                }else {
                    //其他
                    type=7;
                }
                requestIncomeHead();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,getIntent().getStringExtra("title"));
        CommonToolbarUtil.initStatusBarColor(this);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    public void onPopClick(View view) {
//        时间类型 1当日 2昨日 3近7天 4本月 5上个月
        switch (view.getId()){
            case R.id.today_tv:
                dateType=1;
                requestIncomeHead();
                initItemFragment();
                chooseTimePop.dismiss();
                break;
            case R.id.yestoday_tv:
                dateType=2;
                requestIncomeHead();
                initItemFragment();
                chooseTimePop.dismiss();
                break;
            case R.id.week_tv:
                dateType=3;
                requestIncomeHead();
                initItemFragment();
                chooseTimePop.dismiss();
                break;
            case R.id.month_tv:
                dateType=4;
                requestIncomeHead();
                initItemFragment();
                chooseTimePop.dismiss();
                break;
            case R.id.last_month_tv:
                dateType=5;
                requestIncomeHead();
                initItemFragment();
                chooseTimePop.dismiss();
                break;
                default:
                    break;
        }
    }

    /**
     * 每次切换时间刷新当前选中的fragment列表数据
     */
    private void initItemFragment() {
        int currentItem = income_viewpager.getCurrentItem();
        requestFragmentListData(currentItem);
    }

    private void requestFragmentListData(int currentItem) {
        Fragment item = incomeTabAdapter.getItem(currentItem);
        if(item instanceof InComeFragment){
            InComeFragment  currentFragment = (InComeFragment)item;
            currentFragment.requestIncomeList(false,false);
        }
    }

    class IncomeTabAdapter extends FragmentStatePagerAdapter {

        public IncomeTabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return inComeFragments.get(position);
//            return InComeFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return inComeFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }
}
