package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 */

public class ChoiceMenuBean {

    /**
     * status : 200
     * data : [{"attributeId":1,"attributeList":"巧克力棒,条状,圆球","attributeName":"形状"},{"attributeId":2,"attributeList":"简装,瓶装,袋装","attributeName":"包装单位"},{"attributeId":3,"attributeList":"含蔗糖,不含蔗糖","attributeName":"是否添加蔗糖"}]
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
         * attributeId : 1
         * attributeList : 巧克力棒,条状,圆球
         * attributeName : 形状
         */

        private int attributeId;
        private String attributeList;
        private String attributeName;

        public int getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(int attributeId) {
            this.attributeId = attributeId;
        }

        public String getAttributeList() {
            return attributeList;
        }

        public void setAttributeList(String attributeList) {
            this.attributeList = attributeList;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }
    }
}
