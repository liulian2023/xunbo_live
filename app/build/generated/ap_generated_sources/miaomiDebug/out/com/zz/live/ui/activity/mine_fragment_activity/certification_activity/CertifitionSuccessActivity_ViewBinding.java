// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.certification_activity;

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

public class CertifitionSuccessActivity_ViewBinding implements Unbinder {
  private CertifitionSuccessActivity target;

  private View view7f090329;

  private View view7f090504;

  private View view7f0904a0;

  @UiThread
  public CertifitionSuccessActivity_ViewBinding(CertifitionSuccessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CertifitionSuccessActivity_ViewBinding(final CertifitionSuccessActivity target,
      View source) {
    this.target = target;

    View view;
    target.name_tv = Utils.findRequiredViewAsType(source, R.id.name_tv, "field 'name_tv'", TextView.class);
    target.id_card_tv = Utils.findRequiredViewAsType(source, R.id.id_card_tv, "field 'id_card_tv'", TextView.class);
    target.phone_tv = Utils.findRequiredViewAsType(source, R.id.phone_tv, "field 'phone_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.one_photo_iv, "field 'one_photo_iv' and method 'onClick'");
    target.one_photo_iv = Utils.castView(view, R.id.one_photo_iv, "field 'one_photo_iv'", ImageView.class);
    view7f090329 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_photo_iv, "field 'two_photo_iv' and method 'onClick'");
    target.two_photo_iv = Utils.castView(view, R.id.two_photo_iv, "field 'two_photo_iv'", ImageView.class);
    view7f090504 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three_photo_iv, "field 'three_photo_iv' and method 'onClick'");
    target.three_photo_iv = Utils.castView(view, R.id.three_photo_iv, "field 'three_photo_iv'", ImageView.class);
    view7f0904a0 = view;
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
    CertifitionSuccessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name_tv = null;
    target.id_card_tv = null;
    target.phone_tv = null;
    target.one_photo_iv = null;
    target.two_photo_iv = null;
    target.three_photo_iv = null;

    view7f090329.setOnClickListener(null);
    view7f090329 = null;
    view7f090504.setOnClickListener(null);
    view7f090504 = null;
    view7f0904a0.setOnClickListener(null);
    view7f0904a0 = null;
  }
}
