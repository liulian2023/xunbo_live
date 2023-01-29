// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.agent_center_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AgentCenterActivity_ViewBinding implements Unbinder {
  private AgentCenterActivity target;

  private View view7f090059;

  private View view7f09005a;

  @UiThread
  public AgentCenterActivity_ViewBinding(AgentCenterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AgentCenterActivity_ViewBinding(final AgentCenterActivity target, View source) {
    this.target = target;

    View view;
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.agent_nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.agent_error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.agent_reload_tv, "field 'reload_tv'", TextView.class);
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.agent_loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.agent_center_toolbar = Utils.findRequiredViewAsType(source, R.id.agent_center_toolbar, "field 'agent_center_toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.agent_center_toolbar_back_iv, "field 'agent_center_toolbar_back_iv' and method 'onClick'");
    target.agent_center_toolbar_back_iv = Utils.castView(view, R.id.agent_center_toolbar_back_iv, "field 'agent_center_toolbar_back_iv'", ImageView.class);
    view7f090059 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.agent_center_toolbar_right_tv, "field 'agent_center_toolbar_right_tv' and method 'onClick'");
    target.agent_center_toolbar_right_tv = Utils.castView(view, R.id.agent_center_toolbar_right_tv, "field 'agent_center_toolbar_right_tv'", TextView.class);
    view7f09005a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.draw_into_proportion_tv = Utils.findRequiredViewAsType(source, R.id.draw_into_proportion_tv, "field 'draw_into_proportion_tv'", TextView.class);
    target.draw_into_amount_tv = Utils.findRequiredViewAsType(source, R.id.draw_into_amount_tv, "field 'draw_into_amount_tv'", TextView.class);
    target.mine_member_tv = Utils.findRequiredViewAsType(source, R.id.mine_member_tv, "field 'mine_member_tv'", TextView.class);
    target.live_data_tv = Utils.findRequiredViewAsType(source, R.id.live_data_tv, "field 'live_data_tv'", TextView.class);
    target.name_tv = Utils.findRequiredViewAsType(source, R.id.name_tv, "field 'name_tv'", TextView.class);
    target.id_tv = Utils.findRequiredViewAsType(source, R.id.id_tv, "field 'id_tv'", TextView.class);
    target.title_iv = Utils.findRequiredViewAsType(source, R.id.title_iv, "field 'title_iv'", ImageView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.agent_recycler = Utils.findRequiredViewAsType(source, R.id.agent_recycler, "field 'agent_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AgentCenterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nodata_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.loading_linear = null;
    target.agent_center_toolbar = null;
    target.agent_center_toolbar_back_iv = null;
    target.agent_center_toolbar_right_tv = null;
    target.draw_into_proportion_tv = null;
    target.draw_into_amount_tv = null;
    target.mine_member_tv = null;
    target.live_data_tv = null;
    target.name_tv = null;
    target.id_tv = null;
    target.title_iv = null;
    target.refresh = null;
    target.agent_recycler = null;

    view7f090059.setOnClickListener(null);
    view7f090059 = null;
    view7f09005a.setOnClickListener(null);
    view7f09005a = null;
  }
}
