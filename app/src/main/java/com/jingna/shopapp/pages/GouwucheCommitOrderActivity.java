package com.jingna.shopapp.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GouwucheCommitOrderAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.AddressBean;
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.bean.FragmentGouwucheBean;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

    private String id = "";//商品id
    private String skuid = "";
    private static final int SDK_PAY_FLAG = 1;
    private String addressId = "";//会员地址id
    private double goodsPrice;

    private GouwucheCommitOrderAdapter adapter;
    private List<FragmentGouwucheBean.DataBean> seller = new ArrayList<>();

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

        String goodsId = "";
        List<Map<String, String>> maps = new ArrayList<>();
        for (FragmentGouwucheBean.DataBean dataBean : seller){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("sellerId", dataBean.getSellerId()+"");
            for (FragmentGouwucheBean.DataBean.ShopGoodsBean shopGoodsBean : dataBean.getShopGoods()){
                goodsId = goodsId+shopGoodsBean.getCarId()+",";
            }
            map.put("skuId", goodsId);
            maps.add(map);
            goodsId = "";
        }
        Gson gson = new Gson();
        String json = gson.toJson(maps);
        Logger.e("123123", json);

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

    @OnClick({R.id.rl_back, R.id.tv_commit, R.id.ll_address})
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
        }
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
        }
    }

    /**
     * 提交订单
     */
    private void commitOrder() {



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
                    }
                    break;
            }
        }

    };

}
