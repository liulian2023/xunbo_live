package com.zz.live.base;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zz.live.receiver.NetWorkStateReceiver;
import com.zz.live.utils.ActivityStack;
import com.zz.live.utils.ActivityUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.umeng.analytics.MobclickAgent;
import com.zz.live.utils.StringMyUtil;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseActivity是所有Activity的基类，把一些公共的方法放到里面，如基础样式设置，权限封装，网络状态监听等
 * <p>
 *
 */
public abstract class BaseActivity extends MySupportActivity   implements NetWorkStateReceiver.NetChangeListener{

//    public static NetWorkStateReceiver.NetChangeListener netEvent;// 网络状态改变监听事件
    //网络状态监听库
//     NetWorkStateReceiver netWorkStateReceiver;
    public static NetWorkStateChange netEvent;

    private static long clickGapTime = 0;

    protected KProgressHUD mKProgressHUD;
    private Unbinder unbinder;

    //  public static final int CLICK_GAP_RESPONSE = 700;//700ms内不响应

    public static class NetWorkStateChange implements NetWorkStateReceiver.NetChangeListener{
         WeakReference<BaseActivity>baseActivityWeakReference;

        public WeakReference<BaseActivity> getBaseActivityWeakReference() {
            return baseActivityWeakReference;
        }

        public void setBaseActivityWeakReference(WeakReference<BaseActivity> baseActivityWeakReference) {
            this.baseActivityWeakReference = baseActivityWeakReference;
        }

        public NetWorkStateChange(WeakReference<BaseActivity> baseActivityWeakReference) {
            this.baseActivityWeakReference = baseActivityWeakReference;
        }

        @Override
        public void onNetChange(boolean netWorkState) {

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mUnbinder = ButterKnife.bind(this);
        // 添加到Activity工具类
        ActivityUtil.getInstance().addActivity(this);
        ActivityStack.getInstance().add(this);
//        ProgressDialogUtil.show(this);
        // 执行初始化方法
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        init(savedInstanceState);
        // 初始化netEvent
        netEvent= new NetWorkStateChange(new WeakReference<>(this));
        initStatusBar();
    }

    protected  void initStatusBar(){
    }

    public abstract int getLayoutId() ;


    // 抽象 - 初始化方法，可以对数据进行初始化
    protected abstract void init(Bundle savedInstanceState);



    //请求列表数据失败时,重新加载
    public void errorRefresh(){

    }


    public void showLoading() {
        if(mKProgressHUD==null){
            mKProgressHUD = KProgressHUD.create(this);
        }
        if(!mKProgressHUD.isShowing()){
            mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }

    }
    public void showLoadingTip(String tip) {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setLabel(tip)
                .show();
    }

    public void closeLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
/*
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }*/
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(netWorkStateReceiver, filter);
        Log.e("","注册");
        MobclickAgent.onResume(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityUtil.getInstance().removeActivity(this);
            ActivityStack.getInstance().remove(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showtoast(String str){
        str = StringMyUtil.isEmptyString(str)?"":str;
        if(StringMyUtil.isEmptyString(str)||str.equalsIgnoreCase("timeout")||str.contains("超时")){
            return;
        }
        Toast toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        toast.setText(str);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 防止点击过快
     * @param CLICK_GAP_RESPONSE   设置的时长  700毫秒
     * @return
     */
    protected boolean clickGapFilter(int CLICK_GAP_RESPONSE){
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis-clickGapTime< CLICK_GAP_RESPONSE){
            return false;
        }
        clickGapTime = currentTimeMillis;
        return true;
    }
    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除Activity
        ActivityUtil.getInstance().removeActivity(this);
        ActivityStack.getInstance().remove(this);
//        unregisterReceiver(netWorkStateReceiver);
        Log.e("","注销");
        unbinder.unbind();
        unbinder=null;
//        ActivityStack.getInstance().remove(this);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, ev)) { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {  //判断得到的焦点控件是否包含EditText
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }
    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}