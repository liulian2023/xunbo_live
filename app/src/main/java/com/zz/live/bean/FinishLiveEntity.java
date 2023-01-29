package com.zz.live.bean;

import java.io.Serializable;

public class FinishLiveEntity {

    /**
     * code : 0
     * msg : 直播已结束
     * data : {"liveTime":-11,"totalAmount":null,"viewers":null}
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
         * liveTime : -11
         * totalAmount : null
         * viewers : null
         */

        private String liveTime;
        private String totalAmount;
        private String viewers;
        private String giftAmount;
        private String subAmount;

        public String getSubAmount() {
            return subAmount;
        }

        public void setSubAmount(String subAmount) {
            this.subAmount = subAmount;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public String getLiveTime() {
            return liveTime;
        }

        public void setLiveTime(String liveTime) {
            this.liveTime = liveTime;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getViewers() {
            return viewers;
        }

        public void setViewers(String viewers) {
            this.viewers = viewers;
        }
    }
}
