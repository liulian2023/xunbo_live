// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ToyActivity_ViewBinding implements Unbinder {
  private ToyActivity target;

  private View view7f09041a;

  private View view7f0903c9;

  @UiThread
  public ToyActivity_ViewBinding(ToyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ToyActivity_ViewBinding(final ToyActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.stop_search_tv, "field 'stop_search_tv' and method 'onClick'");
    target.stop_search_tv = Utils.castView(view, R.id.stop_search_tv, "field 'stop_search_tv'", TextView.class);
    view7f09041a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.search_toy_tv, "field 'search_toy_tv' and method 'onClick'");
    target.search_toy_tv = Utils.castView(view, R.id.search_toy_tv, "field 'search_toy_tv'", TextView.class);
    view7f0903c9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.bluetooth_status_tv = Utils.findRequiredViewAsType(source, R.id.bluetooth_status_tv, "field 'bluetooth_status_tv'", TextView.class);
    target.searching_tv = Utils.findRequiredViewAsType(source, R.id.searching_tv, "field 'searching_tv'", TextView.class);
    target.toy_recycler = Utils.findRequiredViewAsType(source, R.id.toy_recycler, "field 'toy_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ToyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.stop_search_tv = null;
    target.search_toy_tv = null;
    target.bluetooth_status_tv = null;
    target.searching_tv = null;
    target.toy_recycler = null;

    view7f09041a.setOnClickListener(null);
    view7f09041a = null;
    view7f0903c9.setOnClickListener(null);
    view7f0903c9 = null;
  }
}
