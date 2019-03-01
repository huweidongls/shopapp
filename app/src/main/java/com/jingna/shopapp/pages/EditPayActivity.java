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

public class EditPayActivity extends BaseActivity {

    private Context context = EditPayActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pay);

        StatusBarUtils.setStatusBar(EditPayActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(EditPayActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_set_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_set_pwd:
                intent.setClass(context, SetPayPwdActivity.class);
                startActivity(intent);
                break;
        }
    }

}
