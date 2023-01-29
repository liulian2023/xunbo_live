package com.zz.live.bean;

import java.util.List;

public class AnchorReportEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"id":"1333763613522472962","username":"yezhu001","nickname":"猪一号","image":"images/20201201/29dff5ab0c1d4ac88faddec87312c41e.png","playTime":"0"},{"id":"1333763795890810882","username":"yezhu002","nickname":"猪二号","image":"images/20201201/51dc729dead14ccda655e4762e851192.png","playTime":"0"}],"total":2,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
     */

    private String code;
    private String msg;
    /**
     * records : [{"id":"1333763613522472962","username":"yezhu001","nickname":"猪一号","image":"images/20201201/29dff5ab0c1d4ac88faddec87312c41e.png","playTime":"0"},{"id":"1333763795890810882","username":"yezhu002","nickname":"猪二号","image":"images/20201201/51dc729dead14ccda655e4762e851192.png","playTime":"0"}]
     * total : 2
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
        private String total;
        private String size;
        private String current;
        private boolean hitCount;
        private boolean searchCount;
        private String pages;
        /**
         * id : 1333763613522472962
         * username : yezhu001
         * nickname : 猪一号
         * image : images/20201201/29dff5ab0c1d4ac88faddec87312c41e.png
         * playTime : 0
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
            private String id;
            private String username;
            private String nickname;
            private String image;
            private String playTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPlayTime() {
                return playTime;
            }

            public void setPlayTime(String playTime) {
                this.playTime = playTime;
            }
        }
    }
}
