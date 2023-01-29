package com.cambodia.zhanbang.rxhttp.net.common;

import android.text.TextUtils;


import com.cambodia.zhanbang.rxhttp.net.converter.GsonConverterFactory;
import com.cambodia.zhanbang.rxhttp.net.https.SslSocketFactory;
import com.cambodia.zhanbang.rxhttp.net.https.UnSafeHostnameVerify;
import com.cambodia.zhanbang.rxhttp.net.https.UnSafeTrustManager;
import com.cambodia.zhanbang.rxhttp.net.interceptor.EncodedInterceptor;
import com.cambodia.zhanbang.rxhttp.net.interceptor.HttpBasrUrlInterceptor;
import com.cambodia.zhanbang.rxhttp.net.interceptor.HttpCacheInterceptor;
import com.cambodia.zhanbang.rxhttp.net.interceptor.HttpHeaderInterceptor;
import com.cambodia.zhanbang.rxhttp.net.interceptor.HttpLoggerInterceptor;
import com.cambodia.zhanbang.rxhttp.net.utils.AppContextUtils;
import com.cambodia.zhanbang.rxhttp.net.utils.SSLUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * created  by ganzhe on 2019/9/12.
 * Retrofit+RxJava网络请求封装
 */
public class RetrofitFactory {

    private static volatile CompositeDisposable mCompositeDisposable;
    private final Retrofit.Builder  build;
    private Retrofit retrofit;
   // public final CacheProvider cacheProvider;

    private RetrofitFactory() {

        // 指定缓存路径,缓存大小100Mb
        File cacheFile = new File(AppContextUtils.getContext().getCacheDir(), "HttpCache");
        File rxcacheFile = new File(AppContextUtils.getContext().getCacheDir(), "RxCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder()
                .readTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(ApiConfig.getDefaultTimeout(), TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpBasrUrlInterceptor())
                .addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
//                .addInterceptor(new EncodedInterceptor())
                //允许失败重试
                .retryOnConnectionFailure(true)
                .cache(cache);


        if (ApiConfig.getOpenHttps()) {
        /*    httpClientBuilder.sslSocketFactory(1 == ApiConfig.getSslSocketConfigure().getVerifyType() ?
                    SslSocketFactory.getSSLSocketFactory(ApiConfig.getSslSocketConfigure().getCertificateInputStream()) :
                    SslSocketFactory.getSSLSocketFactory(), new UnSafeTrustManager());   */
            httpClientBuilder.sslSocketFactory(SSLUtils.createSSLSocketFactory());
            httpClientBuilder.hostnameVerifier(new UnSafeHostnameVerify());
        }
        OkHttpClient httpClient = httpClientBuilder.build();


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .registerTypeAdapterFactory(new NullTypeAdapterFactory())
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();

        build = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        if (!TextUtils.isEmpty(ApiConfig.getBaseUrl1())) {
            retrofit = build.baseUrl(ApiConfig.getBaseUrl1()).build();
        }

//        cacheProvider = new RxCache.Builder()
//                .persistence(rxcacheFile, new GsonSpeaker())
//                .using(CacheProvider.class);

    }



    private static class RetrofitFactoryHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();

    }

    public static final RetrofitFactory getInstance() {
        return RetrofitFactoryHolder.INSTANCE;
    }


    /**
     * 获取单例CompositeDisposable
     *
     * @return
     */
    public static CompositeDisposable getCompositeDisposableInstance() {
        if (mCompositeDisposable == null) {
            synchronized (CompositeDisposable.class) {
                mCompositeDisposable = new CompositeDisposable();
            }
        }
        return mCompositeDisposable;
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param clazz 传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(Class<T> clazz) {
        checkNotNull(retrofit, "BaseUrl not init,you should init first!");
        return retrofit.create(clazz);
    }

    /**
     * 根据Api接口类生成Api实体
     *
     * @param baseUrl baseUrl
     * @param clazz   传入的Api接口类
     * @return Api实体类
     */
    public <T> T create(String baseUrl, Class<T> clazz) {
        return build.baseUrl(baseUrl).build().create(clazz);
    }

    private <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }


}
