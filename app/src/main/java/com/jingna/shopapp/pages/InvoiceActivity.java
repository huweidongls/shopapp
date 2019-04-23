package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.receiver.Logger;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.StringUtils;
import com.jingna.shopapp.util.ToastUtil;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceActivity extends BaseActivity {

    private Context context = InvoiceActivity.this;

    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.iv_putong)
    ImageView ivPutong;
    @BindView(R.id.iv_zhuanyong)
    ImageView ivZhuanyong;
    @BindView(R.id.view_putong)
    View viewPutong;
    @BindView(R.id.view_zhuanyong)
    View viewZhuanyong;
    @BindView(R.id.iv_dianzi)
    ImageView ivDianzi;
    @BindView(R.id.iv_zhizhi)
    ImageView ivZhizhi;
    @BindView(R.id.iv_danwei)
    ImageView ivDanwei;
    @BindView(R.id.iv_geren)
    ImageView ivGeren;
    @BindView(R.id.iv_mingxi)
    ImageView ivMingxi;
    @BindView(R.id.iv_leibie)
    ImageView ivLeibie;
    @BindView(R.id.ll_company_address)
    LinearLayout llCompanyAddress;
    @BindView(R.id.ll_company_phone)
    LinearLayout llCompanyPhone;
    @BindView(R.id.view_company_address)
    View viewCompanyAddress;
    @BindView(R.id.view_company_phone)
    View viewCompanyPhone;
    @BindView(R.id.et_invoicetitle)
    EditText etInvoiceTitle;
    @BindView(R.id.et_invoicecode)
    EditText etInvoiceCode;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_company_address)
    EditText etCompanyAddress;
    @BindView(R.id.et_company_phone)
    EditText etCompanyPhone;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    private String invoiceTitle = "";
    private String invoiceCode = "";
    private String invoiceContent = "商品明细";
    private String invoiceType = "电子发票";
    private String generalInvoice = "普通发票";
    private String personalCompanies = "公司发票";
    private String invoiceContacts = "";
    private String invoicePhone = "";
    private String invoiceEmail = "";
    private String invoiceCompaniesAdress = "";
    private String invoiceCompaniesPhone = "";
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        price = getIntent().getDoubleExtra("price", 0.00);
        StatusBarUtils.setStatusBar(InvoiceActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(InvoiceActivity.this);
        initData();

    }

    private void initData() {

        tvPrice.setText(price+"");

    }

    @OnClick({R.id.rl_back, R.id.btn_sure, R.id.rl_putong, R.id.rl_zhuanyong, R.id.ll_dianzi, R.id.ll_zhizhi, R.id.ll_danwei,
            R.id.ll_geren, R.id.ll_mingxi, R.id.ll_leibie})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                sure();
                break;
            case R.id.rl_putong:
                llCompanyAddress.setVisibility(View.GONE);
                llCompanyPhone.setVisibility(View.GONE);
                viewCompanyAddress.setVisibility(View.GONE);
                viewCompanyPhone.setVisibility(View.GONE);
                Glide.with(context).load(R.mipmap.apply_true).into(ivPutong);
                Glide.with(context).load(R.mipmap.apply_false).into(ivZhuanyong);
                viewPutong.setBackgroundColor(Color.parseColor("#F71F1F"));
                viewZhuanyong.setBackgroundColor(getResources().getColor(R.color.line));
                generalInvoice = "普通发票";
                break;
            case R.id.rl_zhuanyong:
                llCompanyAddress.setVisibility(View.VISIBLE);
                llCompanyPhone.setVisibility(View.VISIBLE);
                viewCompanyAddress.setVisibility(View.VISIBLE);
                viewCompanyPhone.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.mipmap.apply_false).into(ivPutong);
                Glide.with(context).load(R.mipmap.apply_true).into(ivZhuanyong);
                viewPutong.setBackgroundColor(getResources().getColor(R.color.line));
                viewZhuanyong.setBackgroundColor(Color.parseColor("#F71F1F"));
                generalInvoice = "增值税专用发票";
                break;
            case R.id.ll_dianzi:
                Glide.with(context).load(R.mipmap.apply_true).into(ivDianzi);
                Glide.with(context).load(R.mipmap.apply_false).into(ivZhizhi);
                invoiceType = "电子发票";
                break;
            case R.id.ll_zhizhi:
                Glide.with(context).load(R.mipmap.apply_false).into(ivDianzi);
                Glide.with(context).load(R.mipmap.apply_true).into(ivZhizhi);
                invoiceType = "纸质发票";
                break;
            case R.id.ll_danwei:
                Glide.with(context).load(R.mipmap.apply_true).into(ivDanwei);
                Glide.with(context).load(R.mipmap.apply_false).into(ivGeren);
                personalCompanies = "公司发票";
                break;
            case R.id.ll_geren:
                Glide.with(context).load(R.mipmap.apply_false).into(ivDanwei);
                Glide.with(context).load(R.mipmap.apply_true).into(ivGeren);
                personalCompanies = "个人发票";
                break;
            case R.id.ll_mingxi:
                Glide.with(context).load(R.mipmap.apply_true).into(ivMingxi);
                Glide.with(context).load(R.mipmap.apply_false).into(ivLeibie);
                invoiceContent = "商品明细";

                break;
            case R.id.ll_leibie:
                Glide.with(context).load(R.mipmap.apply_false).into(ivMingxi);
                Glide.with(context).load(R.mipmap.apply_true).into(ivLeibie);
                invoiceContent = "商品类别";
                break;
        }
    }

    private void sure() {

        invoiceTitle = etInvoiceTitle.getText().toString();
        invoiceCode = etInvoiceCode.getText().toString();
        invoiceContacts = etName.getText().toString();
        invoicePhone = etPhoneNum.getText().toString();
        invoiceEmail = etEmail.getText().toString();
        invoiceCompaniesAdress = etCompanyAddress.getText().toString();
        invoiceCompaniesPhone = etCompanyPhone.getText().toString();

        Intent intent = new Intent();
        Map<String, String> map = new LinkedHashMap<>();

        if(generalInvoice.equals("普通发票")){
            if(TextUtils.isEmpty(invoiceTitle)||TextUtils.isEmpty(invoiceCode)||TextUtils.isEmpty(invoiceContacts)
                    ||TextUtils.isEmpty(invoicePhone)||TextUtils.isEmpty(invoiceEmail)){
                ToastUtil.showShort(context, "请完善信息之后提交");
            }else {
                if(!StringUtils.isPhoneNumberValid(invoicePhone)){
                    ToastUtil.showShort(context, "请输入正确格式的手机号");
                }else if(!StringUtils.isEmail(invoiceEmail)){
                    ToastUtil.showShort(context, "请输入正确格式的邮箱");
                }else {
                    map.put("invoiceTitle", invoiceTitle);
                    map.put("invoiceCode", invoiceCode);
                    map.put("invoiceContent", invoiceContent);
                    map.put("invoiceType", invoiceType);
                    map.put("generalInvoice", generalInvoice);
                    map.put("personalCompanies", personalCompanies);
                    map.put("invoiceContacts", invoiceContacts);
                    map.put("invoicePhone", invoicePhone);
                    map.put("invoiceEmail", invoiceEmail);
                    intent.putExtra("map", (Serializable) map);
                    setResult(101, intent);
                    finish();
                }
            }
        }else {
            if(TextUtils.isEmpty(invoiceTitle)||TextUtils.isEmpty(invoiceCode)||TextUtils.isEmpty(invoiceContacts)
                    ||TextUtils.isEmpty(invoicePhone)||TextUtils.isEmpty(invoiceEmail)||TextUtils.isEmpty(invoiceCompaniesAdress)
                    ||TextUtils.isEmpty(invoiceCompaniesPhone)){
                ToastUtil.showShort(context, "请完善信息之后提交");
            }else {
                if(!StringUtils.isPhoneNumberValid(invoicePhone)||!StringUtils.isPhoneNumberValid(invoiceCompaniesPhone)){
                    ToastUtil.showShort(context, "请输入正确格式的手机号");
                }else if(!StringUtils.isEmail(invoiceEmail)){
                    ToastUtil.showShort(context, "请输入正确格式的邮箱");
                }else {
                    map.put("invoiceTitle", invoiceTitle);
                    map.put("invoiceCode", invoiceCode);
                    map.put("invoiceContent", invoiceContent);
                    map.put("invoiceType", invoiceType);
                    map.put("generalInvoice", generalInvoice);
                    map.put("personalCompanies", personalCompanies);
                    map.put("invoiceContacts", invoiceContacts);
                    map.put("invoicePhone", invoicePhone);
                    map.put("invoiceEmail", invoiceEmail);
                    map.put("invoiceCompaniesAdress", invoiceCompaniesAdress);
                    map.put("invoiceCompaniesPhone", invoiceCompaniesPhone);
                    intent.putExtra("map", (Serializable) map);
                    setResult(101, intent);
                    finish();
                }
            }
        }

    }

}
