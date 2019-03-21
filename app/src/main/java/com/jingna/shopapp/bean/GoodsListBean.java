package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/5.
 */

public class GoodsListBean {

    /**
     * status : 200
     * data : [{"sellerId":4,"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"goodsId":4,"commentCount":"2","sellerName":"测试商铺1","productName":"核桃"},{"sellerId":4,"goodsId":7,"commentCount":"0","sellerName":"测试商铺1","productName":"谷物零食"}]
     * totalPage : 1
     * totalCount : 2
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
         * sellerId : 4
         * pic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * price : 10
         * goodsId : 4
         * commentCount : 2
         * sellerName : 测试商铺1
         * productName : 核桃
         */

        private int sellerId;
        private String pic;
        private int price;
        private int goodsId;
        private String commentCount;
        private String sellerName;
        private String productName;

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
