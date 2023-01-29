// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.home_fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeLiveFragment_ViewBinding implements Unbinder {
  private HomeLiveFragment target;

  @UiThread
  public HomeLiveFragment_ViewBinding(HomeLiveFragment target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.home_live_refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.mRecycler = Utils.findRequiredViewAsType(source, R.id.home_live_recycle, "field 'mRecycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeLiveFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.mRecycler = null;
  }
}
