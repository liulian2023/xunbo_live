package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.HouseKeeperEnity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.adapter.HouseKeeperAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.RequestUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Headers;

public class AnchorHouseKeeperFragment extends BaseFragment {

    @BindView(R.id.house_keeper_recycler)
    RecyclerView house_keeper_recycler;
    HouseKeeperAdapter houseKeeperAdapter;
    ArrayList<HouseKeeperEnity>houseKeeperEnityArrayList= new ArrayList<>();
    int dataType;

    TextView total_amount_tv;

    @Override
    protected void init(Bundle savedInstanceState) {
        getArgumentsData();
        initRecycler();
        requestHouseKeeperData();
    }

    private void requestHouseKeeperData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("dateType",dataType);
        HttpApiUtils.wwwNormalRequest(getActivity(), this, RequestUtils.HOUSE_HOME, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
                String totalAmount = jsonObject.getString("totalAmount");
                String subscribe = jsonObject.getString("subscribe");
                String giftAmount = jsonObject.getString("giftAmount");
                String cashBack = jsonObject.getString("cashBack");
                String  otherAmount = jsonObject.getString("otherAmount");
                total_amount_tv.setText(totalAmount);
                for (int i = 0; i <houseKeeperEnityArrayList.size() ; i++) {
                    HouseKeeperEnity houseKeeperEnity = houseKeeperEnityArrayList.get(i);
                    switch (i){
                        case 0://订阅
                            houseKeeperEnity.setAmount(subscribe);
                            break;
                        case 1://礼物
                            houseKeeperEnity.setAmount(giftAmount);
                            break;
                        case 2://返现
                            houseKeeperEnity.setAmount(cashBack);
                            break;
                        case 3://其他
                            houseKeeperEnity.setAmount(otherAmount);
                            break;
                        default:
                            break;
                    }
                }
                houseKeeperAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
                System.out.println(msg);
            }
        });
    }

    private void getArgumentsData() {
        int position=  getArguments().getInt(CommonStr.POSITION);
        dataType=position+1;
    }

    private void initRecycler() {
        houseKeeperAdapter = new HouseKeeperAdapter(R.layout.house_keeper_recycler_item,houseKeeperEnityArrayList);
        house_keeper_recycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        house_keeper_recycler.setAdapter(houseKeeperAdapter);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.house_keeper_recycler_head_layout,null);
        bindHeadView(headView);
        houseKeeperAdapter.addHeaderView(headView);
        houseKeeperEnityArrayList.add( new HouseKeeperEnity("订阅",R.drawable.dy_icon));
        houseKeeperEnityArrayList.add( new HouseKeeperEnity("礼物",R.drawable.lw_icon));
        houseKeeperEnityArrayList.add( new HouseKeeperEnity("返现",R.drawable.fx_icon));
        houseKeeperEnityArrayList.add( new HouseKeeperEnity("其他",R.drawable.qt_icon));


    }

    private void bindHeadView(View headView) {
        total_amount_tv = headView.findViewById(R.id.total_amount_tv);
    }

    public static AnchorHouseKeeperFragment newInstance(int position){
        AnchorHouseKeeperFragment anchorHouseKeeperFragment = new AnchorHouseKeeperFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        anchorHouseKeeperFragment.setArguments(bundle);
        return anchorHouseKeeperFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_anchor_house_keeper;
    }


}
