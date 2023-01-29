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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f09029e;

  private View view7f09029c;

  private TextWatcher view7f09029cTextWatcher;

  private View view7f0902a0;

  private TextWatcher view7f0902a0TextWatcher;

  private View view7f0904b8;

  private View view7f09029b;

  private View view7f09029f;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar_back_iv = Utils.findRequiredViewAsType(source, R.id.toolbar_back_iv, "field 'toolbar_back_iv'", ImageView.class);
    target.toolbar_title_tv = Utils.findRequiredViewAsType(source, R.id.toolbar_title_tv, "field 'toolbar_title_tv'", TextView.class);
    target.login_app_name_tv = Utils.findRequiredViewAsType(source, R.id.login_app_name_tv, "field 'login_app_name_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.login_btn, "field 'login_btn' and method 'onClick'");
    target.login_btn = Utils.castView(view, R.id.login_btn, "field 'login_btn'", Button.class);
    view7f09029e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_account_etv, "field 'login_account_etv' and method 'onAccountTextChange'");
    target.login_account_etv = Utils.castView(view, R.id.login_account_etv, "field 'login_account_etv'", EditText.class);
    view7f09029c = view;
    view7f09029cTextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
        target.onAccountTextChange(p0);
      }
    };
    ((TextView) view).addTextChangedListener(view7f09029cTextWatcher);
    view = Utils.findRequiredView(source, R.id.login_password_etv, "field 'login_password_etv' and method 'onPasswordTextChange'");
    target.login_password_etv = Utils.castView(view, R.id.login_password_etv, "field 'login_password_etv'", EditText.class);
    view7f0902a0 = view;
    view7f0902a0TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
        target.onPasswordTextChange(p0);
      }
    };
    ((TextView) view).addTextChangedListener(view7f0902a0TextWatcher);
    view = Utils.findRequiredView(source, R.id.to_register_tv, "field 'to_register_tv' and method 'onClick'");
    target.to_register_tv = Utils.castView(view, R.id.to_register_tv, "field 'to_register_tv'", TextView.class);
    view7f0904b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_account_clear_iv, "field 'login_account_clear_iv' and method 'onClick'");
    target.login_account_clear_iv = Utils.castView(view, R.id.login_account_clear_iv, "field 'login_account_clear_iv'", ImageView.class);
    view7f09029b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_password_clear_iv, "field 'login_password_clear_iv' and method 'onClick'");
    target.login_password_clear_iv = Utils.castView(view, R.id.login_password_clear_iv, "field 'login_password_clear_iv'", ImageView.class);
    view7f09029f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.platform_etv = Utils.findRequiredViewAsType(source, R.id.platform_etv, "field 'platform_etv'", EditText.class);
    target.login_version_name_tv = Utils.findRequiredViewAsType(source, R.id.login_version_name_tv, "field 'login_version_name_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_back_iv = null;
    target.toolbar_title_tv = null;
    target.login_app_name_tv = null;
    target.login_btn = null;
    target.login_account_etv = null;
    target.login_password_etv = null;
    target.to_register_tv = null;
    target.login_account_clear_iv = null;
    target.login_password_clear_iv = null;
    target.platform_etv = null;
    target.login_version_name_tv = null;

    view7f09029e.setOnClickListener(null);
    view7f09029e = null;
    ((TextView) view7f09029c).removeTextChangedListener(view7f09029cTextWatcher);
    view7f09029cTextWatcher = null;
    view7f09029c = null;
    ((TextView) view7f0902a0).removeTextChangedListener(view7f0902a0TextWatcher);
    view7f0902a0TextWatcher = null;
    view7f0902a0 = null;
    view7f0904b8.setOnClickListener(null);
    view7f0904b8 = null;
    view7f09029b.setOnClickListener(null);
    view7f09029b = null;
    view7f09029f.setOnClickListener(null);
    view7f09029f = null;
  }
}
