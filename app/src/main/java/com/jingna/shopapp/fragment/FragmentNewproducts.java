package com.jingna.shopapp.fragment;

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
import com.jingna.shopapp.adapter.FragmentShopNewGoodsAdapter;
import com.jingna.shopapp.adapter.FragmentYiwanchengAdapter;
import com.jingna.shopapp.adapter.ShopGoodsAdapter;
import com.jingna.shopapp.bean.ShopGoodsBean;
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
 * Created by Administrator on 2019/4/4.
 */

public class FragmentNewproducts extends Fragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private String id;
    private FragmentShopNewGoodsAdapter adapter;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    private List<ShopGoodsBean.DataBean> mList;
    private int page=1;
    private int sx=0;
    public static FragmentNewproducts newInstance(String id) {
        FragmentNewproducts newFragment = new FragmentNewproducts();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newgoods, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        Shop_News_goods(page);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                sx = 1;
                Shop_News_goods(page);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
       /* smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                page = page + 1;
                sx = 1;
                Shop_News_goods(page);
            }
        });*/

    }
    private  void Shop_News_goods(int page){
        mList = new ArrayList<>();
        ViseHttp.GET("/AppSeller/queryList")
                .addParam("pageNum",page+"")
                .addParam("pageSize","10")
                .addParam("orderBy","goods.create_time desc")
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
                                adapter = new FragmentShopNewGoodsAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
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
