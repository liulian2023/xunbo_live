// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

import android.view.View;
import android.widget.EditText;
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

public class AnchorManageActivity_ViewBinding implements Unbinder {
  private AnchorManageActivity target;

  private View view7f0904c0;

  private View view7f0903ca;

  @UiThread
  public AnchorManageActivity_ViewBinding(AnchorManageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnchorManageActivity_ViewBinding(final AnchorManageActivity target, View source) {
    this.target = target;

    View view;
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.anchor_manage_recycler = Utils.findRequiredViewAsType(source, R.id.anchor_manage_recycler, "field 'anchor_manage_recycler'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.toolbar_right_iv, "field 'toolbar_right_iv' and method 'onClick'");
    target.toolbar_right_iv = Utils.castView(view, R.id.toolbar_right_iv, "field 'toolbar_right_iv'", ImageView.class);
    view7f0904c0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.anchor_manage_search_etv = Utils.findRequiredViewAsType(source, R.id.anchor_manage_search_etv, "field 'anchor_manage_search_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.search_tv, "field 'search_tv' and method 'onClick'");
    target.search_tv = Utils.castView(view, R.id.search_tv, "field 'search_tv'", TextView.class);
    view7f0903ca = view;
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
    AnchorManageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.anchor_manage_recycler = null;
    target.refresh = null;
    target.toolbar_right_iv = null;
    target.anchor_manage_search_etv = null;
    target.search_tv = null;

    view7f0904c0.setOnClickListener(null);
    view7f0904c0 = null;
    view7f0903ca.setOnClickListener(null);
    view7f0903ca = null;
  }
}
