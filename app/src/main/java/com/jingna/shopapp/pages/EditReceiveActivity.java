package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.AddressGetOneBean;
import com.jingna.shopapp.bean.JsonBean;
import com.jingna.shopapp.util.GetJsonDataUtil;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.StringUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditReceiveActivity extends BaseActivity {

    private Context context = EditReceiveActivity.this;

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_set)
    ImageView ivSet;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String id = "";

    private String acquiescentAdress = "0";

    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_receive);

        id = getIntent().getStringExtra("id");
        userId = SpUtils.getUserId(context);
        StatusBarUtils.setStatusBar(EditReceiveActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(EditReceiveActivity.this);
        initData();

    }

    private void initData() {

        initJsonData();
        String url = "/MemAdress/getOne?id="+id;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AddressGetOneBean bean = gson.fromJson(data, AddressGetOneBean.class);
                                etName.setText(bean.getData().getConsignee());
                                etPhoneNum.setText(bean.getData().getConsigneeTel());
                                tvCity.setText(bean.getData().getLocation());
                                etAddress.setText(bean.getData().getAdress());
                                if(bean.getData().getAcquiescentAdress().equals("0")){
                                    acquiescentAdress = "0";
                                    Glide.with(context).load(R.mipmap.address_off).into(ivSet);
                                }else {
                                    acquiescentAdress = "1";
                                    Glide.with(context).load(R.mipmap.address_on).into(ivSet);
                                }
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

    @OnClick({R.id.rl_back, R.id.ll_city, R.id.iv_set, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_city:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);
                        tvCity.setText(tx);
                    }
                })
                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
                break;
            case R.id.iv_set:
                if(acquiescentAdress.equals("0")){
                    Glide.with(context).load(R.mipmap.address_on).into(ivSet);
                    acquiescentAdress = "1";
                }else {
                    Glide.with(context).load(R.mipmap.address_off).into(ivSet);
                    acquiescentAdress = "0";
                }
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void save() {

        String name = etName.getText().toString();
        String phoneNum = etPhoneNum.getText().toString();
        String city = tvCity.getText().toString();
        String address = etAddress.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(phoneNum)||TextUtils.isEmpty(city)||TextUtils.isEmpty(address)){
            ToastUtil.showShort(context, "请完善收货信息");
        }else if(!StringUtils.isPhoneNumberValid(phoneNum)){
            ToastUtil.showShort(context, "请输入正确的手机号码");
        }else  {
            ViseHttp.POST("/MemAdress/toUpdate")
                    .addParam("id", id)
                    .addParam("memberId", userId)
                    .addParam("consignee", name)
                    .addParam("consigneeTel", phoneNum)
                    .addParam("location", city)
                    .addParam("adress", address)
                    .addParam("acquiescentAdress", acquiescentAdress)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "保存成功");
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

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
