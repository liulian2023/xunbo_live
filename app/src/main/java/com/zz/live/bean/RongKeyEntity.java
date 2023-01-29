package com.zz.live.bean;

public class RongKeyEntity {

    /**
     * code : 0
     * msg : null
     * data : {"secretId":"LWmHe7Qr7uxfHVYoLXclkw==","secretKey":"uTugGGgRYd/nWMjx1wB79w==","platformCode":"7NyITX89GfFKIsySZQrmBA==","sign":null}
     */

    private int code;
    private Object msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * secretId : LWmHe7Qr7uxfHVYoLXclkw==
         * secretKey : uTugGGgRYd/nWMjx1wB79w==
         * platformCode : 7NyITX89GfFKIsySZQrmBA==
         * sign : null
         */

        private String secretId;
        private String secretKey;
        private String platformCode;
        private Object sign;

        public String getSecretId() {
            return secretId;
        }

        public void setSecretId(String secretId) {
            this.secretId = secretId;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getPlatformCode() {
            return platformCode;
        }

        public void setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }
    }
}
