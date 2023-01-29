// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

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

public class AddAnchorActitity_ViewBinding implements Unbinder {
  private AddAnchorActitity target;

  private View view7f090051;

  @UiThread
  public AddAnchorActitity_ViewBinding(AddAnchorActitity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddAnchorActitity_ViewBinding(final AddAnchorActitity target, View source) {
    this.target = target;

    View view;
    target.account_etv = Utils.findRequiredViewAsType(source, R.id.account_etv, "field 'account_etv'", EditText.class);
    target.login_password_etv = Utils.findRequiredViewAsType(source, R.id.login_password_etv, "field 'login_password_etv'", EditText.class);
    target.sure_password_etv = Utils.findRequiredViewAsType(source, R.id.sure_password_etv, "field 'sure_password_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.add_anchor_btn, "field 'add_anchor_btn' and method 'onClick'");
    target.add_anchor_btn = Utils.castView(view, R.id.add_anchor_btn, "field 'add_anchor_btn'", Button.class);
    view7f090051 = view;
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
    AddAnchorActitity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.account_etv = null;
    target.login_password_etv = null;
    target.sure_password_etv = null;
    target.add_anchor_btn = null;

    view7f090051.setOnClickListener(null);
    view7f090051 = null;
  }
}
