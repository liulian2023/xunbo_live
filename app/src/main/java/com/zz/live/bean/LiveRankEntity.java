package com.zz.live.bean;

import java.util.List;

public class LiveRankEntity {


    /**
     * code : 0
     * msg : null
     * data : {"records":[{"platformMemberId":"10262","giverUserImage":"http://10.6.46.34:8185/upload/images/20190911/1568202507495.jpg","giverName":"萌新757G1E86","totalAmount":121}],"total":1,"size":50,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private String code;
    private Object msg;
    /**
     * records : [{"platformMemberId":"10262","giverUserImage":"http://10.6.46.34:8185/upload/images/20190911/1568202507495.jpg","giverName":"萌新757G1E86","totalAmount":121}]
     * total : 1
     * size : 50
     * current : 1
     * orders : []
     * hitCount : false
     * searchCount : true
     * pages : 1
     */

    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
        private String total;
        private String size;
        private String current;
        private boolean hitCount;
        private boolean searchCount;
        private String pages;
        /**
         * platformMemberId : 10262
         * giverUserImage : http://10.6.46.34:8185/upload/images/20190911/1568202507495.jpg
         * giverName : 萌新757G1E86
         * totalAmount : 121.0
         */

        private List<RecordsBean> records;
        private List<?> orders;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
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

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
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
            private String platformMemberId;
            private String giverUserImage;
            private String giverName;
            private String totalAmount;

            public String getPlatformMemberId() {
                return platformMemberId;
            }

            public void setPlatformMemberId(String platformMemberId) {
                this.platformMemberId = platformMemberId;
            }

            public String getGiverUserImage() {
                return giverUserImage;
            }

            public void setGiverUserImage(String giverUserImage) {
                this.giverUserImage = giverUserImage;
            }

            public String getGiverName() {
                return giverName;
            }

            public void setGiverName(String giverName) {
                this.giverName = giverName;
            }

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }
        }
    }
}
