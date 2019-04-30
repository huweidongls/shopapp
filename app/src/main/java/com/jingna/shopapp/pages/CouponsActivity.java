package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsDetailsViewpagerAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.fragment.FragmentExpiredCoupons;
import com.jingna.shopapp.fragment.FragmentNotUseCoupons;
import com.jingna.shopapp.fragment.FragmentUseRecordCoupons;
import com.jingna.shopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponsActivity extends BaseActivity {

    private Context context = CouponsActivity.this;

    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tv_not_use)
    TextView tvNotUse;
    @BindView(R.id.view_not_use)
    View viewNotUse;
    @BindView(R.id.tv_use_record)
    TextView tvUseRecord;
    @BindView(R.id.view_use_record)
    View viewUseRecord;
    @BindView(R.id.tv_expired)
    TextView tvExpired;
    @BindView(R.id.view_expired)
    View viewExpired;

    private FragmentManager mFragmentManager;
    private GoodsDetailsViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        StatusBarUtils.setStatusBar(CouponsActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CouponsActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentNotUseCoupons());
        fragmentList.add(new FragmentUseRecordCoupons());
        fragmentList.add(new FragmentExpiredCoupons());
        mViewPagerFragmentAdapter = new GoodsDetailsViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tvNotUse.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        tvUseRecord.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        tvExpired.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        viewNotUse.setVisibility(View.VISIBLE);
                        viewUseRecord.setVisibility(View.GONE);
                        viewExpired.setVisibility(View.GONE);
                        break;
                    case 1:
                        tvNotUse.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        tvUseRecord.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        tvExpired.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        viewNotUse.setVisibility(View.GONE);
                        viewUseRecord.setVisibility(View.VISIBLE);
                        viewExpired.setVisibility(View.GONE);
                        break;
                    case 2:
                        tvNotUse.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        tvUseRecord.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        tvExpired.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        viewNotUse.setVisibility(View.GONE);
                        viewUseRecord.setVisibility(View.GONE);
                        viewExpired.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_not_use, R.id.tv_use_record, R.id.tv_expired})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_not_use:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_use_record:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_expired:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

}
