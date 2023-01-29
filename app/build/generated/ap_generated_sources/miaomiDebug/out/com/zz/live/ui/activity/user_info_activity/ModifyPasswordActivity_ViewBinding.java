// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.user_info_activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ModifyPasswordActivity_ViewBinding implements Unbinder {
  private ModifyPasswordActivity target;

  private View view7f09018f;

  @UiThread
  public ModifyPasswordActivity_ViewBinding(ModifyPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ModifyPasswordActivity_ViewBinding(final ModifyPasswordActivity target, View source) {
    this.target = target;

    View view;
    target.old_password_etv = Utils.findRequiredViewAsType(source, R.id.old_password_etv, "field 'old_password_etv'", EditText.class);
    target.new_password_etv = Utils.findRequiredViewAsType(source, R.id.new_password_etv, "field 'new_password_etv'", EditText.class);
    target.sure_new_password_etv = Utils.findRequiredViewAsType(source, R.id.sure_new_password_etv, "field 'sure_new_password_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.find_psd_btn, "field 'find_psd_btn' and method 'onClick'");
    target.find_psd_btn = Utils.castView(view, R.id.find_psd_btn, "field 'find_psd_btn'", Button.class);
    view7f09018f = view;
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
    ModifyPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.old_password_etv = null;
    target.new_password_etv = null;
    target.sure_new_password_etv = null;
    target.find_psd_btn = null;

    view7f09018f.setOnClickListener(null);
    view7f09018f = null;
  }
}
