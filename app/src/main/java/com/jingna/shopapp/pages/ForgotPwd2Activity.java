package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class ForgotPwd2Activity extends BaseActivity {

    private Context context = ForgotPwd2Activity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_phonenum)
    TextView tvPhoneNum;

    private String phoneNum = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd2);

        phoneNum = getIntent().getStringExtra("phone");
        MyApplication.forgotTimeCount.setActivity(ForgotPwd2Activity.this);
        StatusBarUtils.setStatusBar(ForgotPwd2Activity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ForgotPwd2Activity.this);
        initData();

    }

    private void initData() {

        MyApplication.forgotTimeCount.start();
        String showNum = phoneNum.substring(0, 3)+"****"+phoneNum.substring(7, 11);
        tvPhoneNum.setText(showNum);

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.tv_get_code})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
            case R.id.tv_get_code:
                MyApplication.forgotTimeCount.start();
                getCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {

        String url = "/MemUser/sendMessage?phone="+phoneNum;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "验证码发送成功");
                            }else {
                                ToastUtil.showShort(context, "验证码发送失败");
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

    private void next() {

        String code = etCode.getText().toString();
        String url = "/MemUser/matchCode?phone="+phoneNum+"&code="+code;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Intent intent = new Intent();
                                intent.setClass(context, ForgotPwd3Activity.class);
                                intent.putExtra("phone", phoneNum);
                                startActivity(intent);
                                finish();
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
