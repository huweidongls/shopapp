package com.jingna.shopapp.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.DetailsOrderGoodsListAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.CommitOrderZhifubaoBean;
import com.jingna.shopapp.bean.DetailsOrderBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsOrderActivity extends BaseActivity {

    private Context context = DetailsOrderActivity.this;

    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.tv_details_address)
    TextView tvDetailsAddress;
    @BindView(R.id.tv_seller_title)
    TextView tvSellerTitle;
    @BindView(R.id.rv_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_real_pay_price)
    TextView tvRealPayPrice;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.tv_delete_order)
    TextView tvDeleteOrder;
    @BindView(R.id.tv_check_logistics)
    TextView tvCheckLogistics;
    @BindView(R.id.tv_buy_again)
    TextView tvBuyAgain;
    @BindView(R.id.tv_cancel_order)
    TextView tvCancelOrder;
    @BindView(R.id.tv_return_price)
    TextView tvReturnPrice;
    @BindView(R.id.tv_to_pay)
    TextView tvToPay;

    private DetailsOrderGoodsListAdapter adapter;
    private List<DetailsOrderBean.DataBean.ListBean> mList;

    private static final int SDK_PAY_FLAG = 1;
    private String orderId = "";
    private DetailsOrderBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);

        orderId = getIntent().getStringExtra("orderId");
        StatusBarUtils.setStatusBar(DetailsOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(DetailsOrderActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET("/AppOrder/orderDetails")
                .addParam("goodsOrderId", orderId)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                bean = gson.fromJson(data, DetailsOrderBean.class);
                                //判断订单状态更新顶部
                                String orderStatus = bean.getData().getOrderStatus();
                                if(orderStatus.equals("0")){
                                    tvOrderStatus.setText("等待付款");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.GONE);
                                    tvCancelOrder.setVisibility(View.VISIBLE);
                                    tvToPay.setVisibility(View.VISIBLE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.GONE);
                                }else if(orderStatus.equals("1")){
                                    tvOrderStatus.setText("待收货");
                                    tvCheckLogistics.setVisibility(View.VISIBLE);
                                    tvBuyAgain.setVisibility(View.GONE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.VISIBLE);
                                    tvDeleteOrder.setVisibility(View.GONE);
                                }else if(orderStatus.equals("2")){
                                    tvOrderStatus.setText("待收货");
                                    tvCheckLogistics.setVisibility(View.VISIBLE);
                                    tvBuyAgain.setVisibility(View.GONE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.VISIBLE);
                                    tvDeleteOrder.setVisibility(View.GONE);
                                }else if(orderStatus.equals("3")){
                                    tvOrderStatus.setText("待收货");
                                    tvCheckLogistics.setVisibility(View.VISIBLE);
                                    tvBuyAgain.setVisibility(View.GONE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.GONE);
                                }else if(orderStatus.equals("4")){
                                    tvOrderStatus.setText("已完成");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.VISIBLE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.GONE);
                                }else if(orderStatus.equals("5")){
                                    tvOrderStatus.setText("已完成");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.VISIBLE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.VISIBLE);
                                }else if(orderStatus.equals("6")){
                                    tvOrderStatus.setText("已完成");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.VISIBLE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.VISIBLE);
                                }else if(orderStatus.equals("7")){
                                    tvOrderStatus.setText("已取消");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.VISIBLE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.VISIBLE);
                                }else if(orderStatus.equals("8")){
                                    tvOrderStatus.setText("已退款");
                                    tvCheckLogistics.setVisibility(View.GONE);
                                    tvBuyAgain.setVisibility(View.VISIBLE);
                                    tvCancelOrder.setVisibility(View.GONE);
                                    tvToPay.setVisibility(View.GONE);
                                    tvReturnPrice.setVisibility(View.GONE);
                                    tvDeleteOrder.setVisibility(View.VISIBLE);
                                }
                                //加载地址信息
                                tvAddressName.setText(bean.getData().getAddresUname());
                                tvAddressPhone.setText(bean.getData().getAddresPhone());
                                tvDetailsAddress.setText("地址: "+bean.getData().getAddresName());
                                //加载店铺及商品信息
                                tvSellerTitle.setText(bean.getData().getSellerName());
                                mList = bean.getData().getList();
                                adapter = new DetailsOrderGoodsListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvGoodsList.setLayoutManager(manager);
                                rvGoodsList.setAdapter(adapter);
                                //加载订单信息
                                tvOrderId.setText(bean.getData().getId());
                                tvCreateTime.setText(bean.getData().getCreateTime());
                                tvPayTime.setText(bean.getData().getPaymentTime());
                                //加载商品价格
                                tvOrderPrice.setText("¥"+bean.getData().getOrderPrice());
                                tvRealPayPrice.setText("¥"+bean.getData().getOrderPrice());
                                //关闭加载框
                                tvLoading.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @OnClick({R.id.rl_back, R.id.tv_delete_order, R.id.tv_cancel_order, R.id.tv_return_price, R.id.tv_to_pay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_delete_order:
                DialogCustom dialogDelete = new DialogCustom(context, "是否删除订单", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.POST("/AppOrder/toDelete")
                                .addParam("goodsOrderId", orderId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "订单删除成功");
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                });
                dialogDelete.show();
                break;
            case R.id.tv_cancel_order:
                DialogCustom dialogCancel = new DialogCustom(context, "是否取消订单", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET("/AppOrder/cancellationOrder")
                                .addParam("goodsOrderId", orderId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "订单取消成功");
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                });
                dialogCancel.show();
                break;
            case R.id.tv_return_price:
                DialogCustom dialogCustom = new DialogCustom(context, "是否发起退款", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET("/AppOrder/orderRefund")
                                .addParam("id", orderId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "退款成功");
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                });
                dialogCustom.show();
                break;
            case R.id.tv_to_pay:
                ViseHttp.GET("/AppOrder/listOrdersSubmitted")
                        .addParam("id", orderId)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        CommitOrderZhifubaoBean zhifubaoBean = gson.fromJson(data, CommitOrderZhifubaoBean.class);
                                        aliPay(zhifubaoBean.getData().getData());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
//                aliPay("alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019032963745653&biz_content=%7B%22body%22%3A%22%E7%B2%BE%E7%BA%B3%E5%95%86%E5%93%81%E6%94%AF%E4%BB%98%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%221555665270270%2C1555665268761%2C1555665266956%2C%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%B2%BE%E7%BA%B3%E5%95%86%E5%9F%8E%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fjshop.5ijiaoyu.cn%2FZhiFuBao%2Fpay&sign=UBQLDUxSGYWAhZpXngcS4MDDWD8infoKJjVdvdoFddyews5YX00cDGXuTZxTWSjgfTjR8rYDV8IInAgl8iSVEPr6EWARcZLgPYb4wIeHODBkHeEcBt4mcJN9aztuGMVg%2F5mCS0rusgh3i12I%2FPwfJGJ3orSckDgaZBtTpN3v9r5hX7vNH298rcnzwouNHGh38YsVnRLruL9Yce9DO9JCekBaIlWRdEje7tbm02rPoY7VCKbVITuJamxUTCZPqcC1Kmcxk7wHmv%2FPCCvf8vx3zGIc8oTOJRyZRAc9xMX7Tw49gnzNU1395xHDqQOKF7bCPum%2BekgYfi4XF0xmeULYQw%3D%3D&sign_type=RSA2&timestamp=2019-04-19+17%3A14%3A23&version=1.0\",");
                break;
        }
    }

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(DetailsOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if(result.get("resultStatus").equals("9000")){
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };

}
