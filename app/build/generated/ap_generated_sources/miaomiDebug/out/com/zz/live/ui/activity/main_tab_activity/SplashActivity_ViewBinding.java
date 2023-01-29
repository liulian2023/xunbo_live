// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashActivity_ViewBinding implements Unbinder {
  private SplashActivity target;

  private View view7f0903a2;

  @UiThread
  public SplashActivity_ViewBinding(SplashActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashActivity_ViewBinding(final SplashActivity target, View source) {
    this.target = target;

    View view;
    target.splash_image = Utils.findRequiredViewAsType(source, R.id.splash_image, "field 'splash_image'", ImageView.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.reload_tv, "field 'reload_tv' and method 'onClick'");
    target.reload_tv = Utils.castView(view, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    view7f0903a2 = view;
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
    SplashActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.splash_image = null;
    target.error_linear = null;
    target.reload_tv = null;

    view7f0903a2.setOnClickListener(null);
    view7f0903a2 = null;
  }
}
