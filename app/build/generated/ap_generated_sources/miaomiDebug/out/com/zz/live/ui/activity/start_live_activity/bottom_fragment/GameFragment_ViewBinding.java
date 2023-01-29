// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity.bottom_fragment;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GameFragment_ViewBinding implements Unbinder {
  private GameFragment target;

  @UiThread
  public GameFragment_ViewBinding(GameFragment target, View source) {
    this.target = target;

    target.typename_tv = Utils.findRequiredViewAsType(source, R.id.typename_tv, "field 'typename_tv'", TextView.class);
    target.qishu_tv = Utils.findRequiredViewAsType(source, R.id.qishu_tv, "field 'qishu_tv'", TextView.class);
    target.count_time_tv = Utils.findRequiredViewAsType(source, R.id.count_time_tv, "field 'count_time_tv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GameFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.typename_tv = null;
    target.qishu_tv = null;
    target.count_time_tv = null;
  }
}
