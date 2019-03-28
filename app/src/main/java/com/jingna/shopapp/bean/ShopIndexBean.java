package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/3/28.
 */

public class ShopIndexBean {


    /**
     * status : 200
     * data : {"id":4,"sellerName":"测试商铺1","appSellerLogo":"/upload/samsung.jpg","memberNum":"1","memStatus":"0"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * sellerName : 测试商铺1
         * appSellerLogo : /upload/samsung.jpg
         * memberNum : 1
         * memStatus : 0
         */

        private int id;
        private String sellerName;
        private String appSellerLogo;
        private String memberNum;
        private String memStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getAppSellerLogo() {
            return appSellerLogo;
        }

        public void setAppSellerLogo(String appSellerLogo) {
            this.appSellerLogo = appSellerLogo;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getMemStatus() {
            return memStatus;
        }

        public void setMemStatus(String memStatus) {
            this.memStatus = memStatus;
        }
    }
}
