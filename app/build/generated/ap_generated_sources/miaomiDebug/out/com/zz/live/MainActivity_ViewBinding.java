// Generated code from Butter Knife. Do not modify!
package com.zz.live;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f090439;

  private View view7f090436;

  private View view7f09043f;

  private View view7f09043c;

  private View view7f090413;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tab_home_linear, "field 'tab_home_linear' and method 'onClick'");
    target.tab_home_linear = Utils.castView(view, R.id.tab_home_linear, "field 'tab_home_linear'", LinearLayout.class);
    view7f090439 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tab_dynamic_linear, "field 'tab_dynamic_linear' and method 'onClick'");
    target.tab_dynamic_linear = Utils.castView(view, R.id.tab_dynamic_linear, "field 'tab_dynamic_linear'", LinearLayout.class);
    view7f090436 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tab_rank_linear, "field 'tab_rank_linear' and method 'onClick'");
    target.tab_rank_linear = Utils.castView(view, R.id.tab_rank_linear, "field 'tab_rank_linear'", LinearLayout.class);
    view7f09043f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tab_mine_linear, "field 'tab_mine_linear' and method 'onClick'");
    target.tab_mine_linear = Utils.castView(view, R.id.tab_mine_linear, "field 'tab_mine_linear'", LinearLayout.class);
    view7f09043c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tab_home_img = Utils.findRequiredViewAsType(source, R.id.tab_home_img, "field 'tab_home_img'", ImageView.class);
    target.tab_dynamic_img = Utils.findRequiredViewAsType(source, R.id.tab_dynamic_img, "field 'tab_dynamic_img'", ImageView.class);
    target.tab_rank_img = Utils.findRequiredViewAsType(source, R.id.tab_rank_img, "field 'tab_rank_img'", ImageView.class);
    target.tab_mine_img = Utils.findRequiredViewAsType(source, R.id.tab_mine_img, "field 'tab_mine_img'", ImageView.class);
    target.tab_home_tv = Utils.findRequiredViewAsType(source, R.id.tab_home_tv, "field 'tab_home_tv'", TextView.class);
    target.tab_dynamic_tv = Utils.findRequiredViewAsType(source, R.id.tab_dynamic_tv, "field 'tab_dynamic_tv'", TextView.class);
    target.tab_rank_tv = Utils.findRequiredViewAsType(source, R.id.tab_rank_tv, "field 'tab_rank_tv'", TextView.class);
    target.tab_mine_tv = Utils.findRequiredViewAsType(source, R.id.tab_mine_tv, "field 'tab_mine_tv'", TextView.class);
    target.tab_unread_tv = Utils.findRequiredViewAsType(source, R.id.tab_unread_tv, "field 'tab_unread_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.start_live_iv, "field 'start_live_iv' and method 'onClick'");
    target.start_live_iv = Utils.castView(view, R.id.start_live_iv, "field 'start_live_iv'", ImageView.class);
    view7f090413 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.maim_fragment_content = Utils.findRequiredViewAsType(source, R.id.maim_fragment_content, "field 'maim_fragment_content'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tab_home_linear = null;
    target.tab_dynamic_linear = null;
    target.tab_rank_linear = null;
    target.tab_mine_linear = null;
    target.tab_home_img = null;
    target.tab_dynamic_img = null;
    target.tab_rank_img = null;
    target.tab_mine_img = null;
    target.tab_home_tv = null;
    target.tab_dynamic_tv = null;
    target.tab_rank_tv = null;
    target.tab_mine_tv = null;
    target.tab_unread_tv = null;
    target.start_live_iv = null;
    target.maim_fragment_content = null;

    view7f090439.setOnClickListener(null);
    view7f090439 = null;
    view7f090436.setOnClickListener(null);
    view7f090436 = null;
    view7f09043f.setOnClickListener(null);
    view7f09043f = null;
    view7f09043c.setOnClickListener(null);
    view7f09043c = null;
    view7f090413.setOnClickListener(null);
    view7f090413 = null;
  }
}
