package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 */

public class AppCouponBean {

    /**
     * status : 200
     * data : [{"couponId":"23","couponName":"核桃专用优惠券","maxMoney":"100","sumDiscount":"50","parameter":"0.75","type":"1","validTime":"2019-05-01 00:00:00","overDueTime":"2019-05-07 00:00:00","usageMode":"2","goodsId":"4"}]
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
         * couponId : 23
         * couponName : 核桃专用优惠券
         * maxMoney : 100
         * sumDiscount : 50
         * parameter : 0.75
         * type : 1
         * validTime : 2019-05-01 00:00:00
         * overDueTime : 2019-05-07 00:00:00
         * usageMode : 2
         * goodsId : 4
         */

        private String couponId;
        private String couponName;
        private String maxMoney;
        private String sumDiscount;
        private String parameter;
        private String type;
        private String validTime;
        private String overDueTime;
        private String usageMode;
        private String goodsId;
        private String categoryId;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(String maxMoney) {
            this.maxMoney = maxMoney;
        }

        public String getSumDiscount() {
            return sumDiscount;
        }

        public void setSumDiscount(String sumDiscount) {
            this.sumDiscount = sumDiscount;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValidTime() {
            return validTime;
        }

        public void setValidTime(String validTime) {
            this.validTime = validTime;
        }

        public String getOverDueTime() {
            return overDueTime;
        }

        public void setOverDueTime(String overDueTime) {
            this.overDueTime = overDueTime;
        }

        public String getUsageMode() {
            return usageMode;
        }

        public void setUsageMode(String usageMode) {
            this.usageMode = usageMode;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }
}
