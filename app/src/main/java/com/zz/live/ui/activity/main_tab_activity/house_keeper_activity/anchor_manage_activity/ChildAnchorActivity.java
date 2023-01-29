package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.zz.live.bean.AnchorManageEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.mine_fragment_activity.income_activity.InComeFragment;
import com.zz.live.ui.pop.BanAnchorPop;
import com.zz.live.ui.pop.ChooseTimePop;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

public class ChildAnchorActivity extends BaseActivity implements BasePopupWindow.OnPopClickListener{
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
    @BindView(R.id.child_anchor_title_iv)
    ImageView child_anchor_title_iv;
    @BindView(R.id.nickname_tv)
    TextView nickname_tv;
    @BindView(R.id.username_tv)
    TextView username_tv;
    @BindView(R.id.modify_password_iv)
    ImageView modify_password_iv;
    @BindView(R.id.block_iv)
    ImageView block_iv;

    ArrayList<String> titleList = new ArrayList<>();
    IncomeTabAdapter incomeTabAdapter;
    //    类型 1礼物 2提现 3订阅 4返现 5其他 不传所有
    int type=0;
    //    时间类型 1当日 2昨日 3近7天 4本月 5上个月
    int dateType=1;
    ChooseTimePop chooseTimePop;
    ArrayList<InComeFragment>inComeFragments = new ArrayList<>();
    private AnchorManageEntity.DataBean.RecordsBean recordsBean;
    BanAnchorPop banAnchorPop;
    private String lockFlag;
    Handler handler = new Handler();
    @Override
    public int getLayoutId() {
        return R.layout.activity_child_anchor;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getIntentData();
        initView();
        requestIncomeHead();


    }

    private void getIntentData() {
        recordsBean = (AnchorManageEntity.DataBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        nickname_tv.setText(recordsBean.getNickname());
        username_tv.setText(recordsBean.getUsername());
        GlideLoadViewUtil.LoadTitleView(this,recordsBean.getImage(),child_anchor_title_iv);
        lockFlag = recordsBean.getLockFlag();
        if(lockFlag.equals("1") ){
            //当前主播已被拉黑
            block_iv.setImageResource(R.drawable.jf_icon);
        }else {
            block_iv.setImageResource(R.drawable.ft_icon);
        }
    }

    public static void startAty(Context context,  AnchorManageEntity.DataBean.RecordsBean recordsBean){
        Intent intent = new Intent(context, ChildAnchorActivity.class);
        intent.putExtra("recordsBean", (Serializable) recordsBean);
        context.startActivity(intent);
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
        data.put("userId",recordsBean.getId());
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
    @OnClick({R.id.choose_time_iv,R.id.block_iv,R.id.modify_password_iv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.choose_time_iv:
                if(chooseTimePop==null){
                    chooseTimePop = new ChooseTimePop(ChildAnchorActivity.this);
                    chooseTimePop.setOnPopClickListener(this);
                }
                chooseTimePop.showAtLocation(choose_time_iv, Gravity.BOTTOM,0,0);
                Utils.darkenBackground(ChildAnchorActivity.this,0.7f);
                break;
            case R.id.block_iv:
                banAnchorPop = new BanAnchorPop(ChildAnchorActivity.this,lockFlag.equals("1")?"解封":"封禁");
                banAnchorPop.setOnPopClickListener(this);
                if(!banAnchorPop.isShowing()){
                    banAnchorPop.showAtLocation(block_iv,Gravity.BOTTOM,0,0);
                }
                Utils.darkenBackground(ChildAnchorActivity.this,0.7f);
                break;
            case R.id.modify_password_iv:

                ModifyAnchorPasswordActivity.startAty(ChildAnchorActivity.this,recordsBean);
                break;
            default:
                break;
        }
    }
    private void initView() {
        titleList.add("全部");
        titleList.add("订阅");
        titleList.add("礼物");
        titleList.add("返现");
        titleList.add("提现");
        titleList.add("其他");
        for (int i = 0; i < titleList.size(); i++) {
            inComeFragments.add(InComeFragment.newInstance(i, titleList.get(i),recordsBean.getId()));
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
                    type=4;
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
        CommonToolbarUtil.initToolbar(this,"收入详情");
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
            case R.id.ban_anchor_tv:
                //封禁主播
                HttpApiUtils.pathRequest(ChildAnchorActivity.this, RequestUtils.BAN_ANCHOR, recordsBean.getId(), new HttpApiUtils.OnRequestLintener() {
                    @Override
                    public void onSuccess(String result) {
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        String data = jsonObject.getString("data");
                        lockFlag=data;
                        String msg = jsonObject.getString("msg");
                        showtoast(msg.replace("拉黑","封停"));
                        banAnchorPop.dismiss();
                        if(lockFlag.equals("1") ){
                            //当前主播已被拉黑
                            block_iv.setImageResource(R.drawable.jf_icon);
                        }else {
                            block_iv.setImageResource(R.drawable.ft_icon);
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                    }
                });
                break;
            default:
                break;
        }
    }

    private void initItemFragment() {
        int currentItem = income_viewpager.getCurrentItem();
        if(currentItem==0){
            requestFragmentListData(currentItem+1);
        }else if(currentItem==titleList.size()-1){
            requestFragmentListData(currentItem-1);
        }else {
            requestFragmentListData(currentItem+1);
            requestFragmentListData(currentItem-1);
        }
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
            return titleList.size();
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
