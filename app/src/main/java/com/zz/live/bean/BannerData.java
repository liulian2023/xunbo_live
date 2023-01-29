/*
 * Copyright (c) 2019.  ganzhe
 */

package com.zz.live.bean;

import java.util.List;

public class BannerData {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"title":"测试3","link":"https://avuejs.com/","content":"测试3","headImage":"xblive/20200531/a84ed7de239e4b3bbd0389aa5c91d127.jpg"},{"title":"测试2","link":"https://avuejs.com/","content":"测试2","headImage":"xblive/20200531/e7858954d9264fe590b017264d5fca2d.jpg"},{"title":"测试1","link":"https://avuejs.com/","content":"测试1","headImage":"xblive/20200531/3a5437c2f0c342c0ac2f4c9941961d66.jpg"}],"total":3,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"title":"测试3","link":"https://avuejs.com/","content":"测试3","headImage":"xblive/20200531/a84ed7de239e4b3bbd0389aa5c91d127.jpg"},{"title":"测试2","link":"https://avuejs.com/","content":"测试2","headImage":"xblive/20200531/e7858954d9264fe590b017264d5fca2d.jpg"},{"title":"测试1","link":"https://avuejs.com/","content":"测试1","headImage":"xblive/20200531/3a5437c2f0c342c0ac2f4c9941961d66.jpg"}]
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

        public static class RecordsBean {
            /**
             * title : 测试3
             * link : https://avuejs.com/
             * content : 测试3
             * headImage : xblive/20200531/a84ed7de239e4b3bbd0389aa5c91d127.jpg
             */

            private String title;
            private String link;
            private String content;
            private String headImage;

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

            public String getHeadImage() {
                return headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }
        }
    }
}
