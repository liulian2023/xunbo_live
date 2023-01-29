package com.zz.live.bean;

public class LiveParamEntity {


    /**
     * code : 0
     * msg : null
     * data : {"UMPUSH_ANDROID":"O9OJ2QCh49Yjh/YSkQZJRg==","MEIHU":"L8IU099sFQgc/x8ZdY524h+xiPYicNIVxdnxUTLFpS2aHFq+QOi6/nY8MpnvwXF+","UM_IOS":"3G/w51JC2aKtkdO7v1X8PwaTQ3n0WPjFxsAIfTsGOho=","UM_ANDROID":"VJoBznZLhvuQkOAeZzmo1DK05Z2VtDLQt+/EXxcEpFs=","secretKey":"i2cNeBMCcqUlye8UrDcpB7gCt648MaJsqGTrRDW86jXjj1vr5jyiTovoc5JsxYXK","RONGCLOUD":"hj+XFATPjyx+kMahoY6kmg==","secretId":"UG/QQOn8+HxLz9PGrp67puz2MB8uYiUjb4E2marIIxOrnPd9R3BJZMppV024mXlZF7a/wQABtBrP\n0mmH+ilZQR3/8Hub7OSSnLVPzHBcrwvy60DJsOg0M0SZSWteAVUK"}
     */

    private int code;
    private Object msg;
    /**
     * UMPUSH_ANDROID : O9OJ2QCh49Yjh/YSkQZJRg==
     * MEIHU : L8IU099sFQgc/x8ZdY524h+xiPYicNIVxdnxUTLFpS2aHFq+QOi6/nY8MpnvwXF+
     * UM_IOS : 3G/w51JC2aKtkdO7v1X8PwaTQ3n0WPjFxsAIfTsGOho=
     * UM_ANDROID : VJoBznZLhvuQkOAeZzmo1DK05Z2VtDLQt+/EXxcEpFs=
     * secretKey : i2cNeBMCcqUlye8UrDcpB7gCt648MaJsqGTrRDW86jXjj1vr5jyiTovoc5JsxYXK
     * RONGCLOUD : hj+XFATPjyx+kMahoY6kmg==
     * secretId : UG/QQOn8+HxLz9PGrp67puz2MB8uYiUjb4E2marIIxOrnPd9R3BJZMppV024mXlZF7a/wQABtBrP
     0mmH+ilZQR3/8Hub7OSSnLVPzHBcrwvy60DJsOg0M0SZSWteAVUK
     */

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
        private String UMPUSH_ANDROID;
        private String MEIHU;
        private String UM_IOS;
        private String UM_ANDROID;
        private String secretKey;
        private String RONGCLOUD;
        private String secretId;

        public String getUMPUSH_ANDROID() {
            return UMPUSH_ANDROID;
        }

        public void setUMPUSH_ANDROID(String UMPUSH_ANDROID) {
            this.UMPUSH_ANDROID = UMPUSH_ANDROID;
        }

        public String getMEIHU() {
            return MEIHU;
        }

        public void setMEIHU(String MEIHU) {
            this.MEIHU = MEIHU;
        }

        public String getUM_IOS() {
            return UM_IOS;
        }

        public void setUM_IOS(String UM_IOS) {
            this.UM_IOS = UM_IOS;
        }

        public String getUM_ANDROID() {
            return UM_ANDROID;
        }

        public void setUM_ANDROID(String UM_ANDROID) {
            this.UM_ANDROID = UM_ANDROID;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getRONGCLOUD() {
            return RONGCLOUD;
        }

        public void setRONGCLOUD(String RONGCLOUD) {
            this.RONGCLOUD = RONGCLOUD;
        }

        public String getSecretId() {
            return secretId;
        }

        public void setSecretId(String secretId) {
            this.secretId = secretId;
        }
    }
}
