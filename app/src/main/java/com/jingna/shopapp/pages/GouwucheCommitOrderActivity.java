package com.jingna.shopapp.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.CommitOrderCouponsAdapter;
import com.jingna.shopapp.adapter.GouwucheCommitOrderAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.AddressBean;
import com.jingna.shopapp.bean.AppCouponBean;
import com.jingna.shopapp.bean.CommitOrderZhifubaoBean;
import com.jingna.shopapp.bean.FragmentGouwucheBean;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GouwucheCommitOrderActivity extends BaseActivity {

    private Context context = GouwucheCommitOrderActivity.this;

    @BindView(R.id.rv_goods_list)
    RecyclerView rvGoodsList;
    @BindView(R.id.tv_goods_all_price)
    TextView tvGoodsAllPrice;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.tv_recieve_name)
    TextView tvRecieveName;
    @BindView(R.id.tv_recieve_phone)
    TextView tvRecievePhone;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_coupons)
    TextView tvCoupons;

    private static final int SDK_PAY_FLAG = 1;
    private String addressId = "";//会员地址id
    private double goodsPrice;
    private String carId = "";
    private String goodsId = "";
    private Map<String, String> map;//发票map
    private int invoiceId = 0;//是否开发票，0不开，1开
    private String couponId = "";//优惠券id

    private GouwucheCommitOrderAdapter adapter;
    private List<FragmentGouwucheBean.DataBean> seller = new ArrayList<>();

    private PopupWindow popupWindow;
    private CommitOrderCouponsAdapter couponsAdapter;
    private List<AppCouponBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seller = (List<FragmentGouwucheBean.DataBean>) getIntent().getSerializableExtra("seller");
        setContentView(R.layout.activity_gouwuche_commit_order);
        StatusBarUtils.setStatusBar(GouwucheCommitOrderActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(GouwucheCommitOrderActivity.this);
        initData();
    }

    private void initData() {

//        tvGoodsNum.setText(goodsNum+"");
//        if(goodsBean != null){
//            Glide.with(context).load(Const.BASE_URL+goodsBean.getData().getShopGoods().getAppPic()).into(ivTitle);
//            tvGoodsName.setText(goodsBean.getData().getShopGoods().getGoodsName());
//            tvGoodsPrice.setText("¥"+goodsPrice);
//            tvGoodsAllPrice.setText("¥"+goodsPrice*goodsNum);
//            tvAllPrice.setText("¥"+goodsPrice*goodsNum);
//        }

        for (FragmentGouwucheBean.DataBean dataBean : seller){
            for (FragmentGouwucheBean.DataBean.ShopGoodsBean shopGoodsBean : dataBean.getShopGoods()){
                carId = carId+shopGoodsBean.getCarId()+",";
                goodsId = goodsId+shopGoodsBean.getId()+",";
                goodsPrice = goodsPrice + shopGoodsBean.getPrice();
            }
        }
        Logger.e("123123", carId);

        tvGoodsAllPrice.setText(goodsPrice+"");
        tvAllPrice.setText(goodsPrice+"");

        adapter = new GouwucheCommitOrderAdapter(seller);
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvGoodsList.setLayoutManager(manager);
        rvGoodsList.setAdapter(adapter);

        //接口获取当前用户默认收货地址
        ViseHttp.GET("/MemAdress/queryList")
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AddressBean bean = gson.fromJson(data, AddressBean.class);
                                List<AddressBean.DataBean> list = bean.getData();
                                for (int i = 0; i<list.size(); i++){
                                    if(list.get(i).getAcquiescentAdress().equals("1")){
                                        tvRecieveName.setText(list.get(i).getConsignee());
                                        tvRecievePhone.setText(list.get(i).getConsigneeTel());
                                        tvRecieveAddress.setText(list.get(i).getLocation()+list.get(i).getAdress());
                                        addressId = list.get(i).getId()+"";
                                    }
                                }
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

    @OnClick({R.id.rl_back, R.id.tv_commit, R.id.ll_address, R.id.rl_invoice, R.id.rl_coupons})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_commit:
                commitOrder();
                break;
            case R.id.ll_address:
                intent.setClass(context, AddressActivity.class);
                intent.putExtra("type", "order");
                startActivityForResult(intent, 1);
                break;
            case R.id.rl_invoice:
                intent.setClass(context, InvoiceActivity.class);
                intent.putExtra("price", goodsPrice);
                startActivityForResult(intent, 100);
                break;
            case R.id.rl_coupons:
                //优惠券
                showCouponsPop();
                break;
        }
    }

    /**
     * 显示优惠券pop
     */
    private void showCouponsPop() {

        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_commitorder_coupons, null);
        final RecyclerView recyclerView = view.findViewById(R.id.rv);
        mList = new ArrayList<>();

        ViseHttp.GET("/AppCoupon/queryList")
                .addParam("memberId", SpUtils.getUserId(context))
                .addParam("type", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AppCouponBean couponBean = gson.fromJson(data, AppCouponBean.class);
                                mList = couponBean.getData();
                                couponsAdapter = new CommitOrderCouponsAdapter(mList, new CommitOrderCouponsAdapter.ClickListener() {
                                    @Override
                                    public void onUse(int pos) {
                                        tvCoupons.setText("已使用优惠券");
                                        couponId = mList.get(pos).getCouponId();
                                        ViseHttp.GET("/AppCoupon/returnPrice")
                                                .addParam("couponId", couponId)
                                                .addParam("price", goodsPrice+"")
                                                .addParam("memberId", SpUtils.getUserId(context))
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(data);
                                                            if(jsonObject1.optString("status").equals("200")){
                                                                double price = jsonObject1.optDouble("data");
                                                                tvGoodsAllPrice.setText("¥"+price);
                                                                tvAllPrice.setText("¥"+price);
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFail(int errCode, String errMsg) {

                                                    }
                                                });
                                        popupWindow.dismiss();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(couponsAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            AddressBean.DataBean bean = (AddressBean.DataBean) data.getSerializableExtra("address");
            tvRecieveName.setText(bean.getConsignee());
            tvRecievePhone.setText(bean.getConsigneeTel());
            tvRecieveAddress.setText(bean.getLocation()+bean.getAdress());
            addressId = bean.getId()+"";
        }else if(resultCode == 101){
            invoiceId = 1;
            tvInvoice.setText("开发票");
            map = (Map<String, String>) data.getSerializableExtra("map");
        }
    }

    /**
     * 提交订单
     */
    private void commitOrder() {

        if(invoiceId == 0){
            ViseHttp.GET("/AppOrder/settleAccounts")
                    .addParam("memberId", SpUtils.getUserId(context))
                    .addParam("addressId", addressId)
                    .addParam("cartId", carId)
                    .addParam("invoiceId", "0")
                    .addParam("couponId", couponId)
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
        }else if(invoiceId == 1){
            ViseHttp.GET("/AppOrder/settleAccounts")
                    .addParam("memberId", SpUtils.getUserId(context))
                    .addParam("addressId", addressId)
                    .addParam("cartId", carId)
                    .addParam("invoiceId", "1")
                    .addParam("couponId", couponId)
                    .addParams(map)
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
        }

    }

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(GouwucheCommitOrderActivity.this);
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
                        finish();
                    }
                    break;
            }
        }

    };

}
