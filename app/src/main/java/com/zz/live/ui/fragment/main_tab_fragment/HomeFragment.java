package com.zz.live.ui.fragment.main_tab_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.HomeEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.OnLineCustomerActivity;
import com.zz.live.ui.activity.mine_fragment_activity.FeedBackActivity;
import com.zz.live.ui.activity.mine_fragment_activity.MessageActivity;
import com.zz.live.ui.activity.mine_fragment_activity.MineDetailsActivity;
import com.zz.live.ui.activity.mine_fragment_activity.wallet_activity.MineWalletActivity;
import com.zz.live.ui.activity.mine_fragment_activity.income_activity.InComeActivity;
import com.zz.live.ui.activity.user_info_activity.UserInfoActivity;
import com.zz.live.ui.adapter.HomeAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.DateUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Headers;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    /*
    头部控件
     */
    RelativeLayout margin_top_view;
    TextView live_time_tv;
    TextView mine_amount_tv;
    ImageView kefu_iv;
    @BindView(R.id.mine_recycler)
    RecyclerView mine_recycler;
    HomeAdapter homeAdapter;
    ArrayList<HomeEntity> mineEntityArrayList  = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
        SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
        if(sp.getLoginStatus()){
            getUserInfo();
        }
    }
    private void getUserInfo() {
        HashMap<String, Object> data = new HashMap<>();
//      data.put("type",1);
        HttpApiUtils.normalRequest(getActivity(),null, RequestUtils.USER_INFO, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                UserInfoEntity.DataBean userInfoEntityData = userInfoEntity.getData();
                SharePreferencesUtil.putString(getActivity(), CommonStr.USER_INFO,result);
                Utils.setUserInfoBean(userInfoEntityData);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    /**
     * 直播时长 我的收入
     */
    private void requestHeadData() {
        HttpApiUtils.wwwNormalRequest(getActivity(), this, RequestUtils.HOME_HEAD_DATA, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
                String totalAmount = jsonObject.getString("totalAmount");
                String playTime = jsonObject.getString("playTime");
                live_time_tv.setText(DateUtil.secToTime(Integer.parseInt(playTime)));
                mine_amount_tv.setText(totalAmount);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

/*    @Override
    public void onStart() {
        super.onStart();
        requestHeadData();
    }*/

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        requestHeadData();
    }

    private void initRecycler() {
        homeAdapter = new HomeAdapter(R.layout.home_recycler_item_layout,mineEntityArrayList);
        mine_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mine_recycler.setAdapter(homeAdapter);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.home_recycler_head_layout,null);
        bindHeadView(headView);
        homeAdapter.addHeaderView(headView);
        homeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                HomeEntity homeEntity = mineEntityArrayList.get(position);
                initRecyckerItemClick(homeEntity);
            }
        });
        addRecycleData();
    }

    private void initRecyckerItemClick(HomeEntity homeEntity) {
        /*
      memberStatus 0 审核中  1 已认证  2认证失败 3 未认证
       */
        String typeName = homeEntity.getTypeName();
        if(typeName.equals("个人设置")){
            startActivity(new Intent(getContext(),UserInfoActivity.class));
        }else if(typeName.equals("我的钱包")){
            startActivity(new Intent(getContext(), MineWalletActivity.class));
        }else if(typeName.equals("收入详情")){
            InComeActivity.startAty(getContext(),"收入详情");
        }else if(typeName.equals("开播历史")){
            MineDetailsActivity.startAty(getContext(),2);
        }else if(typeName.equals("我的消息")){
            startActivity(new Intent(getContext(),MessageActivity.class));
        }else {
            startActivity(new Intent(getContext(), FeedBackActivity.class));
        }
    }


    private void addRecycleData() {
        mineEntityArrayList.clear();
        mineEntityArrayList.add(new HomeEntity(R.drawable.wdqb_icon,"我的钱包"));
        mineEntityArrayList.add(new HomeEntity(R.drawable.srxq_icon,"收入详情"));
        mineEntityArrayList.add(new HomeEntity(R.drawable.kbls_icon,"开播历史"));
        mineEntityArrayList.add(new HomeEntity(R.drawable.znxx_icon,"我的消息"));
        mineEntityArrayList.add(new HomeEntity(R.drawable.wod_yjfk_icon,"意见反馈"));
        mineEntityArrayList.add(new HomeEntity(R.drawable.wdxx_icon,"个人设置"));
        homeAdapter.notifyDataSetChanged();
    }

    private void bindHeadView(View headView) {
        margin_top_view = headView.findViewById(R.id.margin_top_view);
        live_time_tv = headView.findViewById(R.id.live_time_tv);
        mine_amount_tv = headView.findViewById(R.id.mine_amount_tv);
        kefu_iv = headView.findViewById(R.id.kefu_iv);
        kefu_iv.setOnClickListener(this);
        ImmersionBar.with(this)
                .titleBarMarginTop(margin_top_view)
                .navigationBarColor(R.color.transparent)
                .init();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kefu_iv:
                OnLineCustomerActivity.startAty(getContext(), SharePreferencesUtil.getString(getContext(), CommonStr.CUSTOMER,""));
                break;
            default:
                break;
        }
    }

}
