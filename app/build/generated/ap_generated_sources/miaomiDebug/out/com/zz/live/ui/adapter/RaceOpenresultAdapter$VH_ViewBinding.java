// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RaceOpenresultAdapter$VH_ViewBinding implements Unbinder {
  private RaceOpenresultAdapter.VH target;

  @UiThread
  public RaceOpenresultAdapter$VH_ViewBinding(RaceOpenresultAdapter.VH target, View source) {
    this.target = target;

    target.qishu = Utils.findRequiredViewAsType(source, R.id.qishu, "field 'qishu'", TextView.class);
    target.time = Utils.findRequiredViewAsType(source, R.id.time, "field 'time'", TextView.class);
    target.race_openresult_iv1 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv1, "field 'race_openresult_iv1'", ImageView.class);
    target.race_openresult_iv2 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv2, "field 'race_openresult_iv2'", ImageView.class);
    target.race_openresult_iv3 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv3, "field 'race_openresult_iv3'", ImageView.class);
    target.race_openresult_iv4 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv4, "field 'race_openresult_iv4'", ImageView.class);
    target.race_openresult_iv5 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv5, "field 'race_openresult_iv5'", ImageView.class);
    target.race_openresult_iv6 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv6, "field 'race_openresult_iv6'", ImageView.class);
    target.race_openresult_iv7 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv7, "field 'race_openresult_iv7'", ImageView.class);
    target.race_openresult_iv8 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv8, "field 'race_openresult_iv8'", ImageView.class);
    target.race_openresult_iv9 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv9, "field 'race_openresult_iv9'", ImageView.class);
    target.race_openresult_iv10 = Utils.findRequiredViewAsType(source, R.id.race_openresult_iv10, "field 'race_openresult_iv10'", ImageView.class);
    target.race_hezhi_one = Utils.findRequiredViewAsType(source, R.id.race_hezhi_one, "field 'race_hezhi_one'", TextView.class);
    target.race_hezhi_two = Utils.findRequiredViewAsType(source, R.id.race_hezhi_two, "field 'race_hezhi_two'", TextView.class);
    target.race_hezhi_three = Utils.findRequiredViewAsType(source, R.id.race_hezhi_three, "field 'race_hezhi_three'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RaceOpenresultAdapter.VH target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qishu = null;
    target.time = null;
    target.race_openresult_iv1 = null;
    target.race_openresult_iv2 = null;
    target.race_openresult_iv3 = null;
    target.race_openresult_iv4 = null;
    target.race_openresult_iv5 = null;
    target.race_openresult_iv6 = null;
    target.race_openresult_iv7 = null;
    target.race_openresult_iv8 = null;
    target.race_openresult_iv9 = null;
    target.race_openresult_iv10 = null;
    target.race_hezhi_one = null;
    target.race_hezhi_two = null;
    target.race_hezhi_three = null;
  }
}
