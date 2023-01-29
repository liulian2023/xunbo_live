package com.zz.live.bean;

import java.util.List;

public class AnchorDataEntity {


    /**
     * code : 0
     * msg : null
     * data : {"records":[{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":1,"playTime":0,"commission":0.83},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695945171398657","image":null,"nickname":"b4t6i8gSuN","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695945171398657","image":null,"nickname":"b4t6i8gSuN","giftAmount":10,"playTime":0,"commission":8.3},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":10,"playTime":290,"commission":8.3}],"total":24,"size":15,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":2}
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
         * records : [{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":1,"playTime":0,"commission":0.83},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":1,"playTime":290,"commission":0.83},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695945171398657","image":null,"nickname":"b4t6i8gSuN","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695545554890753","image":null,"nickname":"10E83BIVYg","giftAmount":10,"playTime":0,"commission":7.2},{"memberId":"1262695672545832961","image":null,"nickname":"5sZKP2hIoL","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695788572864513","image":null,"nickname":"5672i5WPCz","giftAmount":10,"playTime":290,"commission":7.2},{"memberId":"1262695945171398657","image":null,"nickname":"b4t6i8gSuN","giftAmount":10,"playTime":0,"commission":8.3},{"memberId":"1262695427476844545","image":null,"nickname":"934161qKRt","giftAmount":10,"playTime":290,"commission":8.3}]
         * total : 24
         * size : 15
         * current : 1
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 2
         */

        private String total;
        private String size;
        private String current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
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
             * memberId : 1262695427476844545
             * image : null
             * nickname : 934161qKRt
             * giftAmount : 1.0
             * playTime : 290
             * commission : 0.83
             */

            private String memberId;
            private String image;
            private String nickname;
            private String giftAmount;
            private String playTime;
            private String commission;

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
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

            public String getGiftAmount() {
                return giftAmount;
            }

            public void setGiftAmount(String giftAmount) {
                this.giftAmount = giftAmount;
            }

            public String getPlayTime() {
                return playTime;
            }

            public void setPlayTime(String playTime) {
                this.playTime = playTime;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }
        }
    }
}
