package com.jingna.shopapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.ShopGoodsAdapter;
import com.jingna.shopapp.adapter.ShopIndexAdapter;
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.bean.ShopIndexBean;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/13.
 */

public class FragmentShopGoods extends Fragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private List<ShopGoodsBean.DataBean> mList;
    private ShopGoodsAdapter adapter;
    private String id;
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
    private void initData() {
        mList = new ArrayList<>();
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
}
