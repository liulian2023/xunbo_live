package com.zz.live.bean;

import java.util.List;

public class AddCardEntity {

    /**
     * code : 0
     * msg : null
     * data : {"records":[{"createUser":null,"createTime":"2020-05-26 16:42:43","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:42:43","updateIp":null,"version":0,"id":"123456","name":"屌丝银行","backImg":null,"icon":null,"delFlag":0,"code":"dsyh"},{"createUser":null,"createTime":"2020-05-26 16:43:12","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:43:12","updateIp":null,"version":0,"id":"123456789","name":"逗比银行","backImg":null,"icon":null,"delFlag":0,"code":"2454"},{"createUser":null,"createTime":"2020-05-26 16:43:30","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:43:30","updateIp":null,"version":0,"id":"98754","name":"渣渣银行","backImg":null,"icon":null,"delFlag":0,"code":null}],"total":3,"size":100,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"createUser":null,"createTime":"2020-05-26 16:42:43","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:42:43","updateIp":null,"version":0,"id":"123456","name":"屌丝银行","backImg":null,"icon":null,"delFlag":0,"code":"dsyh"},{"createUser":null,"createTime":"2020-05-26 16:43:12","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:43:12","updateIp":null,"version":0,"id":"123456789","name":"逗比银行","backImg":null,"icon":null,"delFlag":0,"code":"2454"},{"createUser":null,"createTime":"2020-05-26 16:43:30","createIp":null,"updateUser":null,"updateTime":"2020-05-26 16:43:30","updateIp":null,"version":0,"id":"98754","name":"渣渣银行","backImg":null,"icon":null,"delFlag":0,"code":null}]
         * total : 3
         * size : 100
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
             * createTime : 2020-05-26 16:42:43
             * createIp : null
             * updateUser : null
             * updateTime : 2020-05-26 16:42:43
             * updateIp : null
             * version : 0
             * id : 123456
             * name : 屌丝银行
             * backImg : null
             * icon : null
             * delFlag : 0
             * code : dsyh
             */

            private Object createUser;
            private String createTime;
            private Object createIp;
            private Object updateUser;
            private String updateTime;
            private Object updateIp;
            private int version;
            private String id;
            private String name;
            private Object backImg;
            private Object icon;
            private int delFlag;
            private String code;

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getCreateIp() {
                return createIp;
            }

            public void setCreateIp(Object createIp) {
                this.createIp = createIp;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUpdateIp() {
                return updateIp;
            }

            public void setUpdateIp(Object updateIp) {
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getBackImg() {
                return backImg;
            }

            public void setBackImg(Object backImg) {
                this.backImg = backImg;
            }

            public Object getIcon() {
                return icon;
            }

            public void setIcon(Object icon) {
                this.icon = icon;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
