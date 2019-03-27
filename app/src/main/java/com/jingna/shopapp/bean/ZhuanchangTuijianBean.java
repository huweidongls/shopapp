package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/4.
 */

public class ZhuanchangTuijianBean {

    /**
     * status : 200
     * data : {"Recommend":[{"id":2,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","oftenType":0},{"id":4,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","oftenType":0}],"Commonly":[{"id":3,"categoryName":"分类名称","categoryPic":"/upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg","oftenType":1}]}
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
        private List<RecommendBean> Recommend;
        private List<CommonlyBean> Commonly;

        public List<RecommendBean> getRecommend() {
            return Recommend;
        }

        public void setRecommend(List<RecommendBean> Recommend) {
            this.Recommend = Recommend;
        }

        public List<CommonlyBean> getCommonly() {
            return Commonly;
        }

        public void setCommonly(List<CommonlyBean> Commonly) {
            this.Commonly = Commonly;
        }

        public static class RecommendBean {
            /**
             * id : 2
             * categoryName : 分类名称
             * categoryPic : /upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg
             * oftenType : 0
             */

            private int id;
            private String categoryName;
            private String appCategoryPic;
            private int oftenType;

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

            public int getOftenType() {
                return oftenType;
            }

            public void setOftenType(int oftenType) {
                this.oftenType = oftenType;
            }
        }

        public static class CommonlyBean {
            /**
             * id : 3
             * categoryName : 分类名称
             * categoryPic : /upload/0c8fbb037e6941aaab2e659ec4a869f8.jpg
             * oftenType : 1
             */

            private int id;
            private String categoryName;
            private String appCategoryPic;
            private int oftenType;

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

            public int getOftenType() {
                return oftenType;
            }

            public void setOftenType(int oftenType) {
                this.oftenType = oftenType;
            }
        }
    }
}
