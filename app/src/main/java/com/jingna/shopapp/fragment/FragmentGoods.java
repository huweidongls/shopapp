package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentGoodsDetailsCommentListAdapter;
import com.jingna.shopapp.adapter.FragmentGoodsSelectPopRvAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.bean.FragmentGoodsSelectPopBean;
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

    private String id = "5";

    private List<String> bannerList;

    private View selectView;
    private PopupWindow popupWindow;

    /**
     * pop列表
     */
    private List<FragmentGoodsSelectPopBean.DataBean.AttrListBean> mSelectList;
    private FragmentGoodsSelectPopRvAdapter popRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, null);

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

    private void initSelect() {

        selectView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_fragment_goods_select, null);
        RelativeLayout rlBack = selectView.findViewById(R.id.rl_back);
        RecyclerView popRv = selectView.findViewById(R.id.rv);

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        ViseHttp.GET("/AppGoodsShop/getGoodsByAttribute")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                FragmentGoodsSelectPopBean selectPopBean = gson.fromJson(data, FragmentGoodsSelectPopBean.class);
                                mSelectList = selectPopBean.getData().getAttrList();
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
