// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity;

import android.text.Editable;
import android.text.TextWatcher;
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
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view7f09039c;

  private TextWatcher view7f09039cTextWatcher;

  private View view7f09039d;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar_back_iv = Utils.findRequiredViewAsType(source, R.id.toolbar_back_iv, "field 'toolbar_back_iv'", ImageView.class);
    target.toolbar_title_tv = Utils.findRequiredViewAsType(source, R.id.toolbar_title_tv, "field 'toolbar_title_tv'", TextView.class);
    target.invite_code_etv = Utils.findRequiredViewAsType(source, R.id.invite_code_etv, "field 'invite_code_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.register_account_etv, "field 'register_account_etv' and method 'onTextChange'");
    target.register_account_etv = Utils.castView(view, R.id.register_account_etv, "field 'register_account_etv'", EditText.class);
    view7f09039c = view;
    view7f09039cTextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
        target.onTextChange(p0);
      }
    };
    ((TextView) view).addTextChangedListener(view7f09039cTextWatcher);
    target.register_password_etv = Utils.findRequiredViewAsType(source, R.id.register_password_etv, "field 'register_password_etv'", EditText.class);
    target.register_sure_password_etv = Utils.findRequiredViewAsType(source, R.id.register_sure_password_etv, "field 'register_sure_password_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.register_next_btn, "field 'register_next_btn' and method 'onClick'");
    target.register_next_btn = Utils.castView(view, R.id.register_next_btn, "field 'register_next_btn'", Button.class);
    view7f09039d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.account_presence_tip_tv = Utils.findRequiredViewAsType(source, R.id.account_presence_tip_tv, "field 'account_presence_tip_tv'", TextView.class);
    target.account_success_tip_iv = Utils.findRequiredViewAsType(source, R.id.account_success_tip_iv, "field 'account_success_tip_iv'", ImageView.class);
    target.invite_code_tip_tv = Utils.findRequiredViewAsType(source, R.id.invite_code_tip_tv, "field 'invite_code_tip_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_back_iv = null;
    target.toolbar_title_tv = null;
    target.invite_code_etv = null;
    target.register_account_etv = null;
    target.register_password_etv = null;
    target.register_sure_password_etv = null;
    target.register_next_btn = null;
    target.account_presence_tip_tv = null;
    target.account_success_tip_iv = null;
    target.invite_code_tip_tv = null;

    ((TextView) view7f09039c).removeTextChangedListener(view7f09039cTextWatcher);
    view7f09039cTextWatcher = null;
    view7f09039c = null;
    view7f09039d.setOnClickListener(null);
    view7f09039d = null;
  }
}
