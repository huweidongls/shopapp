package com.jingna.shopapp.pages;

import android.content.Context;
import android.os.Bundle;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;

import butterknife.ButterKnife;

public class InsertReceiveActivity extends BaseActivity {

    private Context context = InsertReceiveActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_receive);

        ButterKnife.bind(InsertReceiveActivity.this);

    }
}
