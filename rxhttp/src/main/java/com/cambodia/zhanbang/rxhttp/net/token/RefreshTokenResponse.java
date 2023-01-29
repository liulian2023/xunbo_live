package com.cambodia.zhanbang.rxhttp.net.token;

import com.cambodia.zhanbang.rxhttp.net.model.BaseEntity;

/**
 * Created by zhpan on 2018/3/27.
 */

public class RefreshTokenResponse extends BaseEntity {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
