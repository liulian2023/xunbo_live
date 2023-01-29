// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity.certification_activity;

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

public class CertificationActivity_ViewBinding implements Unbinder {
  private CertificationActivity target;

  private View view7f090511;

  private View view7f090513;

  private View view7f090512;

  private View view7f0900fc;

  @UiThread
  public CertificationActivity_ViewBinding(CertificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CertificationActivity_ViewBinding(final CertificationActivity target, View source) {
    this.target = target;

    View view;
    target.name_etv = Utils.findRequiredViewAsType(source, R.id.name_etv, "field 'name_etv'", EditText.class);
    target.id_card_etv = Utils.findRequiredViewAsType(source, R.id.id_card_etv, "field 'id_card_etv'", EditText.class);
    target.phone_num_etv = Utils.findRequiredViewAsType(source, R.id.phone_num_etv, "field 'phone_num_etv'", EditText.class);
    target.photo_one_iv = Utils.findRequiredViewAsType(source, R.id.photo_one_iv, "field 'photo_one_iv'", ImageView.class);
    target.photo_two_iv = Utils.findRequiredViewAsType(source, R.id.photo_two_iv, "field 'photo_two_iv'", ImageView.class);
    target.photo_three_iv = Utils.findRequiredViewAsType(source, R.id.photo_three_iv, "field 'photo_three_iv'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.upload_one_photo_tv, "field 'upload_one_photo_tv' and method 'onClick'");
    target.upload_one_photo_tv = Utils.castView(view, R.id.upload_one_photo_tv, "field 'upload_one_photo_tv'", TextView.class);
    view7f090511 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.upload_two_photo_tv, "field 'upload_two_photo_tv' and method 'onClick'");
    target.upload_two_photo_tv = Utils.castView(view, R.id.upload_two_photo_tv, "field 'upload_two_photo_tv'", TextView.class);
    view7f090513 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.upload_three_photo_tv, "field 'upload_three_photo_tv' and method 'onClick'");
    target.upload_three_photo_tv = Utils.castView(view, R.id.upload_three_photo_tv, "field 'upload_three_photo_tv'", TextView.class);
    view7f090512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.certification_btn, "field 'certification_btn' and method 'onClick'");
    target.certification_btn = Utils.castView(view, R.id.certification_btn, "field 'certification_btn'", Button.class);
    view7f0900fc = view;
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
    CertificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name_etv = null;
    target.id_card_etv = null;
    target.phone_num_etv = null;
    target.photo_one_iv = null;
    target.photo_two_iv = null;
    target.photo_three_iv = null;
    target.upload_one_photo_tv = null;
    target.upload_two_photo_tv = null;
    target.upload_three_photo_tv = null;
    target.certification_btn = null;

    view7f090511.setOnClickListener(null);
    view7f090511 = null;
    view7f090513.setOnClickListener(null);
    view7f090513 = null;
    view7f090512.setOnClickListener(null);
    view7f090512 = null;
    view7f0900fc.setOnClickListener(null);
    view7f0900fc = null;
  }
}
