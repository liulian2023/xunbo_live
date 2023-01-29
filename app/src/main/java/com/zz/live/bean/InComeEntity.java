package com.zz.live.bean;

import java.util.List;

public class InComeEntity {

    /**
     * code : 0
     * msg : null
     * data : {"records":[{"createTime":"2020-06-12 10:24:14","type":0,"transactionType":1,"amount":20.4,"balance":1625.2,"username":null,"remark":"new001打赏您1个口红"},{"createTime":"2020-06-12 10:24:11","type":0,"transactionType":1,"amount":66.6,"balance":1604.8,"username":null,"remark":"new001打赏您1个礼炮"},{"createTime":"2020-06-12 10:24:09","type":0,"transactionType":1,"amount":199.8,"balance":1538.2,"username":null,"remark":"new001打赏您1个666"},{"createTime":"2020-06-12 10:24:05","type":0,"transactionType":1,"amount":206.4,"balance":1338.4,"username":null,"remark":"new001打赏您1个天马"},{"createTime":"2020-06-12 10:24:03","type":0,"transactionType":1,"amount":206.4,"balance":1132,"username":null,"remark":"new001打赏您1个天马"},{"createTime":"2020-06-12 10:24:01","type":0,"transactionType":1,"amount":234,"balance":925.6,"username":null,"remark":"new001打赏您1个邮轮"},{"createTime":"2020-06-12 10:24:00","type":0,"transactionType":1,"amount":264,"balance":691.6,"username":null,"remark":"new001打赏您1个直升机"},{"createTime":"2020-06-12 10:23:55","type":0,"transactionType":1,"amount":0.3,"balance":427.6,"username":null,"remark":"new001打赏您1个棒棒糖"},{"createTime":"2020-06-11 16:59:41","type":1,"transactionType":2,"amount":50,"balance":427.3,"username":null,"remark":null},{"createTime":"2020-06-11 16:40:40","type":0,"transactionType":1,"amount":264,"balance":477.3,"username":null,"remark":"new001打赏您1个直升机"}],"total":23,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":3}
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
         * records : [{"createTime":"2020-06-12 10:24:14","type":0,"transactionType":1,"amount":20.4,"balance":1625.2,"username":null,"remark":"new001打赏您1个口红"},{"createTime":"2020-06-12 10:24:11","type":0,"transactionType":1,"amount":66.6,"balance":1604.8,"username":null,"remark":"new001打赏您1个礼炮"},{"createTime":"2020-06-12 10:24:09","type":0,"transactionType":1,"amount":199.8,"balance":1538.2,"username":null,"remark":"new001打赏您1个666"},{"createTime":"2020-06-12 10:24:05","type":0,"transactionType":1,"amount":206.4,"balance":1338.4,"username":null,"remark":"new001打赏您1个天马"},{"createTime":"2020-06-12 10:24:03","type":0,"transactionType":1,"amount":206.4,"balance":1132,"username":null,"remark":"new001打赏您1个天马"},{"createTime":"2020-06-12 10:24:01","type":0,"transactionType":1,"amount":234,"balance":925.6,"username":null,"remark":"new001打赏您1个邮轮"},{"createTime":"2020-06-12 10:24:00","type":0,"transactionType":1,"amount":264,"balance":691.6,"username":null,"remark":"new001打赏您1个直升机"},{"createTime":"2020-06-12 10:23:55","type":0,"transactionType":1,"amount":0.3,"balance":427.6,"username":null,"remark":"new001打赏您1个棒棒糖"},{"createTime":"2020-06-11 16:59:41","type":1,"transactionType":2,"amount":50,"balance":427.3,"username":null,"remark":null},{"createTime":"2020-06-11 16:40:40","type":0,"transactionType":1,"amount":264,"balance":477.3,"username":null,"remark":"new001打赏您1个直升机"}]
         * total : 23
         * size : 10
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 3
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

        public static class RecordsBean {
            /**
             * createTime : 2020-06-12 10:24:14
             * type : 0 收支类型0收入1支出
             * transactionType : 1 类型 1礼物 2提现 3订阅 4返现 5其他
             * amount : 20.4
             * balance : 1625.2
             * username : null
             * remark : new001打赏您1个口红
             */

            private String createTime;
            private int type;
            private int transactionType;
            private String amount;
            private String balance;
            private String username;
            private String remark;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getTransactionType() {
                return transactionType;
            }

            public void setTransactionType(int transactionType) {
                this.transactionType = transactionType;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
