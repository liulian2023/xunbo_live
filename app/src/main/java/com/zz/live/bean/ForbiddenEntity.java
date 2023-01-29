package com.zz.live.bean;

import java.util.List;

public class ForbiddenEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : [{"rongId":"77","nickName":"股神挂了","image":"http://148.66.6.21:8185/upload/images/20190911/1568206880594.jpg","platformCode":"xunbo","unGagTime":"2020-07-03 13:21:07"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rongId : 77
         * nickName : 股神挂了
         * image : http://148.66.6.21:8185/upload/images/20190911/1568206880594.jpg
         * platformCode : xunbo
         * unGagTime : 2020-07-03 13:21:07
         */

        private String rongId;
        private String nickName;
        private String image;
        private String platformCode;
        private String unGagTime;

        public String getRongId() {
            return rongId;
        }

        public void setRongId(String rongId) {
            this.rongId = rongId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPlatformCode() {
            return platformCode;
        }

        public void setPlatformCode(String platformCode) {
            this.platformCode = platformCode;
        }

        public String getUnGagTime() {
            return unGagTime;
        }

        public void setUnGagTime(String unGagTime) {
            this.unGagTime = unGagTime;
        }
    }
}
