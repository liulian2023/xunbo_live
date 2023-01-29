package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.io.Serializable;
import java.util.List;

public class LiveEntity extends CommonModel {


    /**
     * code : 0
     * msg : 查询成功
     * data : {"records":[{"nickname":"433","categoryId":"34","title":"43","memberId":"1262695788572864513","cover":"","gameClassId":12,"playUrl":"VXor3opOMLTceSsLacoUoAuc1EPGxd6VWIBayhhMLhumf9H+WRacDgAskngHDiG1","image":null,"accountId":"92185963","playAuthentication":"sChO3SgdhfKBLHT0v22meg==","streamName":"9k0HbGBS5il2G7QBJchhMQ==","roomId":"1262695788572864513334520ff5bfb4a39bb75cd2306d68b66"},{"nickname":"50Y39zXfgm","categoryId":"1254291154032500737","title":"ghhhw","memberId":"1265570669146472449","cover":"xblive/20200602/624c14df578a4c909d0d515f877b4039.jpg","gameClassId":65,"playUrl":"VXor3opOMLTceSsLacoUoAuc1EPGxd6VWIBayhhMLhv+3YmKabiIykXEAvF+XYWnIvZFnUY5dThU\nTOdwd+a+L5+nCGBnCfNSw4vi3AIJ8shGK9KM+UQjVuto5iw42myr87kQUbEHLBVuPtcgzemnMqWH\n39W6FNnvp6KXJBVkN5k=","image":null,"accountId":"67689745","playAuthentication":"cK5b5YztNg1ZVh3I8VqV77wrEM81NbYk/LWBcsmQX2a/Z8NE8OPTQugeT40zMuCpdRLzAm9Oq+qK\na4mPS31B0w==","streamName":"WWknIt1+L2XChgcrUW9dKCpSxK7Gpkf1rwEihZrXUK8=","roomId":"12655706691464724492c9180c8ef37480b9b428510e0187033"}],"total":2,"size":10,"current":1,"orders":[],"hitCount":false,"searchCount":true,"pages":1}
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

    public static class DataBean implements Serializable {
        /**
         * records : [{"nickname":"433","categoryId":"34","title":"43","memberId":"1262695788572864513","cover":"","gameClassId":12,"playUrl":"VXor3opOMLTceSsLacoUoAuc1EPGxd6VWIBayhhMLhumf9H+WRacDgAskngHDiG1","image":null,"accountId":"92185963","playAuthentication":"sChO3SgdhfKBLHT0v22meg==","streamName":"9k0HbGBS5il2G7QBJchhMQ==","roomId":"1262695788572864513334520ff5bfb4a39bb75cd2306d68b66"},{"nickname":"50Y39zXfgm","categoryId":"1254291154032500737","title":"ghhhw","memberId":"1265570669146472449","cover":"xblive/20200602/624c14df578a4c909d0d515f877b4039.jpg","gameClassId":65,"playUrl":"VXor3opOMLTceSsLacoUoAuc1EPGxd6VWIBayhhMLhv+3YmKabiIykXEAvF+XYWnIvZFnUY5dThU\nTOdwd+a+L5+nCGBnCfNSw4vi3AIJ8shGK9KM+UQjVuto5iw42myr87kQUbEHLBVuPtcgzemnMqWH\n39W6FNnvp6KXJBVkN5k=","image":null,"accountId":"67689745","playAuthentication":"cK5b5YztNg1ZVh3I8VqV77wrEM81NbYk/LWBcsmQX2a/Z8NE8OPTQugeT40zMuCpdRLzAm9Oq+qK\na4mPS31B0w==","streamName":"WWknIt1+L2XChgcrUW9dKCpSxK7Gpkf1rwEihZrXUK8=","roomId":"12655706691464724492c9180c8ef37480b9b428510e0187033"}]
         * total : 2
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

        public static class RecordsBean extends CommonModel implements Serializable {
            /**
             * nickname : 433
             * categoryId : 34
             * title : 43
             * memberId : 1262695788572864513
             * cover :
             * gameClassId : 12
             * playUrl : VXor3opOMLTceSsLacoUoAuc1EPGxd6VWIBayhhMLhumf9H+WRacDgAskngHDiG1
             * image : null
             * accountId : 92185963
             * playAuthentication : sChO3SgdhfKBLHT0v22meg==
             * streamName : 9k0HbGBS5il2G7QBJchhMQ==
             * roomId : 1262695788572864513334520ff5bfb4a39bb75cd2306d68b66
             */

            private String nickname;
            private String categoryId;
            private String title;
            private String memberId;
            private String cover;
            private int gameClassId;
            private String playUrl;
            private String image;
            private String accountId;
            private String playAuthentication;
            private String streamName;
            private String roomId;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getGameClassId() {
                return gameClassId;
            }

            public void setGameClassId(int gameClassId) {
                this.gameClassId = gameClassId;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getPlayAuthentication() {
                return playAuthentication;
            }

            public void setPlayAuthentication(String playAuthentication) {
                this.playAuthentication = playAuthentication;
            }

            public String getStreamName() {
                return streamName;
            }

            public void setStreamName(String streamName) {
                this.streamName = streamName;
            }

            public String getRoomId() {
                return roomId;
            }

            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}
