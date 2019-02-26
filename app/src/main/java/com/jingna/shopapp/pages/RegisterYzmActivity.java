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

public class RegisterYzmActivity extends BaseActivity {

    private Context context = RegisterYzmActivity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    private String phoneNumber;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_yzm);

        MyApplication.ftptimecount.setActivity(RegisterYzmActivity.this);
        phoneNumber = getIntent().getStringExtra("number");
        StatusBarUtils.setStatusBar(RegisterYzmActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(RegisterYzmActivity.this);
        initData();

    }

    private void initData() {

        MyApplication.ftptimecount.start();

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.tv_get_code})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.tv_get_code:
                MyApplication.ftptimecount.start();
                getCode();
                break;
        }
    }

    /**
     * 根据手机号获取验证码
     */
    private void getCode() {

        String url = "/MemUser/sendMessage?phone="+phoneNumber;
        Log.e("123123", phoneNumber);
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

    /**
     * 验证码是否匹配，匹配则跳转设置密码
     */
    private void next() {

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.showShort(context, "验证码不能为空");
        }else {
            String url = "/MemUser/matchCode?phone="+phoneNumber+"&code="+code;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Log.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Intent intent = new Intent();
                                    intent.setClass(context, RegisterSetPwdActivity.class);
                                    intent.putExtra("number", phoneNumber);
                                    startActivity(intent);
                                    finish();
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

}
