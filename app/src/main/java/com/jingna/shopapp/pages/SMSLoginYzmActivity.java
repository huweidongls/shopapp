package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SMSLoginYzmActivity extends BaseActivity {

    private Context context = SMSLoginYzmActivity.this;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_code)
    EditText etCode;

    private String phoneNum = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin_yzm);

        phoneNum = getIntent().getStringExtra("phone");
        MyApplication.smsCodeTimeCount.setActivity(SMSLoginYzmActivity.this);
        StatusBarUtils.setStatusBar(SMSLoginYzmActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(SMSLoginYzmActivity.this);
        initData();

    }

    private void initData() {

        MyApplication.smsCodeTimeCount.start();

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                MyApplication.smsCodeTimeCount.start();
                getCode();
                break;
            case R.id.btn_login:
                next();
                break;
        }
    }

    /**
     * 登录
     */
    private void next() {

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.showShort(context, "验证码不能为空");
        }else {
            String url = "/MemUser/loginAPP?phoneNum="+phoneNum+"&code="+code;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "登录成功");
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, "验证码不正确");
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

    /**
     * 获取验证码
     */
    private void getCode() {

        String url = "/MemUser/sendMessage?phone="+phoneNum;
        Log.e("123123", phoneNum);
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "短信验证码发送成功");
                            }else {
                                ToastUtil.showShort(context, "短信验证码发送失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.e("123123", errMsg);
                    }
                });

    }

}
