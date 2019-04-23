package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsListAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.GoodsListBean;
import com.jingna.shopapp.util.StatusBarUtils;
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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodsListActivity extends BaseActivity {

    private Context context = SearchGoodsListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.iv_bottom_zonghe)
    ImageView ivBottomZonghe;
    @BindView(R.id.ll_xiaoliang)
    LinearLayout llXiaoliang;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.rl_jiage)
    RelativeLayout rlJiage;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.iv_top_jiage)
    ImageView ivTopJiage;
    @BindView(R.id.iv_bottom_jiage)
    ImageView ivBottomJiage;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_search_text)
    TextView tvSearchText;
    @BindView(R.id.tv_loading)
    TextView tvLoading;

    private String orderBy = "";
    private int page = 1;
    private String goodsName = "";

    private PopupWindow popupWindowZonghe;
    private View popViewZonghe;

    private GoodsListAdapter adapter;
    private List<GoodsListBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_list);

        goodsName = getIntent().getStringExtra("goodsName");
        StatusBarUtils.setStatusBar(SearchGoodsListActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SearchGoodsListActivity.this);
        initData();

    }

    private void initData() {

        tvSearchText.setText(goodsName);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                onReGet();
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                page = page + 1;
                ViseHttp.POST("/AppGoodsShop/queryList")
                        .addParam("pageNum", page+"")
                        .addParam("pageSize", "10")
                        .addParam("goodsName", goodsName)
                        .addParam("orderBy", orderBy)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    Log.e("123123", data);
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        GoodsListBean bean = gson.fromJson(data, GoodsListBean.class);
                                        mList.addAll(bean.getData());
                                        adapter.notifyDataSetChanged();
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

        mList = new ArrayList<>();
        ViseHttp.POST("/AppGoodsShop/queryList")
                .addParam("pageNum", page+"")
                .addParam("pageSize", "10")
                .addParam("goodsName", goodsName)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GoodsListBean bean = gson.fromJson(data, GoodsListBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter = new GoodsListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                            }
                            tvLoading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tvLoading.setVisibility(View.GONE);
                    }
                });

    }

    /**
     * 根据筛选条件刷新列表
     */
    private void onReGet() {

        ViseHttp.POST("/AppGoodsShop/queryList")
                .addParam("pageNum", page+"")
                .addParam("pageSize", "10")
                .addParam("goodsName", goodsName)
                .addParam("orderBy", orderBy)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GoodsListBean bean = gson.fromJson(data, GoodsListBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter.notifyDataSetChanged();
                            }
                            tvLoading.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tvLoading.setVisibility(View.GONE);
                    }
                });

    }

    /**
     * 显示综合筛选排序pop
     */
    private void showPopZonghe(){

        popViewZonghe = LayoutInflater.from(context).inflate(R.layout.popupwindow_goods_list_zonghe, null);

        RelativeLayout rl1 = popViewZonghe.findViewById(R.id.rl1);
        RelativeLayout rl2 = popViewZonghe.findViewById(R.id.rl2);
        RelativeLayout rl3 = popViewZonghe.findViewById(R.id.rl3);
        final TextView tv1 = popViewZonghe.findViewById(R.id.tv1);
        final TextView tv2 = popViewZonghe.findViewById(R.id.tv2);
        final TextView tv3 = popViewZonghe.findViewById(R.id.tv3);
        final ImageView iv1 = popViewZonghe.findViewById(R.id.iv1);
        final ImageView iv2 = popViewZonghe.findViewById(R.id.iv2);
        final ImageView iv3 = popViewZonghe.findViewById(R.id.iv3);

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderBy = "";
                tvLoading.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.parseColor("#FF0004"));
                tv2.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#333333"));
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.GONE);
                onReGet();
                popupWindowZonghe.dismiss();
            }
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderBy = "goods.create_time desc";
                tvLoading.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.parseColor("#333333"));
                tv2.setTextColor(Color.parseColor("#FF0004"));
                tv3.setTextColor(Color.parseColor("#333333"));
                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.GONE);
                onReGet();
                popupWindowZonghe.dismiss();
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderBy = "commentCount desc";
                tvLoading.setVisibility(View.VISIBLE);
                tv1.setTextColor(Color.parseColor("#333333"));
                tv2.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#FF0004"));
                iv1.setVisibility(View.GONE);
                iv2.setVisibility(View.GONE);
                iv3.setVisibility(View.VISIBLE);
                onReGet();
                popupWindowZonghe.dismiss();
            }
        });

        if(TextUtils.isEmpty(orderBy)){
            tv1.setTextColor(Color.parseColor("#FF0004"));
            tv2.setTextColor(Color.parseColor("#333333"));
            tv3.setTextColor(Color.parseColor("#333333"));
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        }else if(orderBy.equals("goods.create_time desc")){
            tv1.setTextColor(Color.parseColor("#333333"));
            tv2.setTextColor(Color.parseColor("#FF0004"));
            tv3.setTextColor(Color.parseColor("#333333"));
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.GONE);
        }else if(orderBy.equals("commentCount desc")){
            tv1.setTextColor(Color.parseColor("#333333"));
            tv2.setTextColor(Color.parseColor("#333333"));
            tv3.setTextColor(Color.parseColor("#FF0004"));
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.VISIBLE);
        }

        popupWindowZonghe = new PopupWindow(popViewZonghe, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindowZonghe.setTouchable(true);
        popupWindowZonghe.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindowZonghe.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindowZonghe.setOutsideTouchable(true);
//        popupWindowZonghe.showAtLocation(getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
        popupWindowZonghe.showAsDropDown(llShaixuan);
        // 设置popWindow的显示和消失动画
//        popupWindowZonghe.setAnimationStyle(R.style.mypopwindow_anim_style);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
        popupWindowZonghe.update();

        popupWindowZonghe.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_zonghe, R.id.ll_xiaoliang, R.id.rl_jiage, R.id.ll_search, R.id.rl_msg})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_zonghe:
                tvZonghe.setTextColor(Color.parseColor("#FF0004"));
                Glide.with(context).load(R.mipmap.bottom_red).into(ivBottomZonghe);
                tvXiaoliang.setTextColor(Color.parseColor("#333333"));
                tvJiage.setTextColor(Color.parseColor("#333333"));
                Glide.with(context).load(R.mipmap.top_b).into(ivTopJiage);
                Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomJiage);
                showPopZonghe();
                break;
            case R.id.ll_xiaoliang:
                tvLoading.setVisibility(View.VISIBLE);
                tvZonghe.setTextColor(Color.parseColor("#333333"));
                Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomZonghe);
                tvXiaoliang.setTextColor(Color.parseColor("#FF0004"));
                tvJiage.setTextColor(Color.parseColor("#333333"));
                Glide.with(context).load(R.mipmap.top_b).into(ivTopJiage);
                Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomJiage);
                orderBy = "goods.sale desc";
                onReGet();
                break;
            case R.id.rl_jiage:
                tvLoading.setVisibility(View.VISIBLE);
                if(!orderBy.equals("goods.price asc")){
                    tvZonghe.setTextColor(Color.parseColor("#333333"));
                    Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomZonghe);
                    tvXiaoliang.setTextColor(Color.parseColor("#333333"));
                    tvJiage.setTextColor(Color.parseColor("#FF0004"));
                    Glide.with(context).load(R.mipmap.top_red).into(ivTopJiage);
                    Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomJiage);
                    orderBy = "goods.price asc";
                    onReGet();
                }else {
                    tvZonghe.setTextColor(Color.parseColor("#333333"));
                    Glide.with(context).load(R.mipmap.bottom_b).into(ivBottomZonghe);
                    tvXiaoliang.setTextColor(Color.parseColor("#333333"));
                    tvJiage.setTextColor(Color.parseColor("#FF0004"));
                    Glide.with(context).load(R.mipmap.top_b).into(ivTopJiage);
                    Glide.with(context).load(R.mipmap.bottom_red).into(ivBottomJiage);
                    orderBy = "goods.price desc";
                    onReGet();
                }
                break;
            case R.id.ll_search:
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_msg:
                intent.setClass(context, MessageActivity.class);
                startActivity(intent);
                break;
        }
    }

}
