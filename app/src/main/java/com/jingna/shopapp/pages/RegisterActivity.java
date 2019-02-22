package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.dialog.RegisterDialog;
import com.jingna.shopapp.dialog.SendYzmDialog;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    @BindView(R.id.et_number)
    EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StatusBarUtils.setStatusBar(RegisterActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(RegisterActivity.this);
        initData();

    }

    private void initData() {

        RegisterDialog dialog = new RegisterDialog(context, new RegisterDialog.ClickListener() {
            @Override
            public void onSure() {

            }

            @Override
            public void onCancel() {
                RegisterActivity.this.finish();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

    }

    @OnClick({R.id.rl_back, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    private void next() {

        String phoneNum = etNumber.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else {
            SendYzmDialog dialog = new SendYzmDialog(context, phoneNum, new SendYzmDialog.ClickListener() {
                @Override
                public void onSure() {
                    
                }
            });
            dialog.show();
        }

    }

}
