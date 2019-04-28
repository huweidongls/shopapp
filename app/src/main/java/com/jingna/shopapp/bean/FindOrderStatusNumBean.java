package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/4/28.
 */

public class FindOrderStatusNumBean {

    /**
     * status : 200
     * data : {"shippedNum":0,"notEvaluatedNum":0,"cancelledNum":0,"accomplishedNum":0,"unpaidNum":1}
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

    public static class DataBean {
        /**
         * shippedNum : 0
         * notEvaluatedNum : 0
         * cancelledNum : 0
         * accomplishedNum : 0
         * unpaidNum : 1
         */

        private int shippedNum;
        private int notEvaluatedNum;
        private int cancelledNum;
        private int accomplishedNum;
        private int unpaidNum;

        public int getShippedNum() {
            return shippedNum;
        }

        public void setShippedNum(int shippedNum) {
            this.shippedNum = shippedNum;
        }

        public int getNotEvaluatedNum() {
            return notEvaluatedNum;
        }

        public void setNotEvaluatedNum(int notEvaluatedNum) {
            this.notEvaluatedNum = notEvaluatedNum;
        }

        public int getCancelledNum() {
            return cancelledNum;
        }

        public void setCancelledNum(int cancelledNum) {
            this.cancelledNum = cancelledNum;
        }

        public int getAccomplishedNum() {
            return accomplishedNum;
        }

        public void setAccomplishedNum(int accomplishedNum) {
            this.accomplishedNum = accomplishedNum;
        }

        public int getUnpaidNum() {
            return unpaidNum;
        }

        public void setUnpaidNum(int unpaidNum) {
            this.unpaidNum = unpaidNum;
        }
    }
}
