package com.cambodia.zhanbang.rxhttp.net.exception;

import com.cambodia.zhanbang.rxhttp.net.common.ErrorCode;

/**
 * created  by ganzhe on 2019/11/13.
 */
public class TokenExpiredException extends RuntimeException {
    private int errorCode;

    public TokenExpiredException(int errorCode,String cause) {
        super(ErrorCode.getErrorMessage(errorCode, cause), new Throwable(cause));
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
