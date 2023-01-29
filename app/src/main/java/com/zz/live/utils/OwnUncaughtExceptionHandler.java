package com.zz.live.utils;

import android.util.Log;

import androidx.annotation.NonNull;

public class OwnUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable ex) {
        StackTraceElement[] elements = ex.getStackTrace();

        StringBuilder reason =new StringBuilder(ex.toString());

        if (elements !=null && elements.length >0) {

            for (StackTraceElement element : elements) {

                reason.append("\n");

                reason.append(element.toString());

            }

        }

        Log.e("全局异常捕获", reason.toString());

        android.os.Process.killProcess(android.os.Process.myPid());

    }

}

