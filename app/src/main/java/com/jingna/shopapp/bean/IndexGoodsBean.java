package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/20.
 */

public class IndexGoodsBean {
    /**
     * status : 200
     * data : [{"sellerId":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1,"subTitle":"草莓味,菠萝味","goodsId":4,"favorableRate":"99","commentCount":"15","updateTime":"2019-04-19T03:50:27.000+0000","sellerName":"测试商铺1"}]
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
         * sellerId : 4
         * goodsName : 核桃
         * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * price : 1
         * subTitle : 草莓味,菠萝味
         * goodsId : 4
         * favorableRate : 99
         * commentCount : 15
         * updateTime : 2019-04-19T03:50:27.000+0000
         * sellerName : 测试商铺1
         */

        private int sellerId;
        private String goodsName;
        private String appPic;
        private double price;
        private String subTitle;
        private int goodsId;
        private String favorableRate;
        private String commentCount;
        private String updateTime;
        private String sellerName;

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getFavorableRate() {
            return favorableRate;
        }

        public void setFavorableRate(String favorableRate) {
            this.favorableRate = favorableRate;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }
    }
}
