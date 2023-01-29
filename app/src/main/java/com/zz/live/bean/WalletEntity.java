package com.zz.live.bean;

public class WalletEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"balance":0,"tmSubscribe":0,"lmSubscribe":0,"tmGiftAmount":0,"lmGiftAmount":0,"tmCashBack":0,"lmCashBack":0,"tdFans":0,"ytdFans":0,"tdSubscribe":0,"ytdSubscribe":0,"tdGiftAmount":0,"ytdGiftAmount":0,"tdCashBack":0,"ytdCashBack":0,"tdOther":0,"ytdOther":0}
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

    public static class DataBean {
        /**
         * balance : 0.0
         * tmSubscribe : 0.0
         * lmSubscribe : 0.0
         * tmGiftAmount : 0.0
         * lmGiftAmount : 0.0
         * tmCashBack : 0.0
         * lmCashBack : 0.0
         * tdFans : 0
         * ytdFans : 0
         * tdSubscribe : 0.0
         * ytdSubscribe : 0.0
         * tdGiftAmount : 0.0
         * ytdGiftAmount : 0.0
         * tdCashBack : 0.0
         * ytdCashBack : 0.0
         * tdOther : 0.0
         * ytdOther : 0.0
         */

        private String balance;
        private String tmSubscribe;
        private String lmSubscribe;
        private String tmGiftAmount;
        private String lmGiftAmount;
        private String tmCashBack;
        private String lmCashBack;
        private int tdFans;
        private int ytdFans;
        private String tdSubscribe;
        private String ytdSubscribe;
        private String tdGiftAmount;
        private String ytdGiftAmount;
        private String tdCashBack;
        private String ytdCashBack;
        private String tdOther;
        private String ytdOther;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTmSubscribe() {
            return tmSubscribe;
        }

        public void setTmSubscribe(String tmSubscribe) {
            this.tmSubscribe = tmSubscribe;
        }

        public String getLmSubscribe() {
            return lmSubscribe;
        }

        public void setLmSubscribe(String lmSubscribe) {
            this.lmSubscribe = lmSubscribe;
        }

        public String getTmGiftAmount() {
            return tmGiftAmount;
        }

        public void setTmGiftAmount(String tmGiftAmount) {
            this.tmGiftAmount = tmGiftAmount;
        }

        public String getLmGiftAmount() {
            return lmGiftAmount;
        }

        public void setLmGiftAmount(String lmGiftAmount) {
            this.lmGiftAmount = lmGiftAmount;
        }

        public String getTmCashBack() {
            return tmCashBack;
        }

        public void setTmCashBack(String tmCashBack) {
            this.tmCashBack = tmCashBack;
        }

        public String getLmCashBack() {
            return lmCashBack;
        }

        public void setLmCashBack(String lmCashBack) {
            this.lmCashBack = lmCashBack;
        }

        public int getTdFans() {
            return tdFans;
        }

        public void setTdFans(int tdFans) {
            this.tdFans = tdFans;
        }

        public int getYtdFans() {
            return ytdFans;
        }

        public void setYtdFans(int ytdFans) {
            this.ytdFans = ytdFans;
        }

        public String getTdSubscribe() {
            return tdSubscribe;
        }

        public void setTdSubscribe(String tdSubscribe) {
            this.tdSubscribe = tdSubscribe;
        }

        public String getYtdSubscribe() {
            return ytdSubscribe;
        }

        public void setYtdSubscribe(String ytdSubscribe) {
            this.ytdSubscribe = ytdSubscribe;
        }

        public String getTdGiftAmount() {
            return tdGiftAmount;
        }

        public void setTdGiftAmount(String tdGiftAmount) {
            this.tdGiftAmount = tdGiftAmount;
        }

        public String getYtdGiftAmount() {
            return ytdGiftAmount;
        }

        public void setYtdGiftAmount(String ytdGiftAmount) {
            this.ytdGiftAmount = ytdGiftAmount;
        }

        public String getTdCashBack() {
            return tdCashBack;
        }

        public void setTdCashBack(String tdCashBack) {
            this.tdCashBack = tdCashBack;
        }

        public String getYtdCashBack() {
            return ytdCashBack;
        }

        public void setYtdCashBack(String ytdCashBack) {
            this.ytdCashBack = ytdCashBack;
        }

        public String getTdOther() {
            return tdOther;
        }

        public void setTdOther(String tdOther) {
            this.tdOther = tdOther;
        }

        public String getYtdOther() {
            return ytdOther;
        }

        public void setYtdOther(String ytdOther) {
            this.ytdOther = ytdOther;
        }
    }
}
