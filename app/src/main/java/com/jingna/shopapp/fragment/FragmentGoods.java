package com.jingna.shopapp.fragment;

import android.content.Intent;
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
import android.widget.LinearLayout;
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
import com.jingna.shopapp.bean.RichTextBean;
import com.jingna.shopapp.pages.CommitOrderActivity;
import com.jingna.shopapp.pages.GoodsDetailsActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.pages.ShopIndexActivity;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.HtmlFromUtils;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.jingna.shopapp.widget.ISlideCallback;
import com.jingna.shopapp.widget.ObservableScrollView;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bleu.widget.slidedetails.SlideDetailsLayout;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentGoods extends BaseFragment implements ISlideCallback {

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.rv_comment)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.tv_favorablerate)
    TextView tvFavorablerate;
    @BindView(R.id.iv_shop_avatar)
    ImageView ivShopAvatar;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_follow_num)
    TextView tvShopFollowNum;
    @BindView(R.id.tv_has_select)
    TextView tvHasSelect;
    @BindView(R.id.iv_follow)
    ImageView ivFollow;
    @BindView(R.id.slidedetails)
    SlideDetailsLayout mSlideDetailsLayout;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tv_rl2)
    TextView tvRl2;

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
    private String skuid = "";

    private FragmentGoodsBean goodsBean;

    private TextView tvSure;
    private boolean isToBuy = false;//是否是立即购买

    public static FragmentGoods newInstance(String id, String attr, int goodsNum) {
        FragmentGoods newFragment = new FragmentGoods();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("attr", attr);
        bundle.putInt("goodsnum", goodsNum);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    private Map<String, String> signMap;
    private String signJson = "";
    private int goodsNum = 1;
    private double goodsPrice = 1.00;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            signJson = args.getString("attr");
            goodsNum = args.getInt("goodsnum", 1);
        }
        signMap = new HashMap<>();
        ButterKnife.bind(this, view);
//        initView();
        initData();
        initDetails();
        initSelect();

        return view;
    }

    private void initView() {

        GoodsDetailsActivity goodsDetailsActivity = (GoodsDetailsActivity) getActivity();
        final RelativeLayout rlBack = goodsDetailsActivity.getRlBack();
        final ImageView ivBack = goodsDetailsActivity.getIvBack();
        final MagicIndicator magicIndicator = goodsDetailsActivity.getMagic();
        final RelativeLayout rlTop = goodsDetailsActivity.getRlTop();

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    rlTop.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
//                    StatusBarUtils.setStatusBarTransparent(getActivity());
                    rlBack.setBackgroundResource(R.drawable.bg_42000000_round);
                    Glide.with(getContext()).load(R.mipmap.backff).into(ivBack);
                } else if (y > 0 && y <= 888) {
                    float scale = (float) y / 888;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    rlTop.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) alpha, 255, 255, 255));
                } else {
                    rlTop.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) 255, 255, 255, 255));
                    rlBack.setBackgroundResource(R.drawable.bg_ffffff_round);
                    Glide.with(getContext()).load(R.mipmap.back_b).into(ivBack);
                }
            }
        });

    }

    private void initDetails() {

        ViseHttp.GET("/AppGoodsShop/richText")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                RichTextBean textBean = gson.fromJson(data, RichTextBean.class);
                                HtmlFromUtils.setTextFromHtml(getActivity(), tvRl2, textBean.getData().getDetailMobileHtml());
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
    public void openDetails(boolean smooth) {
        mSlideDetailsLayout.smoothOpen(smooth);
    }

    @Override
    public void closeDetails(boolean smooth) {
        mSlideDetailsLayout.smoothClose(smooth);
    }

    private void initData() {

        ViseHttp.GET("/AppGoodsShop/getOne")
                .addParam("goodsId", id)
                .addParam("memberId", SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                goodsBean = gson.fromJson(data, FragmentGoodsBean.class);
                                //加载轮播图
                                String bannerPic = goodsBean.getData().getShopGoods().getAppPic();
                                if(!TextUtils.isEmpty(bannerPic)){
                                    String[] banners = bannerPic.split(",");
                                    bannerList = new ArrayList<>();
                                    for (int i = 0; i<banners.length; i++){
                                        bannerList.add(Const.BASE_URL+banners[i]);
                                    }
                                    init(banner, bannerList);
                                }
                                //加载商品价格
                                if(TextUtils.isEmpty(signJson)){
                                    goodsPrice = goodsBean.getData().getShopGoods().getPrice();
                                    tvPrice.setText("¥"+goodsPrice*goodsNum);
                                }
                                //加载商品标题
                                tvTitle.setText(goodsBean.getData().getShopGoods().getGoodsName());
                                //加载评论数量
                                tvCommentNum.setText("评价("+goodsBean.getData().getCommentTotalNum()+"条)");
                                //加载好评率
                                if(TextUtils.isEmpty(goodsBean.getData().getShopGoods().getFavorableRate())){
                                    tvFavorablerate.setText("0%");
                                }else {
                                    tvFavorablerate.setText(goodsBean.getData().getShopGoods().getFavorableRate()+"%");
                                }
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
                                //加载店铺信息
                                Glide.with(getContext()).load(Const.BASE_URL+goodsBean.getData().getShopGoods().getAppSellerLogo()).into(ivShopAvatar);
                                tvShopName.setText(goodsBean.getData().getShopGoods().getSellerName());
                                tvShopFollowNum.setText(goodsBean.getData().getShopGoods().getFollowSellerNum()+"人关注");
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
        final TextView tvPopPrice = selectView.findViewById(R.id.tv_price);
        tvSure = selectView.findViewById(R.id.tv_sure);
        final TextView tvGoodsType = selectView.findViewById(R.id.tv_goods_type);
        RelativeLayout rlJianhao = selectView.findViewById(R.id.rl_jianhao);
        final TextView tvGoodsNum = selectView.findViewById(R.id.tv_goods_num);
        RelativeLayout rlJiahao = selectView.findViewById(R.id.rl_jiahao);

        tvGoodsNum.setText(goodsNum+"");

        rlJianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goodsNum > 1){
                    goodsNum = goodsNum - 1;
                    tvGoodsNum.setText(goodsNum+"");
                    tvPrice.setText("¥"+goodsPrice*goodsNum);
                }
            }
        });

        rlJiahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsNum = goodsNum + 1;
                tvGoodsNum.setText(goodsNum+"");
                tvPrice.setText("¥"+goodsPrice*goodsNum);
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SpUtils.getUserId(getContext()).equals("0")){
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    if(isToBuy){
                        if(signMap.size() == mSelectList.size()) {
                            if(!TextUtils.isEmpty(skuid)){
                                Intent intent = new Intent();
                                intent.setClass(getContext(), CommitOrderActivity.class);
                                intent.putExtra("bean", goodsBean);
                                intent.putExtra("id", id);
                                intent.putExtra("skuid", skuid);
                                intent.putExtra("goodsnum", goodsNum);
                                intent.putExtra("goodsprice", goodsPrice);
                                startActivity(intent);
                                popupWindow.dismiss();
                            }else {
                                ToastUtil.showShort(getContext(), "该商品暂无库存");
                            }
                        }else {
                            ToastUtil.showShort(getContext(), "请选择商品规格后在进行购买");
                        }
                    }else {
                        if(signMap.size() == mSelectList.size()){
                            if(!TextUtils.isEmpty(skuid)){
                                Gson gson = new Gson();
                                String attr = gson.toJson(signMap);
                                //添加购物车
                                ViseHttp.POST("/ShopCart/toUpdate")
                                        .addParam("userid", SpUtils.getUserId(getContext()))
                                        .addParam("goodsid", id)
                                        .addParam("sellerId", goodsBean.getData().getShopGoods().getSellerId()+"")
                                        .addParam("goodsNum", goodsNum+"")
                                        .addParam("attributesStr", attr)
                                        .addParam("skuid", skuid)
                                        .request(new ACallback<String>() {
                                            @Override
                                            public void onSuccess(String data) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(data);
                                                    if(jsonObject.optString("status").equals("200")){
                                                        ToastUtil.showShort(getContext(), "已添加到购物车");
                                                        popupWindow.dismiss();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onFail(int errCode, String errMsg) {

                                            }
                                        });
                            }else {
                                ToastUtil.showShort(getContext(), "该商品暂无库存");
                            }
                        }else {
                            ToastUtil.showShort(getContext(), "请选择商品规格后在添加到购物车");
                        }
                    }
                }
            }
        });

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        ViseHttp.POST("/AppGoodsShop/selectGoods")
                .addParam("goodsId", id)
                .addParam("attrMap", signJson)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                final Gson gson = new Gson();
                                FragmentGoodsSelectPopBean selectPopBean = gson.fromJson(data, FragmentGoodsSelectPopBean.class);
                                mSelectList = selectPopBean.getData();
                                popRvAdapter = new FragmentGoodsSelectPopRvAdapter(mSelectList, new FragmentGoodsSelectPopRvAdapter.ClickListener() {
                                    @Override
                                    public void onClick(String pos, String i) {
                                        signMap.put(pos, i);
                                        if(signMap.size() == mSelectList.size()){
                                            String sign = "";
//                                            for (int a = 0; a<signMap.size(); a++){
//                                                sign = sign + signMap.get(a) + ",";
//                                            }
                                            for (String value : signMap.values()) {
                                                sign = sign + value + ",";
                                            }
                                            tvHasSelect.setText(sign.substring(0, sign.length()-1));
                                            ViseHttp.GET("/AppGoodsShop/resultGoods")
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
                                                                        int lowstock = Integer.valueOf(resultBean.getData().get(0).getLowStock());
                                                                        int stock = Integer.valueOf(resultBean.getData().get(0).getStock());
                                                                        Log.e("123123", "low"+lowstock+"stock"+stock);
                                                                        if(stock>lowstock){
                                                                            Glide.with(getContext()).load(Const.BASE_URL+resultBean.getData().get(0).getAppPic()).into(ivTitle);
                                                                            goodsPrice = resultBean.getData().get(0).getPrice();
                                                                            tvPrice.setText("¥"+resultBean.getData().get(0).getPrice()*goodsNum);
                                                                            tvPopPrice.setText("¥"+resultBean.getData().get(0).getPrice());
                                                                            skuid = resultBean.getData().get(0).getSkuId();
                                                                            tvPopPrice.setVisibility(View.VISIBLE);
                                                                            tvGoodsType.setVisibility(View.GONE);
                                                                        }else if(stock<=lowstock){
                                                                            goodsPrice = goodsBean.getData().getShopGoods().getPrice();
                                                                            tvPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice()*goodsNum);
                                                                            tvGoodsType.setText("暂无库存");
                                                                            tvPopPrice.setVisibility(View.GONE);
                                                                            tvGoodsType.setVisibility(View.VISIBLE);
                                                                            skuid = "";
                                                                        }
                                                                    }else if(resultBean.getData()==null||resultBean.getData().size()==0){
                                                                        goodsPrice = goodsBean.getData().getShopGoods().getPrice();
                                                                        tvPrice.setText("¥"+goodsBean.getData().getShopGoods().getPrice());
                                                                        tvGoodsType.setText("暂无库存");
                                                                        tvPopPrice.setVisibility(View.GONE);
                                                                        tvGoodsType.setVisibility(View.VISIBLE);
                                                                        skuid = "";
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
                                LinearLayoutManager manager = new LinearLayoutManager(getContext()){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
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
                        Log.e("123123", errMsg);
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

    @OnClick({R.id.rl_select, R.id.tv_buy, R.id.tv_insert_car, R.id.iv_follow, R.id.rl_to_shop, R.id.tv_all_comment
    , R.id.ll_shop, R.id.tv1, R.id.tv2, R.id.tv3})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_select:
                //显示选择pop
                isToBuy = false;
                tvSure.setText("加入购物车");
                showSelect();
                break;
            case R.id.tv_buy:
                isToBuy = true;
                tvSure.setText("确定");
                showSelect();
                break;
            case R.id.tv_insert_car:
                isToBuy = false;
                tvSure.setText("确定");
                showSelect();
                break;
            case R.id.iv_follow:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else if(goodsBean.getData().getShopGoods().getMemberStatus().equals("1")){
                    followGoods("0");
                }else {
                    followGoods("1");
                }
                break;
            case R.id.rl_to_shop:
                //进入店铺
                intent.setClass(getContext(), ShopIndexActivity.class);
                intent.putExtra("sellerId", goodsBean.getData().getShopGoods().getSellerId()+"");
                startActivity(intent);
                break;
            case R.id.tv_all_comment:
                //跳转全部评价
                GoodsDetailsActivity activity = (GoodsDetailsActivity) getActivity();
                activity.toCommentFragment();
                break;
            case R.id.ll_shop:
                //进入店铺
                intent.setClass(getContext(), ShopIndexActivity.class);
                intent.putExtra("sellerId", goodsBean.getData().getShopGoods().getSellerId()+"");
                startActivity(intent);
                break;
            case R.id.tv1:
                tv1.setTextColor(Color.parseColor("#FF0004"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv2:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#FF0004"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv3:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#FF0004"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 关注商品
     */
    private void followGoods(final String type) {

        ViseHttp.POST("/AppGoodsShop/isFollow")
                .addParam("goodsId", id)
                .addParam("memberId", SpUtils.getUserId(getContext()))
                .addParam("type", type)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                if(type.equals("1")){
                                    ToastUtil.showShort(getContext(), "关注成功");
                                    goodsBean.getData().getShopGoods().setMemberStatus("1");
                                    Glide.with(getContext()).load(R.mipmap.yiguanzhu).into(ivFollow);
                                }else if(type.equals("0")){
                                    ToastUtil.showShort(getContext(), "取消关注成功");
                                    goodsBean.getData().getShopGoods().setMemberStatus("0");
                                    Glide.with(getContext()).load(R.mipmap.guanzhu).into(ivFollow);
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