package com.zz.live.bean;

import java.util.List;

public class RankEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"nickName":"b4t6i8gSuN","totalAmount":31,"image":null,"username":null,"memberId":"1262695945171398657"},{"nickName":"5672i5WPCz","totalAmount":32,"image":null,"username":null,"memberId":"1262695788572864513"},{"nickName":"10E83BIVYg","totalAmount":32,"image":null,"username":null,"memberId":"1262695545554890753"},{"nickName":"00D4F4DuGR","totalAmount":23,"image":null,"username":null,"memberId":"1262695386280390657"},{"nickName":"5sZKP2hIoL","totalAmount":32,"image":null,"username":null,"memberId":"1262695672545832961"},{"nickName":"934161qKRt","totalAmount":23,"image":null,"username":null,"memberId":"1262695427476844545"}],"total":6,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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
         * records : [{"nickName":"b4t6i8gSuN","totalAmount":31,"image":null,"username":null,"memberId":"1262695945171398657"},{"nickName":"5672i5WPCz","totalAmount":32,"image":null,"username":null,"memberId":"1262695788572864513"},{"nickName":"10E83BIVYg","totalAmount":32,"image":null,"username":null,"memberId":"1262695545554890753"},{"nickName":"00D4F4DuGR","totalAmount":23,"image":null,"username":null,"memberId":"1262695386280390657"},{"nickName":"5sZKP2hIoL","totalAmount":32,"image":null,"username":null,"memberId":"1262695672545832961"},{"nickName":"934161qKRt","totalAmount":23,"image":null,"username":null,"memberId":"1262695427476844545"}]
         * total : 6
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
             * nickName : b4t6i8gSuN
             * totalAmount : 31.0
             * image : null
             * username : null
             * memberId : 1262695945171398657
             * sex : 0
             */

            private String nickName;
            private String totalAmount="- - - - 映票";
            private String image;
            private String username = "- - -";
            private String memberId;
            private int sex;

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }
        }
    }
}
