package com.jingna.shopapp.pages;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.GetOneBean;
import com.jingna.shopapp.dialog.InformationNicknameDialog;
import com.jingna.shopapp.dialog.InformationSexDialog;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInformationActivity extends BaseActivity {

    private Context context = PersonInformationActivity.this;

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    private int REQUEST_CODE = 101;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        StatusBarUtils.setStatusBar(PersonInformationActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(PersonInformationActivity.this);
        initData();

    }

    private void initData() {

        String url = "/MemUser/getOne?id="+SpUtils.getUserId(context);
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GetOneBean bean = gson.fromJson(data, GetOneBean.class);
                                Glide.with(context).load(Const.BASE_URL+bean.getData().getHeadPhoto()).into(ivAvatar);
                                tvNickname.setText(bean.getData().getMemName());
                                if(bean.getData().getGender().equals("0")){
                                    tvSex.setText("男");
                                }else {
                                    tvSex.setText("女");
                                }
                                tvBirthday.setText(bean.getData().getMemBirthday());
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

    @OnClick({R.id.rl_back, R.id.rl_avatar, R.id.rl_birthday, R.id.rl_sex, R.id.rl_nickname, R.id.btn_out})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_avatar:
                //单选并剪裁
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setCrop(true)  // 设置是否使用图片剪切功能。
                        .setSingle(true)  //设置是否单选
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
            case R.id.rl_birthday:
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.rl_sex:
                InformationSexDialog sexDialog = new InformationSexDialog(context, new InformationSexDialog.ClickListener() {
                    @Override
                    public void onNan() {
                        onSex(0);
                    }

                    @Override
                    public void onNv() {
                        onSex(1);
                    }
                });
                sexDialog.show();
                break;
            case R.id.rl_nickname:
                InformationNicknameDialog nicknameDialog = new InformationNicknameDialog(context, new InformationNicknameDialog.ClickListener() {
                    @Override
                    public void onSure(String name) {
                        onNickname(name);
                    }
                });
                nicknameDialog.show();
                break;
            case R.id.btn_out:
                SpUtils.clear(context);
                finish();
                break;
        }
    }

    /**
     * 走接口设置昵称
     */
    private void onNickname(final String name) {

        ViseHttp.POST("/MemUser/toUpdate")
                .addParam("id", SpUtils.getUserId(context))
                .addParam("memName", name)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "设置成功");
                                tvNickname.setText(name);
                            }else {
                                ToastUtil.showShort(context, "设置失败");
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
     * 走接口设置性别
     */
    private void onSex(final int sex) {

        ViseHttp.POST("/MemUser/toUpdate")
                .addParam("id", SpUtils.getUserId(context))
                .addParam("gender", sex+"")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, "设置成功");
                                if(sex == 0){
                                    tvSex.setText("男");
                                }else {
                                    tvSex.setText("女");
                                }
                            }else {
                                ToastUtil.showShort(context, "设置失败");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            final ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            File file = new File(images.get(0));

            ViseHttp.UPLOAD("/MemUser/toUpdate")
                    .addParam("id", SpUtils.getUserId(context))
                    .addFile("file0", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Log.e("123123", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "设置成功");
                                    Glide.with(context).load(images.get(0)).into(ivAvatar);
                                }else {
                                    ToastUtil.showShort(context, "设置失败");
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

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            ViseHttp.POST("/MemUser/toUpdate")
                    .addParam("id", SpUtils.getUserId(context))
                    .addParam("memBirthday", days)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "设置成功");
                                    tvBirthday.setText(days);
                                }else {
                                    ToastUtil.showShort(context, "设置失败");
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
    };

}
