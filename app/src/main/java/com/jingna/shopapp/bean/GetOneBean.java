package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/2/27.
 */

public class GetOneBean {

    /**
     * status : 200
     * data : {"id":30,"gender":"0","memBirthday":"2019-02-08","memName":"哈哈哈","headPhoto":"/upload/9b32751e38da4654823e4b39712fead1.png","username":"18686817319","password":"e10adc3949ba59abbe56e057f20f883e","status":0,"phoneNum":"18686817319","newTime":"2019-02-27T08:21:43.000+0000"}
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
         * id : 30
         * gender : 0
         * memBirthday : 2019-02-08
         * memName : 哈哈哈
         * headPhoto : /upload/9b32751e38da4654823e4b39712fead1.png
         * username : 18686817319
         * password : e10adc3949ba59abbe56e057f20f883e
         * status : 0
         * phoneNum : 18686817319
         * newTime : 2019-02-27T08:21:43.000+0000
         */

        private int id;
        private String gender;
        private String memBirthday;
        private String memName;
        private String headPhoto;
        private String username;
        private String password;
        private int status;
        private String phoneNum;
        private String newTime;
        private int goodsNum;
        private int sellerNum;
        private int browseRecord;

        public int getBrowseRecord() {
            return browseRecord;
        }

        public void setBrowseRecord(int browseRecord) {
            this.browseRecord = browseRecord;
        }

        public int getSellerNum() {
            return sellerNum;
        }

        public void setSellerNum(int sellerNum) {
            this.sellerNum = sellerNum;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMemBirthday() {
            return memBirthday;
        }

        public void setMemBirthday(String memBirthday) {
            this.memBirthday = memBirthday;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getNewTime() {
            return newTime;
        }

        public void setNewTime(String newTime) {
            this.newTime = newTime;
        }
    }
}
