package com.zz.live.ui.fragment.main_tab_fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.ui.activity.main_tab_activity.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DynamicFragment extends BaseFragment {
    @BindView(R.id.starusbar_view)
    View starusbar_view;
    @BindView(R.id.test_btn)
    Button test_btn;
    @Override
    protected void init(Bundle savedInstanceState) {
        ImmersionBar.with(this)
                .statusBarView(starusbar_view)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .statusBarColor(R.color.transparent)
                .init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @OnClick({R.id.test_btn})
    public void onClick(View v){
        startActivity(new Intent(getContext(), RegisterActivity.class));
    }
}
