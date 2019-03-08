package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.ButterKnife;

public class DetailsOrderActivity extends BaseActivity {

    private Context context = DetailsOrderActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_order);

        StatusBarUtils.setStatusBar(DetailsOrderActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(DetailsOrderActivity.this);

    }
}
