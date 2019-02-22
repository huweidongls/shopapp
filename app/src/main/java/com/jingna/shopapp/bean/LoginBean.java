package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/2/22.
 */

public class LoginBean {

    /**
     * status : 200
     * data : {"userId":1,"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTA4MDAzNjgzMzcsImV4cCI6MTU1MTQwNTE2OH0.7Vlj5mb2IBd-0Q_UcgcbGpZDSZAcPZwNFGFVKyCFt818LCtvqkh-h1Y2ERxG_vpDeeDZg6myCKiwY4Qawt_pmw"}
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
         * userId : 1
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTA4MDAzNjgzMzcsImV4cCI6MTU1MTQwNTE2OH0.7Vlj5mb2IBd-0Q_UcgcbGpZDSZAcPZwNFGFVKyCFt818LCtvqkh-h1Y2ERxG_vpDeeDZg6myCKiwY4Qawt_pmw
         */

        private int userId;
        private String token;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
