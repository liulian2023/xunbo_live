/*
 * Copyright (c) 2019.  ganzhe
 */

package com.zz.live.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.zz.live.utils.ToastUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

public abstract class MvpBaseActivity <T extends IBasePresenter> extends AbstractRootActivity implements IBaseView{

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected KProgressHUD mKProgressHUD;
    protected T mPresenter;
    protected  abstract T  createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView(this);
            Log.e(TAG,"---mPresenter 不为空" + mPresenter.getClass());
        }
    }

    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                if (Const.ERROR_STATE.equals(state)) {
                    showErrorMsg(state);
                } else if (Const.LOADING_STATE.equals(state)) {
                    showLoading();
                } else if (Const.COMPLETE_STATE.equals(state)) {
                    closeLoading();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mPresenter != null){
            mPresenter.onDetachView();
            mPresenter = null;
        }

    }

    @Override
    public void showLoading() {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void closeLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void showNormal() { }

    @Override
    public void showError() { }

    @Override
    protected int getLayout() {
        return 0;
    }



    @Override
    protected void initEventAndData() { }



    @Override
    public void reload() { }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtils.showToast(errorMsg);
    }



}
