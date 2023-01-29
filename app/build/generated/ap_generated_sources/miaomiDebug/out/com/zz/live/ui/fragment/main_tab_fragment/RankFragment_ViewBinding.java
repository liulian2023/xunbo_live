// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.main_tab_fragment;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.androidkun.xtablayout.XTabLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RankFragment_ViewBinding implements Unbinder {
  private RankFragment target;

  @UiThread
  public RankFragment_ViewBinding(RankFragment target, View source) {
    this.target = target;

    target.starusbar_view = Utils.findRequiredViewAsType(source, R.id.statusbar_view, "field 'starusbar_view'", ImageView.class);
    target.rank_viewpager = Utils.findRequiredViewAsType(source, R.id.rank_viewpager, "field 'rank_viewpager'", ViewPager.class);
    target.rank_tab = Utils.findRequiredViewAsType(source, R.id.rank_tab, "field 'rank_tab'", XTabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RankFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.starusbar_view = null;
    target.rank_viewpager = null;
    target.rank_tab = null;
  }
}
