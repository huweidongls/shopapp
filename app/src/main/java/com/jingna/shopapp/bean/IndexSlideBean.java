package com.jingna.shopapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */

public class IndexSlideBean {

    /**
     * status : 200
     * data : {"banner":[{"appPic":"upload/BrandPic/2019-04-01/e318fe7c41e2472093e8ffb7a60dd310.jpg","skipSite":"www.baidu.com"},{"appPic":"upload/BrandPic/2019-04-01/e318fe7c41e2472093e8ffb7a60dd310.jpg","skipSite":"www.baidu.com"},{"appPic":"upload/BrandPic/2019-04-01/e318fe7c41e2472093e8ffb7a60dd310.jpg","skipSite":"www.baidu.com"},{"skipSite":"www.baidu.com"}],"shopCategory":[{"id":1,"categoryName":"推荐分类","appCategoryPic":"/upload/category/timg.jpg"}]}
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
        private List<BannerBean> banner;
        private List<ShopCategoryBean> shopCategory;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ShopCategoryBean> getShopCategory() {
            return shopCategory;
        }

        public void setShopCategory(List<ShopCategoryBean> shopCategory) {
            this.shopCategory = shopCategory;
        }

        public static class BannerBean {
            /**
             * appPic : upload/BrandPic/2019-04-01/e318fe7c41e2472093e8ffb7a60dd310.jpg
             * skipSite : www.baidu.com
             */

            private String appPic;
            private String skipSite;

            public String getAppPic() {
                return appPic;
            }

            public void setAppPic(String appPic) {
                this.appPic = appPic;
            }

            public String getSkipSite() {
                return skipSite;
            }

            public void setSkipSite(String skipSite) {
                this.skipSite = skipSite;
            }
        }

        public static class ShopCategoryBean {
            /**
             * id : 1
             * categoryName : 推荐分类
             * appCategoryPic : /upload/category/timg.jpg
             */

            private int id;
            private String categoryName;
            private String appCategoryPic;

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

            public void setAppCategoryPic(String appCategoryPic) {
                this.appCategoryPic = appCategoryPic;
            }
        }
    }
}
