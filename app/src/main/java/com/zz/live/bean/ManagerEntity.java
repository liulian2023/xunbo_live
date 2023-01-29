package com.zz.live.bean;

import java.util.List;

public class ManagerEntity  {
    /**
     * code : 0
     * msg : 查询成功
     * data : [{"createUser":"ll11111","createTime":"2020-07-30 20:07:53","createIp":"175.176.41.35","updateUser":null,"updateTime":null,"updateIp":null,"version":0,"id":"1288808530574327810","anchorId":"1277550240594923521","rcUserId":"xunbo_1141","platformCode":"xunbo","isManager":1,"chatroomId":"1277550240594923521c58987de9e334b1ca596e629a3978756","nickName":"D9v6ZM3b","image":""}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * createUser : ll11111
         * createTime : 2020-07-30 20:07:53
         * createIp : 175.176.41.35
         * updateUser : null
         * updateTime : null
         * updateIp : null
         * version : 0
         * id : 1288808530574327810
         * anchorId : 1277550240594923521
         * rcUserId : xunbo_1141
         * platformCode : xunbo
         * isManager : 1
         * chatroomId : 1277550240594923521c58987de9e334b1ca596e629a3978756
         * nickName : D9v6ZM3b
         * image : 
         */

        private String createUser;
        private String createTime;
        private String createIp;
        private String updateUser;
        private String updateTime;
        private String updateIp;
        private String version;
        private String id;
        private String anchorId;
        private String rcUserId;
        private String platformCode;
        private String isManager;
        private String chatroomId;
        private String nickName;
        private String image;

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

        public String getAnchorId() {
            return anchorId;
        }

        public void setAnchorId(String anchorId) {
            this.anchorId = anchorId;
        }

        public String getRcUserId() {
            return rcUserId;
        }

        public void setRcUserId(String rcUserId) {
            this.rcUserId = rcUserId;
        }

        public String getPlatformCode() {
            return platformCode;
        }

        public void setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
        }

        public String getIsManager() {
            return isManager;
        }

        public void setIsManager(String isManager) {
            this.isManager = isManager;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
