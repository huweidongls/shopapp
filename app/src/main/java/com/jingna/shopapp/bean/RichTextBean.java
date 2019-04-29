package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/4/29.
 */

public class RichTextBean {

    /**
     * status : 200
     * data : {"detailMobileHtml":"<p><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2r_2LgILJ8KJjy0FnXXcFDpXa_!!2283496633.png\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2p7ueXdLO8KJjSZPcXXaV0FXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2xV1gXXYM8KJjSZFuXXcf7FXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2YtGgXfjM8KJjSZFyXXXdzVXa_!!2283496633.jpg\"/><img src=\"https://img.alicdn.com/imgextra/i4/2283496633/TB2Q3bmXgLD8KJjSszeXXaGRpXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i4/2283496633/TB2dQrhXlTH8KJjy0FiXXcRsXXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i1/2283496633/TB2FKCeXdLO8KJjSZFxXXaGEVXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i3/2283496633/TB2_yDmXgvD8KJjy0FlXXagBFXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><img src=\"https://img.alicdn.com/imgextra/i3/2283496633/TB2_imeXdHO8KJjSZFtXXchfXXa_!!2283496633.jpg\" class=\"\" width=\"750\" height=\"750\"/><\/p><p>&nbsp;<\/p>"}
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
         * detailMobileHtml : <p><img src="https://img.alicdn.com/imgextra/i1/2283496633/TB2r_2LgILJ8KJjy0FnXXcFDpXa_!!2283496633.png"/><img src="https://img.alicdn.com/imgextra/i1/2283496633/TB2p7ueXdLO8KJjSZPcXXaV0FXa_!!2283496633.jpg"/><img src="https://img.alicdn.com/imgextra/i1/2283496633/TB2xV1gXXYM8KJjSZFuXXcf7FXa_!!2283496633.jpg"/><img src="https://img.alicdn.com/imgextra/i1/2283496633/TB2YtGgXfjM8KJjSZFyXXXdzVXa_!!2283496633.jpg"/><img src="https://img.alicdn.com/imgextra/i4/2283496633/TB2Q3bmXgLD8KJjSszeXXaGRpXa_!!2283496633.jpg" class="" width="750" height="750"/><img src="https://img.alicdn.com/imgextra/i4/2283496633/TB2dQrhXlTH8KJjy0FiXXcRsXXa_!!2283496633.jpg" class="" width="750" height="750"/><img src="https://img.alicdn.com/imgextra/i1/2283496633/TB2FKCeXdLO8KJjSZFxXXaGEVXa_!!2283496633.jpg" class="" width="750" height="750"/><img src="https://img.alicdn.com/imgextra/i3/2283496633/TB2_yDmXgvD8KJjy0FlXXagBFXa_!!2283496633.jpg" class="" width="750" height="750"/><img src="https://img.alicdn.com/imgextra/i3/2283496633/TB2_imeXdHO8KJjSZFtXXchfXXa_!!2283496633.jpg" class="" width="750" height="750"/></p><p>&nbsp;</p>
         */

        private String detailMobileHtml;

        public String getDetailMobileHtml() {
            return detailMobileHtml;
        }

        public void setDetailMobileHtml(String detailMobileHtml) {
            this.detailMobileHtml = detailMobileHtml;
        }
    }
}
