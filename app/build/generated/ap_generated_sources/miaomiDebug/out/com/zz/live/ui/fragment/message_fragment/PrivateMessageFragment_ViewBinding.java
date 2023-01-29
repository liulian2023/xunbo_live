// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.message_fragment;

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

public class PrivateMessageFragment_ViewBinding implements Unbinder {
  private PrivateMessageFragment target;

  @UiThread
  public PrivateMessageFragment_ViewBinding(PrivateMessageFragment target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.private_message_refresh, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.private_message_recycler = Utils.findRequiredViewAsType(source, R.id.private_message_recycler, "field 'private_message_recycler'", RecyclerView.class);
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PrivateMessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.private_message_recycler = null;
    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
  }
}
