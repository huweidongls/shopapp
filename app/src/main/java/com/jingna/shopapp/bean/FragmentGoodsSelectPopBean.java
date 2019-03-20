package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/19.
 */

public class FragmentGoodsSelectPopBean {

    /**
     * status : 200
     * data : {"goods":[{"price":22,"productId":"5","productSn":"","pic":"/upload/13a825c68f296a31200e3503cc660e8.jpg"}],"attrList":[{"attributeName":"包装单位","attribute":"简装,瓶装,袋装"},{"attributeName":"形状","attribute":"巧克力棒,条状,圆球"},{"attributeName":"成分","attribute":"豌豆,大豆,苞米豆"},{"attributeName":"是否添加蔗糖","attribute":"含蔗糖,不含蔗糖"}]}
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
        private List<GoodsBean> goods;
        private List<AttrListBean> attrList;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<AttrListBean> getAttrList() {
            return attrList;
        }

        public void setAttrList(List<AttrListBean> attrList) {
            this.attrList = attrList;
        }

        public static class GoodsBean {
            /**
             * price : 22
             * productId : 5
             * productSn :
             * pic : /upload/13a825c68f296a31200e3503cc660e8.jpg
             */

            private int price;
            private String productId;
            private String productSn;
            private String pic;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductSn() {
                return productSn;
            }

            public void setProductSn(String productSn) {
                this.productSn = productSn;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class AttrListBean {
            /**
             * attributeName : 包装单位
             * attribute : 简装,瓶装,袋装
             */

            private String attributeName;
            private String attribute;

            public String getAttributeName() {
                return attributeName;
            }

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public String getAttribute() {
                return attribute;
            }

            public void setAttribute(String attribute) {
                this.attribute = attribute;
            }
        }
    }
}
