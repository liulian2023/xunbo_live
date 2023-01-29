// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForbiddenFragment_ViewBinding implements Unbinder {
  private ForbiddenFragment target;

  @UiThread
  public ForbiddenFragment_ViewBinding(ForbiddenFragment target, View source) {
    this.target = target;

    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.forbidden_recycler = Utils.findRequiredViewAsType(source, R.id.forbidden_recycler, "field 'forbidden_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForbiddenFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.refresh = null;
    target.forbidden_recycler = null;
  }
}
