package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.io.Serializable;
import java.util.List;

public class HomeClassfyEntity extends CommonModel {

    /**
     * code : 0
     * msg : null
     * data : [{"createUser":"admin","createTime":"2020-04-26 14:08:09","createIp":"0:0:0:0:0:0:0:1","updateUser":null,"updateTime":"2020-04-26 14:08:09","updateIp":null,"version":null,"id":"1254291154032500737","name":"性感","nameCode":"xinggan","imgUrl":"string","description":"性感","delFlag":0},{"createUser":"admin","createTime":"2020-04-26 14:08:33","createIp":"0:0:0:0:0:0:0:1","updateUser":null,"updateTime":"2020-04-26 14:08:32","updateIp":null,"version":null,"id":"1254291251055140866","name":"激情","nameCode":"jiqing","imgUrl":"string","description":"激情","delFlag":0},{"createUser":"admin","createTime":"2020-04-26 14:08:45","createIp":"0:0:0:0:0:0:0:1","updateUser":null,"updateTime":"2020-04-26 14:08:45","updateIp":null,"version":null,"id":"1254291303542661122","name":"模特","nameCode":"mote","imgUrl":"string","description":"模特","delFlag":0}]
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

    public static class DataBean extends CommonModel implements Serializable {
        /**
         * createUser : admin
         * createTime : 2020-04-26 14:08:09
         * createIp : 0:0:0:0:0:0:0:1
         * updateUser : null
         * updateTime : 2020-04-26 14:08:09
         * updateIp : null
         * version : null
         * id : 1254291154032500737
         * name : 性感
         * nameCode : xinggan
         * imgUrl : string
         * description : 性感
         * delFlag : 0
         */

        private String createUser;
        private String createTime;
        private String createIp;
        private String updateUser;
        private String updateTime;
        private String updateIp;
        private String version;
        private String id;
        private String name;
        private String nameCode;
        private String imgUrl;
        private String description;
        private int delFlag;

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateIp() {
            return createIp;
        }

        public void setCreateIp(String createIp) {
            this.createIp = createIp;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateIp() {
            return updateIp;
        }

        public void setUpdateIp(String updateIp) {
            this.updateIp = updateIp;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameCode() {
            return nameCode;
        }

        public void setNameCode(String nameCode) {
            this.nameCode = nameCode;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
