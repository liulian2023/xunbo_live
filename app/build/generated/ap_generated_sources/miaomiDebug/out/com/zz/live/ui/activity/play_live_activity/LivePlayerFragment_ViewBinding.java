// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.play_live_activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LivePlayerFragment_ViewBinding implements Unbinder {
  private LivePlayerFragment target;

  @UiThread
  public LivePlayerFragment_ViewBinding(LivePlayerFragment target, View source) {
    this.target = target;

    target.play_tx_cloud_view = Utils.findRequiredViewAsType(source, R.id.play_tx_cloud_view, "field 'play_tx_cloud_view'", TXCloudVideoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LivePlayerFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.play_tx_cloud_view = null;
  }
}
