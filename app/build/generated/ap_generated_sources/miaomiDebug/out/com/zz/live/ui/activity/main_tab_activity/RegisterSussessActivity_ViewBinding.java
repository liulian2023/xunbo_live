// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity;

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

public class RegisterSussessActivity_ViewBinding implements Unbinder {
  private RegisterSussessActivity target;

  private View view7f0900fc;

  private View view7f0902fc;

  @UiThread
  public RegisterSussessActivity_ViewBinding(RegisterSussessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterSussessActivity_ViewBinding(final RegisterSussessActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.certification_btn, "field 'Certification_btn' and method 'onClick'");
    target.Certification_btn = Utils.castView(view, R.id.certification_btn, "field 'Certification_btn'", Button.class);
    view7f0900fc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.no_certification_btn, "field 'no_Certification_btn' and method 'onClick'");
    target.no_Certification_btn = Utils.castView(view, R.id.no_certification_btn, "field 'no_Certification_btn'", Button.class);
    view7f0902fc = view;
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
    RegisterSussessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.Certification_btn = null;
    target.no_Certification_btn = null;

    view7f0900fc.setOnClickListener(null);
    view7f0900fc = null;
    view7f0902fc.setOnClickListener(null);
    view7f0902fc = null;
  }
}
