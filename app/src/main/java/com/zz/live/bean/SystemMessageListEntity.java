package com.zz.live.bean;

import com.zz.live.ui.adapter.CommonModel;

import java.util.List;

public class SystemMessageListEntity extends CommonModel {

    /**
     * code : 0
     * msg : 查询成功
     * data : [{"createTime":"2020-05-22 14:53:38","type":1,"title":"系统消息","content":"123","unReadCount":1},{"createTime":"2020-05-22 14:54:42","type":1,"title":"粉丝消息","content":"傻屌关注了你","unReadCount":1}]
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

    public static class DataBean extends CommonModel{
        /**
         * createTime : 2020-05-22 14:53:38
         * type : 1
         * title : 系统消息
         * content : 123
         * unReadCount : 1
         */

        private String createTime;
        private int type;
        private String title;
        private String content;
        private int unReadCount;
        private String contentTxt;

        public String getContentTxt() {
            return contentTxt;
        }

        public void setContentTxt(String contentTxt) {
            this.contentTxt = contentTxt;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUnReadCount() {
            return unReadCount;
        }

        public void setUnReadCount(int unReadCount) {
            this.unReadCount = unReadCount;
        }
    }
}
