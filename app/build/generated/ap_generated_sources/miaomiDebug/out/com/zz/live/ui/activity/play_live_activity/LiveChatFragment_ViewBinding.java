// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.play_live_activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LiveChatFragment_ViewBinding implements Unbinder {
  private LiveChatFragment target;

  private View view7f09050b;

  @UiThread
  public LiveChatFragment_ViewBinding(final LiveChatFragment target, View source) {
    this.target = target;

    View view;
    target.liveChatRecycle = Utils.findRequiredViewAsType(source, R.id.live_chat_recycle, "field 'liveChatRecycle'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.un_read_tv, "field 'un_read_tv' and method 'onClick'");
    target.un_read_tv = Utils.castView(view, R.id.un_read_tv, "field 'un_read_tv'", TextView.class);
    view7f09050b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.join_chat_room_recycler = Utils.findRequiredViewAsType(source, R.id.join_chat_room_recycler, "field 'join_chat_room_recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LiveChatFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.liveChatRecycle = null;
    target.un_read_tv = null;
    target.join_chat_room_recycler = null;

    view7f09050b.setOnClickListener(null);
    view7f09050b = null;
  }
}
