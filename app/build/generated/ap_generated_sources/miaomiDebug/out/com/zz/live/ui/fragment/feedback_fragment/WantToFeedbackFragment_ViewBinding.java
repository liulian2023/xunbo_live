// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.feedback_fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WantToFeedbackFragment_ViewBinding implements Unbinder {
  private WantToFeedbackFragment target;

  @UiThread
  public WantToFeedbackFragment_ViewBinding(WantToFeedbackFragment target, View source) {
    this.target = target;

    target.commitBtn = Utils.findRequiredViewAsType(source, R.id.commit_button, "field 'commitBtn'", Button.class);
    target.problemEtv = Utils.findRequiredViewAsType(source, R.id.problem_etv, "field 'problemEtv'", EditText.class);
    target.addIv = Utils.findRequiredViewAsType(source, R.id.add_image, "field 'addIv'", ImageView.class);
    target.meetRecycle = Utils.findRequiredViewAsType(source, R.id.mine_problem_recycle, "field 'meetRecycle'", RecyclerView.class);
    target.lengthTv = Utils.findRequiredViewAsType(source, R.id.edit_text_length_tv, "field 'lengthTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WantToFeedbackFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.commitBtn = null;
    target.problemEtv = null;
    target.addIv = null;
    target.meetRecycle = null;
    target.lengthTv = null;
  }
}
