package com.jingna.shopapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.IndexAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.IndexGoodsBean;
import com.jingna.shopapp.bean.IndexSlideBean;
import com.jingna.shopapp.bean.WxPayBean;
import com.jingna.shopapp.pages.GoodsListActivity;
import com.jingna.shopapp.pages.MessageActivity;
import com.jingna.shopapp.pages.SearchActivity;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.jingna.shopapp.widget.ObservableScrollView;
import com.jingna.shopapp.wxapi.OnResponseListener;
import com.jingna.shopapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
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
 * Created by Administrator on 2019/2/15.
 */

public class FragmentIndex extends BaseFragment {

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_saoyisao)
    RelativeLayout rlSaoyisao;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.iv_saoyisao)
    ImageView ivSaoyisao;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_small_search)
    ImageView ivSmallSearch;
    @BindView(R.id.tv_search_text)
    TextView tvSearchText;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.banner)
    Banner banner;
    /**
     * 分类绑定
     */
    @BindView(R.id.type1)
    ImageView type1;
    @BindView(R.id.type1_name)
    TextView type1_name;
    @BindView(R.id.type2)
    ImageView type2;
    @BindView(R.id.type2_name)
    TextView type2_name;
    @BindView(R.id.type3)
    ImageView type3;
    @BindView(R.id.type3_name)
    TextView type3_name;
    @BindView(R.id.type4)
    ImageView type4;
    @BindView(R.id.type4_name)
    TextView type4_name;
    @BindView(R.id.type5)
    ImageView type5;
    @BindView(R.id.type5_name)
    TextView type5_name;
    //分类绑定结束
    private String memberid= "0";
    private IndexAdapter adapter;
    private List<IndexGoodsBean.DataBean> mList;

    private WXShare wxShare;
    private IWXAPI api;

    private List<String> imgList;
    private String l1="";
    private String l2="";
    private String l3="";
    private String l4="";
    private String l5="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);
        api = WXAPIFactory.createWXAPI(getContext(), null);
        StatusBarUtils.setStatusBarTransparent(getActivity());
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this, view);
        initView();
//        initWebView();
        initListener();
        Homepageinterface();
        return view;
    }

    /**
     *
     * 加载首页幻灯与分类
     */
    private void  Homepageinterface(){
        imgList = new ArrayList<>();
        ViseHttp.GET("IndexPageApi/findBannerCategory")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                IndexSlideBean  IndexSlideBean = gson.fromJson(data, IndexSlideBean.class);
                                for(com.jingna.shopapp.bean.IndexSlideBean.DataBean.BannerBean ban : IndexSlideBean.getData().getBanner()){
                                    imgList.add(Const.BASE_URL+ban.getAppPic());
                                }
                                init(banner,imgList);
                                //开始分配分类第一组
                                Glide.with(getContext()).load(Const.BASE_URL+IndexSlideBean.getData().getShopCategory().get(0).getAppCategoryPic()).into(type1);
                                type1_name.setText(IndexSlideBean.getData().getShopCategory().get(0).getCategoryName());
                                l1 = IndexSlideBean.getData().getShopCategory().get(0).getId()+"";
                                //第二组
                                Glide.with(getContext()).load(Const.BASE_URL+IndexSlideBean.getData().getShopCategory().get(1).getAppCategoryPic()).into(type2);
                                type2_name.setText(IndexSlideBean.getData().getShopCategory().get(1).getCategoryName());
                                l2 = IndexSlideBean.getData().getShopCategory().get(1).getId()+"";
                                //第三组
                                Glide.with(getContext()).load(Const.BASE_URL+IndexSlideBean.getData().getShopCategory().get(2).getAppCategoryPic()).into(type3);
                                type3_name.setText(IndexSlideBean.getData().getShopCategory().get(2).getCategoryName());
                                l3 = IndexSlideBean.getData().getShopCategory().get(2).getId()+"";
                                //第四组
                                Glide.with(getContext()).load(Const.BASE_URL+IndexSlideBean.getData().getShopCategory().get(3).getAppCategoryPic()).into(type4);
                                type4_name.setText(IndexSlideBean.getData().getShopCategory().get(3).getCategoryName());
                                l4 = IndexSlideBean.getData().getShopCategory().get(3).getId()+"";
                                //第五组
                                Glide.with(getContext()).load(Const.BASE_URL+IndexSlideBean.getData().getShopCategory().get(4).getAppCategoryPic()).into(type5);
                                type5_name.setText(IndexSlideBean.getData().getShopCategory().get(4).getCategoryName());
                                l5 = IndexSlideBean.getData().getShopCategory().get(4).getId()+"";
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
     * 微信分享回调
     */
    private void initListener() {

        wxShare = new WXShare(getContext());
        wxShare.setListener(new OnResponseListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showShort(getContext(), "分享成功");
            }

            @Override
            public void onCancel() {
                ToastUtil.showShort(getContext(), "取消分享");
            }

            @Override
            public void onFail(String message) {
                ToastUtil.showShort(getContext(), "分享失败");
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        wxShare.register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wxShare.unregister();
    }

    public void initWebView(){

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        String url = "http://www.baidu.com";
        webView.loadUrl(url);
    }

    private void initView() {

//        int result = 0;
//        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getContext().getResources().getDimensionPixelSize(resourceId);
//        }
//        setMargins(rlSearch, 0, result, 0, 0);

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    rlSearch.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
//                    StatusBarUtils.setStatusBarTransparent(getActivity());
                    llSearch.setBackgroundResource(R.drawable.bg_index_search);
                    rlSaoyisao.setBackgroundResource(R.drawable.bg_42000000_round);
                    rlMessage.setBackgroundResource(R.drawable.bg_42000000_round);
                    Glide.with(getContext()).load(R.mipmap.saoyisao).into(ivSaoyisao);
                    Glide.with(getContext()).load(R.mipmap.message).into(ivMessage);
                    Glide.with(getContext()).load(R.mipmap.search).into(ivSmallSearch);
                    tvSearchText.setTextColor(Color.parseColor("#9C9C9C"));
                } else if (y > 0 && y <= 888) {
                    float scale = (float) y / 888;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    rlSearch.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) alpha, 255, 255, 255));
                } else {
                    rlSearch.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) 255, 255, 255, 255));
                    llSearch.setBackgroundResource(R.drawable.bg_index_search_dark);
                    rlSaoyisao.setBackgroundResource(R.drawable.bg_ffffff_round);
                    rlMessage.setBackgroundResource(R.drawable.bg_ffffff_round);
                    Glide.with(getContext()).load(R.mipmap.saoyisao_dark).into(ivSaoyisao);
                    Glide.with(getContext()).load(R.mipmap.message_dark).into(ivMessage);
                    Glide.with(getContext()).load(R.mipmap.search_light).into(ivSmallSearch);
                    tvSearchText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });
        /**
         * 首页推荐商品
         */
        mList = new ArrayList<>();
        if( !SpUtils.getUserId(getContext()).equals("0")){
            memberid = SpUtils.getUserId(getContext());
        }
        ViseHttp.GET("IndexPageApi/queryRecommandStatusGoods")
                .addParam("memberId",memberid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("12121212", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                IndexGoodsBean bean = gson.fromJson(data, IndexGoodsBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter = new IndexAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else {
            StatusBarUtils.setStatusBarTransparent(getActivity());
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll_search, R.id.rl_message})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll1:
                intent.setClass(getContext(), GoodsListActivity.class);
                intent.putExtra("id",l1);
                startActivity(intent);
//                ViseHttp.POST("/pay/wxpay")
//                        .request(new ACallback<String>() {
//                            @Override
//                            public void onSuccess(String data) {
//                                Log.e("123123", data);
//                                Gson gson = new Gson();
//                                WxPayBean payBean = gson.fromJson(data, WxPayBean.class);
//                                wxPay(payBean);
//                            }
//
//                            @Override
//                            public void onFail(int errCode, String errMsg) {
//
//                            }
//                        });

//                //微信分享
//                DialogCustom dialogCustom = new DialogCustom(getActivity(), "“商城”想要打开“微信”", new DialogCustom.OnYesListener() {
//                    @Override
//                    public void onYes() {
//                        wxShare.shareUrl("http://www.baidu.com", "1", "2",
//                                "/upload/13a825c68f296a31200e3503cc660e8.jpg");
//                        Log.e("123123", "分享");
//                    }
//                });
//                dialogCustom.show();
                break;
            case R.id.ll2:
                intent.setClass(getContext(), GoodsListActivity.class);
                intent.putExtra("id",l2);
                startActivity(intent);
                break;
            case R.id.ll3:
                intent.setClass(getContext(), GoodsListActivity.class);
                intent.putExtra("id",l3);
                startActivity(intent);
                break;
            case R.id.ll4:
                intent.setClass(getContext(), GoodsListActivity.class);
                intent.putExtra("id",l4);
                startActivity(intent);
                break;
            case R.id.ll5:
                intent.setClass(getContext(), GoodsListActivity.class);
                intent.putExtra("id",l5);
                startActivity(intent);
                break;
            case R.id.ll_search:
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_message:
                intent.setClass(getContext(), MessageActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void wxPay(WxPayBean model) {
        api.registerApp(WXShare.APP_ID);
        PayReq req = new PayReq();
        req.appId = model.getAppId();
        req.partnerId = model.getMchId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimeStamp() + "";
        req.packageValue = "Sign=WXPay";
        req.sign = model.getPaySign();
        req.extData = "app data";
        api.sendReq(req);
    }

//    public static void setMargins (View v, int l, int t, int r, int b) {
//        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
//            p.setMargins(l, t, r, b);
//            v.requestLayout();
//        }
//    }

}
