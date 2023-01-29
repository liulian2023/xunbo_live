// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.details_fragment;

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

public class GiftDetailsFragment_ViewBinding implements Unbinder {
  private GiftDetailsFragment target;

  @UiThread
  public GiftDetailsFragment_ViewBinding(GiftDetailsFragment target, View source) {
    this.target = target;

    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.gift_details__recycler = Utils.findRequiredViewAsType(source, R.id.gift_details__recycler, "field 'gift_details__recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GiftDetailsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.refreshLayout = null;
    target.gift_details__recycler = null;
  }
}
