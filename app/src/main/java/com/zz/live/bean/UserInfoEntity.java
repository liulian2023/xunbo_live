package com.zz.live.bean;

import java.io.Serializable;

public class UserInfoEntity implements Serializable {

    /**
     * code : 0
     * msg : null
     * data : {"id":"1264452153443962882","username":"ll1111","accountId":"91038600","nickname":"听听歌姑姑姑父","image":"xblive/20200528/1302d0d135204e598760d5f497ea486a.jpg","phone":"12643467437","sex":0,"area":null,"fansNumber":0,"roomId":"R5XOz0ycdUDcS8pZPWewrVaNNxs/RLgNAqIzpZC+ahAQ2rJJhcQXZaWXv4EalJWYqKQWH8Y2X75C\nWTCGyslQgA==","rongCloudKey":"uTugGGgRYd/nWMjx1wB79w==","userToken":"rswYvLsUDVSYx9Dwt4IFD61TiH2gwSklu0dqcR/5z0zqCuJskc6zvAt95tRkFg413Elxc9pFjNr9\nQxX3IqGTdwBBvIB058o7hCJkjUV+yTz0P6pNcUkhMuNuElsVCSWjB2iFunyKhzsaCmHuE43OQw==","prohibitPlayTime":null,"userType":3,"userStatus":1,"verifyPictures":"xblive/20200528/150fe65354aa41a484465b018a4987b3.jpg,xblive/20200528/785dec0547ff4441a44a08095f06d386.jpg,xblive/20200528/b707f2212e9f411da364a7735b4abfbb.jpg","lockFlag":"0","password":"$2a$10$6JLD6Kqp4sEt5v2iw8v5xeVgOTv4HKBXjblZDxxOu5j4bKRglpi5u","identityCard":"394783267363263836","realName":"hgff","nicknameCount":0}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1264452153443962882
         * username : ll1111
         * accountId : 91038600
         * nickname : 听听歌姑姑姑父
         * image : xblive/20200528/1302d0d135204e598760d5f497ea486a.jpg
         * phone : 12643467437
         * sex : 0
         * area : null
         * fansNumber : 0
         * roomId : R5XOz0ycdUDcS8pZPWewrVaNNxs/RLgNAqIzpZC+ahAQ2rJJhcQXZaWXv4EalJWYqKQWH8Y2X75C
         WTCGyslQgA==
         * rongCloudKey : uTugGGgRYd/nWMjx1wB79w==
         * userToken : rswYvLsUDVSYx9Dwt4IFD61TiH2gwSklu0dqcR/5z0zqCuJskc6zvAt95tRkFg413Elxc9pFjNr9
         QxX3IqGTdwBBvIB058o7hCJkjUV+yTz0P6pNcUkhMuNuElsVCSWjB2iFunyKhzsaCmHuE43OQw==
         * prohibitPlayTime : null
         * userType : 3
         * userStatus : 1
         * verifyPictures : xblive/20200528/150fe65354aa41a484465b018a4987b3.jpg,xblive/20200528/785dec0547ff4441a44a08095f06d386.jpg,xblive/20200528/b707f2212e9f411da364a7735b4abfbb.jpg
         * lockFlag : 0
         * password : $2a$10$6JLD6Kqp4sEt5v2iw8v5xeVgOTv4HKBXjblZDxxOu5j4bKRglpi5u
         * identityCard : 394783267363263836
         * realName : hgff
         * nicknameCount : 0
         * callingCard
         */
        /*
        /用户类型 1族长2主播3家族主播 （族长只显示 代理中心 主播和家族主播显示 主播管家&开播按钮&排行榜 ，主播能提现，家族主播不能提现）
         */
        private long id;
        private String username;
        private String accountId;
        private String nickname;
        private String image;
        private String phone;
        private int sex;
        private String area;
        private int fansNumber;
        private String roomId;
        private String rongCloudKey;
        private String userToken;
        private String prohibitPlayTime;
        private int userType;// 1族长2主播3家族主播
        private int userStatus;
        private String verifyPictures;
        private String lockFlag;
        private String password;
        private String identityCard;
        private String realName;
        private int nicknameCount;
        private String memberRake;
        private String callingCard;
        private  String forbidCallingCard = "";//是否禁止推送名片 0 否 1是

        public String getForbidCallingCard() {
            return forbidCallingCard;
        }

        public void setForbidCallingCard(String forbidCallingCard) {
            this.forbidCallingCard = forbidCallingCard;
        }

        public String getCallingCard() {
            return callingCard;
        }

        public void setCallingCard(String callingCard) {
            this.callingCard = callingCard;
        }

        public String getMemberRake() {
            return memberRake;
        }

        public void setMemberRake(String memberRake) {
            this.memberRake = memberRake;
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

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getFansNumber() {
            return fansNumber;
        }

        public void setFansNumber(int fansNumber) {
            this.fansNumber = fansNumber;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRongCloudKey() {
            return rongCloudKey;
        }

        public void setRongCloudKey(String rongCloudKey) {
            this.rongCloudKey = rongCloudKey;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getProhibitPlayTime() {
            return prohibitPlayTime;
        }

        public void setProhibitPlayTime(String prohibitPlayTime) {
            this.prohibitPlayTime = prohibitPlayTime;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
            this.userStatus = userStatus;
        }

        public String getVerifyPictures() {
            return verifyPictures;
        }

        public void setVerifyPictures(String verifyPictures) {
            this.verifyPictures = verifyPictures;
        }

        public String getLockFlag() {
            return lockFlag;
        }

        public void setLockFlag(String lockFlag) {
            this.lockFlag = lockFlag;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getNicknameCount() {
            return nicknameCount;
        }

        public void setNicknameCount(int nicknameCount) {
            this.nicknameCount = nicknameCount;
        }
    }
}
