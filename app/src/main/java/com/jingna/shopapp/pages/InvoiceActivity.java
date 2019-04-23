package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceActivity extends BaseActivity {

    private Context context = InvoiceActivity.this;

    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.iv_putong)
    ImageView ivPutong;
    @BindView(R.id.iv_zhuanyong)
    ImageView ivZhuanyong;
    @BindView(R.id.view_putong)
    View viewPutong;
    @BindView(R.id.view_zhuanyong)
    View viewZhuanyong;
    @BindView(R.id.iv_dianzi)
    ImageView ivDianzi;
    @BindView(R.id.iv_zhizhi)
    ImageView ivZhizhi;
    @BindView(R.id.iv_danwei)
    ImageView ivDanwei;
    @BindView(R.id.iv_geren)
    ImageView ivGeren;
    @BindView(R.id.iv_mingxi)
    ImageView ivMingxi;
    @BindView(R.id.iv_leibie)
    ImageView ivLeibie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        StatusBarUtils.setStatusBar(InvoiceActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(InvoiceActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure, R.id.rl_putong, R.id.rl_zhuanyong, R.id.ll_dianzi, R.id.ll_zhizhi, R.id.ll_danwei,
            R.id.ll_geren, R.id.ll_mingxi, R.id.ll_leibie})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:

                break;
            case R.id.rl_putong:
                Glide.with(context).load(R.mipmap.apply_true).into(ivPutong);
                Glide.with(context).load(R.mipmap.apply_false).into(ivZhuanyong);
                viewPutong.setBackgroundColor(Color.parseColor("#F71F1F"));
                viewZhuanyong.setBackgroundColor(getResources().getColor(R.color.line));
                break;
            case R.id.rl_zhuanyong:
                Glide.with(context).load(R.mipmap.apply_false).into(ivPutong);
                Glide.with(context).load(R.mipmap.apply_true).into(ivZhuanyong);
                viewPutong.setBackgroundColor(getResources().getColor(R.color.line));
                viewZhuanyong.setBackgroundColor(Color.parseColor("#F71F1F"));
                break;
            case R.id.ll_dianzi:
                Glide.with(context).load(R.mipmap.apply_true).into(ivDianzi);
                Glide.with(context).load(R.mipmap.apply_false).into(ivZhizhi);
                break;
            case R.id.ll_zhizhi:
                Glide.with(context).load(R.mipmap.apply_false).into(ivDianzi);
                Glide.with(context).load(R.mipmap.apply_true).into(ivZhizhi);
                break;
            case R.id.ll_danwei:
                Glide.with(context).load(R.mipmap.apply_true).into(ivDanwei);
                Glide.with(context).load(R.mipmap.apply_false).into(ivGeren);
                break;
            case R.id.ll_geren:
                Glide.with(context).load(R.mipmap.apply_false).into(ivDanwei);
                Glide.with(context).load(R.mipmap.apply_true).into(ivGeren);
                break;
            case R.id.ll_mingxi:
                Glide.with(context).load(R.mipmap.apply_true).into(ivMingxi);
                Glide.with(context).load(R.mipmap.apply_false).into(ivLeibie);
                break;
            case R.id.ll_leibie:
                Glide.with(context).load(R.mipmap.apply_false).into(ivMingxi);
                Glide.with(context).load(R.mipmap.apply_true).into(ivLeibie);
                break;
        }
    }

}
