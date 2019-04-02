package com.jingna.shopapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.IndexAdapter;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.pages.CommitOrderActivity;
import com.jingna.shopapp.pages.GoodsListActivity;
import com.jingna.shopapp.pages.LoginActivity;
import com.jingna.shopapp.pages.MessageActivity;
import com.jingna.shopapp.pages.MessagePreferentialActivity;
import com.jingna.shopapp.pages.MessageServiceActivity;
import com.jingna.shopapp.pages.OrderTrackingActivity;
import com.jingna.shopapp.pages.RegisterActivity;
import com.jingna.shopapp.pages.RegisterYzmActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.pages.SearchActivity;
import com.jingna.shopapp.pages.ShopIndexActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.jingna.shopapp.widget.ObservableScrollView;
import com.jingna.shopapp.wxapi.OnResponseListener;
import com.jingna.shopapp.wxapi.WXShare;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/2/15.
 */

public class FragmentIndex extends Fragment {

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

    private IndexAdapter adapter;
    private List<String> mList;

    private WXShare wxShare;

    private static final int SDK_PAY_FLAG = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        StatusBarUtils.setStatusBarTransparent(getActivity());
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this, view);
        initView();
//        initWebView();
        initListener();

        return view;
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

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
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
                ViseHttp.POST("/ZhiFuBao/ZhiFuBaoPay/pay")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    String s = jsonObject.optString("data");
                                    aliPay(s);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.ll2:
                intent.setClass(getContext(), CommitOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll3:
                intent.setClass(getContext(), OrderTrackingActivity.class);
                startActivity(intent);
                break;
            case R.id.ll4:
                intent.setClass(getContext(), ShopIndexActivity.class);
                startActivity(intent);
                break;
            case R.id.ll5:
                //微信分享
                DialogCustom dialogCustom = new DialogCustom(getActivity(), "“商城”想要打开“微信”", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        wxShare.shareUrl("http://www.baidu.com", "1", "2",
                                "/upload/13a825c68f296a31200e3503cc660e8.jpg");
                        Log.e("123123", "分享");
                    }
                });
                dialogCustom.show();
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

//    public static void setMargins (View v, int l, int t, int r, int b) {
//        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
//            p.setMargins(l, t, r, b);
//            v.requestLayout();
//        }
//    }

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
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };

}
