// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.feedback_fragment;

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

public class MineFeedbackFragment_ViewBinding implements Unbinder {
  private MineFeedbackFragment target;

  @UiThread
  public MineFeedbackFragment_ViewBinding(MineFeedbackFragment target, View source) {
    this.target = target;

    target.mRecy = Utils.findRequiredViewAsType(source, R.id.mine_feedback_recycle, "field 'mRecy'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.errorLinear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'errorLinear'", LinearLayout.class);
    target.reloadTv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reloadTv'", TextView.class);
    target.loadingLinear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loadingLinear'", ConstraintLayout.class);
    target.nodataLinear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodataLinear'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineFeedbackFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecy = null;
    target.refreshLayout = null;
    target.errorLinear = null;
    target.reloadTv = null;
    target.loadingLinear = null;
    target.nodataLinear = null;
  }
}
