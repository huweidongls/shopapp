package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */

public class DetailsOrderBean {

    /**
     * status : 200
     * data : {"id":"1555317742584","userName":"18686817319","userId":"30","addresId":"9","addresPhone":"15246464646","addresUname":"明年","addresName":"哦咯了","addresCode":"150000","sellerId":"4","sellerName":"测试商铺1","sellerPhone":"1231","invoiceId":"发票ID","invoiceType":"发票类型","invoiceContent":"发票内容","invoiceCode":"发票号","invoiceTitle":"发票抬头","skuId":"1","goodsId":"4","orderStatus":"8","paymentTime":"2019-04-15T08:42:18.000+0000","createTime":"2019-04-15T08:42:12.000+0000","orderPrice":1,"isDelete":"0","refundTime":"2019-04-16T08:42:39.000+0000","list":[{"id":28,"goodsName":"核桃","goodsNumber":1,"sukSpecifications":"简装,不含蔗糖,豌豆,","goodsPrice":1,"createTime":"2019-04-15T08:42:12.000+0000","skuId":"1","goodsId":"4","sellerId":"4","goodsOrderId":"1555317742584","goodPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg"}]}
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
         * id : 1555317742584
         * userName : 18686817319
         * userId : 30
         * addresId : 9
         * addresPhone : 15246464646
         * addresUname : 明年
         * addresName : 哦咯了
         * addresCode : 150000
         * sellerId : 4
         * sellerName : 测试商铺1
         * sellerPhone : 1231
         * invoiceId : 发票ID
         * invoiceType : 发票类型
         * invoiceContent : 发票内容
         * invoiceCode : 发票号
         * invoiceTitle : 发票抬头
         * skuId : 1
         * goodsId : 4
         * orderStatus : 8
         * paymentTime : 2019-04-15T08:42:18.000+0000
         * createTime : 2019-04-15T08:42:12.000+0000
         * orderPrice : 1
         * isDelete : 0
         * refundTime : 2019-04-16T08:42:39.000+0000
         * list : [{"id":28,"goodsName":"核桃","goodsNumber":1,"sukSpecifications":"简装,不含蔗糖,豌豆,","goodsPrice":1,"createTime":"2019-04-15T08:42:12.000+0000","skuId":"1","goodsId":"4","sellerId":"4","goodsOrderId":"1555317742584","goodPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg"}]
         */

        private String id;
        private String userName;
        private String userId;
        private String addresId;
        private String addresPhone;
        private String addresUname;
        private String addresName;
        private String addresCode;
        private String sellerId;
        private String sellerName;
        private String sellerPhone;
        private String invoiceId;
        private String invoiceType;
        private String invoiceContent;
        private String invoiceCode;
        private String invoiceTitle;
        private String skuId;
        private String goodsId;
        private String orderStatus;
        private String paymentTime;
        private String createTime;
        private double orderPrice;
        private String isDelete;
        private String refundTime;
        private List<ListBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddresId() {
            return addresId;
        }

        public void setAddresId(String addresId) {
            this.addresId = addresId;
        }

        public String getAddresPhone() {
            return addresPhone;
        }

        public void setAddresPhone(String addresPhone) {
            this.addresPhone = addresPhone;
        }

        public String getAddresUname() {
            return addresUname;
        }

        public void setAddresUname(String addresUname) {
            this.addresUname = addresUname;
        }

        public String getAddresName() {
            return addresName;
        }

        public void setAddresName(String addresName) {
            this.addresName = addresName;
        }

        public String getAddresCode() {
            return addresCode;
        }

        public void setAddresCode(String addresCode) {
            this.addresCode = addresCode;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerPhone() {
            return sellerPhone;
        }

        public void setSellerPhone(String sellerPhone) {
            this.sellerPhone = sellerPhone;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public void setInvoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public String getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(String invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceCode() {
            return invoiceCode;
        }

        public void setInvoiceCode(String invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 28
             * goodsName : 核桃
             * goodsNumber : 1
             * sukSpecifications : 简装,不含蔗糖,豌豆,
             * goodsPrice : 1
             * createTime : 2019-04-15T08:42:12.000+0000
             * skuId : 1
             * goodsId : 4
             * sellerId : 4
             * goodsOrderId : 1555317742584
             * goodPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
             */

            private int id;
            private String goodsName;
            private int goodsNumber;
            private String sukSpecifications;
            private double goodsPrice;
            private String createTime;
            private String skuId;
            private String goodsId;
            private String sellerId;
            private String goodsOrderId;
            private String goodPic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getGoodsNumber() {
                return goodsNumber;
            }

            public void setGoodsNumber(int goodsNumber) {
                this.goodsNumber = goodsNumber;
            }

            public String getSukSpecifications() {
                return sukSpecifications;
            }

            public void setSukSpecifications(String sukSpecifications) {
                this.sukSpecifications = sukSpecifications;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public String getGoodsOrderId() {
                return goodsOrderId;
            }

            public void setGoodsOrderId(String goodsOrderId) {
                this.goodsOrderId = goodsOrderId;
            }

            public String getGoodPic() {
                return goodPic;
            }

            public void setGoodPic(String goodPic) {
                this.goodPic = goodPic;
            }
        }
    }
}
