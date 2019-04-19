package com.jingna.shopapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 */

public class FragmentGoodsBean implements Serializable {

    /**
     * status : 200
     * data : {"commentList":[{"goodsComment":"1","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","commentTime":"2019-03-22T08:54:40.000+0000","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png","goodsName":"核桃","commentLevel":"3"}],"shopGoods":{"sellerId":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"subTitle":"草莓味,菠萝味","favorableRate":"99","sellerName":"测试商铺1","followSellerNum":"0","appSellerLogo":"/upload/samsung.jpg","memberStatus":"0"},"commentTotalNum":1}
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

    public static class DataBean implements Serializable {
        /**
         * commentList : [{"goodsComment":"1","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","commentTime":"2019-03-22T08:54:40.000+0000","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png","goodsName":"核桃","commentLevel":"3"}]
         * shopGoods : {"sellerId":4,"goodsName":"核桃","appPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","price":10,"subTitle":"草莓味,菠萝味","favorableRate":"99","sellerName":"测试商铺1","followSellerNum":"0","appSellerLogo":"/upload/samsung.jpg","memberStatus":"0"}
         * commentTotalNum : 1
         */

        private ShopGoodsBean shopGoods;
        private int commentTotalNum;
        private List<CommentListBean> commentList;

        public ShopGoodsBean getShopGoods() {
            return shopGoods;
        }

        public void setShopGoods(ShopGoodsBean shopGoods) {
            this.shopGoods = shopGoods;
        }

        public int getCommentTotalNum() {
            return commentTotalNum;
        }

        public void setCommentTotalNum(int commentTotalNum) {
            this.commentTotalNum = commentTotalNum;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class ShopGoodsBean implements Serializable {
            /**
             * sellerId : 4
             * goodsName : 核桃
             * appPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
             * price : 10
             * subTitle : 草莓味,菠萝味
             * favorableRate : 99
             * sellerName : 测试商铺1
             * followSellerNum : 0
             * appSellerLogo : /upload/samsung.jpg
             * memberStatus : 0
             */

            private int sellerId;
            private String goodsName;
            private String appPic;
            private double price;
            private String subTitle;
            private String favorableRate;
            private String sellerName;
            private String followSellerNum;
            private String appSellerLogo;
            private String memberStatus;

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

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getFavorableRate() {
                return favorableRate;
            }

            public void setFavorableRate(String favorableRate) {
                this.favorableRate = favorableRate;
            }

            public String getSellerName() {
                return sellerName;
            }

            public void setSellerName(String sellerName) {
                this.sellerName = sellerName;
            }

            public String getFollowSellerNum() {
                return followSellerNum;
            }

            public void setFollowSellerNum(String followSellerNum) {
                this.followSellerNum = followSellerNum;
            }

            public String getAppSellerLogo() {
                return appSellerLogo;
            }

            public void setAppSellerLogo(String appSellerLogo) {
                this.appSellerLogo = appSellerLogo;
            }

            public String getMemberStatus() {
                return memberStatus;
            }

            public void setMemberStatus(String memberStatus) {
                this.memberStatus = memberStatus;
            }
        }

        public static class CommentListBean implements Serializable {
            /**
             * goodsComment : 1
             * goodsCommentPic : /upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
             * commentTime : 2019-03-22T08:54:40.000+0000
             * memName : 哈哈哈
             * headPhoto : /upload/447dfa41461547c2b63dd58647325416.png
             * goodsName : 核桃
             * commentLevel : 3
             */

            private String goodsComment;
            private String goodsCommentPic;
            private String commentTime;
            private String memName;
            private String headPhoto;
            private String goodsName;
            private int commentLevel;

            public String getGoodsComment() {
                return goodsComment;
            }

            public void setGoodsComment(String goodsComment) {
                this.goodsComment = goodsComment;
            }

            public String getGoodsCommentPic() {
                return goodsCommentPic;
            }

            public void setGoodsCommentPic(String goodsCommentPic) {
                this.goodsCommentPic = goodsCommentPic;
            }

            public String getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(String commentTime) {
                this.commentTime = commentTime;
            }

            public String getMemName() {
                return memName;
            }

            public void setMemName(String memName) {
                this.memName = memName;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
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
        }
    }
}
