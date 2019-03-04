package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/4.
 */

public class ZhuanchangTuijianBean {

    /**
     * status : 200
     * data : [{"id":2,"categoryName":"分类名称","categoryPic":"E:\\jnkjupload/sysBanner/2019-02-26/d13650facbbf4a85ae9db619e7402645.jpg"},{"id":3,"categoryName":"分类名称","categoryPic":"E:\\jnkjupload/sysBanner/2019-02-26/d13650facbbf4a85ae9db619e7402645.jpg"},{"id":4,"categoryName":"分类名称","categoryPic":"E:\\jnkjupload/sysBanner/2019-02-26/d13650facbbf4a85ae9db619e7402645.jpg"}]
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
         * categoryPic : E:\jnkjupload/sysBanner/2019-02-26/d13650facbbf4a85ae9db619e7402645.jpg
         */

        private int id;
        private String categoryName;
        private String categoryPic;

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

        public String getCategoryPic() {
            return categoryPic;
        }

        public void setCategoryPic(String categoryPic) {
            this.categoryPic = categoryPic;
        }
    }
}
