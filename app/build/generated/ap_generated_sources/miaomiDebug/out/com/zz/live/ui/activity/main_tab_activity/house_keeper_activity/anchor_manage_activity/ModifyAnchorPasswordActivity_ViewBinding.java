// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ModifyAnchorPasswordActivity_ViewBinding implements Unbinder {
  private ModifyAnchorPasswordActivity target;

  private View view7f090123;

  @UiThread
  public ModifyAnchorPasswordActivity_ViewBinding(ModifyAnchorPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ModifyAnchorPasswordActivity_ViewBinding(final ModifyAnchorPasswordActivity target,
      View source) {
    this.target = target;

    View view;
    target.title_iv = Utils.findRequiredViewAsType(source, R.id.title_iv, "field 'title_iv'", ImageView.class);
    target.nickname_tv = Utils.findRequiredViewAsType(source, R.id.nickname_tv, "field 'nickname_tv'", TextView.class);
    target.username_tv = Utils.findRequiredViewAsType(source, R.id.username_tv, "field 'username_tv'", TextView.class);
    target.login_password_etv = Utils.findRequiredViewAsType(source, R.id.login_password_etv, "field 'login_password_etv'", EditText.class);
    target.sure_login_password_etv = Utils.findRequiredViewAsType(source, R.id.sure_login_password_etv, "field 'sure_login_password_etv'", EditText.class);
    target.family_password_etv = Utils.findRequiredViewAsType(source, R.id.family_password_etv, "field 'family_password_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.commit_btn, "field 'commit_btn' and method 'onClick'");
    target.commit_btn = Utils.castView(view, R.id.commit_btn, "field 'commit_btn'", Button.class);
    view7f090123 = view;
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
    ModifyAnchorPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title_iv = null;
    target.nickname_tv = null;
    target.username_tv = null;
    target.login_password_etv = null;
    target.sure_login_password_etv = null;
    target.family_password_etv = null;
    target.commit_btn = null;

    view7f090123.setOnClickListener(null);
    view7f090123 = null;
  }
}
