// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity;

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

public class ChooseLotteryActivity_ViewBinding implements Unbinder {
  private ChooseLotteryActivity target;

  @UiThread
  public ChooseLotteryActivity_ViewBinding(ChooseLotteryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooseLotteryActivity_ViewBinding(ChooseLotteryActivity target, View source) {
    this.target = target;

    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.choose_lottery_recycler = Utils.findRequiredViewAsType(source, R.id.choose_lottery_recycler, "field 'choose_lottery_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseLotteryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.refresh = null;
    target.choose_lottery_recycler = null;
  }
}
