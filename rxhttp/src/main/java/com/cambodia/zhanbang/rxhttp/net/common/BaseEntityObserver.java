package com.cambodia.zhanbang.rxhttp.net.common;


import com.cambodia.zhanbang.rxhttp.net.model.BaseEntity;
import com.cambodia.zhanbang.rxhttp.net.utils.RxExceptionUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * created  by ganzhe on 2019/11/13.
 */
public abstract class BaseEntityObserver<T extends BaseEntity> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = RxExceptionUtils.exceptionHandler(e);
        onFail(errorMsg);
        onRequestEnd();
    }

    @Override
    public void onComplete() {
        onRequestEnd();
    }

    abstract public void onSuccess(T response);
    abstract public void onFail(String msg);
    /**
     * 网络请求开始
     */
    protected void onRequestStart() {
    }
    /**
     * 网络请求结束
     */
    protected void onRequestEnd() {
    }
}
