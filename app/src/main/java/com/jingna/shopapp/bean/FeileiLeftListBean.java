package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/1.
 */

public class FeileiLeftListBean {

    /**
     * status : 200
     * data : [{"id":2,"categoryName":"分类名称"},{"id":3,"categoryName":"分类名称"}]
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
         * id : 2
         * categoryName : 分类名称
         */

        private int id;
        private String categoryName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
