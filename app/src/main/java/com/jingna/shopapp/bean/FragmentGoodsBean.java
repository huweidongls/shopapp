package com.jingna.shopapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 */

public class FragmentGoodsBean implements Serializable {

    /**
     * status : 200
     * data : {"commentList":[{"goodsComment":"很好吃不错","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"},{"goodsComment":"确实好吃","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"}],"shopGoods":{"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","price":20,"productName":"开心果"}}
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
         * commentList : [{"goodsComment":"很好吃不错","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"},{"goodsComment":"确实好吃","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"}]
         * shopGoods : {"pic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","price":20,"productName":"开心果"}
         */

        private ShopGoodsBean shopGoods;
        private List<CommentListBean> commentList;

        public ShopGoodsBean getShopGoods() {
            return shopGoods;
        }

        public void setShopGoods(ShopGoodsBean shopGoods) {
            this.shopGoods = shopGoods;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class ShopGoodsBean implements Serializable {
            /**
             * pic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
             * price : 20
             * goodsName : 开心果
             */

            private String pic;
            private int price;
            private String goodsName;

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

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }

        public static class CommentListBean implements Serializable {
            /**
             * goodsComment : 很好吃不错
             * goodsCommentPic : /upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
             * memName : 哈哈哈
             * headPhoto : /upload/447dfa41461547c2b63dd58647325416.png
             */

            private String goodsComment;
            private String goodsCommentPic;
            private String memName;
            private String headPhoto;

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
        }
    }
}
