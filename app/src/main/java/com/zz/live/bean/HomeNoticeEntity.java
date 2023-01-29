package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.io.Serializable;
import java.util.List;

public class HomeNoticeEntity implements Serializable {




    /**
     * code : 0
     * msg : null
     * data : {"records":[{"title":"st测试ring","link":"str测试ing","content":"<p>测试1111111111111111111111111111111111111111111111111111啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊水水水水水水水水水水水水水水水水水水水<\/p>","sort":1,"createTime":"2020-11-08 12:37:06"}],"total":1,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private String code;
    private Object msg;
    /**
     * records : [{"title":"st测试ring","link":"str测试ing","content":"<p>测试1111111111111111111111111111111111111111111111111111啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊水水水水水水水水水水水水水水水水水水水<\/p>","sort":1,"createTime":"2020-11-08 12:37:06"}]
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

    public static class DataBean {
        private String total;
        private String size;
        private String current;
        private boolean hitCount;
        private boolean searchCount;
        private String pages;
    
        /**
         * title : st测试ring
         * link : str测试ing
         * content : <p>测试1111111111111111111111111111111111111111111111111111啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊水水水水水水水水水水水水水水水水水水水</p>
         * sort : 1
         * createTime : 2020-11-08 12:37:06
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
            private String title;
            private String link;
            private String content;
            private String sort;
            private String createTime;
            int status=0;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
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
