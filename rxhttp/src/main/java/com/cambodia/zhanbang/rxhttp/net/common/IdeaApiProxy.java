package com.cambodia.zhanbang.rxhttp.net.common;

import com.cambodia.zhanbang.rxhttp.net.token.IGlobalManager;
import com.cambodia.zhanbang.rxhttp.net.token.ProxyHandler;

import java.lang.reflect.Proxy;


/**
 * created  by ganzhe on 2019/11/13.
 */
public class IdeaApiProxy {

    @SuppressWarnings("unchecked")
    public <T> T getApiService(Class<T> tClass,String baseUrl,IGlobalManager manager) {
        T t = RetrofitFactory.getInstance().create(baseUrl,tClass);

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[] { tClass }, new ProxyHandler(t, manager,baseUrl));
    }
}
