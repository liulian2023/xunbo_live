// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GiftFragment_ViewBinding implements Unbinder {
  private GiftFragment target;

  @UiThread
  public GiftFragment_ViewBinding(GiftFragment target, View source) {
    this.target = target;

    target.gift_recycler = Utils.findRequiredViewAsType(source, R.id.gift_recycler, "field 'gift_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GiftFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gift_recycler = null;
  }
}
