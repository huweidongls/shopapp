package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */

public class FragmentGouwucheBean {

    /**
     * status : 200
     * data : [{"goodsid":4,"goodsNum":2,"skuid":1,"sellerId":4,"sellerName":"测试商铺1","shopGoods":[{"id":4,"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1,"weight":1,"sellerName":"测试商铺1","productName":"核桃","goodsNum":2},{"id":5,"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","price":44,"weight":1,"sellerName":"测试商铺1","productName":"开心果","goodsNum":1}]},{"goodsid":6,"goodsNum":2,"skuid":22,"sellerId":5,"sellerName":"测试商铺2","shopGoods":[{"id":6,"pic":"/upload/83f0c6823d9fbe77978d57a6c45710d.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","price":44,"sellerName":"测试商铺2","productName":"麦丽素","goodsNum":2}]}]
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
         * skuid : 1
         * sellerId : 4
         * sellerName : 测试商铺1
         * shopGoods : [{"id":4,"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1,"weight":1,"sellerName":"测试商铺1","productName":"核桃","goodsNum":2},{"id":5,"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","price":44,"weight":1,"sellerName":"测试商铺1","productName":"开心果","goodsNum":1}]
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
             * pic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
             * price : 1
             * weight : 1
             * sellerName : 测试商铺1
             * productName : 核桃
             * goodsNum : 2
             */

            private int id;
            private String pic;
            private int price;
            private int weight;
            private String sellerName;
            private String productName;
            private int goodsNum;
            private boolean isSelect;        //商品是否在购物车中被选中

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

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
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
