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

public class SystemMessageFragment_ViewBinding implements Unbinder {
  private SystemMessageFragment target;

  @UiThread
  public SystemMessageFragment_ViewBinding(SystemMessageFragment target, View source) {
    this.target = target;

    target.system_message_recfresh = Utils.findRequiredViewAsType(source, R.id.system_message_list_refresh, "field 'system_message_recfresh'", SmartRefreshLayout.class);
    target.system_message_recycler = Utils.findRequiredViewAsType(source, R.id.system_message_list_recycler, "field 'system_message_recycler'", RecyclerView.class);
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SystemMessageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.system_message_recfresh = null;
    target.system_message_recycler = null;
    target.loading_linear = null;
    target.nodata_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
  }
}
