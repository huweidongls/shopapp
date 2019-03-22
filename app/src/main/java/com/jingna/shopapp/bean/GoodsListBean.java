package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/5.
 */

public class GoodsListBean {

    /**
     * status : 200
     * data : [{"sellerId":4,"goodsName":"核桃","pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"goodsId":4,"commentCount":"3","sellerName":"测试商铺1"}]
     * totalPage : 1
     * totalCount : 1
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
         * goodsName : 核桃
         * pic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * price : 10
         * goodsId : 4
         * commentCount : 3
         * sellerName : 测试商铺1
         */

        private int sellerId;
        private String goodsName;
        private String pic;
        private int price;
        private int goodsId;
        private String commentCount;
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
    }
}
