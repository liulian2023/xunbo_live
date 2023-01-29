package com.cambodia.zhanbang.rxhttp.sp;

import com.cambodia.zhanbang.rxhttp.net.model.UserEntity;

/**
 * created  by ganzhe on 2019/9/26.
 */
public interface SharedPreferenceHelper {
    /**
     * 设置登录状态
     *
     * @param loginStatus
     */
    void setLoginStatus(boolean loginStatus);

    /**
     * 获取登录状态
     * @return
     */
    boolean getLoginStatus();

    /**
     * 设置phonenum
     * @param strPhone
     */
    void setPhoneNum(String strPhone);

    /**
     * 获取phoneNum
     */
    String getPhoneNum();

    /**
     * 设置 获取token
     * @param token
     */
    void setToken(String token);

    String getToken();

    //消息通知开关
    void setMsgSwitch(boolean msgSwitchStatus);
    boolean getMsgSwitch();
    //声音提示开关
    void setVoiceSwitch(boolean voiceSwitchStatus);
    boolean getVoiceSwitch();
    /**
     * 设置获取  token_refresh
     * @param token_refresh
     */
    void setTokenRefresh(String token_refresh);

    String getTokenRefresh();

    void putObject(String key,Object object);
    Object getObject(String key,Object object);

    void setUserInfo(String  userinfo);
    String getUserInfo();

    void setShiJianCha(Long  shiJianCha);
    long getShiJianCha();

    void setUserId(long userId);
    long getUserId();

    void setImageDomin(String imageDomin);
    String getImageDomin();

    //appName
    void  setAppName(String appName);
    String getAppName();


    //前端域名
    void setFrontDomain(String url);
    String getFrontDomain();

    void ClearSp();
    //搜索记录
    void  setSearchCache(String searchCache);
    String getSearchCache();

    /*
系统参数
 */
    void setSysParameter(String sysParameter);
    String getSysParameter();

    /*
输入的自定义金额
*/
    void setCustomAmout(String customAmount);
    String getCustomAmout();
/*
上次选中的金额
 */
    void setCurrentAmout(String currentAmount);
    String getCurrentAmout();

    void setIsCustomChip(boolean isCustomChip);
    boolean getIsCustomChip();

    void setDeviceCode (String deviceCode);
    String getDeviceCode();

    /**
     * 筹码json 2  str  存储
      * @param choumaData
     */
    void setChoumaData(String choumaData);
    String getChouma();

    void setLiveSearchCache(String liveSearchCache);
    String getLiveSearchCache();

    void setRongKey(String rongKey);
    String getRongKey();

    void setRongToken(String token);
    String getRongToken();
    void setRoomId(String rooId);
    String getRoomId();
    //切换的新域名
    void setNewBaseUrl(String newBaseUrl);
    String getNewBaseUrl();
    //域名列表
    void setUrlList(String urlList);
    String getUrlList();
    //平台id
    void setPlatform(String urlList);
    String getPlatform();
}
