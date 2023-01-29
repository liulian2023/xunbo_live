// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.main_tab_fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.androidkun.xtablayout.XTabLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeLiveFragment_ViewBinding implements Unbinder {
  private HomeLiveFragment target;

  private View view7f0901fc;

  private View view7f0901f5;

  @UiThread
  public HomeLiveFragment_ViewBinding(final HomeLiveFragment target, View source) {
    this.target = target;

    View view;
    target.actionBarLinear = Utils.findRequiredViewAsType(source, R.id.home_action_linear, "field 'actionBarLinear'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.home_search_iv, "field 'searchIv' and method 'OnClick'");
    target.searchIv = Utils.castView(view, R.id.home_search_iv, "field 'searchIv'", ImageView.class);
    view7f0901fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_on_line_kf_iv, "field 'onlineKfIv' and method 'OnClick'");
    target.onlineKfIv = Utils.castView(view, R.id.home_on_line_kf_iv, "field 'onlineKfIv'", ImageView.class);
    view7f0901f5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.OnClick(p0);
      }
    });
    target.mTab = Utils.findRequiredViewAsType(source, R.id.home_tab, "field 'mTab'", XTabLayout.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.home_fragment_viewpager, "field 'mViewPager'", ViewPager.class);
    target.starusbar_view = Utils.findRequiredView(source, R.id.starusbar_view, "field 'starusbar_view'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeLiveFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.actionBarLinear = null;
    target.searchIv = null;
    target.onlineKfIv = null;
    target.mTab = null;
    target.mViewPager = null;
    target.starusbar_view = null;

    view7f0901fc.setOnClickListener(null);
    view7f0901fc = null;
    view7f0901f5.setOnClickListener(null);
    view7f0901f5 = null;
  }
}
