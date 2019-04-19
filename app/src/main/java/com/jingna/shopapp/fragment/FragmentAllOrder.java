package com.jingna.shopapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentAllOrderAdapter;
import com.jingna.shopapp.base.OrderBaseFragment;
import com.jingna.shopapp.bean.CommitOrderZhifubaoBean;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.ToastUtil;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/16.
 */

public class FragmentAllOrder extends OrderBaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_loading)
    TextView tvLoading;

    private FragmentAllOrderAdapter adapter;
    private List<OrderDaifukuanBean.DataBean> mList;

    private int page = 1;

    private static final int SDK_PAY_FLAG = 1;
    private int position;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_all_order, null);
//
//        ButterKnife.bind(this, view);
//        initData();
//
//        return view;
//    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_order, null);

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

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.GET("/AppOrder/queryList")
                        .addParam("pageNum", "1")
                        .addParam("pageSize", "10")
                        .addParam("type", "10")
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
                        .addParam("type", "10")
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
                .addParam("type", "10")
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
                                adapter = new FragmentAllOrderAdapter(mList, new FragmentAllOrderAdapter.ClickListener() {
                                    @Override
                                    public void onPay(final int pos) {
                                        ViseHttp.GET("/AppOrder/listOrdersSubmitted")
                                                .addParam("id", mList.get(pos).getId())
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(data);
                                                            if(jsonObject1.optString("status").equals("200")){
                                                                Gson gson = new Gson();
                                                                CommitOrderZhifubaoBean zhifubaoBean = gson.fromJson(data, CommitOrderZhifubaoBean.class);
                                                                position = pos;
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

                                    @Override
                                    public void onReturnPrice(final int pos) {
                                        DialogCustom dialogCustom = new DialogCustom(getContext(), "是否发起退款", new DialogCustom.OnYesListener() {
                                            @Override
                                            public void onYes() {
                                                ViseHttp.GET("/AppOrder/orderRefund")
                                                        .addParam("id", mList.get(pos).getId())
                                                        .request(new ACallback<String>() {
                                                            @Override
                                                            public void onSuccess(String data) {
                                                                try {
                                                                    JSONObject jsonObject1 = new JSONObject(data);
                                                                    if(jsonObject1.optString("status").equals("200")){
                                                                        ToastUtil.showShort(getContext(), "退款成功");
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
                                    }
                                });
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

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
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
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        mList.get(position).setOrderStatus("8");
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }

    };

}
