package com.jingna.shopapp.bean;

/**
 * Created by Administrator on 2019/4/4.
 */

public class CommitOrderZhifubaoBean {

    /**
     * status : 200
     * data : {"data":"alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019032963745653&biz_content=%7B%22body%22%3A%22%E7%B2%BE%E7%BA%B3%E5%95%86%E5%93%81%E6%94%AF%E4%BB%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221554342774526%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E9%A3%9E%E5%88%A9%E6%B5%A6%E6%98%BE%E7%A4%BA%E5%99%A8%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fjshop.5ijiaoyu.cn%2FZhiFuBao%2Fpay&sign=dLXVtnwe6NBJkDP5J%2Ff3O4PBx5kbXnr5JpyfTFCCudTNiER680OK3pLhiwfjQTKqLCakm3XMpYkJh3MAhVZnCEf3PITbrfNCaVGjr3jz1OW7bmk60ExlkrbyQ3gzMqBm1t86Z5RTAwW1bd%2FrugX0sF8OPRZ%2FbKhvVlH95fbsgRQ%2FX40xeIeHcS0STDw6fVjUJ%2F%2BYNPB8IQyMdQs7EPzJa1jDe4Dwt63VHldyoir2DQxBPXjVi4VB2zlLWH3BEBjIr5sLQoiwEYOYewzC4P%2BdRUDnxX2keLoRe0K68GH57pzNnjEo30Q0bXKShzUA8EfuEuC6BcMGA9LDU2dSBqjqaQ%3D%3D&sign_type=RSA2&timestamp=2019-04-04+09%3A52%3A49&version=1.0","status":0}
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
         * data : alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019032963745653&biz_content=%7B%22body%22%3A%22%E7%B2%BE%E7%BA%B3%E5%95%86%E5%93%81%E6%94%AF%E4%BB%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221554342774526%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E9%A3%9E%E5%88%A9%E6%B5%A6%E6%98%BE%E7%A4%BA%E5%99%A8%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fjshop.5ijiaoyu.cn%2FZhiFuBao%2Fpay&sign=dLXVtnwe6NBJkDP5J%2Ff3O4PBx5kbXnr5JpyfTFCCudTNiER680OK3pLhiwfjQTKqLCakm3XMpYkJh3MAhVZnCEf3PITbrfNCaVGjr3jz1OW7bmk60ExlkrbyQ3gzMqBm1t86Z5RTAwW1bd%2FrugX0sF8OPRZ%2FbKhvVlH95fbsgRQ%2FX40xeIeHcS0STDw6fVjUJ%2F%2BYNPB8IQyMdQs7EPzJa1jDe4Dwt63VHldyoir2DQxBPXjVi4VB2zlLWH3BEBjIr5sLQoiwEYOYewzC4P%2BdRUDnxX2keLoRe0K68GH57pzNnjEo30Q0bXKShzUA8EfuEuC6BcMGA9LDU2dSBqjqaQ%3D%3D&sign_type=RSA2&timestamp=2019-04-04+09%3A52%3A49&version=1.0
         * status : 0
         */

        private String data;
        private int status;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
