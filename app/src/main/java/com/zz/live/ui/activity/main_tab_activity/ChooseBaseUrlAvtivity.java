package com.zz.live.ui.activity.main_tab_activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.BaseUrlEntity;
import com.zz.live.ui.adapter.BaseUrlAdapter;
import com.zz.live.utils.CommonToolbarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooseBaseUrlAvtivity extends BaseActivity {
    @BindView(R.id.base_url_recycler)
    RecyclerView base_url_recycler;
    BaseUrlAdapter baseUrlAdapter;
    ArrayList<BaseUrlEntity.DataBean> baseUrlEntityArrayList = new ArrayList<>();
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();


    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_base_url_avtivity;
    }

    @Override
    protected void init(Bundle savedInstanceState) { ;
        initRecycler();
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"选择线路");
        CommonToolbarUtil.initStatusBarColor(this);
    }

    private void initRecycler() {
        baseUrlAdapter = new BaseUrlAdapter(R.layout.base_url_recycler_item,baseUrlEntityArrayList);
        base_url_recycler.setLayoutManager(new LinearLayoutManager(this));
        base_url_recycler.setAdapter(baseUrlAdapter);
        baseUrlAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                for (int i = 0; i < baseUrlEntityArrayList.size(); i++) {
                    BaseUrlEntity.DataBean appRequestDomainsBean = baseUrlEntityArrayList.get(i);
                    if(position==i){
                        appRequestDomainsBean.setCheck(true);
                        sp.setNewBaseUrl(appRequestDomainsBean.getDomain());
                        startActivity(new Intent(ChooseBaseUrlAvtivity.this,SplashActivity.class));
                        finish();
                    }else {
                        appRequestDomainsBean.setCheck(false);
                    }
                }
                baseUrlAdapter.notifyDataSetChanged();
            }
        });
        String urlList = sp.getUrlList();
        String newBaseUrl = sp.getNewBaseUrl();
        BaseUrlEntity baseUrlEntity = JSONObject.parseObject(urlList, BaseUrlEntity.class);
        List<BaseUrlEntity.DataBean> appRequestDomains = baseUrlEntity.getData();
        for (int i = 0; i < appRequestDomains.size(); i++) {
            BaseUrlEntity.DataBean appRequestDomainsBean = appRequestDomains.get(i);
            if(newBaseUrl.equals(appRequestDomainsBean.getDomain())){
                appRequestDomainsBean.setCheck(true);
            }
        }
        baseUrlEntityArrayList.addAll(appRequestDomains);
        baseUrlAdapter.notifyDataSetChanged();

    }


    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
