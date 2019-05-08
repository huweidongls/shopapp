package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/5/7.
 */

public class AppCouponNumBean {

    /**
     * status : 200
     * data : {"isAlreadyNum":2,"alreadyNum":2,"isOverdueNum":1}
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
         * isAlreadyNum : 2
         * alreadyNum : 2
         * isOverdueNum : 1
         */

        private int isAlreadyNum;
        private int alreadyNum;
        private int isOverdueNum;

        public int getIsAlreadyNum() {
            return isAlreadyNum;
        }

        public void setIsAlreadyNum(int isAlreadyNum) {
            this.isAlreadyNum = isAlreadyNum;
        }

        public int getAlreadyNum() {
            return alreadyNum;
        }

        public void setAlreadyNum(int alreadyNum) {
            this.alreadyNum = alreadyNum;
        }

        public int getIsOverdueNum() {
            return isOverdueNum;
        }

        public void setIsOverdueNum(int isOverdueNum) {
            this.isOverdueNum = isOverdueNum;
        }
    }
}
