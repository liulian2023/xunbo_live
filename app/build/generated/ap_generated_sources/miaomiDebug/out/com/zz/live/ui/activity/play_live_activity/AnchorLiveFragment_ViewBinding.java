// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.play_live_activity;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.opensource.svgaplayer.SVGAImageView;
import com.zz.live.R;
import com.zz.live.myView.gift.GiftView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnchorLiveFragment_ViewBinding implements Unbinder {
  private AnchorLiveFragment target;

  private View view7f09011d;

  private View view7f090160;

  @UiThread
  @SuppressLint("ClickableViewAccessibility")
  public AnchorLiveFragment_ViewBinding(final AnchorLiveFragment target, View source) {
    this.target = target;

    View view;
    target.live_drawerLayout = Utils.findRequiredViewAsType(source, R.id.live_drawerLayout, "field 'live_drawerLayout'", DrawerLayout.class);
    target.live_cover_iv = Utils.findRequiredViewAsType(source, R.id.live_cover_iv, "field 'live_cover_iv'", ImageView.class);
    target.play_fail_linear = Utils.findRequiredViewAsType(source, R.id.play_fail_linear, "field 'play_fail_linear'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.clear_screen_iv, "field 'clear_screen_iv' and method 'onClick'");
    target.clear_screen_iv = Utils.castView(view, R.id.clear_screen_iv, "field 'clear_screen_iv'", ImageView.class);
    view7f09011d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.linearLayout7 = Utils.findRequiredViewAsType(source, R.id.linearLayout7, "field 'linearLayout7'", LinearLayout.class);
    target.editPanel = Utils.findRequiredViewAsType(source, R.id.live_edit_panel, "field 'editPanel'", EditPanel.class);
    view = Utils.findRequiredView(source, R.id.drawwe_linear, "field 'drawwe_linear', method 'onClick', and method 'onTouch'");
    target.drawwe_linear = Utils.castView(view, R.id.drawwe_linear, "field 'drawwe_linear'", ConstraintLayout.class);
    view7f090160 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        target.onTouch(p0, p1);
        return true;
      }
    });
    target.tv_num = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tv_num'", TextView.class);
    target.giftView = Utils.findRequiredViewAsType(source, R.id.giftView, "field 'giftView'", GiftView.class);
    target.money_tv = Utils.findRequiredViewAsType(source, R.id.money_tv, "field 'money_tv'", TextView.class);
    target.svgaImageView = Utils.findRequiredViewAsType(source, R.id.svga_imageview, "field 'svgaImageView'", SVGAImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnchorLiveFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.live_drawerLayout = null;
    target.live_cover_iv = null;
    target.play_fail_linear = null;
    target.clear_screen_iv = null;
    target.linearLayout7 = null;
    target.editPanel = null;
    target.drawwe_linear = null;
    target.tv_num = null;
    target.giftView = null;
    target.money_tv = null;
    target.svgaImageView = null;

    view7f09011d.setOnClickListener(null);
    view7f09011d = null;
    view7f090160.setOnClickListener(null);
    view7f090160.setOnTouchListener(null);
    view7f090160 = null;
  }
}
