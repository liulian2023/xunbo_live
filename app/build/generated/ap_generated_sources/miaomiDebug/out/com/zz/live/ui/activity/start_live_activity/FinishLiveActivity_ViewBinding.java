// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity;

import android.view.View;
import android.widget.Button;
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

public class FinishLiveActivity_ViewBinding implements Unbinder {
  private FinishLiveActivity target;

  private View view7f0903a4;

  @UiThread
  public FinishLiveActivity_ViewBinding(FinishLiveActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FinishLiveActivity_ViewBinding(final FinishLiveActivity target, View source) {
    this.target = target;

    View view;
    target.big_bg_iv = Utils.findRequiredViewAsType(source, R.id.big_bg_iv, "field 'big_bg_iv'", ImageView.class);
    target.title_iv = Utils.findRequiredViewAsType(source, R.id.title_iv, "field 'title_iv'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.return_home_btn, "field 'return_home_btn' and method 'onClick'");
    target.return_home_btn = Utils.castView(view, R.id.return_home_btn, "field 'return_home_btn'", Button.class);
    view7f0903a4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.live_time_tv = Utils.findRequiredViewAsType(source, R.id.live_time_tv, "field 'live_time_tv'", TextView.class);
    target.gift_amount_tv = Utils.findRequiredViewAsType(source, R.id.gift_amount_tv, "field 'gift_amount_tv'", TextView.class);
    target.watch_num_tv = Utils.findRequiredViewAsType(source, R.id.watch_num_tv, "field 'watch_num_tv'", TextView.class);
    target.name_tv = Utils.findRequiredViewAsType(source, R.id.name_tv, "field 'name_tv'", TextView.class);
    target.toll_amount_tv = Utils.findRequiredViewAsType(source, R.id.toll_amount_tv, "field 'toll_amount_tv'", TextView.class);
    target.toll_linear = Utils.findRequiredViewAsType(source, R.id.toll_linear, "field 'toll_linear'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FinishLiveActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.big_bg_iv = null;
    target.title_iv = null;
    target.return_home_btn = null;
    target.live_time_tv = null;
    target.gift_amount_tv = null;
    target.watch_num_tv = null;
    target.name_tv = null;
    target.toll_amount_tv = null;
    target.toll_linear = null;

    view7f0903a4.setOnClickListener(null);
    view7f0903a4 = null;
  }
}
