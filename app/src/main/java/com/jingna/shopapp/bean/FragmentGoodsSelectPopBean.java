package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/19.
 */

public class FragmentGoodsSelectPopBean {

    /**
     * status : 200
     * data : [{"price":10,"attributeName":"包装单位","goodsId":"4","inputList":"简装,瓶装,袋装","goodsSn":"1112312","pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg"},{"price":10,"attributeName":"成分","goodsId":"4","inputList":"豌豆,大豆,苞米豆","goodsSn":"1112312","pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg"},{"price":10,"attributeName":"是否添加蔗糖","goodsId":"4","inputList":"含蔗糖,不含蔗糖","goodsSn":"1112312","pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg"}]
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
         * price : 10
         * attributeName : 包装单位
         * goodsId : 4
         * inputList : 简装,瓶装,袋装
         * goodsSn : 1112312
         * pic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         */

        private int price;
        private String attributeName;
        private String goodsId;
        private String inputList;
        private String goodsSn;
        private String pic;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getInputList() {
            return inputList;
        }

        public void setInputList(String inputList) {
            this.inputList = inputList;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}