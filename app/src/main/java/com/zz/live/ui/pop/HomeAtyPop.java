package com.zz.live.ui.pop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.HomeNoticeEntity;
import com.zz.live.ui.adapter.GiftZSQAdapter;
import com.zz.live.ui.adapter.HomeAtyRecyclerAdapter;
import com.zz.live.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeAtyPop extends BasePopupWindow {
    private Activity activity;
    TextView app_name_tv;
    ViewPager2 home_aty_viewPager;
    RecyclerView home_indicator_recycler;
    Button sure_btn;
    HomeAtyRecyclerAdapter homeAtyRecyclerAdapter;
    ArrayList<HomeNoticeEntity.DataBean.RecordsBean> dataList = new ArrayList<>();
    private GiftZSQAdapter giftZSQAdapter;
    private List<Boolean> zsqList = new ArrayList<>();
    public HomeAtyPop(Context context ,Activity activity, ArrayList<HomeNoticeEntity.DataBean.RecordsBean> dataList) {
        super(context);
        this .activity = activity;
        this .dataList = dataList;
        initZSQRecycler();
        initViewPager();
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ProgressDialogUtil.darkenBackground(activity,1f);
                //得到recycleView 中所有item的viewHolder,然后找到webView后进行反注册, 避免webView未释放引起的内存泄漏
                for (int i = 0; i < dataList.size(); i++) {
                  WebView home_aty_webView = (WebView) homeAtyRecyclerAdapter.getViewByPosition(i,R.id.home_aty_webView);
                    if (home_aty_webView != null) {
                        ViewParent parent = home_aty_webView.getParent();
                        if (parent != null) {
                            ((ViewGroup) parent).removeView(home_aty_webView);
                        }
                        home_aty_webView.stopLoading();
                        // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                        home_aty_webView.getSettings().setJavaScriptEnabled(false);
                        home_aty_webView.clearHistory();
                        home_aty_webView.clearView();
                        home_aty_webView.removeAllViews();
                        try {
                            home_aty_webView.destroy();
                        } catch (Throwable ex) {

                        }
                    }
                }
                if(mOnDissmissListener!=null){
                    mOnDissmissListener.onDissmiss();
                }
            }
        });
    }

    private void initZSQRecycler() {
        /**
         * 初始化下方指示器
         */
        giftZSQAdapter = new GiftZSQAdapter(R.layout.item_zsq,zsqList, GiftZSQAdapter.SelectorColor.BULE);
        LinearLayoutManager zsqLinearLayoutManager =  new LinearLayoutManager(mContext);
        zsqLinearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        home_indicator_recycler.setLayoutManager(zsqLinearLayoutManager);
        home_indicator_recycler.setHasFixedSize(true);
        home_indicator_recycler.setAdapter(giftZSQAdapter);
        if(dataList.size()!=1){//只有一条数据时不显示圆点显示器
            for (int i=0;i<dataList.size();i++){
                zsqList.add(false);
            }
        }

        giftZSQAdapter.notifyDataSetChanged();
    }

    private void initViewPager() {



        homeAtyRecyclerAdapter = new HomeAtyRecyclerAdapter(R.layout.home_aty_recycler_item,dataList);
        home_aty_viewPager.setAdapter(homeAtyRecyclerAdapter);
        home_aty_viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                int currentItem = home_aty_viewPager.getCurrentItem();
                HomeNoticeEntity.DataBean.RecordsBean recordsBean = dataList.get(currentItem);
//                app_name_tv.setText(String.format("【%s】",recordsBean.getTitle()));
                app_name_tv.setText(recordsBean.getTitle());
                for (int i =0;i<zsqList.size();i++){
                    zsqList.set(i,false);
                }
                if(zsqList.size()>position){
                    zsqList.set(position,true);
                }
                giftZSQAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.home_aty_pop_layout,null);
        app_name_tv = rootView.findViewById(R.id.app_name_tv);
        home_aty_viewPager = rootView.findViewById(R.id.home_aty_viewPager);
        home_indicator_recycler = rootView.findViewById(R.id.home_indicator_recycler);
        sure_btn = rootView.findViewById(R.id.sure_btn);
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeAtyPop.this.dismiss();
            }
        });
    }

    public HomeAtyRecyclerAdapter getHomeAtyRecyclerAdapter() {
        return homeAtyRecyclerAdapter;
    }

    public void setHomeAtyRecyclerAdapter(HomeAtyRecyclerAdapter homeAtyRecyclerAdapter) {
        this.homeAtyRecyclerAdapter = homeAtyRecyclerAdapter;
    }
}
