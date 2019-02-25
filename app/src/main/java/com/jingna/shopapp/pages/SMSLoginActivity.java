package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMSLoginActivity extends BaseActivity {

    private Context context = SMSLoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin);

        StatusBarUtils.setStatusBar(SMSLoginActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(SMSLoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_yzm})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_yzm:
            intent.setClass(context, SMSLoginYzmActivity.class);
            startActivity(intent);
            break;
        }
    }

}
