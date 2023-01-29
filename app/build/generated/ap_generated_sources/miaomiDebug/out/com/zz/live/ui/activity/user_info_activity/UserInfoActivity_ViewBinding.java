// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.user_info_activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

  private View view7f090517;

  private View view7f090036;

  private View view7f0902fa;

  private View view7f0903e1;

  private View view7f0902a2;

  private View view7f09034a;

  private View view7f09016f;

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(final UserInfoActivity target, View source) {
    this.target = target;

    View view;
    target.title_iv = Utils.findRequiredViewAsType(source, R.id.title_iv, "field 'title_iv'", ImageView.class);
    target.account_tv = Utils.findRequiredViewAsType(source, R.id.account_tv, "field 'account_tv'", TextView.class);
    target.nickname_tv = Utils.findRequiredViewAsType(source, R.id.nickname_tv, "field 'nickname_tv'", TextView.class);
    target.sex_tv = Utils.findRequiredViewAsType(source, R.id.sex_tv, "field 'sex_tv'", TextView.class);
    target.login_psd_tv = Utils.findRequiredViewAsType(source, R.id.login_psd_tv, "field 'login_psd_tv'", TextView.class);
    target.pay_psd_tv = Utils.findRequiredViewAsType(source, R.id.pay_psd_tv, "field 'pay_psd_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.user_title_relativeLayout, "field 'user_title_relativeLayout' and method 'onClick'");
    target.user_title_relativeLayout = Utils.castView(view, R.id.user_title_relativeLayout, "field 'user_title_relativeLayout'", RelativeLayout.class);
    view7f090517 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.account_relativeLayout, "field 'account_relativeLayout' and method 'onClick'");
    target.account_relativeLayout = Utils.castView(view, R.id.account_relativeLayout, "field 'account_relativeLayout'", RelativeLayout.class);
    view7f090036 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nickname_relativeLayout, "field 'nickname_relativeLayout' and method 'onClick'");
    target.nickname_relativeLayout = Utils.castView(view, R.id.nickname_relativeLayout, "field 'nickname_relativeLayout'", RelativeLayout.class);
    view7f0902fa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sex_relativeLayout, "field 'sex_relativeLayout' and method 'onClick'");
    target.sex_relativeLayout = Utils.castView(view, R.id.sex_relativeLayout, "field 'sex_relativeLayout'", RelativeLayout.class);
    view7f0903e1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_psd_relativeLayout, "field 'login_psd_relativeLayout' and method 'onClick'");
    target.login_psd_relativeLayout = Utils.castView(view, R.id.login_psd_relativeLayout, "field 'login_psd_relativeLayout'", RelativeLayout.class);
    view7f0902a2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.pay_psd_relativeLayout, "field 'pay_psd_relativeLayout' and method 'onClick'");
    target.pay_psd_relativeLayout = Utils.castView(view, R.id.pay_psd_relativeLayout, "field 'pay_psd_relativeLayout'", RelativeLayout.class);
    view7f09034a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.exit_login_btn, "field 'exit_login_btn' and method 'onClick'");
    target.exit_login_btn = Utils.castView(view, R.id.exit_login_btn, "field 'exit_login_btn'", Button.class);
    view7f09016f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.nickname_next_iv = Utils.findRequiredViewAsType(source, R.id.nickname_next_iv, "field 'nickname_next_iv'", ImageView.class);
    target.version_code_tv = Utils.findRequiredViewAsType(source, R.id.version_code_tv, "field 'version_code_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title_iv = null;
    target.account_tv = null;
    target.nickname_tv = null;
    target.sex_tv = null;
    target.login_psd_tv = null;
    target.pay_psd_tv = null;
    target.user_title_relativeLayout = null;
    target.account_relativeLayout = null;
    target.nickname_relativeLayout = null;
    target.sex_relativeLayout = null;
    target.login_psd_relativeLayout = null;
    target.pay_psd_relativeLayout = null;
    target.exit_login_btn = null;
    target.nickname_next_iv = null;
    target.version_code_tv = null;

    view7f090517.setOnClickListener(null);
    view7f090517 = null;
    view7f090036.setOnClickListener(null);
    view7f090036 = null;
    view7f0902fa.setOnClickListener(null);
    view7f0902fa = null;
    view7f0903e1.setOnClickListener(null);
    view7f0903e1 = null;
    view7f0902a2.setOnClickListener(null);
    view7f0902a2 = null;
    view7f09034a.setOnClickListener(null);
    view7f09034a = null;
    view7f09016f.setOnClickListener(null);
    view7f09016f = null;
  }
}
