// Generated code from Butter Knife. Do not modify!
package com.zz.live.ui.activity.start_live_activity;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.nodemedia.NodeCameraView;
import com.faceunity.nama.ui.FaceUnityView;
import com.opensource.svgaplayer.SVGAImageView;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.zz.live.R;
import com.zz.live.myView.gift.GiftView;
import com.zz.live.ui.activity.play_live_activity.EditPanel;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;
import pl.droidsonroids.gif.GifImageView;

public class StartLiveActivity_ViewBinding implements Unbinder {
  private StartLiveActivity target;

  private View view7f090368;

  private View view7f0900b5;

  private View view7f090410;

  private View view7f090100;

  private View view7f090415;

  private TextWatcher view7f090415TextWatcher;

  private View view7f090412;

  private View view7f090057;

  private View view7f090112;

  private View view7f090113;

  private View view7f090160;

  private View view7f0900de;

  private View view7f0902ca;

  private View view7f09024a;

  private View view7f090116;

  private View view7f0902e7;

  private View view7f090114;

  private View view7f0904c5;

  private View view7f09015a;

  private View view7f090250;

  private View view7f0903db;

  private View view7f0903da;

  private View view7f09028e;

  private View view7f0903ab;

  private View view7f0903aa;

  private View view7f090108;

  private View view7f0902f4;

  private View view7f0902f2;

  private View view7f090387;

  private View view7f09007f;

  private View view7f090080;

  private View view7f090177;

  private View view7f090135;

  private View view7f0904d0;

  private View view7f0904ce;

  private View view7f0904cc;

  @UiThread
  public StartLiveActivity_ViewBinding(StartLiveActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  @SuppressLint("ClickableViewAccessibility")
  public StartLiveActivity_ViewBinding(final StartLiveActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.pusher_tx_cloud_view, "field 'mPusherView' and method 'onClick'");
    target.mPusherView = Utils.castView(view, R.id.pusher_tx_cloud_view, "field 'mPusherView'", TXCloudVideoView.class);
    view7f090368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.node_pusher_view = Utils.findRequiredViewAsType(source, R.id.node_pusher_view, "field 'node_pusher_view'", NodeCameraView.class);
    view = Utils.findRequiredView(source, R.id.beauty_iv, "field 'beauty_iv' and method 'onClick'");
    target.beauty_iv = Utils.castView(view, R.id.beauty_iv, "field 'beauty_iv'", ImageView.class);
    view7f0900b5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.live_constrainLayout_group = Utils.findRequiredViewAsType(source, R.id.live_constrainLayout_group, "field 'live_constrainLayout_group'", Group.class);
    view = Utils.findRequiredView(source, R.id.start_live_btn, "field 'start_live_btn' and method 'onClick'");
    target.start_live_btn = Utils.castView(view, R.id.start_live_btn, "field 'start_live_btn'", Button.class);
    view7f090410 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.change_camera_iv, "field 'change_camera_iv' and method 'onClick'");
    target.change_camera_iv = Utils.castView(view, R.id.change_camera_iv, "field 'change_camera_iv'", ImageView.class);
    view7f090100 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.start_live_titile_tv = Utils.findRequiredViewAsType(source, R.id.start_live_titile_tv, "field 'start_live_titile_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.start_live_title_etv, "field 'start_live_title_etv' and method 'onTextChanged'");
    target.start_live_title_etv = Utils.castView(view, R.id.start_live_title_etv, "field 'start_live_title_etv'", EditText.class);
    view7f090415 = view;
    view7f090415TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
        target.onTextChanged(p0);
      }
    };
    ((TextView) view).addTextChangedListener(view7f090415TextWatcher);
    view = Utils.findRequiredView(source, R.id.start_live_constraintLayout, "field 'start_live_constraintLayout' and method 'onClick'");
    target.start_live_constraintLayout = Utils.castView(view, R.id.start_live_constraintLayout, "field 'start_live_constraintLayout'", ConstraintLayout.class);
    view7f090412 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_title_frameLayout, "field 'add_title_frameLayout' and method 'onClick'");
    target.add_title_frameLayout = Utils.castView(view, R.id.add_title_frameLayout, "field 'add_title_frameLayout'", FrameLayout.class);
    view7f090057 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.choose_channel_iv, "field 'choose_channel_iv' and method 'onClick'");
    target.choose_channel_iv = Utils.castView(view, R.id.choose_channel_iv, "field 'choose_channel_iv'", ImageView.class);
    view7f090112 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.choose_channel_tv, "field 'choose_channel_tv' and method 'onClick'");
    target.choose_channel_tv = Utils.castView(view, R.id.choose_channel_tv, "field 'choose_channel_tv'", TextView.class);
    view7f090113 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.cover_iv = Utils.findRequiredViewAsType(source, R.id.cover_iv, "field 'cover_iv'", ImageView.class);
    target.linearLayout7 = Utils.findRequiredViewAsType(source, R.id.linearLayout7, "field 'linearLayout7'", LinearLayout.class);
    target.editPanel = Utils.findRequiredViewAsType(source, R.id.live_edit_panel, "field 'editPanel'", EditPanel.class);
    view = Utils.findRequiredView(source, R.id.drawwe_linear, "field 'drawwe_linear' and method 'onTouch'");
    target.drawwe_linear = Utils.castView(view, R.id.drawwe_linear, "field 'drawwe_linear'", ConstraintLayout.class);
    view7f090160 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        target.onTouch(p0, p1);
        return true;
      }
    });
    target.tv_num = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tv_num'", TextView.class);
    target.giftView = Utils.findRequiredViewAsType(source, R.id.giftView, "field 'giftView'", GiftView.class);
    target.money_tv = Utils.findRequiredViewAsType(source, R.id.money_tv, "field 'money_tv'", TextView.class);
    target.tv_countdown = Utils.findRequiredViewAsType(source, R.id.tv_countdown, "field 'tv_countdown'", TextView.class);
    target.svgaImageView = Utils.findRequiredViewAsType(source, R.id.svga_imageview, "field 'svgaImageView'", SVGAImageView.class);
    target.pusher_drawerLayout = Utils.findRequiredViewAsType(source, R.id.pusher_drawerLayout, "field 'pusher_drawerLayout'", DrawerLayout.class);
    view = Utils.findRequiredView(source, R.id.bottom_pop_iv, "field 'iv_bottomgift' and method 'onClick'");
    target.iv_bottomgift = Utils.castView(view, R.id.bottom_pop_iv, "field 'iv_bottomgift'", ImageView.class);
    view7f0900de = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.meiyan_iv, "field 'iv_centre' and method 'onClick'");
    target.iv_centre = Utils.castView(view, R.id.meiyan_iv, "field 'iv_centre'", ImageView.class);
    view7f0902ca = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_close, "field 'iv_close' and method 'onClick'");
    target.iv_close = Utils.castView(view, R.id.iv_close, "field 'iv_close'", ImageView.class);
    view7f09024a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.choose_lottery_tv, "field 'choose_lottery_tv' and method 'onClick'");
    target.choose_lottery_tv = Utils.castView(view, R.id.choose_lottery_tv, "field 'choose_lottery_tv'", TextView.class);
    view7f090116 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.more_iv, "field 'more_iv' and method 'onClick'");
    target.more_iv = Utils.castView(view, R.id.more_iv, "field 'more_iv'", ImageView.class);
    view7f0902e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.choose_lottery_iv, "field 'choose_lottery_iv' and method 'onClick'");
    target.choose_lottery_iv = Utils.castView(view, R.id.choose_lottery_iv, "field 'choose_lottery_iv'", ImageView.class);
    view7f090114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.lottrry_open_result_constraintLayout = Utils.findRequiredViewAsType(source, R.id.lottrry_open_result_constraintLayout, "field 'lottrry_open_result_constraintLayout'", ConstraintLayout.class);
    target.open_result_top_layout = Utils.findRequiredViewAsType(source, R.id.open_result_top_layout, "field 'open_result_top_layout'", ConstraintLayout.class);
    target.tv_lottery_name = Utils.findRequiredViewAsType(source, R.id.tv_lottery_name, "field 'tv_lottery_name'", TextView.class);
    target.tv_lottery_qishu = Utils.findRequiredViewAsType(source, R.id.tv_lottery_qishu, "field 'tv_lottery_qishu'", TextView.class);
    view = Utils.findRequiredView(source, R.id.top_close_iv, "field 'top_close_iv' and method 'onClick'");
    target.top_close_iv = Utils.castView(view, R.id.top_close_iv, "field 'top_close_iv'", ImageView.class);
    view7f0904c5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.dismiss_open_result_iv, "field 'dismiss_open_result_iv' and method 'onClick'");
    target.dismiss_open_result_iv = Utils.castView(view, R.id.dismiss_open_result_iv, "field 'dismiss_open_result_iv'", ImageView.class);
    view7f09015a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recy_lottery = Utils.findRequiredViewAsType(source, R.id.recy_lottery, "field 'recy_lottery'", RecyclerView.class);
    target.wrap_frameLayout = Utils.findRequiredViewAsType(source, R.id.wrap_frameLayout, "field 'wrap_frameLayout'", FrameLayout.class);
    target.countdown_iv = Utils.findRequiredViewAsType(source, R.id.countdown_iv, "field 'countdown_iv'", GifImageView.class);
    target.live_time_tv = Utils.findRequiredViewAsType(source, R.id.live_time_tv, "field 'live_time_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_lottery, "field 'iv_lottery' and method 'onClick'");
    target.iv_lottery = Utils.castView(view, R.id.iv_lottery, "field 'iv_lottery'", ImageView.class);
    view7f090250 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.set_business_tv, "field 'set_business_tv' and method 'onClick'");
    target.set_business_tv = Utils.castView(view, R.id.set_business_tv, "field 'set_business_tv'", TextView.class);
    view7f0903db = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.set_business_iv, "field 'set_business_iv' and method 'onClick'");
    target.set_business_iv = Utils.castView(view, R.id.set_business_iv, "field 'set_business_iv'", ImageView.class);
    view7f0903da = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_gift, "field 'll_gift' and method 'onClick'");
    target.ll_gift = Utils.castView(view, R.id.ll_gift, "field 'll_gift'", LinearLayout.class);
    view7f09028e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.room_type_tv, "field 'room_type_tv' and method 'onClick'");
    target.room_type_tv = Utils.castView(view, R.id.room_type_tv, "field 'room_type_tv'", TextView.class);
    view7f0903ab = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.room_type_iv, "field 'room_type_iv' and method 'onClick'");
    target.room_type_iv = Utils.castView(view, R.id.room_type_iv, "field 'room_type_iv'", ImageView.class);
    view7f0903aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.change_room_type_iv, "field 'change_room_type_iv' and method 'onClick'");
    target.change_room_type_iv = Utils.castView(view, R.id.change_room_type_iv, "field 'change_room_type_iv'", ImageView.class);
    view7f090108 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nature_tv, "field 'nature_tv' and method 'onClick'");
    target.nature_tv = Utils.castView(view, R.id.nature_tv, "field 'nature_tv'", TextView.class);
    view7f0902f4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nature_iv, "field 'nature_iv' and method 'onClick'");
    target.nature_iv = Utils.castView(view, R.id.nature_iv, "field 'nature_iv'", ImageView.class);
    view7f0902f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recy_renshu = Utils.findRequiredViewAsType(source, R.id.recy_renshu, "field 'recy_renshu'", RecyclerView.class);
    target.speed_tv = Utils.findRequiredViewAsType(source, R.id.speed_tv, "field 'speed_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rank_linear, "field 'rank_linear' and method 'onClick'");
    target.rank_linear = Utils.castView(view, R.id.rank_linear, "field 'rank_linear'", LinearLayout.class);
    view7f090387 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.faceUnityView = Utils.findRequiredViewAsType(source, R.id.face_unity_view, "field 'faceUnityView'", FaceUnityView.class);
    target.join_svga_imageview = Utils.findRequiredViewAsType(source, R.id.join_svga_imageview, "field 'join_svga_imageview'", SVGAImageView.class);
    target.test_svga_imageview = Utils.findRequiredViewAsType(source, R.id.test_svga_imageview, "field 'test_svga_imageview'", SVGAImageView.class);
    view = Utils.findRequiredView(source, R.id.area_iv, "field 'area_iv' and method 'onClick'");
    target.area_iv = Utils.castView(view, R.id.area_iv, "field 'area_iv'", ImageView.class);
    view7f09007f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.area_tv, "field 'area_tv' and method 'onClick'");
    target.area_tv = Utils.castView(view, R.id.area_tv, "field 'area_tv'", TextView.class);
    view7f090080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.face_wrap_linear = Utils.findRequiredViewAsType(source, R.id.face_wrap_linear, "field 'face_wrap_linear'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.face_top_linear, "field 'face_top_linear' and method 'onClick'");
    target.face_top_linear = Utils.castView(view, R.id.face_top_linear, "field 'face_top_linear'", LinearLayout.class);
    view7f090177 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.count_down_fail_refresh_tv, "field 'count_down_fail_refresh_tv' and method 'onClick'");
    target.count_down_fail_refresh_tv = Utils.castView(view, R.id.count_down_fail_refresh_tv, "field 'count_down_fail_refresh_tv'", TextView.class);
    view7f090135 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.count_down_fail_loading_iv = Utils.findRequiredViewAsType(source, R.id.count_down_fail_loading_iv, "field 'count_down_fail_loading_iv'", GifImageView.class);
    view = Utils.findRequiredView(source, R.id.toy_tv, "field 'toy_tv' and method 'onClick'");
    target.toy_tv = Utils.castView(view, R.id.toy_tv, "field 'toy_tv'", TextView.class);
    view7f0904d0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.toy_iv, "field 'toy_iv' and method 'onClick'");
    target.toy_iv = Utils.castView(view, R.id.toy_iv, "field 'toy_iv'", ImageView.class);
    view7f0904ce = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.toy_disconnect_tv, "field 'toy_disconnect_tv' and method 'onClick'");
    target.toy_disconnect_tv = Utils.castView(view, R.id.toy_disconnect_tv, "field 'toy_disconnect_tv'", TextView.class);
    view7f0904cc = view;
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
    StartLiveActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPusherView = null;
    target.node_pusher_view = null;
    target.beauty_iv = null;
    target.live_constrainLayout_group = null;
    target.start_live_btn = null;
    target.change_camera_iv = null;
    target.start_live_titile_tv = null;
    target.start_live_title_etv = null;
    target.start_live_constraintLayout = null;
    target.add_title_frameLayout = null;
    target.choose_channel_iv = null;
    target.choose_channel_tv = null;
    target.cover_iv = null;
    target.linearLayout7 = null;
    target.editPanel = null;
    target.drawwe_linear = null;
    target.tv_num = null;
    target.giftView = null;
    target.money_tv = null;
    target.tv_countdown = null;
    target.svgaImageView = null;
    target.pusher_drawerLayout = null;
    target.iv_bottomgift = null;
    target.iv_centre = null;
    target.iv_close = null;
    target.choose_lottery_tv = null;
    target.more_iv = null;
    target.choose_lottery_iv = null;
    target.lottrry_open_result_constraintLayout = null;
    target.open_result_top_layout = null;
    target.tv_lottery_name = null;
    target.tv_lottery_qishu = null;
    target.top_close_iv = null;
    target.dismiss_open_result_iv = null;
    target.recy_lottery = null;
    target.wrap_frameLayout = null;
    target.countdown_iv = null;
    target.live_time_tv = null;
    target.iv_lottery = null;
    target.set_business_tv = null;
    target.set_business_iv = null;
    target.ll_gift = null;
    target.room_type_tv = null;
    target.room_type_iv = null;
    target.change_room_type_iv = null;
    target.nature_tv = null;
    target.nature_iv = null;
    target.recy_renshu = null;
    target.speed_tv = null;
    target.rank_linear = null;
    target.faceUnityView = null;
    target.join_svga_imageview = null;
    target.test_svga_imageview = null;
    target.area_iv = null;
    target.area_tv = null;
    target.face_wrap_linear = null;
    target.face_top_linear = null;
    target.count_down_fail_refresh_tv = null;
    target.count_down_fail_loading_iv = null;
    target.toy_tv = null;
    target.toy_iv = null;
    target.toy_disconnect_tv = null;

    view7f090368.setOnClickListener(null);
    view7f090368 = null;
    view7f0900b5.setOnClickListener(null);
    view7f0900b5 = null;
    view7f090410.setOnClickListener(null);
    view7f090410 = null;
    view7f090100.setOnClickListener(null);
    view7f090100 = null;
    ((TextView) view7f090415).removeTextChangedListener(view7f090415TextWatcher);
    view7f090415TextWatcher = null;
    view7f090415 = null;
    view7f090412.setOnClickListener(null);
    view7f090412 = null;
    view7f090057.setOnClickListener(null);
    view7f090057 = null;
    view7f090112.setOnClickListener(null);
    view7f090112 = null;
    view7f090113.setOnClickListener(null);
    view7f090113 = null;
    view7f090160.setOnTouchListener(null);
    view7f090160 = null;
    view7f0900de.setOnClickListener(null);
    view7f0900de = null;
    view7f0902ca.setOnClickListener(null);
    view7f0902ca = null;
    view7f09024a.setOnClickListener(null);
    view7f09024a = null;
    view7f090116.setOnClickListener(null);
    view7f090116 = null;
    view7f0902e7.setOnClickListener(null);
    view7f0902e7 = null;
    view7f090114.setOnClickListener(null);
    view7f090114 = null;
    view7f0904c5.setOnClickListener(null);
    view7f0904c5 = null;
    view7f09015a.setOnClickListener(null);
    view7f09015a = null;
    view7f090250.setOnClickListener(null);
    view7f090250 = null;
    view7f0903db.setOnClickListener(null);
    view7f0903db = null;
    view7f0903da.setOnClickListener(null);
    view7f0903da = null;
    view7f09028e.setOnClickListener(null);
    view7f09028e = null;
    view7f0903ab.setOnClickListener(null);
    view7f0903ab = null;
    view7f0903aa.setOnClickListener(null);
    view7f0903aa = null;
    view7f090108.setOnClickListener(null);
    view7f090108 = null;
    view7f0902f4.setOnClickListener(null);
    view7f0902f4 = null;
    view7f0902f2.setOnClickListener(null);
    view7f0902f2 = null;
    view7f090387.setOnClickListener(null);
    view7f090387 = null;
    view7f09007f.setOnClickListener(null);
    view7f09007f = null;
    view7f090080.setOnClickListener(null);
    view7f090080 = null;
    view7f090177.setOnClickListener(null);
    view7f090177 = null;
    view7f090135.setOnClickListener(null);
    view7f090135 = null;
    view7f0904d0.setOnClickListener(null);
    view7f0904d0 = null;
    view7f0904ce.setOnClickListener(null);
    view7f0904ce = null;
    view7f0904cc.setOnClickListener(null);
    view7f0904cc = null;
  }
}
