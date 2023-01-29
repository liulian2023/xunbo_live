package com.zz.live.ui.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.NatureEntity;
import com.zz.live.ui.adapter.NatureAdapter;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseNaturePop extends BasePopupWindow {
    RecyclerView nature_recycler;
    NatureAdapter natureAdapter;
    ArrayList<NatureEntity> natureEntityArrayList= new ArrayList<>();

    public ArrayList<NatureEntity> getNatureEntityArrayList() {
        return natureEntityArrayList;
    }

    public ChooseNaturePop(Context context, boolean focusable) {
        super(context);
        setAnimationStyle(R.style.down_to_up150);
        initRecycler();
    }

    @Override
    public void initView() {
        super.initView();
        rootView = LayoutInflater.from(mContext).inflate(R.layout.nature_list_pop_layout,null);
    }
    private void initRecycler() {
        nature_recycler = rootView.findViewById(R.id.nature_recycler);
        natureAdapter = new NatureAdapter(R.layout.nature_recycler_item,natureEntityArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        nature_recycler.setLayoutManager(linearLayoutManager);
        nature_recycler.setAdapter(natureAdapter);
        String natureStr = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.NATURE, "");
        String[] split = natureStr.split(",");
        List<String> natureList = Arrays.asList(split);
        for (int i = 0; i < natureList.size(); i++) {
            natureEntityArrayList.add(new NatureEntity(natureList.get(i)));
        }
        natureAdapter.notifyDataSetChanged();
        natureAdapter.addChildClickViewIds( R.id.nature_content_tv);
        natureAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if(mOnPopItemClick!=null){
                    mOnPopItemClick.onPopItemClick(view,position);
                }
            }
        });

    }
}
