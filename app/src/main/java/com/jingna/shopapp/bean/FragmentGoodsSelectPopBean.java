package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/19.
 */

public class FragmentGoodsSelectPopBean {

    /**
     * status : 200
     * data : [{"price":10,"appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","attributeName":"包装单位","goodsId":"4","inputList":"简装,瓶装,袋装","goodsSn":"1112312","attrId":"2","attrType":"简装"},{"price":10,"appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","attributeName":"成分","goodsId":"4","inputList":"豌豆,大豆,苞米豆","goodsSn":"1112312","attrId":"4","attrType":"豌豆"},{"price":10,"appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","attributeName":"是否添加蔗糖","goodsId":"4","inputList":"含蔗糖,不含蔗糖","goodsSn":"1112312","attrId":"3","attrType":"含蔗糖"}]
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
         * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * attributeName : 包装单位
         * goodsId : 4
         * inputList : 简装,瓶装,袋装
         * goodsSn : 1112312
         * attrId : 2
         * attrType : 简装
         */

        private double price;
        private String appPic;
        private String attributeName;
        private String goodsId;
        private String value;
        private String goodsSn;
        private String attrId;
        private String attrType;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
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
            return value;
        }

        public void setInputList(String inputList) {
            this.value = inputList;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public String getAttrId() {
            return attrId;
        }

        public void setAttrId(String attrId) {
            this.attrId = attrId;
        }

        public String getAttrType() {
            return attrType;
        }

        public void setAttrType(String attrType) {
            this.attrType = attrType;
        }
    }
}
