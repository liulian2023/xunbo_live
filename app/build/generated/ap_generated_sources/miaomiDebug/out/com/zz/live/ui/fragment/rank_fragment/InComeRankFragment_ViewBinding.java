// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.fragment.rank_fragment;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zz.live.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InComeRankFragment_ViewBinding implements Unbinder {
  private InComeRankFragment target;

  private View view7f090146;

  private View view7f090526;

  private View view7f0902e5;

  private View view7f090063;

  @UiThread
  public InComeRankFragment_ViewBinding(final InComeRankFragment target, View source) {
    this.target = target;

    View view;
    target.rank_refresh = Utils.findRequiredViewAsType(source, R.id.rank_refresh, "field 'rank_refresh'", SmartRefreshLayout.class);
    target.rank_recycler = Utils.findRequiredViewAsType(source, R.id.rank_recycler, "field 'rank_recycler'", RecyclerView.class);
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radioGroup, "field 'radioGroup'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.day_rank_rbt, "field 'day_rank_rbt' and method 'onClick'");
    target.day_rank_rbt = Utils.castView(view, R.id.day_rank_rbt, "field 'day_rank_rbt'", RadioButton.class);
    view7f090146 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.week_rank_rbt, "field 'week_rank_rbt' and method 'onClick'");
    target.week_rank_rbt = Utils.castView(view, R.id.week_rank_rbt, "field 'week_rank_rbt'", RadioButton.class);
    view7f090526 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.month_rank_rbt, "field 'month_rank_rbt' and method 'onClick'");
    target.month_rank_rbt = Utils.castView(view, R.id.month_rank_rbt, "field 'month_rank_rbt'", RadioButton.class);
    view7f0902e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.all_rank_rbt, "field 'all_rank_rbt' and method 'onClick'");
    target.all_rank_rbt = Utils.castView(view, R.id.all_rank_rbt, "field 'all_rank_rbt'", RadioButton.class);
    view7f090063 = view;
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
    InComeRankFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rank_refresh = null;
    target.rank_recycler = null;
    target.radioGroup = null;
    target.day_rank_rbt = null;
    target.week_rank_rbt = null;
    target.month_rank_rbt = null;
    target.all_rank_rbt = null;

    view7f090146.setOnClickListener(null);
    view7f090146 = null;
    view7f090526.setOnClickListener(null);
    view7f090526 = null;
    view7f0902e5.setOnClickListener(null);
    view7f0902e5 = null;
    view7f090063.setOnClickListener(null);
    view7f090063 = null;
  }
}
