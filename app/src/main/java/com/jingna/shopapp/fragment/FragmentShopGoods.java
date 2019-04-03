package com.jingna.shopapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.ShopGoodsAdapter;
import com.jingna.shopapp.adapter.ShopIndexAdapter;
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.bean.ShopIndexBean;
import com.jingna.shopapp.bean.ShopIndexGoodsBean;
import com.jingna.shopapp.pages.LoginActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/3/13.
 */

public class FragmentShopGoods extends Fragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.Recommend)
    TextView Recommend;
    @BindView(R.id.top_Recommend)
    ImageView top_Recommend;
    @BindView(R.id.Salesvolume)
    TextView Salesvolume;
    @BindView(R.id.price_goods)
    TextView price_goods;
    @BindView(R.id.top_price_img)
    ImageView top_price_img;
    @BindView(R.id.bottom_price_img)
    ImageView bottom_price_img;
    @BindView(R.id.stock)
    TextView stock;
    private List<ShopGoodsBean.DataBean> mList;
    private ShopGoodsAdapter adapter;
    private String id;
    private int page = 1;
    private int tj =0;//推荐
    private int xl =0;//销量
    private int jg =0;//价格
    private int yh =0;//仅看有货
    private int zx =0;//价格判断字段
    public static FragmentShopGoods newInstance(String id) {
        FragmentShopGoods newFragment = new FragmentShopGoods();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_good_list, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }
    @OnClick({R.id.Recommend,R.id.Salesvolume,R.id.price_goods,R.id.stock})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Recommend:
                Recommend_List();
                break;
            case R.id.Salesvolume:
                Salesvolume_List();
                break;
            case R.id.price_goods:
                price_goods_List();
                break;
            case R.id.stock:
                stock_List();
                break;
        }
    }
    private  void  Recommend_List(){//推荐商品
        Sdefault();
         xl =0;//销量
         jg =0;//价格
         yh =0;//仅看有货
         zx =0;//价格判断字段
        tj = 1;
        Glide.with(getContext()).load(R.mipmap.bottom_red).into(top_Recommend);//切换图片
        Recommend.setTextColor(Color.parseColor("#FF0004"));//设置字体颜色
        Glide.with(getContext()).load(R.mipmap.top_b).into(top_price_img);//切换图片
        Glide.with(getContext()).load(R.mipmap.bottom_b).into(bottom_price_img);//切换图片
        price_goods.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        Salesvolume.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        stock.setTextColor(Color.parseColor("#333333"));//设置字体颜色
    }
    private  void  Salesvolume_List(){//销量查看
        xl = 1;
        tj =0;//推荐
        jg =0;//价格
        yh =0;//仅看有货
        zx =0;//价格判断字段
        ShopGoods("goods.sale desc",0);
        Salesvolume.setTextColor(Color.parseColor("#FF0004"));//设置字体颜色
        Glide.with(getContext()).load(R.mipmap.top_b).into(top_price_img);//切换图片
        Glide.with(getContext()).load(R.mipmap.bottom_b).into(bottom_price_img);//切换图片
        price_goods.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        Glide.with(getContext()).load(R.mipmap.bottom_b).into(top_Recommend);//切换图片
        Recommend.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        stock.setTextColor(Color.parseColor("#333333"));//设置字体颜色
    }
    private  void  price_goods_List(){//价格查看
        if (jg == 0){//asc
            tj =0;//推荐
            xl =0;//销量
            yh =0;//仅看有货
            zx = 1;
            ShopGoods("goods.price asc",0);
            jg = 1;
            Glide.with(getContext()).load(R.mipmap.top_red).into(top_price_img);//切换图片
            price_goods.setTextColor(Color.parseColor("#FF0004"));//设置字体颜色
            Glide.with(getContext()).load(R.mipmap.bottom_b).into(bottom_price_img);//切换图片
            Salesvolume.setTextColor(Color.parseColor("#333333"));//设置字体颜色
            Glide.with(getContext()).load(R.mipmap.bottom_b).into(top_Recommend);//切换图片
            Recommend.setTextColor(Color.parseColor("#333333"));//设置字体颜色
            stock.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        }else{//desc
            tj =0;//推荐
            xl =0;//销量
            yh =0;//仅看有货
            ShopGoods("goods.price desc",0);
            jg = 0;
            zx = 1;
            Glide.with(getContext()).load(R.mipmap.bottom_red).into(bottom_price_img);//切换图片
            price_goods.setTextColor(Color.parseColor("#FF0004"));//设置字体颜色
            Glide.with(getContext()).load(R.mipmap.top_b).into(top_price_img);//切换图片
            Salesvolume.setTextColor(Color.parseColor("#333333"));//设置字体颜色
            Glide.with(getContext()).load(R.mipmap.bottom_b).into(top_Recommend);//切换图片
            Recommend.setTextColor(Color.parseColor("#333333"));//设置字体颜色
            stock.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        }
    }
    private  void  stock_List(){//只看有货 stock
        tj =0;//推荐
        xl =0;//销量
        jg =0;//价格
        zx =0;//价格判断字段
        yh = 1;
        ShopGoods("",1);
        stock.setTextColor(Color.parseColor("#FF0004"));//设置字体颜色
        Glide.with(getContext()).load(R.mipmap.bottom_b).into(top_Recommend);//切换图片
        Recommend.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        Salesvolume.setTextColor(Color.parseColor("#333333"));//设置字体颜色
        Glide.with(getContext()).load(R.mipmap.top_b).into(top_price_img);//切换图片
        Glide.with(getContext()).load(R.mipmap.bottom_b).into(bottom_price_img);//切换图片
        price_goods.setTextColor(Color.parseColor("#333333"));//设置字体颜色
    }
    private void ShopGoods(String orderBy,int stock){
        ViseHttp.GET("/AppSeller/queryList")
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("sellerId",id)
                .addParam("orderBy",orderBy)
                .addParam("stock",stock+"")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopGoodsBean ShopGoodsBean = gson.fromJson(data,ShopGoodsBean.class);
                                mList.clear();
                                mList.addAll(ShopGoodsBean.getData());
                                adapter = new ShopGoodsAdapter(mList,id);
                                GridLayoutManager manager = new GridLayoutManager(getContext(),2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
    private void initData() {
       mList = new ArrayList<>();
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                if(tj == 1){
                    Recommend_List();
                }else if(xl == 1){
                    Salesvolume_List();
                }else if(zx==1){
                    price_goods_List();
                }else if(yh==1){
                    stock_List();
                }
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                page = page + 1;
                ViseHttp.GET("/AppGoodsShop/queryList")
                        .addParam("pageNum",page+"")
                        .addParam("pageSize","10")
                        .addParam("sellerId",id)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    Log.e("123123", data);
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        ShopGoodsBean bean = gson.fromJson(data, ShopGoodsBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(tj == 1){
                                    Recommend_List();
                                }else if(xl == 1){
                                    Salesvolume_List();
                                }else if(zx==1){
                                    price_goods_List();
                                }else if(yh==1){
                                    stock_List();
                                }
                                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                            }
                        });

            }
        });
        ViseHttp.GET("/AppSeller/queryList")
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("sellerId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopGoodsBean ShopGoodsBean = gson.fromJson(data,ShopGoodsBean.class);
                                mList.clear();
                                mList.addAll(ShopGoodsBean.getData());
                                adapter = new ShopGoodsAdapter(mList,id);
                                GridLayoutManager manager = new GridLayoutManager(getContext(),2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
    private void  Sdefault(){
        ViseHttp.GET("/AppSeller/homeList")
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("sellerId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopGoodsBean ShopGoodsBean = gson.fromJson(data,ShopGoodsBean.class);
                                mList.clear();
                                mList.addAll(ShopGoodsBean.getData());
                                adapter = new ShopGoodsAdapter(mList,id);
                                GridLayoutManager manager = new GridLayoutManager(getContext(),2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
