package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.AddressBean;
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitOrderActivity extends BaseActivity {

    private Context context = CommitOrderActivity.this;

    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
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

    private int goodsNum = 1;

    private FragmentGoodsBean goodsBean;

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        api = WXAPIFactory.createWXAPI(this, null);
        goodsBean = (FragmentGoodsBean) getIntent().getSerializableExtra("bean");
        StatusBarUtils.setStatusBar(CommitOrderActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(CommitOrderActivity.this);
        initData();

    }

    private void initData() {

        if(goodsBean != null){
            Glide.with(context).load(Const.BASE_URL+goodsBean.getData().getShopGoods().getPic()).into(ivTitle);
            tvGoodsName.setText(goodsBean.getData().getShopGoods().getGoodsName());
            tvGoodsPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
            tvGoodsAllPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
            tvAllPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
        }

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

    @OnClick({R.id.rl_back, R.id.rl_jianhao, R.id.rl_jiahao})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_jianhao:
                if(goodsNum > 1){
                    goodsNum = goodsNum - 1;
                    tvGoodsNum.setText(goodsNum+"");
                }
                break;
            case R.id.rl_jiahao:
                goodsNum = goodsNum + 1;
                tvGoodsNum.setText(goodsNum+"");
                break;
        }
    }

}
