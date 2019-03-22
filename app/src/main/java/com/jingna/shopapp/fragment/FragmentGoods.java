package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentGoodsDetailsCommentListAdapter;
import com.jingna.shopapp.adapter.FragmentGoodsSelectPopRvAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.bean.FragmentGoodsSelectPopBean;
import com.jingna.shopapp.bean.GoodsSelectResultBean;
import com.jingna.shopapp.util.Const;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private List<String> bannerList;

    private View selectView;
    private PopupWindow popupWindow;

    /**
     * pop列表
     */
    private List<FragmentGoodsSelectPopBean.DataBean> mSelectList;
    private FragmentGoodsSelectPopRvAdapter popRvAdapter;

    private String id = "";

    public static FragmentGoods newInstance(String id) {
        FragmentGoods newFragment = new FragmentGoods();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    private Map<Integer, String> signMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        signMap = new HashMap<>();
        ButterKnife.bind(this, view);
        initData();
        initSelect();

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
                                if(!TextUtils.isEmpty(bannerPic)){
                                    String[] banners = bannerPic.split(",");
                                    bannerList = new ArrayList<>();
                                    for (int i = 0; i<banners.length; i++){
                                        bannerList.add(Const.BASE_URL+banners[i]);
                                    }
                                    init(banner, bannerList);
                                }
                                //加载商品价格
                                tvPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
                                //加载商品标题
                                tvTitle.setText(goodsBean.getData().getShopGoods().getGoodsName());
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

    private void initSelect() {

        selectView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_fragment_goods_select, null);
        RelativeLayout rlBack = selectView.findViewById(R.id.rl_back);
        final RecyclerView popRv = selectView.findViewById(R.id.rv);
        final ImageView ivTitle = selectView.findViewById(R.id.iv_title);
        final TextView tvPrice = selectView.findViewById(R.id.tv_price);

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        ViseHttp.POST("/AppGoodsShop/selectGoods")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                FragmentGoodsSelectPopBean selectPopBean = gson.fromJson(data, FragmentGoodsSelectPopBean.class);
                                mSelectList = selectPopBean.getData();
                                popRvAdapter = new FragmentGoodsSelectPopRvAdapter(mSelectList, new FragmentGoodsSelectPopRvAdapter.ClickListener() {
                                    @Override
                                    public void onClick(int pos, String i) {
                                        signMap.put(pos, i);
                                        if(signMap.size() == mSelectList.size()){
                                            String sign = "";
                                            for (int a = 0; a<signMap.size(); a++){
                                                sign = sign + signMap.get(a) + ",";
                                            }
                                            ViseHttp.POST("/AppGoodsShop/resultGoods")
                                                    .addParam("goodsId", id)
                                                    .addParam("attrs", sign)
                                                    .request(new ACallback<String>() {
                                                        @Override
                                                        public void onSuccess(String data) {
                                                            Log.e("123123", data);
                                                            try {
                                                                JSONObject jsonObject1 = new JSONObject(data);
                                                                if(jsonObject1.optString("status").equals("200")){
                                                                    Gson gson1 = new Gson();
                                                                    GoodsSelectResultBean resultBean = gson1.fromJson(data, GoodsSelectResultBean.class);
                                                                    if(resultBean.getData()!=null&&resultBean.getData().size()>0){
                                                                        Glide.with(getContext()).load(Const.BASE_URL+resultBean.getData().get(0).getPic()).into(ivTitle);
                                                                        tvPrice.setText("¥"+resultBean.getData().get(0).getPrice());
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
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                popRv.setLayoutManager(manager);
                                popRv.setAdapter(popRvAdapter);
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

    /**
     * 显示选择select
     */
    private void showSelect(){

        popupWindow = new PopupWindow(selectView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
    }

    @OnClick({R.id.rl_select})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_select:
                //显示选择pop
                showSelect();
                break;
        }
    }

}
