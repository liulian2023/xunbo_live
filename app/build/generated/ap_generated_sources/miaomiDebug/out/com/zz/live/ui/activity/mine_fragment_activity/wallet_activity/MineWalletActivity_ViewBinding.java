// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.wallet_activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineWalletActivity_ViewBinding implements Unbinder {
  private MineWalletActivity target;

  private View view7f090522;

  private View view7f0903b1;

  private View view7f0900f2;

  private View view7f0904ba;

  private View view7f090548;

  @UiThread
  public MineWalletActivity_ViewBinding(MineWalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineWalletActivity_ViewBinding(final MineWalletActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.wallet_right_iv, "field 'wallet_right_iv' and method 'onClick'");
    target.wallet_right_iv = Utils.castView(view, R.id.wallet_right_iv, "field 'wallet_right_iv'", ImageView.class);
    view7f090522 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rule_tv, "field 'rule_tv' and method 'onClick'");
    target.rule_tv = Utils.castView(view, R.id.rule_tv, "field 'rule_tv'", TextView.class);
    view7f0903b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mine_amount_tv = Utils.findRequiredViewAsType(source, R.id.mine_amount_tv, "field 'mine_amount_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cash_back_btn, "field 'cash_back_btn' and method 'onClick'");
    target.cash_back_btn = Utils.castView(view, R.id.cash_back_btn, "field 'cash_back_btn'", Button.class);
    view7f0900f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.month_follow_tv = Utils.findRequiredViewAsType(source, R.id.month_follow_tv, "field 'month_follow_tv'", TextView.class);
    target.month_gift_tv = Utils.findRequiredViewAsType(source, R.id.month_gift_tv, "field 'month_gift_tv'", TextView.class);
    target.month_cash_back_tv = Utils.findRequiredViewAsType(source, R.id.month_cash_back_tv, "field 'month_cash_back_tv'", TextView.class);
    target.last_month_follow_tv = Utils.findRequiredViewAsType(source, R.id.last_month_follow_tv, "field 'last_month_follow_tv'", TextView.class);
    target.last_month_gift_tv = Utils.findRequiredViewAsType(source, R.id.last_month_gift_tv, "field 'last_month_gift_tv'", TextView.class);
    target.last_month_cash_back_tv = Utils.findRequiredViewAsType(source, R.id.last_month_cash_back_tv, "field 'last_month_cash_back_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.today_tv, "field 'today_tv' and method 'onClick'");
    target.today_tv = Utils.castView(view, R.id.today_tv, "field 'today_tv'", RadioButton.class);
    view7f0904ba = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.yestoday_tv, "field 'yestoday_tv' and method 'onClick'");
    target.yestoday_tv = Utils.castView(view, R.id.yestoday_tv, "field 'yestoday_tv'", RadioButton.class);
    view7f090548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.fans_count_tv = Utils.findRequiredViewAsType(source, R.id.fans_count_tv, "field 'fans_count_tv'", TextView.class);
    target.follow_count_tv = Utils.findRequiredViewAsType(source, R.id.follow_count_tv, "field 'follow_count_tv'", TextView.class);
    target.gift_account_tv = Utils.findRequiredViewAsType(source, R.id.gift_account_tv, "field 'gift_account_tv'", TextView.class);
    target.cash_back_account_tv = Utils.findRequiredViewAsType(source, R.id.cash_back_account_tv, "field 'cash_back_account_tv'", TextView.class);
    target.other_account_tv = Utils.findRequiredViewAsType(source, R.id.other_account_tv, "field 'other_account_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MineWalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.wallet_right_iv = null;
    target.rule_tv = null;
    target.mine_amount_tv = null;
    target.cash_back_btn = null;
    target.month_follow_tv = null;
    target.month_gift_tv = null;
    target.month_cash_back_tv = null;
    target.last_month_follow_tv = null;
    target.last_month_gift_tv = null;
    target.last_month_cash_back_tv = null;
    target.today_tv = null;
    target.yestoday_tv = null;
    target.fans_count_tv = null;
    target.follow_count_tv = null;
    target.gift_account_tv = null;
    target.cash_back_account_tv = null;
    target.other_account_tv = null;

    view7f090522.setOnClickListener(null);
    view7f090522 = null;
    view7f0903b1.setOnClickListener(null);
    view7f0903b1 = null;
    view7f0900f2.setOnClickListener(null);
    view7f0900f2 = null;
    view7f0904ba.setOnClickListener(null);
    view7f0904ba = null;
    view7f090548.setOnClickListener(null);
    view7f090548 = null;
  }
}
