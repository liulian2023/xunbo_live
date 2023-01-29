// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IncomeLiveActivity_ViewBinding implements Unbinder {
  private IncomeLiveActivity target;

  private View view7f090234;

  private View view7f090537;

  private View view7f09022e;

  private TextWatcher view7f09022eTextWatcher;

  private View view7f09022d;

  private View view7f090052;

  @UiThread
  public IncomeLiveActivity_ViewBinding(IncomeLiveActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IncomeLiveActivity_ViewBinding(final IncomeLiveActivity target, View source) {
    this.target = target;

    View view;
    target.income_toolbar = Utils.findRequiredViewAsType(source, R.id.income_toolbar, "field 'income_toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.income_toolbar_back_iv, "field 'income_toolbar_back_iv' and method 'onCLick'");
    target.income_toolbar_back_iv = Utils.castView(view, R.id.income_toolbar_back_iv, "field 'income_toolbar_back_iv'", ImageView.class);
    view7f090234 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    target.income_toolbar_title_tv = Utils.findRequiredViewAsType(source, R.id.income_toolbar_title_tv, "field 'income_toolbar_title_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.withdraw_note_tv, "field 'income_note_tv' and method 'onCLick'");
    target.income_note_tv = Utils.castView(view, R.id.withdraw_note_tv, "field 'income_note_tv'", TextView.class);
    view7f090537 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    target.balance_tv = Utils.findRequiredViewAsType(source, R.id.balance_tv, "field 'balance_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.income_etv, "field 'income_etv' and method 'onTextChange'");
    target.income_etv = Utils.castView(view, R.id.income_etv, "field 'income_etv'", EditText.class);
    view7f09022e = view;
    view7f09022eTextWatcher = new TextWatcher() {
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
    ((TextView) view).addTextChangedListener(view7f09022eTextWatcher);
    target.bank_card_tv = Utils.findRequiredViewAsType(source, R.id.bank_card_tv, "field 'bank_card_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.income_btn, "field 'income_btn' and method 'onCLick'");
    target.income_btn = Utils.castView(view, R.id.income_btn, "field 'income_btn'", Button.class);
    view7f09022d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_bankcard_constrainLayout, "field 'add_bankcard_constrainLayout' and method 'onCLick'");
    target.add_bankcard_constrainLayout = Utils.castView(view, R.id.add_bankcard_constrainLayout, "field 'add_bankcard_constrainLayout'", ConstraintLayout.class);
    view7f090052 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCLick(p0);
      }
    });
    target.tip_group = Utils.findRequiredViewAsType(source, R.id.tip_group, "field 'tip_group'", Group.class);
    target.tip_tv = Utils.findRequiredViewAsType(source, R.id.tip_tv, "field 'tip_tv'", TextView.class);
    target.tip_iv = Utils.findRequiredViewAsType(source, R.id.tip_iv, "field 'tip_iv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IncomeLiveActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.income_toolbar = null;
    target.income_toolbar_back_iv = null;
    target.income_toolbar_title_tv = null;
    target.income_note_tv = null;
    target.balance_tv = null;
    target.income_etv = null;
    target.bank_card_tv = null;
    target.income_btn = null;
    target.add_bankcard_constrainLayout = null;
    target.tip_group = null;
    target.tip_tv = null;
    target.tip_iv = null;

    view7f090234.setOnClickListener(null);
    view7f090234 = null;
    view7f090537.setOnClickListener(null);
    view7f090537 = null;
    ((TextView) view7f09022e).removeTextChangedListener(view7f09022eTextWatcher);
    view7f09022eTextWatcher = null;
    view7f09022e = null;
    view7f09022d.setOnClickListener(null);
    view7f09022d = null;
    view7f090052.setOnClickListener(null);
    view7f090052 = null;
  }
}
