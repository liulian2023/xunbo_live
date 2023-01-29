// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.view.View;
import android.widget.Button;
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

public class WithdrawDetailsActivity_ViewBinding implements Unbinder {
  private WithdrawDetailsActivity target;

  private View view7f090538;

  @UiThread
  public WithdrawDetailsActivity_ViewBinding(WithdrawDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WithdrawDetailsActivity_ViewBinding(final WithdrawDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.withdraw_status_iv = Utils.findRequiredViewAsType(source, R.id.withdraw_status_iv, "field 'withdraw_status_iv'", ImageView.class);
    target.withdraw_status_tv = Utils.findRequiredViewAsType(source, R.id.withdraw_status_tv, "field 'withdraw_status_tv'", TextView.class);
    target.withdraw_tip_tv = Utils.findRequiredViewAsType(source, R.id.withdraw_tip_tv, "field 'withdraw_tip_tv'", TextView.class);
    target.withdraw_bankName_tv = Utils.findRequiredViewAsType(source, R.id.withdraw_bankName_tv, "field 'withdraw_bankName_tv'", TextView.class);
    target.withdraw_amount = Utils.findRequiredViewAsType(source, R.id.withdraw_amount, "field 'withdraw_amount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.withdraw_return_btn, "field 'withdraw_return_btn' and method 'onClick'");
    target.withdraw_return_btn = Utils.castView(view, R.id.withdraw_return_btn, "field 'withdraw_return_btn'", Button.class);
    view7f090538 = view;
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
    WithdrawDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.withdraw_status_iv = null;
    target.withdraw_status_tv = null;
    target.withdraw_tip_tv = null;
    target.withdraw_bankName_tv = null;
    target.withdraw_amount = null;
    target.withdraw_return_btn = null;

    view7f090538.setOnClickListener(null);
    view7f090538 = null;
  }
}
