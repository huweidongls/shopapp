package com.jingna.shopapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentYiquxiaoAdapter;
import com.jingna.shopapp.base.OrderBaseFragment;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentYiquxiao extends OrderBaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_loading)
    TextView tvLoading;

    private FragmentYiquxiaoAdapter adapter;
    private List<OrderDaifukuanBean.DataBean> mList;

    private int page = 1;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_yiquxiao, null);
//
//        ButterKnife.bind(this, view);
//        initData();
//
//        return view;
//    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_yiquxiao, null);

        ButterKnife.bind(this, view);
//        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        tvLoading.setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void hide() {
        tvLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

        tvLoading.setVisibility(View.VISIBLE);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET("/AppOrder/queryList")
                        .addParam("pageNum", "1")
                        .addParam("pageSize", "10")
                        .addParam("type", "5")
                        .addParam("userId", SpUtils.getUserId(getContext()))
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        OrderDaifukuanBean bean = gson.fromJson(data, OrderDaifukuanBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                refreshLayout.finishRefresh(500);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(500);
                            }
                        });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET("/AppOrder/queryList")
                        .addParam("pageNum", page + "")
                        .addParam("pageSize", "10")
                        .addParam("type", "5")
                        .addParam("userId", SpUtils.getUserId(getContext()))
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        OrderDaifukuanBean bean = gson.fromJson(data, OrderDaifukuanBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page + 1;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                refreshLayout.finishLoadMore(500);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(500);
                            }
                        });
            }
        });

        ViseHttp.GET("/AppOrder/queryList")
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .addParam("type", "5")
                .addParam("userId", SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                OrderDaifukuanBean bean = gson.fromJson(data, OrderDaifukuanBean.class);
                                mList = bean.getData();
                                adapter = new FragmentYiquxiaoAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                tvLoading.setVisibility(View.GONE);
                                page = 2;
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
