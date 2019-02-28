package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingna.shopapp.R;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPhoneNum2Activity extends BaseActivity {

    private Context context = EditPhoneNum2Activity.this;

    @BindView(R.id.btn_get_code)
    Button btnGetCode;

    public Button getCode_btn() {
        return btnGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_num2);

        MyApplication.editPhoneNum2TimeCount.setActivity(EditPhoneNum2Activity.this);
        StatusBarUtils.setStatusBar(EditPhoneNum2Activity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(EditPhoneNum2Activity.this);

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

        MyApplication.editPhoneNum2TimeCount.start();

    }

}
