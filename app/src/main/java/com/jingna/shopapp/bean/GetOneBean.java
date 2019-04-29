package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/2/27.
 */

public class GetOneBean {

    /**
     * status : 200
     * data : {"goodsNum":1,"sellerNum":1,"browseRecord":4,"memberUserInfo":{"id":30,"gender":"0","memBirthday":"2019-02-08","memName":"嘻嘻","headPhoto":"/upload/9cede2d9bea8444d90177dc111983eca.png","username":"18686817319","password":"25f9e794323b453885f5181f1b624d0b","memBalance":"123","memIntegral":"10000","phoneNum":"18686817319","newTime":"2019年03月04日"}}
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
         * goodsNum : 1
         * sellerNum : 1
         * browseRecord : 4
         * memberUserInfo : {"id":30,"gender":"0","memBirthday":"2019-02-08","memName":"嘻嘻","headPhoto":"/upload/9cede2d9bea8444d90177dc111983eca.png","username":"18686817319","password":"25f9e794323b453885f5181f1b624d0b","memBalance":"123","memIntegral":"10000","phoneNum":"18686817319","newTime":"2019年03月04日"}
         */

        private int goodsNum;
        private int sellerNum;
        private int browseRecord;
        private MemberUserInfoBean memberUserInfo;

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getSellerNum() {
            return sellerNum;
        }

        public void setSellerNum(int sellerNum) {
            this.sellerNum = sellerNum;
        }

        public int getBrowseRecord() {
            return browseRecord;
        }

        public void setBrowseRecord(int browseRecord) {
            this.browseRecord = browseRecord;
        }

        public MemberUserInfoBean getMemberUserInfo() {
            return memberUserInfo;
        }

        public void setMemberUserInfo(MemberUserInfoBean memberUserInfo) {
            this.memberUserInfo = memberUserInfo;
        }

        public static class MemberUserInfoBean {
            /**
             * id : 30
             * gender : 0
             * memBirthday : 2019-02-08
             * memName : 嘻嘻
             * headPhoto : /upload/9cede2d9bea8444d90177dc111983eca.png
             * username : 18686817319
             * password : 25f9e794323b453885f5181f1b624d0b
             * memBalance : 123
             * memIntegral : 10000
             * phoneNum : 18686817319
             * newTime : 2019年03月04日
             */

            private int id;
            private String gender;
            private String memBirthday;
            private String memName;
            private String headPhoto;
            private String username;
            private String password;
            private String memBalance;
            private String memIntegral;
            private String phoneNum;
            private String newTime;

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

            public String getMemBalance() {
                return memBalance;
            }

            public void setMemBalance(String memBalance) {
                this.memBalance = memBalance;
            }

            public String getMemIntegral() {
                return memIntegral;
            }

            public void setMemIntegral(String memIntegral) {
                this.memIntegral = memIntegral;
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
}
