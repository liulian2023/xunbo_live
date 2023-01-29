package com.zz.live.bean;

public class SystemParamsEntity {


    /**
     * code : 0
     * msg : null
     * data : {"websiteName":"猫咪直播","maintenanceIsOpen":0,"maintenanceNotice":"fuckcc","websiteDomain":"http://148.66.6.22:9000/","fileDomainUrl":"http://148.66.6.22:9000/","versionInfo":"22","customerAddress":"https://chatlink.mstatik.com/widget/standalone.html?eid=207395","cpWebsiteName":"http://148.66.6.18:8183/web/ws/","cpImageDomain":"http://148.66.6.20:8185/"}
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
         * websiteName : 猫咪直播
         * maintenanceIsOpen : 0
         * maintenanceNotice : fuckcc
         * websiteDomain : http://148.66.6.22:9000/
         * fileDomainUrl : http://148.66.6.22:9000/
         * versionInfo : 22
         * customerAddress : https://chatlink.mstatik.com/widget/standalone.html?eid=207395
         * watchRadio:10
         * cpWebsiteName : http://148.66.6.18:8183/web/ws/
         * cpImageDomain : http://148.66.6.20:8185/
         */

        private String websiteName;
        private int maintenanceIsOpen;
        private String maintenanceNotice;
        private String websiteDomain;
        private String fileDomainUrl;
        private String versionInfo;
        private String customerAddress;
        private int watchRadio;
        private String cpWebsiteName;
        private String cpImageDomain;
        private String nature;

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public int getWatchRadio() {
            return watchRadio;
        }

        public void setWatchRadio(int watchRadio) {
            this.watchRadio = watchRadio;
        }

        public String getWebsiteName() {
            return websiteName;
        }

        public void setWebsiteName(String websiteName) {
            this.websiteName = websiteName;
        }

        public int getMaintenanceIsOpen() {
            return maintenanceIsOpen;
        }

        public void setMaintenanceIsOpen(int maintenanceIsOpen) {
            this.maintenanceIsOpen = maintenanceIsOpen;
        }

        public String getMaintenanceNotice() {
            return maintenanceNotice;
        }

        public void setMaintenanceNotice(String maintenanceNotice) {
            this.maintenanceNotice = maintenanceNotice;
        }

        public String getWebsiteDomain() {
            return websiteDomain;
        }

        public void setWebsiteDomain(String websiteDomain) {
            this.websiteDomain = websiteDomain;
        }

        public String getFileDomainUrl() {
            return fileDomainUrl;
        }

        public void setFileDomainUrl(String fileDomainUrl) {
            this.fileDomainUrl = fileDomainUrl;
        }

        public String getVersionInfo() {
            return versionInfo;
        }

        public void setVersionInfo(String versionInfo) {
            this.versionInfo = versionInfo;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCpWebsiteName() {
            return cpWebsiteName;
        }

        public void setCpWebsiteName(String cpWebsiteName) {
            this.cpWebsiteName = cpWebsiteName;
        }

        public String getCpImageDomain() {
            return cpImageDomain;
        }

        public void setCpImageDomain(String cpImageDomain) {
            this.cpImageDomain = cpImageDomain;
        }
    }
}
