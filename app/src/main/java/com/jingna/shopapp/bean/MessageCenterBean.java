package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */

public class MessageCenterBean {


    /**
     * status : 200
     * data : [{"isImgOrText":"1","msgTitle":"你的账户要被冻结","newSendTime":"2019-04-16 11:12:02","typeName":"系统消息","msgAbstract":"你的账户要被冻结了，还不消停的？","msgUrl":"http://www.baidu.com","typeId":1,"toUserId":1,"msgPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","msgtypePic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","sendTime":"2019-04-16 11:12:02","status":"1"}]
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

    public static class DataBean {
        /**
         * isImgOrText : 1
         * msgTitle : 你的账户要被冻结
         * newSendTime : 2019-04-16 11:12:02
         * typeName : 系统消息
         * msgAbstract : 你的账户要被冻结了，还不消停的？
         * msgUrl : http://www.baidu.com
         * typeId : 1
         * toUserId : 1
         * msgPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * msgtypePic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * sendTime : 2019-04-16 11:12:02
         * status : 1
         */

        private String isImgOrText;
        private String msgTitle;
        private String newSendTime;
        private String typeName;
        private String msgAbstract;
        private String msgUrl;
        private int typeId;
        private int toUserId;
        private String msgPic;
        private String msgtypePic;
        private String sendTime;
        private String status;

        public String getIsImgOrText() {
            return isImgOrText;
        }

        public void setIsImgOrText(String isImgOrText) {
            this.isImgOrText = isImgOrText;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public String getNewSendTime() {
            return newSendTime;
        }

        public void setNewSendTime(String newSendTime) {
            this.newSendTime = newSendTime;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getMsgAbstract() {
            return msgAbstract;
        }

        public void setMsgAbstract(String msgAbstract) {
            this.msgAbstract = msgAbstract;
        }

        public String getMsgUrl() {
            return msgUrl;
        }

        public void setMsgUrl(String msgUrl) {
            this.msgUrl = msgUrl;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getToUserId() {
            return toUserId;
        }

        public void setToUserId(int toUserId) {
            this.toUserId = toUserId;
        }

        public String getMsgPic() {
            return msgPic;
        }

        public void setMsgPic(String msgPic) {
            this.msgPic = msgPic;
        }

        public String getMsgtypePic() {
            return msgtypePic;
        }

        public void setMsgtypePic(String msgtypePic) {
            this.msgtypePic = msgtypePic;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
