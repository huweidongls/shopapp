package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/1.
 */

public class FeileiLeftListBean {

    /**
     * status : 200
     * data : [{"id":2,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","homeType":1},{"id":3,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","homeType":0},{"id":4,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","homeType":0}]
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
         * categoryPic : /upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg
         * homeType : 1
         */

        private int id;
        private String categoryName;
        private String appCategoryPic;
        private int homeType;

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

        public String getAppCategoryPic() {
            return appCategoryPic;
        }

        public void setAppCategoryPic(String categoryPic) {
            this.appCategoryPic = categoryPic;
        }

        public int getHomeType() {
            return homeType;
        }

        public void setHomeType(int homeType) {
            this.homeType = homeType;
        }
    }
}
