package com.cambodia.zhanbang.rxhttp.net.common;

import retrofit2.Retrofit;

/**
 * Created by zhpan on 2017/4/1.
 */

public class IdeaApi {
    public static <T> T getApiService(Class<T> tClass, String baseUrl) {
        T t = RetrofitFactory.getInstance().create(baseUrl,tClass);
        return t;
    }
}
