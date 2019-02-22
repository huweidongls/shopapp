package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
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

public class RegisterSetPwdActivity extends BaseActivity {

    private Context context = RegisterSetPwdActivity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_is_show)
    ImageView ivIsShow;

    private boolean isShowPwd = false;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_set_pwd);

        phoneNumber = getIntent().getStringExtra("number");
        StatusBarUtils.setStatusBar(RegisterSetPwdActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(RegisterSetPwdActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_is_show, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_is_show:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.miwen).into(ivIsShow);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.mingwen).into(ivIsShow);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.btn_sure:
                sure();
                break;
        }
    }

    /**
     * 验证密码是否符合规则及访问注册接口
     */
    private void sure() {

        String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.showShort(context, "密码不能为空");
        }else if(pwd.length()<6||pwd.length()>20){
            ToastUtil.showShort(context, "密码长度为6-20位，请重新设置密码");
        }else {
            String url = "/MemUser/updatePassword?phone="+phoneNumber+"&password="+pwd;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Log.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "注册成功");
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
