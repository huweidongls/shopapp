package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 */

public class GoodsSelectResultBean {

    /**
     * status : 200
     * data : [{"price":2,"skuId":"2","goodsId":"4","goodsName":"核桃","goodsSn":"1112312","lowStock":"1","appPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg","stock":"1"}]
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
         * price : 2
         * skuId : 2
         * goodsId : 4
         * goodsName : 核桃
         * goodsSn : 1112312
         * lowStock : 1
         * appPic : /upload/13a825c68f296a31200e3503cc660e8.jpg
         * stock : 1
         */

        private double price;
        private String skuId;
        private String goodsId;
        private String goodsName;
        private String skuCode;
        private String lowStock;
        private String appPic;
        private String stock;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getLowStock() {
            return lowStock;
        }

        public void setLowStock(String lowStock) {
            this.lowStock = lowStock;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }
    }
}
