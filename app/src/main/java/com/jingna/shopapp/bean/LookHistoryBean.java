package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/12.
 */

public class LookHistoryBean {


    /**
     * status : 200
     * data : [{"goodsbean":[{"sellerId":34,"goodsName":"尼雅（NIYA）臻爱永恒 赤霞珠干红葡萄喜酒 750ml*6 整箱装 ","appPic":"upload/goods/2019-04-23/1b31ba76bf0f4a048a66a09b68e36093.jpg","price":399.12,"goodsId":70,"createTime":"2019-04-24","browseId":"37"},{"sellerId":34,"goodsName":"雪碧味儿葡萄酒","appPic":"upload/goods/2019-04-23/8034a6d802c04193a657a2c890d133ca.jpg","price":999,"goodsId":71,"createTime":"2019-04-24","browseId":"39"}],"datetime":"04月24日"},{"datetime":"04月23日","goodsbean":[{"sellerId":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":1,"goodsId":4,"createTime":"2019-04-23","browseId":"28"}]},{"datetime":"04月23日","goodsbean":[{"sellerId":11,"goodsName":"【稀缺货源抢先预定】HUAWEI/华为 P30 Pro麒麟980超感光徕卡四摄10倍变焦屏内指纹4G手机p20 Pro ","appPic":"upload/goods/2019-04-01/12ff698cf4784fccb06e51a4d519ee8c.jpg,upload/goods/2019-04-01/032cc2b7b684400595840454a59bcb09.jpg,upload/goods/2019-04-01/848445cc46824cf28acb3a8c3e3db709.jpg,upload/goods/2019-04-01/d9e73b0fa5c84dabab55e3bf70d603cf.jpg","price":9999,"goodsId":62,"createTime":"2019-04-23","browseId":"38"},{"sellerId":32,"goodsName":"佳能（Canon）EOS 750D 单反套机 (EF-S 18-135mm f/3.5-5.6 IS STM镜头)","appPic":"upload/goods/2019-04-23/a328b753a1d945a093a4dc0a3d3956f8.jpg,upload/goods/2019-04-23/57e30ad6fd55487ab11b319a08d554f4.jpg,upload/goods/2019-04-23/12a6b5bcdb534dd996b70bb05c12a82b.jpg,upload/goods/2019-04-23/58a69a2e4f09411bb7663337c361530a.jpg,upload/goods/2019-04-23/6b0cb0f5680b4eb2ab59a4376c558a54.png","price":5499,"goodsId":65,"createTime":"2019-04-23","browseId":"33"},{"sellerId":32,"goodsName":"美菱(MELING)252升 三门小型电冰箱 双变频一级能效风冷无霜 节能静音电脑控温宽幅变温 玫瑰金 BCD-252WP3CX","appPic":"upload/goods/2019-04-23/424101cf51d44ea096f76e7ffc22b753.jpg,upload/goods/2019-04-23/a76b0421a2b2435ea13e31fb02d993e3.jpg,upload/goods/2019-04-23/1616c156a20b4828a2bbf5da12a33f1d.jpg,upload/goods/2019-04-23/ad38ac5c6de549af99ba96c1a0d320b3.jpg,upload/goods/2019-04-23/965b3e398b604b6fabb089023c019e47.jpg,upload/goods/2019-04-23/0d8b2bfda2de4352b99c208bd8cbf0c0.jpg,upload/goods/2019-04-23/9215a06822974ce79fa952949051cd28.jpg,upload/goods/2019-04-23/4bf75f69637443638cb043eddfbace39.png","price":2399,"goodsId":69,"createTime":"2019-04-23","browseId":"34"},{"sellerId":32,"goodsName":"戴尔(DELL)灵越3670英特尔酷睿i5台式电脑整机(八代i5-8400 8G 128GSSD 1T 2G独显 WIFI 蓝牙 键鼠)23.6英寸","appPic":"upload/goods/2019-04-23/fa30b709564447ef8ed3a394e24ec3c2.jpg,upload/goods/2019-04-23/bf83945688a9418eb1d7301ac140ce29.jpg,upload/goods/2019-04-23/5d70e593521c4becbc0a89e87edb5503.jpg,upload/goods/2019-04-23/24035c784a7e4852b829188c731353f9.jpg,upload/goods/2019-04-23/fbf6fb6777fd42e2b1011a3be91b722e.jpg,upload/goods/2019-04-23/8a02dc4ed1b54c7daf5afd095bb8090d.jpg","price":4968,"goodsId":68,"createTime":"2019-04-23","browseId":"36"},{"sellerId":32,"goodsName":"戴尔(DELL)灵越3670英特尔酷睿i5台式电脑整机(八代i5-8400 8G 128GSSD 1T 2G独显 WIFI 蓝牙 键鼠)23.6英寸","appPic":"upload/goods/2019-04-23/3001cd2291414ef68ce640ee84cb37fa.jpg,upload/goods/2019-04-23/297535bee53f4daa8a8027bf17645fd6.jpg,upload/goods/2019-04-23/4089a9359f8f4a94b73a47e973dcfed6.jpg,upload/goods/2019-04-23/731d551f99b946f1ac1f44200cb0ee78.jpg,upload/goods/2019-04-23/b657f37fb9104709b3d1a93888d0a2d6.jpg,upload/goods/2019-04-23/74222d1ad87d403ba750d9deec95486d.jpg","price":4567,"goodsId":67,"createTime":"2019-04-23","browseId":"35"},{"createTime":"2019-04-23","browseId":"32"},{"sellerId":32,"goodsName":"佳能（Canon）EOS 750D 单反套机 (EF-S 18-135mm f/3.5-5.6 IS STM镜头)","appPic":"upload/goods/2019-04-23/f1666a7f610d4e299a44f81c0ef39a05.jpg,upload/goods/2019-04-23/810f0f427ad4472796d8b4f13c9bf27b.jpg,upload/goods/2019-04-23/b92fbecffc124732a58dfd282f97679a.jpg,upload/goods/2019-04-23/952c27bdb7384436bf20102a3e90e75d.jpg,upload/goods/2019-04-23/fcdd62aca9bd473fa8beaa428348a019.png","price":5499,"goodsId":66,"createTime":"2019-04-23","browseId":"31"}]}]
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
         * goodsbean : [{"sellerId":34,"goodsName":"尼雅（NIYA）臻爱永恒 赤霞珠干红葡萄喜酒 750ml*6 整箱装 ","appPic":"upload/goods/2019-04-23/1b31ba76bf0f4a048a66a09b68e36093.jpg","price":399.12,"goodsId":70,"createTime":"2019-04-24","browseId":"37"},{"sellerId":34,"goodsName":"雪碧味儿葡萄酒","appPic":"upload/goods/2019-04-23/8034a6d802c04193a657a2c890d133ca.jpg","price":999,"goodsId":71,"createTime":"2019-04-24","browseId":"39"}]
         * datetime : 04月24日
         */

        private String datetime;
        private List<GoodsbeanBean> goodsbean;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public List<GoodsbeanBean> getGoodsbean() {
            return goodsbean;
        }

        public void setGoodsbean(List<GoodsbeanBean> goodsbean) {
            this.goodsbean = goodsbean;
        }

        public static class GoodsbeanBean {
            /**
             * sellerId : 34
             * goodsName : 尼雅（NIYA）臻爱永恒 赤霞珠干红葡萄喜酒 750ml*6 整箱装
             * appPic : upload/goods/2019-04-23/1b31ba76bf0f4a048a66a09b68e36093.jpg
             * price : 399.12
             * goodsId : 70
             * createTime : 2019-04-24
             * browseId : 37
             */

            private int sellerId;
            private String goodsName;
            private String appPic;
            private double price;
            private int goodsId;
            private String createTime;
            private String browseId;

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

            public void setPrice(double price) {
                this.price = price;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getBrowseId() {
                return browseId;
            }

            public void setBrowseId(String browseId) {
                this.browseId = browseId;
            }
        }
    }
}
