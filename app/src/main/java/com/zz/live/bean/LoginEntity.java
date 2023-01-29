package com.zz.live.bean;

public class LoginEntity {

    /**
     * code : 0
     * msg : null
     * data : {"access_token":"9411e860068640c38fe32fb2ac9253e6","id":1334027466353192962,"username":"jia002"}
     */

    private int code;
    private Object msg;
    /**
     * access_token : 9411e860068640c38fe32fb2ac9253e6
     * id : 1334027466353192962
     * username : jia002
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
        private String access_token;
        private long id;
        private String username;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
