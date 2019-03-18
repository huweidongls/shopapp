package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentGoodsDetailsCommentListAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.util.Const;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentGoods extends BaseFragment {

    @BindView(R.id.rv_comment)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private FragmentGoodsDetailsCommentListAdapter adapter;
    private List<FragmentGoodsBean.DataBean.CommentListBean> mList;

    private String id = "5";

    private List<String> bannerList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        ViseHttp.GET("/AppGoodsShop/getOne")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                FragmentGoodsBean goodsBean = gson.fromJson(data, FragmentGoodsBean.class);
                                //加载轮播图
                                String bannerPic = goodsBean.getData().getShopGoods().getPic();
                                String[] banners = bannerPic.split(",");
                                bannerList = new ArrayList<>();
                                for (int i = 0; i<banners.length; i++){
                                    bannerList.add(Const.BASE_URL+banners[i]);
                                }
                                init(banner, bannerList);
                                //加载商品价格
                                tvPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
                                //加载商品标题
                                tvTitle.setText(goodsBean.getData().getShopGoods().getProductName());
                                //加载评论列表
                                mList = goodsBean.getData().getCommentList();
                                adapter = new FragmentGoodsDetailsCommentListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext()){
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
