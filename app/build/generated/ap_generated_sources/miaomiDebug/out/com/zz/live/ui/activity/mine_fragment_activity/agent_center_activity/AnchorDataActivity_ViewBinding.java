// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.agent_center_activity;

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

public class AnchorDataActivity_ViewBinding implements Unbinder {
  private AnchorDataActivity target;

  private View view7f090077;

  private View view7f090066;

  private View view7f090078;

  @UiThread
  public AnchorDataActivity_ViewBinding(AnchorDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnchorDataActivity_ViewBinding(final AnchorDataActivity target, View source) {
    this.target = target;

    View view;
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.anchor_toolbar_back_iv, "field 'anchor_toolbar_back_iv' and method 'onClick'");
    target.anchor_toolbar_back_iv = Utils.castView(view, R.id.anchor_toolbar_back_iv, "field 'anchor_toolbar_back_iv'", ImageView.class);
    view7f090077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.anchor_date_tv, "field 'anchor_date_tv' and method 'onClick'");
    target.anchor_date_tv = Utils.castView(view, R.id.anchor_date_tv, "field 'anchor_date_tv'", TextView.class);
    view7f090066 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.anchor_toolbar_right_iv, "field 'anchor_toolbar_right_iv' and method 'onClick'");
    target.anchor_toolbar_right_iv = Utils.castView(view, R.id.anchor_toolbar_right_iv, "field 'anchor_toolbar_right_iv'", ImageView.class);
    view7f090078 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.gift_amount_tv = Utils.findRequiredViewAsType(source, R.id.gift_amount_tv, "field 'gift_amount_tv'", TextView.class);
    target.draw_in_amount_tv = Utils.findRequiredViewAsType(source, R.id.draw_in_amount_tv, "field 'draw_in_amount_tv'", TextView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.anchor_recycler = Utils.findRequiredViewAsType(source, R.id.anchor_recycler, "field 'anchor_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnchorDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.anchor_toolbar_back_iv = null;
    target.anchor_date_tv = null;
    target.anchor_toolbar_right_iv = null;
    target.gift_amount_tv = null;
    target.draw_in_amount_tv = null;
    target.refresh = null;
    target.anchor_recycler = null;

    view7f090077.setOnClickListener(null);
    view7f090077 = null;
    view7f090066.setOnClickListener(null);
    view7f090066 = null;
    view7f090078.setOnClickListener(null);
    view7f090078 = null;
  }
}
