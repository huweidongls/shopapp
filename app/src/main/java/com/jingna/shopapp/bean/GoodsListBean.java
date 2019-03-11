package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/5.
 */

public class GoodsListBean {

    /**
     * status : 200
     * data : [{"id":1,"sellerId":"1","categoryId":"1","mainTitle":"321","subTitle":"发的","price":"123321","buyNum":43,"goodsPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","brandId":1,"sellerName":"OW代练"},{"id":2,"sellerId":"2","categoryId":"1","mainTitle":"11","subTitle":"改的","price":"43253245","buyNum":23,"goodsPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","brandId":1,"sellerName":"3333"},{"id":4,"sellerId":"2","categoryId":"1","mainTitle":"4324","subTitle":"割发代首","price":"16800","buyNum":10,"goodsPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","brandId":1,"sellerName":"3333"},{"id":8,"sellerId":"1","categoryId":"1","mainTitle":"1小米","subTitle":"1小米9","price":"3699","buyNum":100,"goodsPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","brandId":1,"sellerName":"OW代练"}]
     * totalPage : 1
     * totalCount : 4
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * sellerId : 1
         * categoryId : 1
         * mainTitle : 321
         * subTitle : 发的
         * price : 123321
         * buyNum : 43
         * goodsPic : /upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg
         * brandId : 1
         * sellerName : OW代练
         */

        private int id;
        private String sellerId;
        private String categoryId;
        private String mainTitle;
        private String subTitle;
        private String price;
        private int buyNum;
        private String goodsPic;
        private int brandId;
        private String sellerName;
        private String productName;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getMainTitle() {
            return mainTitle;
        }

        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }
    }
}
