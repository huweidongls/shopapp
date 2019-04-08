package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/8.
 */

public class Attention_Goods_listBean {
    /**
     * status : 200
     * data : [{"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"goodsId":4}]
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
         * goodsName : 核桃
         * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * price : 10
         * goodsId : 4
         */

        private String goodsName;
        private String appPic;
        private int price;
        private int goodsId;

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
    }
}
