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

public class FeedBackActivity_ViewBinding implements Unbinder {
  private FeedBackActivity target;

  @UiThread
  public FeedBackActivity_ViewBinding(FeedBackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FeedBackActivity_ViewBinding(FeedBackActivity target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.feedback_fragment_content, "field 'mViewPager'", ViewPager.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.feedback_tablayout, "field 'tabLayout'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FeedBackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.tabLayout = null;
  }
}
