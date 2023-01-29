package com.zz.live.bean;


import java.util.List;

public class AppUpdateEntity {


    /**
     * code : 0
     * msg : null
     * data : [{"id":1,"type":1,"versionCode":102,"versionName":"v1.02","downloadUrl":"http://148.66.6.20:9000/xblive/20200624/382158a29b08458a81f00eba4e86cb3c.apk","webUrl":"xbios666.cn","tips":"更新版本，祝您中奖！","mustUpdate":false},{"id":2,"type":2,"versionCode":102,"versionName":"v1.02","downloadUrl":"http://148.66.6.20:9000/下载地址","webUrl":null,"tips":"IOS下载","mustUpdate":false}]
     */

    private int code;
    private Object msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : 1
         * versionCode : 102
         * versionName : v1.02
         * downloadUrl : http://148.66.6.20:9000/xblive/20200624/382158a29b08458a81f00eba4e86cb3c.apk
         * webUrl : xbios666.cn
         * tips : 更新版本，祝您中奖！
         * mustUpdate : false
         */

        private int id;
        private int type;
        private int versionCode;
        private String versionName;
        private String downloadUrl;
        private String webUrl;
        private String tips;
        private boolean mustUpdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public boolean isMustUpdate() {
            return mustUpdate;
        }

        public void setMustUpdate(boolean mustUpdate) {
            this.mustUpdate = mustUpdate;
        }
    }
}
