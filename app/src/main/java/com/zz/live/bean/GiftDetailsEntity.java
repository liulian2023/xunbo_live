package com.zz.live.bean;

import java.util.List;

public class GiftDetailsEntity {

    /**
     * code : 0
     * msg : null
     * data : {"records":[{"platformCode":"xunbo","giverName":"到不知道","giftAmount":10,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":10,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"}],"total":5,"size":15,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"platformCode":"xunbo","giverName":"到不知道","giftAmount":10,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":10,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"},{"platformCode":"xunbo","giverName":"到不知道","giftAmount":1,"giftCount":1,"giftName":"菊花开"}]
         * total : 5
         * size : 15
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

        public static class RecordsBean {
            /**
             * platformCode : xunbo
             * giverName : 到不知道
             * giftAmount : 10.0
             * giftCount : 1
             * giftName : 菊花开
             */

            private String platformCode;
            private String giverName;
            private String giftAmount;
            private int giftCount;
            private String giftName;

            public String getPlatformCode() {
                return platformCode;
            }

            public void setPlatformCode(String platformCode) {
                this.platformCode = platformCode;
            }

            public String getGiverName() {
                return giverName;
            }

            public void setGiverName(String giverName) {
                this.giverName = giverName;
            }

            public String getGiftAmount() {
                return giftAmount;
            }

            public void setGiftAmount(String giftAmount) {
                this.giftAmount = giftAmount;
            }

            public int getGiftCount() {
                return giftCount;
            }

            public void setGiftCount(int giftCount) {
                this.giftCount = giftCount;
            }

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }
        }
    }
}
