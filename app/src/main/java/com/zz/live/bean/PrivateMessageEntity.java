package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.util.List;

public class PrivateMessageEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"id":"1212","createTime":"2020-05-22 14:55:12","title":"1212","content":"1212","isRead":0},{"id":"123465","createTime":"2020-05-22 19:44:04","title":"FCUK GO ON ","content":"FUCK GO ON","isRead":0},{"id":"4564123","createTime":"2020-05-22 19:44:19","title":"FUCK GO ON TOO ","content":"FUCK GO ON TOO ","isRead":0}],"total":3,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":"1212","createTime":"2020-05-22 14:55:12","title":"1212","content":"1212","isRead":0},{"id":"123465","createTime":"2020-05-22 19:44:04","title":"FCUK GO ON ","content":"FUCK GO ON","isRead":0},{"id":"4564123","createTime":"2020-05-22 19:44:19","title":"FUCK GO ON TOO ","content":"FUCK GO ON TOO ","isRead":0}]
         * total : 3
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

        public static class RecordsBean extends CommonModel {
            /**
             * id : 1212
             * createTime : 2020-05-22 14:55:12
             * title : 1212
             * content : 1212
             * isRead : 0
             * contentTxt
             */

            private String id;
            private String createTime;
            private String title;
            private String content;
            private int isRead;
            private String contentTxt;

            public String getContentTxt() {
                return contentTxt;
            }

            public void setContentTxt(String contentTxt) {
                this.contentTxt = contentTxt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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
        }
    }
}
