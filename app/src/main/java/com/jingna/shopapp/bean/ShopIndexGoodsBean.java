package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/28.
 */

public class ShopIndexGoodsBean {
    /**
     * status : 200
     * data : [{"goodsId":"50","goodsName":"烂樱桃","goodsPic":"/upload/83f0c6823d9fbe77978d57a6c45710d.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","goodsPrice":"12.00"},{"goodsId":"51","goodsName":"衬衫","goodsPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","goodsPrice":"1.00"},{"goodsId":"52","goodsName":"蓝薄荷","goodsPic":"/upload/83f0c6823d9fbe77978d57a6c45710d.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","goodsPrice":"1.00"}]
     * totalPage : 1
     * totalCount : 3
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
         * goodsId : 50
         * goodsName : 烂樱桃
         * goodsPic : /upload/83f0c6823d9fbe77978d57a6c45710d.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
         * goodsPrice : 12.00
         */

        private String goodsId;
        private String goodsName;
        private String goodsPic;
        private String goodsPrice;

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

        public String getGoodsPic() {
            return goodsPic;
        }

        public void setGoodsPic(String goodsPic) {
            this.goodsPic = goodsPic;
        }

        public String getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(String goodsPrice) {
            this.goodsPrice = goodsPrice;
        }
    }
}
