package com.zz.live.bean;

import java.io.Serializable;
import java.util.List;

public class BankcardEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : [{"id":"1265476687338967041","bankName":"渣渣银行","cardNumber":6568653535686,"branchName":"一亿个","realName":"几个"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 1265476687338967041
         * bankName : 渣渣银行
         * cardNumber : 6568653535686
         * branchName : 一亿个
         * realName : 几个
         */

        private int status=0;//选中状态 0 未选中 1 选中
        private String id;
        private String bankName;
        private String cardNumber;
        private String branchName;
        private String realName;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

    }
}
