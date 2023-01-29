package com.zz.live.bean;

import java.util.List;

public class SystemMessageEntity {

    /**
     * code : 0
     * msg : null
     * data : {"records":[{"createUser":null,"createTime":"2020-05-22 20:27:09","createIp":null,"updateUser":null,"updateTime":"2020-05-23 13:07:19","updateIp":null,"version":0,"id":"52534","title":"545645","content":"24537","isRead":0,"memberId":"1262973836928860162","type":1}],"total":1,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"createUser":null,"createTime":"2020-05-22 20:27:09","createIp":null,"updateUser":null,"updateTime":"2020-05-23 13:07:19","updateIp":null,"version":0,"id":"52534","title":"545645","content":"24537","isRead":0,"memberId":"1262973836928860162","type":1}]
         * total : 1
         * size : 10
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
             * createUser : null
             * createTime : 2020-05-22 20:27:09
             * createIp : null
             * updateUser : null
             * updateTime : 2020-05-23 13:07:19
             * updateIp : null
             * version : 0
             * id : 52534
             * title : 545645
             * content : 24537
             * isRead : 0
             * memberId : 1262973836928860162
             * type : 1
             */

            private String createUser;
            private String createTime;
            private String createIp;
            private String updateUser;
            private String updateTime;
            private String updateIp;
            private int version;
            private String id;
            private String title;
            private String content;
            private int isRead;
            private String memberId;
            private int type;

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateIp() {
                return createIp;
            }

            public void setCreateIp(String createIp) {
                this.createIp = createIp;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateIp() {
                return updateIp;
            }

            public void setUpdateIp(String updateIp) {
                this.updateIp = updateIp;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
