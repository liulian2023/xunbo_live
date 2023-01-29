package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.GiftEntity;
import com.zz.live.ui.adapter.GiftAdapter;
import com.zz.live.utils.CommonStr;

import java.util.ArrayList;

import butterknife.BindView;

public class GiftFragment extends BaseFragment {
    @BindView(R.id.gift_recycler)
    RecyclerView gift_recycler;
    GiftAdapter giftAdapter;
    ArrayList<GiftEntity>giftEntityArrayList = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
    }

    private void initRecycler() {
        giftAdapter = new GiftAdapter(R.layout.gift_recycler_item,giftEntityArrayList);
        gift_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        gift_recycler.setAdapter(giftAdapter);
        for (int i = 0; i < 20; i++) {
            giftEntityArrayList.add(new GiftEntity());
        }
    }

    public static GiftFragment newInstance(int position){
        GiftFragment giftFragment = new GiftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CommonStr.POSITION,position);
        giftFragment.setArguments(bundle);
        return giftFragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_gift;
    }


}
