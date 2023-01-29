// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DanMuFragment_ViewBinding implements Unbinder {
  private DanMuFragment target;

  private View view7f09050b;

  @UiThread
  public DanMuFragment_ViewBinding(final DanMuFragment target, View source) {
    this.target = target;

    View view;
    target.danmu_recycler = Utils.findRequiredViewAsType(source, R.id.danmu_recycler, "field 'danmu_recycler'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.un_read_tv, "field 'un_read_tv' and method 'onClick'");
    target.un_read_tv = Utils.castView(view, R.id.un_read_tv, "field 'un_read_tv'", TextView.class);
    view7f09050b = view;
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
    DanMuFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.danmu_recycler = null;
    target.un_read_tv = null;

    view7f09050b.setOnClickListener(null);
    view7f09050b = null;
  }
}
