package com.zz.live.bean;

import java.util.List;

public class AgentCenterEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"id":"1267009207771660290","image":null,"nickname":"b9Y22lIQxQ","fansNumber":0,"memberStatus":3,"isLive":0},{"id":"1267009740410519553","image":null,"nickname":"86ke2ZRBIh","fansNumber":0,"memberStatus":3,"isLive":0},{"id":"1267014980572659713","image":null,"nickname":"298272BCRG","fansNumber":0,"memberStatus":3,"isLive":0}],"total":3,"size":15,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":"1267009207771660290","image":null,"nickname":"b9Y22lIQxQ","fansNumber":0,"memberStatus":3,"isLive":0},{"id":"1267009740410519553","image":null,"nickname":"86ke2ZRBIh","fansNumber":0,"memberStatus":3,"isLive":0},{"id":"1267014980572659713","image":null,"nickname":"298272BCRG","fansNumber":0,"memberStatus":3,"isLive":0}]
         * total : 3
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
             * id : 1267009207771660290
             * image : null
             * nickname : b9Y22lIQxQ
             * fansNumber : 0
             * memberStatus : 3
             * isLive : 0
             */

            private String id;
            private String image;
            private String nickname;
            private int fansNumber;
            private int memberStatus;
            private int isLive;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getFansNumber() {
                return fansNumber;
            }

            public void setFansNumber(int fansNumber) {
                this.fansNumber = fansNumber;
            }

            public int getMemberStatus() {
                return memberStatus;
            }

            public void setMemberStatus(int memberStatus) {
                this.memberStatus = memberStatus;
            }

            public int getIsLive() {
                return isLive;
            }

            public void setIsLive(int isLive) {
                this.isLive = isLive;
            }
        }
    }
}
