package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingna.shopapp.R;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.StringUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPhoneNum2Activity extends BaseActivity {

    private Context context = EditPhoneNum2Activity.this;

    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_code)
    EditText etCode;

    public Button getCode_btn() {
        return btnGetCode;
    }

    private String newPhoneNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phone_num2);

        MyApplication.editPhoneNum2TimeCount.setActivity(EditPhoneNum2Activity.this);
        StatusBarUtils.setStatusBar(EditPhoneNum2Activity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(EditPhoneNum2Activity.this);

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
                complete();
                break;
        }
    }

    /**
     * 提交接口
     */
    private void complete() {

        String url = "/MemUser/updatePhone";
        ViseHttp.POST(url)
                .addParam("phone", newPhoneNum)
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                SpUtils.setPhoneNum(context, newPhoneNum);
                                ToastUtil.showShort(context, "手机号更换成功");
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

    /**
     * 获取验证码
     */
    private void getCode() {

        MyApplication.editPhoneNum2TimeCount.start();
        newPhoneNum = etPhoneNum.getText().toString();
        if(TextUtils.isEmpty(newPhoneNum)){
            ToastUtil.showShort(context, "手机号不能为空");
        }else if(!StringUtils.isPhoneNumberValid(newPhoneNum)){
            ToastUtil.showShort(context, "请输入正确的手机号");
        }else {
            String url = "/MemUser/sendMessage?phone=" + newPhoneNum;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.optString("status").equals("200")) {
                                    ToastUtil.showShort(context, "验证码发送成功");
                                } else {
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

}
