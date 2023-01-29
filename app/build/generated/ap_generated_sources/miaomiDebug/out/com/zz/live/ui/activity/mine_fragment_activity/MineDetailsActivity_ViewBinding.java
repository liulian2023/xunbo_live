// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.tabs.TabLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineDetailsActivity_ViewBinding implements Unbinder {
  private MineDetailsActivity target;

  @UiThread
  public MineDetailsActivity_ViewBinding(MineDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineDetailsActivity_ViewBinding(MineDetailsActivity target, View source) {
    this.target = target;

    target.details_tabLayout = Utils.findRequiredViewAsType(source, R.id.details_tabLayout, "field 'details_tabLayout'", TabLayout.class);
    target.details_viewPager = Utils.findRequiredViewAsType(source, R.id.details_viewPager, "field 'details_viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.details_tabLayout = null;
    target.details_viewPager = null;
  }
}
