package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/8.
 */

public class Attention_Goods_listBean {

    /**
     * status : 200
     * data : [{"sellerId":11,"goodsName":"【稀缺货源抢先预定】HUAWEI/华为 P30 Pro麒麟980超感光徕卡四摄10倍变焦屏内指纹4G手机p20 Pro ","appPic":"upload/goods/2019-04-01/12ff698cf4784fccb06e51a4d519ee8c.jpg,upload/goods/2019-04-01/032cc2b7b684400595840454a59bcb09.jpg,upload/goods/2019-04-01/848445cc46824cf28acb3a8c3e3db709.jpg,upload/goods/2019-04-01/d9e73b0fa5c84dabab55e3bf70d603cf.jpg","price":9999,"goodsId":62},{"sellerId":32,"goodsName":"美菱(MELING)252升 三门小型电冰箱 双变频一级能效风冷无霜 节能静音电脑控温宽幅变温 玫瑰金 BCD-252WP3CX","appPic":"upload/goods/2019-04-23/424101cf51d44ea096f76e7ffc22b753.jpg,upload/goods/2019-04-23/a76b0421a2b2435ea13e31fb02d993e3.jpg,upload/goods/2019-04-23/1616c156a20b4828a2bbf5da12a33f1d.jpg,upload/goods/2019-04-23/ad38ac5c6de549af99ba96c1a0d320b3.jpg,upload/goods/2019-04-23/965b3e398b604b6fabb089023c019e47.jpg,upload/goods/2019-04-23/0d8b2bfda2de4352b99c208bd8cbf0c0.jpg,upload/goods/2019-04-23/9215a06822974ce79fa952949051cd28.jpg,upload/goods/2019-04-23/4bf75f69637443638cb043eddfbace39.png","price":2399,"goodsId":69}]
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
         * sellerId : 11
         * goodsName : 【稀缺货源抢先预定】HUAWEI/华为 P30 Pro麒麟980超感光徕卡四摄10倍变焦屏内指纹4G手机p20 Pro
         * appPic : upload/goods/2019-04-01/12ff698cf4784fccb06e51a4d519ee8c.jpg,upload/goods/2019-04-01/032cc2b7b684400595840454a59bcb09.jpg,upload/goods/2019-04-01/848445cc46824cf28acb3a8c3e3db709.jpg,upload/goods/2019-04-01/d9e73b0fa5c84dabab55e3bf70d603cf.jpg
         * price : 9999
         * goodsId : 62
         */

        private int sellerId;
        private String goodsName;
        private String appPic;
        private double price;
        private int goodsId;

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

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public double getPrice() {
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
