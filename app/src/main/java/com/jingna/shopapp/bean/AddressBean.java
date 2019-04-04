package com.jingna.shopapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/2/28.
 */

public class AddressBean {

    /**
     * status : 200
     * data : [{"id":1,"memberId":"30","consignee":"哈哈哈","adress":"汉广街41号","acquiescentAdress":"0","location":"黑龙江-哈尔滨-南岗区","consigneeTel":"18686817319","zipCode":"150000","status":"1","createDate":"2019-02-28T03:34:54.000+0000","updateDate":"2019-02-28T03:34:56.000+0000"}]
     * totalPage : 0
     * totalCount : 1
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * memberId : 30
         * consignee : 哈哈哈
         * adress : 汉广街41号
         * acquiescentAdress : 0
         * location : 黑龙江-哈尔滨-南岗区
         * consigneeTel : 18686817319
         * zipCode : 150000
         * status : 1
         * createDate : 2019-02-28T03:34:54.000+0000
         * updateDate : 2019-02-28T03:34:56.000+0000
         */

        private int id;
        private String memberId;
        private String consignee;
        private String adress;
        private String acquiescentAdress;
        private String location;
        private String consigneeTel;
        private String zipCode;
        private String status;
        private String createDate;
        private String updateDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getAcquiescentAdress() {
            return acquiescentAdress;
        }

        public void setAcquiescentAdress(String acquiescentAdress) {
            this.acquiescentAdress = acquiescentAdress;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getConsigneeTel() {
            return consigneeTel;
        }

        public void setConsigneeTel(String consigneeTel) {
            this.consigneeTel = consigneeTel;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
