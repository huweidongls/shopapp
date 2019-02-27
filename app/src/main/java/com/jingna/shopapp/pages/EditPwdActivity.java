package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPwdActivity extends BaseActivity {

    private Context context = EditPwdActivity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;

    public Button getCode_btn() {
        return btnGetCode;
    }

    private String phoneNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pwd);

        phoneNum = SpUtils.getPhoneNum(context);
        StatusBarUtils.setStatusBar(EditPwdActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(EditPwdActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_get_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_get_code:
                getCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {



    }

}
