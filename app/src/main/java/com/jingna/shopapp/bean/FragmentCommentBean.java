package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 */

public class FragmentCommentBean {

    /**
     * status : 200
     * data : [{"goodsComment":"1","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","commentTime":"2019-03-29 07:34:33","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png","goodsName":"核桃","commentLevel":3}]
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
         * goodsComment : 1
         * goodsCommentPic : /upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
         * commentTime : 2019-03-29 07:34:33
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
