/*
 * Copyright (c) 2019.  ganzhe
 */

package com.zz.live.net.api;


import com.alibaba.fastjson.JSON;
import com.cambodia.zhanbang.rxhttp.net.common.RetrofitFactory;
import com.zz.live.BuildConfig;
import com.zz.live.MyApplication;
import com.zz.live.utils.AESUtil;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.SharePreferencesUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


/**
 * created  by ganzhe on 2019/11/14.
 */
public class HttpApiImpl {

    public IHttpApi iHttpApiT;
//    public CacheProvider cacheProvider;


    private HttpApiImpl() {
        iHttpApiT = RetrofitFactory.getInstance().create(IHttpApi.class);
        //     cacheProvider = RetrofitFactory.getInstance().cacheProvider;
    }
    public HttpApiImpl(String base_url) {
        iHttpApiT = RetrofitFactory.getInstance().create(base_url,IHttpApi.class);
        //   cacheProvider = RetrofitFactory.getInstance().cacheProvider;
    }

    public static HttpApiImpl  getInstance() {
        return HttpApiImplHolder.S_INSTANCE;
    }


    private static class HttpApiImplHolder {
        private static final HttpApiImpl S_INSTANCE = new HttpApiImpl();
    }

    public static HttpApiImpl  getInstance1() {
        return HttpApiImplHolder1.S_INSTANCE1;
    }

    private static class HttpApiImplHolder1 {
        private static final HttpApiImpl S_INSTANCE1 = new HttpApiImpl(BuildConfig.API_HOST1);

    }

    public static HttpApiImpl  getInstance2() {
        return HttpApiImplHolder2.S_INSTANCE2;
    }

    private static class HttpApiImplHolder2 {
//        private static final HttpApiImpl S_INSTANCE2 = new HttpApiImpl(BuildConfig.API_HOST2);
        private static final HttpApiImpl S_INSTANCE2 = new HttpApiImpl(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_BASE_URL,BuildConfig.API_HOST2));
    }




    public Observable<Response<ResponseBody>> getPusherUrl() {
        return iHttpApiT.getPusherUrl();
    }

    public Observable<Response<ResponseBody>> login(HashMap<String,Object>data) {
        return iHttpApiT.login(data);
    }

/*    public Observable<Response<ResponseBody>> login(String username,String passWord,String grant_type ) {
        return iHttpApiT.login(username,passWord,grant_type);
    }*/
    public Observable<Response<ResponseBody>> deleteSystemMessage(int type) {
        return iHttpApiT.deleteSystemMessage(type);
    }

    public Observable<Response<ResponseBody>> modifySystemMessageReadStatus(String id) {
        return iHttpApiT.modifySystemMessageReadStatus(id);
    }

    public Observable<Response<ResponseBody>> deletePrivateMessage(String id) {
        return iHttpApiT.deletePrivateMessage(id);
    }

    public Observable<Response<ResponseBody>> modifyPrivateMessageReadStatus(String id) {
        return iHttpApiT.modifyPrivateMessageReadStatus(id);
    }

    public Observable<Response<ResponseBody>> uploadFile(String imgPath) {
        File file = new File(imgPath);
        RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);
        return iHttpApiT.uploadFile(body,part);
    }

    public Observable<Response<ResponseBody>> incomeList(HashMap<String,Object>data){
        return iHttpApiT.incomeList(data);
    }

    public Observable<Response<ResponseBody>> getLastLottery(String url, int type_id){
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数
//        String domain = SharePreferencesUtil.getString(MyApplication.getInstance(),"domain","");
        dataMap.put("type_id", type_id);//传入的参数与数据
        dataMap.put("flag", 1);
        dataMap.put("pageNo", 1);
        dataMap.put("pageSize", 10);
        dataMap.put("domain","http://www.domain.com");
        Map<String,Object> aaa = HttpUtil.getDefReqMap(dataMap);
        return iHttpApiT.getLastLottery(url, aaa);
    }

    /**
     * 请求倒计时
     * @param url
     * @param type_id
     * @return
     * @throws Exception
     */
    public Observable<Response<ResponseBody>> getCountDown(String url, int type_id) {
//        String domain = SharePreferencesUtil.getString(MyApplication.getInstance(), "domain", "");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("type_id", type_id);//传入的参数与数据
//        dataMap.put("domain", domain);
        Map<String,Object> aaa = HttpUtil.getDefReqMap(dataMap);
        return iHttpApiT.getCountDown(url, aaa);
    }

    public void addValidToken(Map<String,Object> data){
        data.put("validToken", AESUtil.encrypt(JSON.toJSONString(data)));
    }

    public Observable<Response<ResponseBody>> pingTest(){
        Map<String, Object> dataMap = new HashMap<>();//上期开奖结果请求参数
        return iHttpApiT.pingTest(dataMap);
    }
}
