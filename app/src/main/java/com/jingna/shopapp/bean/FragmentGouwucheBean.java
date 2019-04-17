package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */

public class FragmentGouwucheBean {

    /**
     * status : 200
     * data : [{"goodsid":4,"goodsNum":2,"skuid":151,"sellerId":4,"sellerName":"测试商铺1","shopGoods":[{"id":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1000,"weight":1,"sellerName":"测试商铺1","skuid":151,"sp1":"黑色","sp2":"128G","sp3":"","goodsNum":2},{"id":5,"goodsName":"开心果","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1000,"weight":1,"sellerName":"测试商铺1","skuid":152,"sp1":"黑色","sp2":"128G","sp3":"","goodsNum":1},{"id":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":5888,"weight":1,"sellerName":"测试商铺1","skuid":146,"sp1":"蓝色","sp2":"64G","sp3":"","goodsNum":1}]},{"goodsid":6,"goodsNum":2,"skuid":153,"sellerId":5,"sellerName":"测试商铺2","shopGoods":[{"id":6,"goodsName":"麦丽素","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1000,"sellerName":"测试商铺2","skuid":153,"sp1":"黑色","sp2":"128G","sp3":"","goodsNum":2}]}]
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
         * goodsid : 4
         * goodsNum : 2
         * skuid : 151
         * sellerId : 4
         * sellerName : 测试商铺1
         * shopGoods : [{"id":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1000,"weight":1,"sellerName":"测试商铺1","skuid":151,"sp1":"黑色","sp2":"128G","sp3":"","goodsNum":2},{"id":5,"goodsName":"开心果","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1000,"weight":1,"sellerName":"测试商铺1","skuid":152,"sp1":"黑色","sp2":"128G","sp3":"","goodsNum":1},{"id":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":5888,"weight":1,"sellerName":"测试商铺1","skuid":146,"sp1":"蓝色","sp2":"64G","sp3":"","goodsNum":1}]
         */

        private int goodsid;
        private int goodsNum;
        private int skuid;
        private int sellerId;
        private boolean isSelect_shop;      //店铺是否在购物车中被选中
        private String sellerName;
        private List<ShopGoodsBean> shopGoods;

        public boolean getIsSelect_shop() {
            return isSelect_shop;
        }

        public void setIsSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getSkuid() {
            return skuid;
        }

        public void setSkuid(int skuid) {
            this.skuid = skuid;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public List<ShopGoodsBean> getShopGoods() {
            return shopGoods;
        }

        public void setShopGoods(List<ShopGoodsBean> shopGoods) {
            this.shopGoods = shopGoods;
        }

        public static class ShopGoodsBean {
            /**
             * id : 4
             * goodsName : 核桃
             * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
             * price : 1000
             * weight : 1
             * sellerName : 测试商铺1
             * skuid : 151
             * sp1 : 黑色
             * sp2 : 128G
             * sp3 :
             * goodsNum : 2
             */

            private int id;
            private String goodsName;
            private String appPic;
            private int price;
            private int weight;
            private String sellerName;
            private int skuid;
            private String sp1;
            private String sp2;
            private String sp3;
            private int goodsNum;
            private boolean isSelect;        //商品是否在购物车中被选中
            private String attributesStr;

            public String getAttributesStr() {
                return attributesStr;
            }

            public void setAttributesStr(String attributesStr) {
                this.attributesStr = attributesStr;
            }

            public boolean getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public int getSkuid() {
                return skuid;
            }

            public void setSkuid(int skuid) {
                this.skuid = skuid;
            }

            public String getSp1() {
                return sp1;
            }

            public void setSp1(String sp1) {
                this.sp1 = sp1;
            }

            public String getSp2() {
                return sp2;
            }

            public void setSp2(String sp2) {
                this.sp2 = sp2;
            }

            public String getSp3() {
                return sp3;
            }

            public void setSp3(String sp3) {
                this.sp3 = sp3;
            }

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }
        }
    }
}
