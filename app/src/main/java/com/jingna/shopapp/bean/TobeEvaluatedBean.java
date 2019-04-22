package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/22.
 */

public class TobeEvaluatedBean {


    /**
     * status : 200
     * data : [{"goodsId":"62","goodsComment":"1111","goodsName":"【稀缺货源抢先预定】HUAWEI/华为 P30 Pro麒麟980超感光徕卡四摄10倍变焦屏内指纹4G手机p20 Pro ","commentLevel":4,"orderId":"1555667187196","sellerName":"测试商铺3","appPic":"upload/goods/2019-04-01/12ff698cf4784fccb06e51a4d519ee8c.jpg,upload/goods/2019-04-01/032cc2b7b684400595840454a59bcb09.jpg,upload/goods/2019-04-01/848445cc46824cf28acb3a8c3e3db709.jpg,upload/goods/2019-04-01/d9e73b0fa5c84dabab55e3bf70d603cf.jpg"}]
     * totalPage : 1
     * totalCount : 1
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
         * goodsId : 62
         * goodsComment : 1111
         * goodsName : 【稀缺货源抢先预定】HUAWEI/华为 P30 Pro麒麟980超感光徕卡四摄10倍变焦屏内指纹4G手机p20 Pro
         * commentLevel : 4
         * orderId : 1555667187196
         * sellerName : 测试商铺3
         * appPic : upload/goods/2019-04-01/12ff698cf4784fccb06e51a4d519ee8c.jpg,upload/goods/2019-04-01/032cc2b7b684400595840454a59bcb09.jpg,upload/goods/2019-04-01/848445cc46824cf28acb3a8c3e3db709.jpg,upload/goods/2019-04-01/d9e73b0fa5c84dabab55e3bf70d603cf.jpg
         */

        private String goodsId;
        private String goodsComment;
        private String goodsName;
        private int commentLevel;
        private String orderId;
        private String sellerName;
        private String appPic;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsComment() {
            return goodsComment;
        }

        public void setGoodsComment(String goodsComment) {
            this.goodsComment = goodsComment;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getCommentLevel() {
            return commentLevel;
        }

        public void setCommentLevel(int commentLevel) {
            this.commentLevel = commentLevel;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }
    }
}
