package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/12.
 */

public class LookHistoryBean {

    /**
     * status : 200
     * data : [{"goodsbean":[{"goodsName":"开心果","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":20,"goodsId":5,"createTime":"2019-04-11","browseId":"3"},{"goodsName":"麦丽素","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":30,"goodsId":6,"createTime":"2019-04-11","browseId":"2"}],"datetime":"04月11日"},{"datetime":"04月10日","goodsbean":[{"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"goodsId":4,"createTime":"2019-04-10","browseId":"1"}]}]
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
         * goodsbean : [{"goodsName":"开心果","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":20,"goodsId":5,"createTime":"2019-04-11","browseId":"3"},{"goodsName":"麦丽素","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":30,"goodsId":6,"createTime":"2019-04-11","browseId":"2"}]
         * datetime : 04月11日
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
             * goodsName : 开心果
             * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
             * price : 20
             * goodsId : 5
             * createTime : 2019-04-11
             * browseId : 3
             */

            private String goodsName;
            private String appPic;
            private double price;
            private int goodsId;
            private String createTime;
            private String browseId;

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
