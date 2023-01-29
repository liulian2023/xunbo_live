package com.cambodia.zhanbang.rxhttp.net.token;



public interface IGlobalManager {
    /**
     * Exit the login state.
     */
    void logout();

    void tokenRefresh(RefreshTokenResponse response);
}
