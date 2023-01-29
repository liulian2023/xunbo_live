package com.zz.live.bean;

public class ChangeRoomTypeEntity  {

    /**
     * code : 0
     * msg : 操作成功
     * data : {"amount":55,"type":2,"streamName":"1277550240594923521_1595050895"}
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
         * amount : 55
         * type : 2
         * streamName : 1277550240594923521_1595050895
         */

        private String amount;
        private int type;
        private String streamName;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStreamName() {
            return streamName;
        }

        public void setStreamName(String streamName) {
            this.streamName = streamName;
        }
    }
}
