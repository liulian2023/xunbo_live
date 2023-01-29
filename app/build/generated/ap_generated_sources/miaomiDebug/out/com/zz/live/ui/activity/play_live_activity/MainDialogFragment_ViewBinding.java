// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.play_live_activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import com.zz.live.myView.VerticalViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainDialogFragment_ViewBinding implements Unbinder {
  private MainDialogFragment target;

  @UiThread
  public MainDialogFragment_ViewBinding(MainDialogFragment target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewPager'", VerticalViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
  }
}
