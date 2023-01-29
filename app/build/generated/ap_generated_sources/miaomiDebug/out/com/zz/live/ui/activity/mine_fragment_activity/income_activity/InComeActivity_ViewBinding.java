// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.income_activity;

import android.view.View;
import android.widget.ImageView;
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

public class InComeActivity_ViewBinding implements Unbinder {
  private InComeActivity target;

  private View view7f090118;

  @UiThread
  public InComeActivity_ViewBinding(InComeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InComeActivity_ViewBinding(final InComeActivity target, View source) {
    this.target = target;

    View view;
    target.income_tab = Utils.findRequiredViewAsType(source, R.id.income_tab, "field 'income_tab'", TabLayout.class);
    target.income_viewpager = Utils.findRequiredViewAsType(source, R.id.income_viewpager, "field 'income_viewpager'", ViewPager.class);
    target.income_amount_tv = Utils.findRequiredViewAsType(source, R.id.income_amount_tv, "field 'income_amount_tv'", TextView.class);
    target.expenses_amount_tv = Utils.findRequiredViewAsType(source, R.id.expenses_amount_tv, "field 'expenses_amount_tv'", TextView.class);
    target.date_tv = Utils.findRequiredViewAsType(source, R.id.date_tv, "field 'date_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.choose_time_iv, "field 'choose_time_iv' and method 'onClick'");
    target.choose_time_iv = Utils.castView(view, R.id.choose_time_iv, "field 'choose_time_iv'", ImageView.class);
    view7f090118 = view;
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
    InComeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.income_tab = null;
    target.income_viewpager = null;
    target.income_amount_tv = null;
    target.expenses_amount_tv = null;
    target.date_tv = null;
    target.choose_time_iv = null;

    view7f090118.setOnClickListener(null);
    view7f090118 = null;
  }
}
