package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.LoginBean;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StatusBarUtils.setStatusBar(LoginActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_login, R.id.tv_code_login, R.id.tv_register, R.id.tv_forgot_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_code_login:
                intent.setClass(context, SMSLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forgot_pwd:
                intent.setClass(context, ForgotPwd1Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login() {

        final String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "手机号或密码不能为空");
        }else {
            String url = "/MemUser/loginAPP?phoneNum="+name+"&password="+pwd;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Logger.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "登录成功");
                                    Gson gson = new Gson();
                                    LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                                    Log.e("123123", loginBean.getData().getUserId()+"");
                                    SpUtils.setUserId(context, loginBean.getData().getUserId()+"");
                                    SpUtils.setToken(context, loginBean.getData().getToken());
                                    SpUtils.setPhoneNum(context, name);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }

    }

}
