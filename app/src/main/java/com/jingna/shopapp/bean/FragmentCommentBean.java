package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 */

public class FragmentCommentBean {

    /**
     * status : 200
     * data : [{"goodsComment":"很好吃不错","goodsCommentPic":"/upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg","commentTime":"2019-03-11T05:59:18.000+0000","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"},{"goodsComment":"确实好吃","commentTime":"2019-03-22T08:54:46.000+0000","memName":"哈哈哈","headPhoto":"/upload/447dfa41461547c2b63dd58647325416.png"}]
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
         * goodsComment : 很好吃不错
         * goodsCommentPic : /upload/13a825c68f296a31200e3503cc660e8.jpg,/upload/13a825c68f296a31200e3503cc660e8.jpg
         * commentTime : 2019-03-11T05:59:18.000+0000
         * memName : 哈哈哈
         * headPhoto : /upload/447dfa41461547c2b63dd58647325416.png
         */

        private String goodsComment;
        private String goodsCommentPic;
        private String commentTime;
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
    }
}
