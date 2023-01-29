package com.zz.live.bean;

public class UserMoneyEntity {


    /**
     * code : 0
     * msg : 查询成功
     * data : {"balance":45,"giftAmount":50,"integral":0,"extraAmount":0,"giftCommission":0}
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
         * balance : 45.0
         * giftAmount : 50.0
         * integral : 0
         * extraAmount : 0.0
         * giftCommission : 0.0
         */

        private String balance;
        private String giftAmount;
        private int integral;
        private String extraAmount;
        private String giftCommission;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getExtraAmount() {
            return extraAmount;
        }

        public void setExtraAmount(String extraAmount) {
            this.extraAmount = extraAmount;
        }

        public String getGiftCommission() {
            return giftCommission;
        }

        public void setGiftCommission(String giftCommission) {
            this.giftCommission = giftCommission;
        }
    }
}
