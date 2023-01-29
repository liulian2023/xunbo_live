package com.zz.live.bean;

import java.io.Serializable;
import java.util.List;

public class WithdrawNoteEntity {


    /**
     * code : 0
     * msg : null
     * data : {"records":[{"createTime":"2020-05-27 16:51:22","status":2,"amount":5,"bankName":"渣渣银行","cardNumber":"5425635862482452","realName":"gff"},{"createTime":"2020-05-27 16:46:50","status":0,"amount":5,"bankName":"渣渣银行","cardNumber":"5425635862482452","realName":"gff"}],"total":2,"size":8,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private int code;
    private Object msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * records : [{"createTime":"2020-05-27 16:51:22","status":2,"amount":5,"bankName":"渣渣银行","cardNumber":"5425635862482452","realName":"gff"},{"createTime":"2020-05-27 16:46:50","status":0,"amount":5,"bankName":"渣渣银行","cardNumber":"5425635862482452","realName":"gff"}]
         * total : 2
         * size : 8
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean implements Serializable {
            /**
             * createTime : 2020-05-27 16:51:22
             * status : 2
             * amount : 5.0
             * bankName : 渣渣银行
             * cardNumber : 5425635862482452
             * realName : gff
             */
            private String createTime;
            private int status;
            private String amount;
            private String bankName;
            private String cardNumber;
            private String realName;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
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

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }
        }
    }
}
