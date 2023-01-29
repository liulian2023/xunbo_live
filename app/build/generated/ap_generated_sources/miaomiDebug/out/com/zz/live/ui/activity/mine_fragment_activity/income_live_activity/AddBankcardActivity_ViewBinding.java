// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddBankcardActivity_ViewBinding implements Unbinder {
  private AddBankcardActivity target;

  private View view7f090111;

  private View view7f090054;

  @UiThread
  public AddBankcardActivity_ViewBinding(AddBankcardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddBankcardActivity_ViewBinding(final AddBankcardActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.choose_bank_relativeLayout, "field 'choose_bank_relativeLayout' and method 'onClick'");
    target.choose_bank_relativeLayout = Utils.castView(view, R.id.choose_bank_relativeLayout, "field 'choose_bank_relativeLayout'", RelativeLayout.class);
    view7f090111 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.bank_second_name_etv = Utils.findRequiredViewAsType(source, R.id.bank_second_name_etv, "field 'bank_second_name_etv'", EditText.class);
    target.card_num_etv = Utils.findRequiredViewAsType(source, R.id.card_num_etv, "field 'card_num_etv'", EditText.class);
    target.card_username_etv = Utils.findRequiredViewAsType(source, R.id.card_username_etv, "field 'card_username_etv'", EditText.class);
    view = Utils.findRequiredView(source, R.id.add_card_sure_btn, "field 'bind_card_sure_btn' and method 'onClick'");
    target.bind_card_sure_btn = Utils.castView(view, R.id.add_card_sure_btn, "field 'bind_card_sure_btn'", Button.class);
    view7f090054 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.bank_name_tv = Utils.findRequiredViewAsType(source, R.id.bank_name_tv, "field 'bank_name_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddBankcardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.choose_bank_relativeLayout = null;
    target.bank_second_name_etv = null;
    target.card_num_etv = null;
    target.card_username_etv = null;
    target.bind_card_sure_btn = null;
    target.bank_name_tv = null;

    view7f090111.setOnClickListener(null);
    view7f090111 = null;
    view7f090054.setOnClickListener(null);
    view7f090054 = null;
  }
}
