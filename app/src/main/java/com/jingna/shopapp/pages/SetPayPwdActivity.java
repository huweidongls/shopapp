package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.widget.PasswordEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPwdActivity extends BaseActivity {

    private Context context = SetPayPwdActivity.this;

    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pay_pwd);

        StatusBarUtils.setStatusBar(SetPayPwdActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(SetPayPwdActivity.this);
        initData();

    }

    private void initData() {

        etPwd.setPasswordFullListener(new PasswordEditText.PasswordFullListener() {
            @Override
            public void passwordFull(String password) {

            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
