package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.util.List;

public class CommonProblemModel extends CommonModel {


    /**
     * code : 0
     * msg : null
     * data : [{"type":0,"explain":"程序bug"},{"type":1,"explain":"功能建议"},{"type":2,"explain":"投诉"},{"type":3,"explain":"其他问题"}]
     */

    private int code;
    private Object msg;
    /**
     * type : 0
     * explain : 程序bug
     */

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

    public static class DataBean extends CommonModel {
        private int type;
        private String explain;
        private int status=0;
        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
