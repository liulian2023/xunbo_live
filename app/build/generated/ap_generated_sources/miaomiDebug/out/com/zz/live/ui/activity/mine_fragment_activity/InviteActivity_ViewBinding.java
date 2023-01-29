// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity;

import android.view.View;
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

public class InviteActivity_ViewBinding implements Unbinder {
  private InviteActivity target;

  private View view7f0903b8;

  private View view7f090133;

  private View view7f0903e4;

  @UiThread
  public InviteActivity_ViewBinding(InviteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InviteActivity_ViewBinding(final InviteActivity target, View source) {
    this.target = target;

    View view;
    target.invite_code_tv = Utils.findRequiredViewAsType(source, R.id.invite_code_tv, "field 'invite_code_tv'", TextView.class);
    target.qr_code_iv = Utils.findRequiredViewAsType(source, R.id.qr_code_iv, "field 'qr_code_iv'", ImageView.class);
    target.rule_one_tv = Utils.findRequiredViewAsType(source, R.id.rule_one_tv, "field 'rule_one_tv'", TextView.class);
    target.rule_two_tv = Utils.findRequiredViewAsType(source, R.id.rule_two_tv, "field 'rule_two_tv'", TextView.class);
    target.rule_three_tv = Utils.findRequiredViewAsType(source, R.id.rule_three_tv, "field 'rule_three_tv'", TextView.class);
    target.rule_four_tv = Utils.findRequiredViewAsType(source, R.id.rule_four_tv, "field 'rule_four_tv'", TextView.class);
    target.rule_five_tv = Utils.findRequiredViewAsType(source, R.id.rule_five_tv, "field 'rule_five_tv'", TextView.class);
    target.invite_tip_tv = Utils.findRequiredViewAsType(source, R.id.invite_tip_tv, "field 'invite_tip_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.save_qr_code_tv, "field 'save_qr_code_tv' and method 'onClick'");
    target.save_qr_code_tv = Utils.castView(view, R.id.save_qr_code_tv, "field 'save_qr_code_tv'", TextView.class);
    view7f0903b8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.copy_tv, "field 'copy_tv' and method 'onClick'");
    target.copy_tv = Utils.castView(view, R.id.copy_tv, "field 'copy_tv'", TextView.class);
    view7f090133 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.share_tv, "field 'share_tv' and method 'onClick'");
    target.share_tv = Utils.castView(view, R.id.share_tv, "field 'share_tv'", TextView.class);
    view7f0903e4 = view;
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
    InviteActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.invite_code_tv = null;
    target.qr_code_iv = null;
    target.rule_one_tv = null;
    target.rule_two_tv = null;
    target.rule_three_tv = null;
    target.rule_four_tv = null;
    target.rule_five_tv = null;
    target.invite_tip_tv = null;
    target.save_qr_code_tv = null;
    target.copy_tv = null;
    target.share_tv = null;

    view7f0903b8.setOnClickListener(null);
    view7f0903b8 = null;
    view7f090133.setOnClickListener(null);
    view7f090133 = null;
    view7f0903e4.setOnClickListener(null);
    view7f0903e4 = null;
  }
}
