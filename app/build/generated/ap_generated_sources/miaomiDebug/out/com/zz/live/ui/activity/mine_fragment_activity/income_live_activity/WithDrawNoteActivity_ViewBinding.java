// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WithDrawNoteActivity_ViewBinding implements Unbinder {
  private WithDrawNoteActivity target;

  private View view7f09052e;

  private View view7f09052f;

  private View view7f09052b;

  @UiThread
  public WithDrawNoteActivity_ViewBinding(WithDrawNoteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithDrawNoteActivity_ViewBinding(final WithDrawNoteActivity target, View source) {
    this.target = target;

    View view;
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.withDraw_toolbar_back_iv, "field 'withDraw_toolbar_back_iv' and method 'onClick'");
    target.withDraw_toolbar_back_iv = Utils.castView(view, R.id.withDraw_toolbar_back_iv, "field 'withDraw_toolbar_back_iv'", ImageView.class);
    view7f09052e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.withDraw_toolbar_right_iv, "field 'withDraw_toolbar_right_iv' and method 'onClick'");
    target.withDraw_toolbar_right_iv = Utils.castView(view, R.id.withDraw_toolbar_right_iv, "field 'withDraw_toolbar_right_iv'", ImageView.class);
    view7f09052f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.withDraw_date_tv, "field 'withDraw_date_tv' and method 'onClick'");
    target.withDraw_date_tv = Utils.castView(view, R.id.withDraw_date_tv, "field 'withDraw_date_tv'", TextView.class);
    view7f09052b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.withDraw_recycler = Utils.findRequiredViewAsType(source, R.id.withDraw_recycler, "field 'withDraw_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WithDrawNoteActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.withDraw_toolbar_back_iv = null;
    target.withDraw_toolbar_right_iv = null;
    target.withDraw_date_tv = null;
    target.refresh = null;
    target.withDraw_recycler = null;

    view7f09052e.setOnClickListener(null);
    view7f09052e = null;
    view7f09052f.setOnClickListener(null);
    view7f09052f = null;
    view7f09052b.setOnClickListener(null);
    view7f09052b = null;
  }
}
