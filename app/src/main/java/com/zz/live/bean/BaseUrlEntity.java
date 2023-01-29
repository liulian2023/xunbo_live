package com.zz.live.bean;

import com.zz.live.utils.AESUtil;

import java.util.List;

public class BaseUrlEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : [{"id":"1279359596995026946","domain":"http://148.66.6.18:9999"},{"id":"1279359815233052674","domain":"http://148.66.6.19:9999"},{"id":"1279360059349934081","domain":"http://148.66.6.20:9999"},{"id":"1279360106321944577","domain":"http://148.66.6.21:9999"},{"id":"1279360141847699457","domain":"http://148.66.6.22:9999"},{"id":"1279360187183931394","domain":"http://148.66.6.23:9999"},{"id":"1279360472312717314","domain":"http://148.66.6.24:9999"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
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
         * id : 1279359596995026946
         * domain : http://148.66.6.18:9999
         */

        private String id;
        private String domain;
        boolean isCheck=false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            try {
                this.domain = AESUtil.decrypt(domain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
