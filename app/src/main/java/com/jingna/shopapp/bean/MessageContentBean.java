package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/4/19.
 */

public class MessageContentBean {
    /**
     * status : 200
     * data : {"id":"1","msgTypeId":"1","createTime":"2019-04-16 11:08:48","updateTime":"2019-04-16 11:09:20","status":"1","msgContent":"开开心心嗨嗨皮皮","createUser":"admin","msgUrl":"http://www.baidu.com","msgPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","msgType":"1","msgAbstract":"你的账户要被冻结了，还不消停的？","msgTitle":"你的账户要被冻结"}
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
         * id : 1
         * msgTypeId : 1
         * createTime : 2019-04-16 11:08:48
         * updateTime : 2019-04-16 11:09:20
         * status : 1
         * msgContent : 开开心心嗨嗨皮皮
         * createUser : admin
         * msgUrl : http://www.baidu.com
         * msgPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * msgType : 1
         * msgAbstract : 你的账户要被冻结了，还不消停的？
         * msgTitle : 你的账户要被冻结
         */

        private String id;
        private String msgTypeId;
        private String createTime;
        private String updateTime;
        private String status;
        private String msgContent;
        private String createUser;
        private String msgUrl;
        private String msgPic;
        private String msgType;
        private String msgAbstract;
        private String msgTitle;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMsgTypeId() {
            return msgTypeId;
        }

        public void setMsgTypeId(String msgTypeId) {
            this.msgTypeId = msgTypeId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getMsgUrl() {
            return msgUrl;
        }

        public void setMsgUrl(String msgUrl) {
            this.msgUrl = msgUrl;
        }

        public String getMsgPic() {
            return msgPic;
        }

        public void setMsgPic(String msgPic) {
            this.msgPic = msgPic;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public String getMsgAbstract() {
            return msgAbstract;
        }

        public void setMsgAbstract(String msgAbstract) {
            this.msgAbstract = msgAbstract;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }
    }
}
