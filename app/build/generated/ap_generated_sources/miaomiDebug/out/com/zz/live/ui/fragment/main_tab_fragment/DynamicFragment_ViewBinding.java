// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.main_tab_fragment;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DynamicFragment_ViewBinding implements Unbinder {
  private DynamicFragment target;

  private View view7f09044f;

  @UiThread
  public DynamicFragment_ViewBinding(final DynamicFragment target, View source) {
    this.target = target;

    View view;
    target.starusbar_view = Utils.findRequiredView(source, R.id.starusbar_view, "field 'starusbar_view'");
    view = Utils.findRequiredView(source, R.id.test_btn, "field 'test_btn' and method 'onClick'");
    target.test_btn = Utils.castView(view, R.id.test_btn, "field 'test_btn'", Button.class);
    view7f09044f = view;
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
    DynamicFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.starusbar_view = null;
    target.test_btn = null;

    view7f09044f.setOnClickListener(null);
    view7f09044f = null;
  }
}
