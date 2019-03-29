package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.shopapp.bean.ShopIndexBean;
import com.jingna.shopapp.customview.ScaleTransitionPagerTitleView;
import com.jingna.shopapp.fragment.FragmentDaifukuan;
import com.jingna.shopapp.fragment.FragmentDaishouhuo;
import com.jingna.shopapp.fragment.FragmentShopCategory;
import com.jingna.shopapp.fragment.FragmentShopGoods;
import com.jingna.shopapp.fragment.FragmentShopindex;
import com.jingna.shopapp.fragment.FragmentYiquxiao;
import com.jingna.shopapp.fragment.FragmentYiwancheng;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopIndexActivity extends AppCompatActivity {
    private Context context = ShopIndexActivity.this;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.sellerLogo)
    ImageView ivlogo;
    @BindView(R.id.sellerName)
    TextView tvShopname;
    @BindView(R.id.memberNum)
    TextView memberNum;
    @BindView(R.id.btn)
    Button follow;
    private FragmentManager mFragmentManager;
    private GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;
    private String sellerId = "";
    private ArrayList<String> mTitleDataList;
    private int index = 0;
    private int follow_radio = 0;//关注状态 0为关注 1为取消关注
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sellerId = "4";//getIntent().getStringExtra("sellerId");
        setContentView(R.layout.activity_shop_index);
        index = getIntent().getIntExtra("index", 0);
        StatusBarUtils.setStatusBar(ShopIndexActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(ShopIndexActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();
        init();
    }
    @OnClick({R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if (follow_radio == 0) {
                    if(SpUtils.getUserId(context).equals("0")){
                        Intent intent = new Intent();
                        intent.setClass(context, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Attention_interface();
                    }
                }else{
                    Removefollw();
                }
                break;
        }
    }
    //关注
    private void Attention_interface(){
        ViseHttp.POST("/AppSeller/follow")
                .addParam("sellerId",sellerId)
                .addParam("sellerMemId",SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                follow.setText("已关注");
                                follow.setBackgroundResource(R.drawable.bg_ffffff_15dp_bord);
                                follow_radio = 1;
                                Toast.makeText(context,"关注成功!",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context,"参数错误!",Toast.LENGTH_SHORT).show();
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
    //取消关注
    private void Removefollw(){
        ViseHttp.POST("/AppSeller/isFollow")
                .addParam("sellerId",sellerId)
                .addParam("memberId",SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){//bg_ff4d16_25dp
                                follow.setText("+关注");
                                follow.setBackgroundResource(R.drawable.bg_ff4d16_25dp);
                                follow_radio = 0;
                                Toast.makeText(context,"取消成功!",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context,"参数错误!",Toast.LENGTH_SHORT).show();
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
    private void init() {
        ViseHttp.GET("/AppSeller/getOne")
                .addParam("sellerId",sellerId)
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ShopIndexBean shopIndexBean = gson.fromJson(data,ShopIndexBean.class);
                                if(!TextUtils.isEmpty(shopIndexBean.getData().getAppSellerLogo())){
                                    Glide.with(context).load(Const.BASE_URL+shopIndexBean.getData().getAppSellerLogo()).into(ivlogo);
                                }
                                tvShopname.setText(shopIndexBean.getData().getSellerName());
                                memberNum.setText(shopIndexBean.getData().getMemberNum()+"人关注");
                                if(shopIndexBean.getData().getMemStatus().equals("1")){
                                    follow.setText("已关注");
                                    follow.setBackgroundResource(R.drawable.bg_ffffff_15dp_bord);
                                    follow_radio = 1;
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

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentShopindex.newInstance(sellerId));
        fragmentList.add(FragmentShopGoods.newInstance(sellerId));
        fragmentList.add(FragmentShopCategory.newInstance(sellerId));
        fragmentList.add(FragmentYiwancheng.newInstance(sellerId));
        mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);

        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("首页");
        mTitleDataList.add("商品");
        mTitleDataList.add("分类");
        mTitleDataList.add("新品");

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                //设置字体
                simplePagerTitleView.setPadding(70,0,70,0);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                simplePagerTitleView.setNormalColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                /*indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setYOffset(UIUtil.dip2px(context, 3));*/
                indicator.setColors(Color.parseColor("#FFFFFF"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
//        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, mViewPager);
        mViewPager.setCurrentItem(index);
    }
}
