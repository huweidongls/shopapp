package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.DetailsOrderBean;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsOrderActivity extends BaseActivity {

    private Context context = DetailsOrderActivity.this;

    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;

    private String orderId = "";

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
                                DetailsOrderBean bean = gson.fromJson(data, DetailsOrderBean.class);
                                //判断订单状态更新顶部
                                String orderStatus = bean.getData().getOrderStatus();
                                if(orderStatus.equals("0")){
                                    tvOrderStatus.setText("等待付款");
                                }else if(orderStatus.equals("1")){
                                    tvOrderStatus.setText("待收货");
                                }else if(orderStatus.equals("2")){
                                    tvOrderStatus.setText("待收货");
                                }else if(orderStatus.equals("3")){
                                    tvOrderStatus.setText("待收货");
                                }else if(orderStatus.equals("4")){
                                    tvOrderStatus.setText("已完成");
                                }else if(orderStatus.equals("5")){
                                    tvOrderStatus.setText("已完成");
                                }else if(orderStatus.equals("6")){
                                    tvOrderStatus.setText("已完成");
                                }else if(orderStatus.equals("7")){
                                    tvOrderStatus.setText("已取消");
                                }else if(orderStatus.equals("8")){
                                    tvOrderStatus.setText("已退款");
                                }
                                //加载地址信息

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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
