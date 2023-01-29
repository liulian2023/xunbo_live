package com.cambodia.zhanbang.rxhttp.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import com.cambodia.zhanbang.rxhttp.net.common.RxLibConstants;
import com.cambodia.zhanbang.rxhttp.net.utils.AppContextUtils;

import static com.cambodia.zhanbang.rxhttp.net.common.RxLibConstants.IMAGE_DOMAIN;


/**
 * created  by ganzhe on 2019/9/26.
 */
public class SharedPreferenceHelperImpl implements SharedPreferenceHelper {

    private final SharedPreferences mSharedPreferences;


    public SharedPreferenceHelperImpl() {
        mSharedPreferences = AppContextUtils.getContext().getSharedPreferences(RxLibConstants.SHAREDPREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mSharedPreferences.edit().putBoolean(RxLibConstants.SP_LOGIN_STATUS,loginStatus).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mSharedPreferences.getBoolean(RxLibConstants.SP_LOGIN_STATUS,false);
    }

    @Override
    public void setPhoneNum(String strPhone) {
        mSharedPreferences.edit().putString(RxLibConstants.SP_PHONE_NUM,strPhone).apply();
    }

    @Override
    public String getPhoneNum() {
        return mSharedPreferences.getString(RxLibConstants.SP_PHONE_NUM,"");
    }

    @Override
    public void setMsgSwitch(boolean msgSwitchStatus) {
        mSharedPreferences.edit().putBoolean(RxLibConstants.SP_MSGSWITCH_STATUS,msgSwitchStatus).apply();
    }

    @Override
    public boolean getMsgSwitch() {
        return mSharedPreferences.getBoolean(RxLibConstants.SP_MSGSWITCH_STATUS,false);
    }

    @Override
    public void setVoiceSwitch(boolean voiceSwitchStatus) {
        mSharedPreferences.edit().putBoolean(RxLibConstants.SP_VOICESWITCH_STATUS,voiceSwitchStatus).apply();
    }

    @Override
    public boolean getVoiceSwitch() {
        return mSharedPreferences.getBoolean(RxLibConstants.SP_VOICESWITCH_STATUS,false);
    }

    @Override
    public void setToken(String token) {
        mSharedPreferences.edit().putString(RxLibConstants.SP_TOKEN,token).apply();
    }

    @Override
    public String getToken() {
        return mSharedPreferences.getString(RxLibConstants.SP_TOKEN,"");
    }

    @Override
    public void setTokenRefresh(String token_refresh) {
        mSharedPreferences.edit().putString(RxLibConstants.SP_TOKENREFRESH,token_refresh).apply();
    }

    @Override
    public String getTokenRefresh() {
        return mSharedPreferences.getString(RxLibConstants.SP_TOKENREFRESH,"");
    }

    @Override
    public void ClearSp() {
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public void setSearchCache(String searchCache) {
        mSharedPreferences.edit().putString(RxLibConstants.SEARCH_CACHE,searchCache).apply();
    }

    @Override
    public String getSearchCache() {
        return mSharedPreferences.getString(RxLibConstants.SEARCH_CACHE,"");
    }

    @Override
    public void putObject(String key, Object object) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    @Override
    public Object getObject(String key,Object object) {
        if (object instanceof String) {
            return mSharedPreferences.getString(key, "");
        } else if (object instanceof Integer) {
            return mSharedPreferences.getInt(key, 0);
        } else if (object instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, false);
        } else if (object instanceof Float) {
            return mSharedPreferences.getFloat(key, 0);
        } else if (object instanceof Long) {
            return mSharedPreferences.getLong(key, 0L);
        }
        return null;
    }

    @Override
    public void setUserInfo(String userInfo) {
        mSharedPreferences.edit().putString(RxLibConstants.SP_USERINFO,userInfo).apply();
    }

    @Override
    public String getUserInfo() {
        return mSharedPreferences.getString(RxLibConstants.SP_USERINFO,"");
    }

    @Override
    public void setShiJianCha(Long shiJianCha) {
        mSharedPreferences.edit().putLong(RxLibConstants.SHIJIAN_CHA,shiJianCha).apply();
    }

    @Override
    public long getShiJianCha() {
        return mSharedPreferences.getLong(RxLibConstants.SHIJIAN_CHA,0l);
    }

    @Override
    public void setUserId(long userId) {
        mSharedPreferences.edit().putLong(RxLibConstants.USER_ID,userId).apply();
    }


    @Override
    public long getUserId() {
        return mSharedPreferences.getLong(RxLibConstants.USER_ID,0);
    }

    @Override
    public void setImageDomin(String imageDomin) {
        mSharedPreferences.edit().putString(IMAGE_DOMAIN,imageDomin).apply();
    }

    @Override
    public String getImageDomin() {
        return mSharedPreferences.getString(IMAGE_DOMAIN,"");
    }


    @Override
    public void setAppName(String appName) {
        mSharedPreferences.edit().putString(RxLibConstants.APP_NAME,appName).apply();
    }

    @Override
    public String getAppName() {
        return mSharedPreferences.getString(RxLibConstants.APP_NAME,"");
    }

    @Override
    public void setFrontDomain(String url) {
        mSharedPreferences.edit().putString(RxLibConstants.FRONT_DOMAIN,url).apply();
    }

    @Override
    public String getFrontDomain() {
        return  mSharedPreferences.getString(RxLibConstants.FRONT_DOMAIN,"");
    }

    @Override
    public void setSysParameter(String sysParameter) {
        mSharedPreferences.edit().putString(RxLibConstants.SYS_PARAMETER,sysParameter).apply();
    }

    @Override
    public String getSysParameter() {
        return mSharedPreferences.getString(RxLibConstants.SYS_PARAMETER,"");
    }

    @Override
    public void setCustomAmout(String customAmount) {
        mSharedPreferences.edit().putString(RxLibConstants.CUSTOM_CHIP_AMOUNT,customAmount).apply();
    }

    @Override
    public String getCustomAmout() {
        return mSharedPreferences.getString(RxLibConstants.CUSTOM_CHIP_AMOUNT,"自定义");
    }

    @Override
    public void setCurrentAmout(String currentAmount) {
        mSharedPreferences.edit().putString(RxLibConstants.CURRENT_CHIP_AMOUNT,currentAmount).apply();
    }

    @Override
    public String getCurrentAmout() {
        return mSharedPreferences.getString(RxLibConstants.CURRENT_CHIP_AMOUNT,"2");
    }

    @Override
    public void setIsCustomChip(boolean isCustomChip) {
        mSharedPreferences.edit().putBoolean(RxLibConstants.IS_CUSTOM_CHIP,isCustomChip).apply();
    }

    @Override
    public boolean getIsCustomChip() {
        return mSharedPreferences.getBoolean(RxLibConstants.IS_CUSTOM_CHIP,false);
    }

    @Override
    public void setDeviceCode(String deviceCode) {
        mSharedPreferences.edit().putString(RxLibConstants.DEVICE_CODE,deviceCode).apply();
    }

    @Override
    public String getDeviceCode() {
        return mSharedPreferences.getString(RxLibConstants.DEVICE_CODE,"");
    }

    @Override
    public void setChoumaData(String choumaData) {
        mSharedPreferences.edit().putString(RxLibConstants.CHOUMADATA,choumaData).apply();
    }

    @Override
    public String getChouma() {
        return mSharedPreferences.getString(RxLibConstants.CHOUMADATA,"");
    }

    @Override
    public void setLiveSearchCache(String liveSearchCache) {
        mSharedPreferences.edit().putString(RxLibConstants.LIVE_SEARCH_CACHE,liveSearchCache).apply();
    }

    @Override
    public String getLiveSearchCache() {
        return mSharedPreferences.getString(RxLibConstants.LIVE_SEARCH_CACHE,"");
    }

    @Override
    public void setRongKey(String rongKey) {
        mSharedPreferences.edit().putString(RxLibConstants.RONG_KEY,rongKey).apply();
    }

    @Override
    public String getRongKey() {
        return mSharedPreferences.getString(RxLibConstants.RONG_KEY,"");
    }

    @Override
    public void setRongToken(String token) {
        mSharedPreferences.edit().putString(RxLibConstants.RONG_TOKEN,token).apply();
    }

    @Override
    public String getRongToken() {
        return mSharedPreferences.getString(RxLibConstants.RONG_TOKEN,"");
    }

    @Override
    public void setRoomId(String roomId) {
        mSharedPreferences.edit().putString(RxLibConstants.ROOM_ID,roomId).apply();
    }

    @Override
    public String getRoomId() {
        return mSharedPreferences.getString(RxLibConstants.ROOM_ID,"");
    }
    @Override
    public void setNewBaseUrl(String newBaseUrl) {
        mSharedPreferences.edit().putString(RxLibConstants.NEW_BASE_URL,newBaseUrl).apply();
    }

    @Override
    public String getNewBaseUrl() {
        String url = mSharedPreferences.getString(RxLibConstants.NEW_BASE_URL, "");
        if(!TextUtils.isEmpty(url)&&!url.endsWith("/")){
            url +="/";
        }
        return url;
    }

    @Override
    public void setUrlList(String urlList) {
        mSharedPreferences.edit().putString(RxLibConstants.URL_LIST,urlList).apply();
    }

    @Override
    public String getUrlList() {
        return mSharedPreferences.getString(RxLibConstants.URL_LIST,"");
    }

    @Override
    public void setPlatform(String platform) {
        mSharedPreferences.edit().putString(RxLibConstants.PLATFORM,platform).apply();
    }

    @Override
    public String getPlatform() {
        return mSharedPreferences.getString(RxLibConstants.PLATFORM,"");
    }
}
