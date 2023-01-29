// Generated code from Butter Knife. Do not modify!
package com.zz.live.base;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseWebActivity_ViewBinding implements Unbinder {
  private BaseWebActivity target;

  @UiThread
  public BaseWebActivity_ViewBinding(BaseWebActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseWebActivity_ViewBinding(BaseWebActivity target, View source) {
    this.target = target;

    target.base_webView = Utils.findRequiredViewAsType(source, R.id.base_webView, "field 'base_webView'", WebView.class);
    target.web_progress = Utils.findRequiredViewAsType(source, R.id.web_progress, "field 'web_progress'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseWebActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.base_webView = null;
    target.web_progress = null;
  }
}
