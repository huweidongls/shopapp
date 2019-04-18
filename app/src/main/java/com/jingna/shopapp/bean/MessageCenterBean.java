package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */

public class MessageCenterBean {


    /**
     * status : 200
     * data : [{"isImgOrText":"1","msgTitle":"你的账户要被冻结","typeName":"系统消息","msgAbstract":"你的账户要被冻结了，还不消停的？","pId":1,"msgUrl":"http://www.baidu.com","typeId":1,"toUserId":36,"msgPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","msgtypePic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","sendTime":"2019-04-18 16:14:55","status":"0"},{"isImgOrText":"1","msgTitle":"你的账户要被冻结","typeName":"系统消息","msgAbstract":"你的账户要被冻结了，还不消停的？","pId":1,"msgUrl":"http://www.baidu.com","typeId":1,"toUserId":32,"msgPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","msgtypePic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","sendTime":"2019-04-18 16:14:51","status":"0"},{"isImgOrText":"1","msgTitle":"你的账户要被冻结","typeName":"系统消息","msgAbstract":"你的账户要被冻结了，还不消停的？","pId":1,"msgUrl":"http://www.baidu.com","typeId":1,"toUserId":34,"msgPic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","msgtypePic":"/upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg","sendTime":"2019-04-18 16:14:54","status":"0"}]
     * totalPage : 0
     * totalCount : 3
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
         * typeName : 系统消息
         * msgAbstract : 你的账户要被冻结了，还不消停的？
         * pId : 1
         * msgUrl : http://www.baidu.com
         * typeId : 1
         * toUserId : 36
         * msgPic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * msgtypePic : /upload/ec53111b83fb2ccdc2f503bdb4d3af0.jpg
         * sendTime : 2019-04-18 16:14:55
         * status : 0
         */

        private String isImgOrText;
        private String msgTitle;
        private String typeName;
        private String msgAbstract;
        private int pId;
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

        public int getPId() {
            return pId;
        }

        public void setPId(int pId) {
            this.pId = pId;
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
