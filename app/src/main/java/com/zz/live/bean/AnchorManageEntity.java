package com.zz.live.bean;

import java.io.Serializable;
import java.util.List;

public class AnchorManageEntity {


    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"id":"1270686427618803714","username":"juzi00009","nickname":"QrQ782gYDu","image":null,"lockFlag":"0"},{"id":"1273938433649188865","username":"kg12345","nickname":"天赐仙女骚骚骚骚","image":"xblive/20200620/00a7f84f482c413c8e945e0d1df67d12.jpg","lockFlag":"0"},{"id":"1273946738782150657","username":"kg123456","nickname":"粉丝骚骚骚","image":"xblive/20200623/e233a972f03948b7b4099793547f6bc3.jpg","lockFlag":"0"},{"id":"1273946838480756738","username":"kg1234567","nickname":"骚骚骚舒淇呀","image":null,"lockFlag":"0"},{"id":"1273952397300899841","username":"xxoo088","nickname":"42PMY3NOYd","image":null,"lockFlag":"0"},{"id":"1274962386014482434","username":"xxoo099","nickname":"kHn484rOzi","image":null,"lockFlag":"0"},{"id":"1275044680402206721","username":"xxoo077","nickname":"85h333FowB","image":null,"lockFlag":"0"},{"id":"1275405836895715330","username":"xxoo098","nickname":"骚骚骚舒淇","image":null,"lockFlag":"0"},{"id":"1275652729206771714","username":"xxoo123","nickname":"04k8d7jOSL","image":null,"lockFlag":"0"}],"total":9,"size":15,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"id":"1270686427618803714","username":"juzi00009","nickname":"QrQ782gYDu","image":null,"lockFlag":"0"},{"id":"1273938433649188865","username":"kg12345","nickname":"天赐仙女骚骚骚骚","image":"xblive/20200620/00a7f84f482c413c8e945e0d1df67d12.jpg","lockFlag":"0"},{"id":"1273946738782150657","username":"kg123456","nickname":"粉丝骚骚骚","image":"xblive/20200623/e233a972f03948b7b4099793547f6bc3.jpg","lockFlag":"0"},{"id":"1273946838480756738","username":"kg1234567","nickname":"骚骚骚舒淇呀","image":null,"lockFlag":"0"},{"id":"1273952397300899841","username":"xxoo088","nickname":"42PMY3NOYd","image":null,"lockFlag":"0"},{"id":"1274962386014482434","username":"xxoo099","nickname":"kHn484rOzi","image":null,"lockFlag":"0"},{"id":"1275044680402206721","username":"xxoo077","nickname":"85h333FowB","image":null,"lockFlag":"0"},{"id":"1275405836895715330","username":"xxoo098","nickname":"骚骚骚舒淇","image":null,"lockFlag":"0"},{"id":"1275652729206771714","username":"xxoo123","nickname":"04k8d7jOSL","image":null,"lockFlag":"0"}]
         * total : 9
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

        public static class RecordsBean implements Serializable {
            /**
             * id : 1270686427618803714
             * username : juzi00009
             * nickname : QrQ782gYDu
             * image : null
             * lockFlag : 0
             */

            private String id;
            private String username;
            private String nickname;
            private String image;
            private String lockFlag;

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

            public String getLockFlag() {
                return lockFlag;
            }

            public void setLockFlag(String lockFlag) {
                this.lockFlag = lockFlag;
            }
        }
    }
}
