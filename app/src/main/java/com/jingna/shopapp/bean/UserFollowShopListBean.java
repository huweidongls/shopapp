package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/11.
 */

public class UserFollowShopListBean {

    /**
     * status : 200
     * data : [{"sellerId":"19","followNum":"9","appSellerLogo":"/upload/samsung.jpg","sellerName":"测试商铺111","categoryId":"2","categoryName":"京东超市","categoryNum":"2"},{"sellerId":"18","followNum":"10","appSellerLogo":"/upload/samsung.jpg","sellerName":"测试商铺21","categoryId":"3","categoryName":"国际名牌","categoryNum":"1"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sellerId : 19
         * followNum : 9
         * appSellerLogo : /upload/samsung.jpg
         * sellerName : 测试商铺111
         * categoryId : 2
         * categoryName : 京东超市
         * categoryNum : 2
         */

        private String sellerId;
        private String followNum;
        private String appSellerLogo;
        private String sellerName;
        private String categoryId;
        private String categoryName;
        private String categoryNum;

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getFollowNum() {
            return followNum;
        }

        public void setFollowNum(String followNum) {
            this.followNum = followNum;
        }

        public String getAppSellerLogo() {
            return appSellerLogo;
        }

        public void setAppSellerLogo(String appSellerLogo) {
            this.appSellerLogo = appSellerLogo;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryNum() {
            return categoryNum;
        }

        public void setCategoryNum(String categoryNum) {
            this.categoryNum = categoryNum;
        }
    }
}
