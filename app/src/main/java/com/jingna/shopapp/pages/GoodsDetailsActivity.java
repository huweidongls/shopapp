package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.customview.ScaleTransitionPagerTitleView;
import com.jingna.shopapp.fragment.FragmentComment;
import com.jingna.shopapp.fragment.FragmentEvaluation;
import com.jingna.shopapp.fragment.FragmentGoods;
import com.jingna.shopapp.fragment.FragmentGoodsDetails;
import com.jingna.shopapp.util.StatusBarUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailsActivity extends BaseActivity {

    private Context context = GoodsDetailsActivity.this;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    private FragmentManager mFragmentManager;
    private GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;

    private ArrayList<String> mTitleDataList;

    private String id = "";
    private String signJson = "";
    private int goodsNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        goodsNum = getIntent().getIntExtra("goodsnum", 1);
        signJson = getIntent().getStringExtra("attr");
        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBarTransparent(GoodsDetailsActivity.this);
        ButterKnife.bind(GoodsDetailsActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();

    }

    public void toCommentFragment(){
        mViewPager.setCurrentItem(2);
    }

    public RelativeLayout getRlBack(){
        return rlBack;
    }

    public ImageView getIvBack(){
        return ivBack;
    }

    public MagicIndicator getMagic(){
        return magicIndicator;
    }

    public RelativeLayout getRlTop(){
        return rlTop;
    }

    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentGoods.newInstance(id, signJson, goodsNum));
        fragmentList.add(FragmentGoodsDetails.newInstance(id));
        fragmentList.add(FragmentComment.newInstance(id));
        fragmentList.add(FragmentEvaluation.newInstance(id));
        mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);

        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("商品");
        mTitleDataList.add("详情");
        mTitleDataList.add("评价");
        mTitleDataList.add("推荐");

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
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#232325"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#232325"));
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
                indicator.setColors(Color.parseColor("#EF250F"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
//        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, mViewPager);

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
