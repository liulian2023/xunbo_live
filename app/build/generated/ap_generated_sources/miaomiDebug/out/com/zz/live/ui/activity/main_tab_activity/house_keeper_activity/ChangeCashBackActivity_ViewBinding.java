// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangeCashBackActivity_ViewBinding implements Unbinder {
  private ChangeCashBackActivity target;

  private View view7f09022d;

  private View view7f090176;

  private View view7f09039b;

  private View view7f090104;

  @UiThread
  public ChangeCashBackActivity_ViewBinding(ChangeCashBackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeCashBackActivity_ViewBinding(final ChangeCashBackActivity target, View source) {
    this.target = target;

    View view;
    target.loading_linear = Utils.findRequiredViewAsType(source, R.id.loading_linear, "field 'loading_linear'", ConstraintLayout.class);
    target.error_linear = Utils.findRequiredViewAsType(source, R.id.error_linear, "field 'error_linear'", LinearLayout.class);
    target.reload_tv = Utils.findRequiredViewAsType(source, R.id.reload_tv, "field 'reload_tv'", TextView.class);
    target.nodata_linear = Utils.findRequiredViewAsType(source, R.id.nodata_linear, "field 'nodata_linear'", LinearLayout.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SmartRefreshLayout.class);
    target.extract_amount_tv = Utils.findRequiredViewAsType(source, R.id.extract_amount_tv, "field 'extract_amount_tv'", TextView.class);
    target.cash_back_amount_tv = Utils.findRequiredViewAsType(source, R.id.cash_back_amount_tv, "field 'cash_back_amount_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.income_btn, "field 'cashbash_btn' and method 'onClick'");
    target.cashbash_btn = Utils.castView(view, R.id.income_btn, "field 'cashbash_btn'", Button.class);
    view7f09022d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.extract_btn, "field 'extract_btn' and method 'onClick'");
    target.extract_btn = Utils.castView(view, R.id.extract_btn, "field 'extract_btn'", Button.class);
    view7f090176 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.refresh_iv, "field 'refresh_iv' and method 'onClick'");
    target.refresh_iv = Utils.castView(view, R.id.refresh_iv, "field 'refresh_iv'", ImageView.class);
    view7f09039b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.change_cashback_toolbar_relativeLayout = Utils.findRequiredViewAsType(source, R.id.change_cashback_toolbar_relativeLayout, "field 'change_cashback_toolbar_relativeLayout'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.change_cashback_back_iv, "field 'change_cashback_back_iv' and method 'onClick'");
    target.change_cashback_back_iv = Utils.castView(view, R.id.change_cashback_back_iv, "field 'change_cashback_back_iv'", ImageView.class);
    view7f090104 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.change_cashBack_recycler = Utils.findRequiredViewAsType(source, R.id.change_cashBack_recycler, "field 'change_cashBack_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangeCashBackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.loading_linear = null;
    target.error_linear = null;
    target.reload_tv = null;
    target.nodata_linear = null;
    target.refresh = null;
    target.extract_amount_tv = null;
    target.cash_back_amount_tv = null;
    target.cashbash_btn = null;
    target.extract_btn = null;
    target.refresh_iv = null;
    target.change_cashback_toolbar_relativeLayout = null;
    target.change_cashback_back_iv = null;
    target.change_cashBack_recycler = null;

    view7f09022d.setOnClickListener(null);
    view7f09022d = null;
    view7f090176.setOnClickListener(null);
    view7f090176 = null;
    view7f09039b.setOnClickListener(null);
    view7f09039b = null;
    view7f090104.setOnClickListener(null);
    view7f090104 = null;
  }
}
