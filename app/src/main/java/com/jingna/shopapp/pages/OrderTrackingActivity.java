package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.ButterKnife;

public class OrderTrackingActivity extends BaseActivity {

    private Context context = OrderTrackingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

        StatusBarUtils.setStatusBar(OrderTrackingActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(OrderTrackingActivity.this);

    }
}
