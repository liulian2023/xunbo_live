// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.main_tab_activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooseBaseUrlAvtivity_ViewBinding implements Unbinder {
  private ChooseBaseUrlAvtivity target;

  @UiThread
  public ChooseBaseUrlAvtivity_ViewBinding(ChooseBaseUrlAvtivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooseBaseUrlAvtivity_ViewBinding(ChooseBaseUrlAvtivity target, View source) {
    this.target = target;

    target.base_url_recycler = Utils.findRequiredViewAsType(source, R.id.base_url_recycler, "field 'base_url_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseBaseUrlAvtivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.base_url_recycler = null;
  }
}
