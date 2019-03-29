package com.jingna.shopapp.fragment;

import android.annotation.SuppressLint;
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

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentDaifukuanAdapter;
import com.jingna.shopapp.adapter.GoodsListAdapter;
import com.jingna.shopapp.adapter.ShopIndexAdapter;
import com.jingna.shopapp.bean.GoodsListBean;
import com.jingna.shopapp.bean.ShopIndexGoodsBean;
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

/**
 * Created by Administrator on 2019/3/12.
 */

public class FragmentShopindex extends Fragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    private List<ShopIndexGoodsBean.DataBean> mList;
    private ShopIndexAdapter adapter;
    private String id;
    private int page = 1;
    public static FragmentShopindex newInstance(String id) {
        FragmentShopindex newFragment = new FragmentShopindex();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_goods, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        mList = new ArrayList<>();
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                Sdefault();
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
                                        ShopIndexGoodsBean bean = gson.fromJson(data, ShopIndexGoodsBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        /*adapter = new ShopIndexAdapter(mList,id);
                                        GridLayoutManager manager = new GridLayoutManager(getContext(),2){
                                            @Override
                                            public boolean canScrollVertically() {
                                                return false;
                                            }
                                        };
                                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                                        recyclerView.setLayoutManager(manager);
                                        recyclerView.setAdapter(adapter);*/
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopIndexGoodsBean bean = gson.fromJson(data, ShopIndexGoodsBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter = new ShopIndexAdapter(mList,id);
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
    public void Sdefault(){
        ViseHttp.GET("/AppSeller/queryList")
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("sellerId",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopIndexGoodsBean bean = gson.fromJson(data, ShopIndexGoodsBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter.notifyDataSetChanged();
                                /*adapter = new ShopIndexAdapter(mList,id);
                                GridLayoutManager manager = new GridLayoutManager(getContext(),2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);*/
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
