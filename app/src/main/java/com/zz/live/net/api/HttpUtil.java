

package com.zz.live.net.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.utils.Base64Utils;
import com.zz.live.BuildConfig;
import com.zz.live.MyApplication;
import com.zz.live.utils.AESUtil;
import com.zz.live.utils.SystemUtil;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * created  by ganzhe on 2019/11/16.
 */
public class HttpUtil {

    public static RequestBody postRequest(HashMap<String,Object> data,boolean isCp){
        if(!isCp){
            data.put("deviceNumber", SystemUtil.getUniqueId(MyApplication.getInstance()));//设备编号
            data.put("deviceIdentifier",String.format("%s",SystemUtil.getSystemModel()));//设备标识
            data.put("machineModel",String.format("%s",SystemUtil.getSystemModel()));//设备标识
            data.put("appVersion", BuildConfig.VERSION_NAME);//版本号
            if(data.size()!=0){
                String encrypt = AESUtil.encrypt(JSON.toJSONString(data));
                data.put("validToken", encrypt.replace("\n",""));
            }
        }
        RequestBody requestBody =null;
        JSONObject root = new JSONObject();
            // 添加公共参数
            if(isCp){
                data = (HashMap<String, Object>) getDefReqMap(data);
                   return SetPostRequestBody(data);
//                requestBody  = RequestBody.create(MediaType.parse("multipart/form-data"), );
            }else {
                Iterator<String> iterator = data.keySet().iterator();
                String key = "";
                while (iterator.hasNext()) {
                    key = iterator.next().toString();
                    Object obj = data.get(key);
                    if(obj instanceof JSONArray){
                        root.put(key,obj);
                    }else {
                        root.put(key, obj);
                    }
                }
                requestBody =RequestBody.create(MediaType.parse("application/json"), root.toString());
//                requestBody =RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), root.toString());
                return requestBody;
            }
    }

    public static HashMap<String,Object> wwwPostRequestBody(HashMap<String,Object> data){
        data.put("deviceNumber", SystemUtil.getUniqueId(MyApplication.getInstance()));//设备编号
        data.put("deviceIdentifier",String.format("%s",SystemUtil.getSystemModel()));//手机型号
        data.put("machineModel",String.format("%s",SystemUtil.getSystemModel()));//手机型号
        data.put("appVersion", BuildConfig.VERSION_NAME);//版本号
        if(data.size()!=0){
            String encrypt = AESUtil.encrypt(JSON.toJSONString(data));
            data.put("validToken", encrypt.replace("\n",""));
        }
        return data;
    }

    public static Map<String,Object> getRequest(HashMap<String,Object>data){

        String encrypt = AESUtil.encrypt(JSON.toJSONString(data));
        data.put("validToken", encrypt.replace("\n",""));
        return data;
    }
    public static String pathRequsest(String path){
        return path;
    }
    public static RequestBody uploadRequest(String filePath){
        File file = new File(filePath);//filePath为图片位置
        RequestBody  fileBody  = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .build();
        return requestBody;
    }


    private static RequestBody SetPostRequestBody(Map<String, Object> BodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, String.valueOf(BodyParams.get(key)));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }


    /**
     * 老CP请求数据封装
     * @param dataMap
     * @return
     */
    public static Map<String,Object> getDefReqMap(Map<String,Object> dataMap){
        Map<String,Object> headerMap = new HashMap<>();
        String version = "1.0";
        String timestamp = System.currentTimeMillis() + "";
        String deviceCode = "145158033846";
        headerMap.put("version", version);//版本号
        headerMap.put("timestamp", timestamp + "");//时间搓
        headerMap.put("code", deviceCode);//设备号

        String dataStr = null;//对请求体进行base64编码
        try {
            dataStr = Base64Utils.encodeBase64String(JSONObject.toJSONString(dataMap));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //版本号【version】+时间戳【timestamp】+应用密钥【secret】+消息体【data】 取MD5
        String token = "";
        StringBuilder sb = new StringBuilder();
        sb.append(version);
        sb.append(timestamp);
        sb.append("a9437e8561ed45209446e936703dbbbd");
        sb.append(dataStr);
        token = new String(Hex.encodeHex(DigestUtils.md5(String.valueOf(sb))));

        headerMap.put("token", token);
        Map<String, Object> map = new HashMap<String, Object>();//请求集合
        map.put("head", headerMap);
        map.put("data", dataStr);
        Map<String, Object> aaa = new HashMap<String, Object>();
        try {
            aaa.put("data", Base64Utils.encodeBase64String(JSONObject.toJSONString(map)));//请求集合再进行一次base64编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aaa;
    }


}
