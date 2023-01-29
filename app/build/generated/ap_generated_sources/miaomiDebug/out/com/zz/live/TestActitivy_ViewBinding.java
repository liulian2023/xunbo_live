// Generated code from Butter Knife. Do not modify!
package com.zz.live;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestActitivy_ViewBinding implements Unbinder {
  private TestActitivy target;

  @UiThread
  public TestActitivy_ViewBinding(TestActitivy target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestActitivy_ViewBinding(TestActitivy target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.test_recycler, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestActitivy target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
