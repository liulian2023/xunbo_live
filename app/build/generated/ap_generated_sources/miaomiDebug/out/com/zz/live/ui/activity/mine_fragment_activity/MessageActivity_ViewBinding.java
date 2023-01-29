// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.mine_fragment_activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageActivity_ViewBinding implements Unbinder {
  private MessageActivity target;

  private View view7f09042c;

  private View view7f09035d;

  private View view7f090431;

  private View view7f090360;

  @UiThread
  public MessageActivity_ViewBinding(MessageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MessageActivity_ViewBinding(final MessageActivity target, View source) {
    this.target = target;

    View view;
    target.message_content_frameLayout = Utils.findRequiredViewAsType(source, R.id.message_content_frameLayout, "field 'message_content_frameLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.system_message_iv, "field 'system_message_iv' and method 'onClick'");
    target.system_message_iv = Utils.castView(view, R.id.system_message_iv, "field 'system_message_iv'", ImageView.class);
    view7f09042c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.private_message_iv, "field 'private_message_iv' and method 'onClick'");
    target.private_message_iv = Utils.castView(view, R.id.private_message_iv, "field 'private_message_iv'", ImageView.class);
    view7f09035d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.system_message_tv, "field 'system_message_tv' and method 'onClick'");
    target.system_message_tv = Utils.castView(view, R.id.system_message_tv, "field 'system_message_tv'", TextView.class);
    view7f090431 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.private_message_tv, "field 'private_message_tv' and method 'onClick'");
    target.private_message_tv = Utils.castView(view, R.id.private_message_tv, "field 'private_message_tv'", TextView.class);
    view7f090360 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.message_content_frameLayout = null;
    target.system_message_iv = null;
    target.private_message_iv = null;
    target.system_message_tv = null;
    target.private_message_tv = null;

    view7f09042c.setOnClickListener(null);
    view7f09042c = null;
    view7f09035d.setOnClickListener(null);
    view7f09035d = null;
    view7f090431.setOnClickListener(null);
    view7f090431 = null;
    view7f090360.setOnClickListener(null);
    view7f090360 = null;
  }
}
