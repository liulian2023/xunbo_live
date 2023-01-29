package com.zz.live.ui.activity.play_live_activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.alibaba.fastjson.JSONObject;
import com.zz.live.R;
import com.zz.live.bean.LiveEntity;
import com.zz.live.bean.evenBus.ShowCoverEven;
import com.zz.live.myView.VerticalViewPager;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.RequestUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import okhttp3.Headers;

@SuppressLint("ValidFragment")
public class MainDialogFragment extends DialogFragment implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager)
    VerticalViewPager viewPager;
    private BaseFragmentPagerAdapter fragmentPagerAdapter;
    private FrameLayout viewById;
    private LinkedList<Fragment> pageFragmentCache = new LinkedList<>();
    private PlayLiveActivity playLiveActivity;
    int current=1;
    public MainDialogFragment(FrameLayout viewById) { this. viewById=viewById; }
    LiveEntity.DataBean.RecordsBean intentBean;//点击传入的been
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);/*同时将界面改为resize已达到软键盘弹出时LiveFragment不会跟随移动*/
        initViewPager();
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        viewPager.setOffscreenPageLimit(0);
        viewPager.setOnPageChangeListener(this);
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        fragmentPagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), pageFragmentCache);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        requestLiveListData(false);
    }
    private void requestLiveListData(boolean isLoadMore) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",current);
        data.put("size",20);
        data.put("categoryId","");
        HttpApiUtils.wwwNormalRequest(getActivity(), this,RequestUtils.LIVE_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                LiveEntity liveEntity = JSONObject.parseObject(result, LiveEntity.class);
                List<LiveEntity.DataBean.RecordsBean> records = liveEntity.getData().getRecords();
/*                if(records.size()==0){
                    Toast toast = Toast.makeText(getContext(), null, Toast.LENGTH_SHORT);
                    toast.setText("没有更多数据啦!");
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }*/
                if(!isLoadMore){//第一次进入时请求
                    if(getActivity() instanceof PlayLiveActivity){
                        playLiveActivity = (PlayLiveActivity) getActivity();
                        intentBean= (LiveEntity.DataBean.RecordsBean) playLiveActivity.getIntent().getSerializableExtra("recordsBean");
                        pageFragmentCache.add(0,LiveFragment.newInstence(0,intentBean));
                    }
                    for (int i = 0; i < records.size(); i++) {
                        LiveEntity.DataBean.RecordsBean recordsBean = records.get(i);
                        //过滤列表中与传入的bean相同的model
//                        if(intentBean!=null&&!intentBean.getPlayUrl().equals(recordsBean.getPlayUrl())){
                        if(intentBean!=null&&!intentBean.getStreamName().equals(recordsBean.getStreamName())){
                            pageFragmentCache.add(LiveFragment.newInstence(i+1, recordsBean));
                        }
                    }
                }else {
                    //滑动到最后一个数据时,每次都在最后面add
                    for (int i = 0; i < records.size(); i++) {
                        LiveEntity.DataBean.RecordsBean recordsBean = records.get(i);
                        pageFragmentCache.add(LiveFragment.newInstence(pageFragmentCache.size(),recordsBean));
                    }
                }
                fragmentPagerAdapter.notifyDataSetChanged();
                //第一次进入播放界面时, 发送通知, 显示封面(viewPager的默认选中再一次不会触发onPageSelected(),需要单独发送一次广播)
                if(!isLoadMore){
                    EventBus.getDefault().post(new ShowCoverEven(0));
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.MainDialog) {/*设置MainDialogFragment的样式，这里的代码最好还是用我的，大家不要改动*/
            @Override
            public void onBackPressed() {
                super.onBackPressed();
                getActivity().finish();
            }
        };
        return dialog;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //每次选中时都发通知显示所有fragment的封面图(否则往上滑动时,不会出现封面图片)
        EventBus.getDefault().post(new ShowCoverEven(position));
       if(position==pageFragmentCache.size()-1){
           current++;
            requestLiveListData(true);
       }
        for (int i = 0; i < pageFragmentCache.size(); i++) {
         LiveFragment liveFragment= (LiveFragment) pageFragmentCache.get(i);
            liveFragment.visibleCount=0;
        }
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }

    /**
     * 该类私有数据适配器
     */
    private class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

        private FragmentManager mFragmentManager;
        private LinkedList<Fragment> pageFragmentCache;

        public BaseFragmentPagerAdapter(FragmentManager fm, LinkedList<Fragment> pageFragmentCache) {
            super(fm);
            this.mFragmentManager = fm;
            this.pageFragmentCache = pageFragmentCache;
        }

        @Override
        public Fragment getItem(int position) {
//            return LiveFragment.newInstence(position);
            return pageFragmentCache.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getCount() {
            return pageFragmentCache.size();
        }

    }
}