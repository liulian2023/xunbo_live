package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.util.List;

public class MineFeedbackModel extends CommonModel {

    /**
     * code : 0
     * msg : null
     * data : {"records":[{"id":"1324990931803369474","opinionType":0,"opinionContent":"Zhbxbccccxxc","pictures":"images/20201107/91338f8c385549a58d49be588556af77.jpg","replyContent":null,"replyTime":null,"appVersion":"1.0.1","status":0,"createTime":"2020-11-07 16:23:50"}],"total":1,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private String code;
    private Object msg;
    /**
     * records : [{"id":"1324990931803369474","opinionType":0,"opinionContent":"Zhbxbccccxxc","pictures":"images/20201107/91338f8c385549a58d49be588556af77.jpg","replyContent":null,"replyTime":null,"appVersion":"1.0.1","status":0,"createTime":"2020-11-07 16:23:50"}]
     * total : 1
     * size : 10
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

    public static class DataBean extends CommonModel{
        private String total;
        private String size;
        private String current;
        private boolean hitCount;
        private boolean searchCount;
        private String pages;
        /**
         * id : 1324990931803369474
         * opinionType : 0
         * opinionTypeExplain":"程序bug",
         * opinionContent : Zhbxbccccxxc
         * pictures : images/20201107/91338f8c385549a58d49be588556af77.jpg
         * replyContent : null
         * replyTime : null
         * appVersion : 1.0.1
         * status : 0
         * createTime : 2020-11-07 16:23:50
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

        public static class RecordsBean extends CommonModel {
            private String id;
            private String opinionType;
            private String opinionTypeExplain;
            private String opinionContent;
            private String pictures;
            private String replyContent;
            private Object replyTime;
            private String appVersion;
            private String status;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOpinionTypeExplain() {
                return opinionTypeExplain;
            }

            public void setOpinionTypeExplain(String opinionTypeExplain) {
                this.opinionTypeExplain = opinionTypeExplain;
            }

            public String getOpinionType() {
                return opinionType;
            }

            public void setOpinionType(String opinionType) {
                this.opinionType = opinionType;
            }

            public String getOpinionContent() {
                return opinionContent;
            }

            public void setOpinionContent(String opinionContent) {
                this.opinionContent = opinionContent;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }

            public String getReplyContent() {
                return replyContent;
            }

            public void setReplyContent(String replyContent) {
                this.replyContent = replyContent;
            }

            public Object getReplyTime() {
                return replyTime;
            }

            public void setReplyTime(Object replyTime) {
                this.replyTime = replyTime;
            }

            public String getAppVersion() {
                return appVersion;
            }

            public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
