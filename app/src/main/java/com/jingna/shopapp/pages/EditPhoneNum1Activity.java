package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.base.BaseActivity;
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

public class EditPhoneNum1Activity extends BaseActivity {

    private Context context = EditPhoneNum1Activity.this;

    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    public Button getCode_btn() {
        return btnGetCode;
    }

    private String phoneNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_num1);

        phoneNum = SpUtils.getPhoneNum(context);
        MyApplication.editPhoneNumTimeCount.setActivity(EditPhoneNum1Activity.this);
        StatusBarUtils.setStatusBar(EditPhoneNum1Activity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(EditPhoneNum1Activity.this);
        initData();

    }

    private void initData() {

        tvPhone.setText("请输入"+phoneNum+"收到的短信验证码");

    }

    @OnClick({R.id.rl_back, R.id.btn_get_code, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_get_code:
                getCode();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    /**
     * 下一步
     */
    private void next() {

        String code = etCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.showShort(context, "验证码不能为空");
        }else {
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
                                    intent.setClass(context, EditPhoneNum2Activity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    ToastUtil.showShort(context, "验证码错误");
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

        MyApplication.editPhoneNumTimeCount.start();
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

}
