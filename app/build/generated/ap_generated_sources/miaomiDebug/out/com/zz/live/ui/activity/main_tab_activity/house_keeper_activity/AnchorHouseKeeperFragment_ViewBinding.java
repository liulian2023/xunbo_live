// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnchorHouseKeeperFragment_ViewBinding implements Unbinder {
  private AnchorHouseKeeperFragment target;

  @UiThread
  public AnchorHouseKeeperFragment_ViewBinding(AnchorHouseKeeperFragment target, View source) {
    this.target = target;

    target.house_keeper_recycler = Utils.findRequiredViewAsType(source, R.id.house_keeper_recycler, "field 'house_keeper_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnchorHouseKeeperFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.house_keeper_recycler = null;
  }
}
