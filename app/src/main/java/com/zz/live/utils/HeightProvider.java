package com.zz.live.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.Method;

/**
 * windowSoftInputMode="adjustNothing" 时,监听键盘高度. (覆盖一层宽度为0,高度为match的pop,通过监听pop高度变化来获取键盘高度)
 */
public class HeightProvider extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener  {
    private Activity mActivity;
    private View rootView;
    private HeightListener listener;
    private int heightMax; // 记录popup内容区的最大高度

    public HeightProvider(Activity activity) {
        super(activity);
        this.mActivity = activity;

        // 基础配置
        rootView = new View(activity);
        setContentView(rootView);

        // 监听全局Layout变化
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        setBackgroundDrawable(new ColorDrawable(0));

        // 设置宽度为0，高度为全屏
        setWidth(0);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);

        /*setClippingEnabled(false);*/
        // 设置键盘弹出方式
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
    }

    public HeightProvider init() {
        if (!isShowing()) {
            final View view = mActivity.getWindow().getDecorView();
            // 延迟加载popupwindow，如果不加延迟就会报错
            view.post(new Runnable() {
                @Override
                public void run() {
                    showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                }
            });
        }
        return this;
    }

    public HeightProvider setHeightListener(HeightListener listener) {
        this.listener = listener;
        return this;
    }


    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        if (rect.bottom > heightMax) {
            heightMax = rect.bottom;
        }
        System.out.println("heightMax="+heightMax+ "bottom= "+rect.bottom);
        // 两者的差值就是键盘的高度
        int keyboardHeight = heightMax - rect.bottom;
        if (listener != null) {
            int navigationBarHeight = ImmersionBar.getNavigationBarHeight(mActivity);
            listener.onHeightChanged(keyboardHeight,navigationBarHeight);
        }
    }


    public interface HeightListener {
        /**
         *
         * @param height 键盘高度
         * @param navigateHeight 虚拟键高度
         */
        void onHeightChanged(int height,int navigateHeight);
    }
    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavBar(context)) {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

}
