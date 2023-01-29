package com.zz.live.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class BaseDialogFragment extends DialogFragment {
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        *//**
         * 重写onActivityCreated 解决内存泄漏问题
         *//*
        if (getShowsDialog()) {
            setShowsDialog(false);
        }
        super.onActivityCreated(savedInstanceState);
        setShowsDialog(true);

        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException(
                        "DialogFragment can not be attached to a container view");
            }
            getDialog().setContentView(view);
        }
        final Activity activity = getActivity();
        if (activity != null) {
            getDialog().setOwnerActivity(activity);
        }
        if (savedInstanceState != null) {
            Bundle dialogState = savedInstanceState.getBundle(SAVED_DIALOG_STATE_TAG);
            if (dialogState != null) {
                getDialog().onRestoreInstanceState(dialogState);
            }
        }
    }*/


    public void errorRefresh(){
    }
}
