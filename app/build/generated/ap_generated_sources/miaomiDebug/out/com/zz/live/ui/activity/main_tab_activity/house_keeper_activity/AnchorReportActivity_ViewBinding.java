// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnchorReportActivity_ViewBinding implements Unbinder {
  private AnchorReportActivity target;

  private View view7f090416;

  private View view7f090168;

  private View view7f090239;

  @UiThread
  public AnchorReportActivity_ViewBinding(AnchorReportActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnchorReportActivity_ViewBinding(final AnchorReportActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.start_time_tv, "field 'start_time_tv' and method 'onClick'");
    target.start_time_tv = Utils.castView(view, R.id.start_time_tv, "field 'start_time_tv'", TextView.class);
    view7f090416 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.end_time_tv, "field 'end_time_tv' and method 'onClick'");
    target.end_time_tv = Utils.castView(view, R.id.end_time_tv, "field 'end_time_tv'", TextView.class);
    view7f090168 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.inquire_btn, "field 'inquire_btn' and method 'onClick'");
    target.inquire_btn = Utils.castView(view, R.id.inquire_btn, "field 'inquire_btn'", Button.class);
    view7f090239 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", RefreshLayout.class);
    target.anchor_report_recycler = Utils.findRequiredViewAsType(source, R.id.anchor_report_recycler, "field 'anchor_report_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnchorReportActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.start_time_tv = null;
    target.end_time_tv = null;
    target.inquire_btn = null;
    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.refresh = null;
    target.anchor_report_recycler = null;

    view7f090416.setOnClickListener(null);
    view7f090416 = null;
    view7f090168.setOnClickListener(null);
    view7f090168 = null;
    view7f090239.setOnClickListener(null);
    view7f090239 = null;
  }
}
