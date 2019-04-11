package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

public class ForgotPwd1Activity extends BaseActivity {

    private Context context = ForgotPwd1Activity.this;

    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd1);

        StatusBarUtils.setStatusBar(ForgotPwd1Activity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(ForgotPwd1Activity.this);

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

        final String phoneNum = etPhoneNum.getText().toString();
        String url = "/MemUser/sendMessage?phone="+phoneNum;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "验证码发送成功");
                                Intent intent = new Intent();
                                intent.setClass(context, ForgotPwd2Activity.class);
                                intent.putExtra("phone", phoneNum);
                                startActivity(intent);
                                finish();
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
