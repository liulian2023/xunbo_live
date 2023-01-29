package com.zz.live.bean;

public class AgentInfoEntity {

    /**
     * code : 0
     * msg : 查询成功
     * data : {"image":null,"accountId":"67689745","nickname":"50Y39zXfgm","parentExtraRake":0,"giftAmount":null,"memberCount":0,"commission":0}
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
         * image : null
         * accountId : 67689745
         * nickname : 50Y39zXfgm
         * parentExtraRake : 0
         * giftAmount : null
         * memberCount : 0
         * commission : 0.0
         */

        private String image;
        private String accountId;
        private String nickname;
        private String parentExtraRake;
        private String giftAmount;
        private String memberCount;
        private String commission;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getParentExtraRake() {
            return parentExtraRake;
        }

        public void setParentExtraRake(String parentExtraRake) {
            this.parentExtraRake = parentExtraRake;
        }

        public String getGiftAmount() {
            return giftAmount;
        }

        public void setGiftAmount(String giftAmount) {
            this.giftAmount = giftAmount;
        }

        public String getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(String memberCount) {
            this.memberCount = memberCount;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }
    }
}
