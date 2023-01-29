// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnchorHouseKeeperActivity_ViewBinding implements Unbinder {
  private AnchorHouseKeeperActivity target;

  private View view7f0903dc;

  private View view7f090259;

  private View view7f090068;

  private View view7f090070;

  private View view7f090102;

  private View view7f09018e;

  @UiThread
  public AnchorHouseKeeperActivity_ViewBinding(AnchorHouseKeeperActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnchorHouseKeeperActivity_ViewBinding(final AnchorHouseKeeperActivity target,
      View source) {
    this.target = target;

    View view;
    target.housKeeper_title_relativeLayout = Utils.findRequiredViewAsType(source, R.id.housKeeper_title_relativeLayout, "field 'housKeeper_title_relativeLayout'", RelativeLayout.class);
    target.house_keeper_tab = Utils.findRequiredViewAsType(source, R.id.house_keeper_tab, "field 'house_keeper_tab'", TabLayout.class);
    target.house_keeper_viewPager = Utils.findRequiredViewAsType(source, R.id.house_keeper_viewPager, "field 'house_keeper_viewPager'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.set_iv, "field 'set_iv' and method 'onClick'");
    target.set_iv = Utils.castView(view, R.id.set_iv, "field 'set_iv'", ImageView.class);
    view7f0903dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.kefu_iv, "field 'kefu_iv' and method 'onClick'");
    target.kefu_iv = Utils.castView(view, R.id.kefu_iv, "field 'kefu_iv'", ImageView.class);
    view7f090259 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.username_tv = Utils.findRequiredViewAsType(source, R.id.username_tv, "field 'username_tv'", TextView.class);
    target.family_name_tv = Utils.findRequiredViewAsType(source, R.id.family_name_tv, "field 'family_name_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.anchor_manage_linear, "field 'anchor_manage_linear' and method 'onClick'");
    target.anchor_manage_linear = Utils.castView(view, R.id.anchor_manage_linear, "field 'anchor_manage_linear'", LinearLayout.class);
    view7f090068 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.anchor_report_linear, "field 'anchor_report_linear' and method 'onClick'");
    target.anchor_report_linear = Utils.castView(view, R.id.anchor_report_linear, "field 'anchor_report_linear'", LinearLayout.class);
    view7f090070 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.change_cashBack_linear, "field 'change_cashBack_linear' and method 'onClick'");
    target.change_cashBack_linear = Utils.castView(view, R.id.change_cashBack_linear, "field 'change_cashBack_linear'", LinearLayout.class);
    view7f090102 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.finance_reconciliation_linear, "field 'finance_reconciliation_linear' and method 'onClick'");
    target.finance_reconciliation_linear = Utils.castView(view, R.id.finance_reconciliation_linear, "field 'finance_reconciliation_linear'", LinearLayout.class);
    view7f09018e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AnchorHouseKeeperActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.housKeeper_title_relativeLayout = null;
    target.house_keeper_tab = null;
    target.house_keeper_viewPager = null;
    target.set_iv = null;
    target.kefu_iv = null;
    target.username_tv = null;
    target.family_name_tv = null;
    target.anchor_manage_linear = null;
    target.anchor_report_linear = null;
    target.change_cashBack_linear = null;
    target.finance_reconciliation_linear = null;

    view7f0903dc.setOnClickListener(null);
    view7f0903dc = null;
    view7f090259.setOnClickListener(null);
    view7f090259 = null;
    view7f090068.setOnClickListener(null);
    view7f090068 = null;
    view7f090070.setOnClickListener(null);
    view7f090070 = null;
    view7f090102.setOnClickListener(null);
    view7f090102 = null;
    view7f09018e.setOnClickListener(null);
    view7f09018e = null;
  }
}
