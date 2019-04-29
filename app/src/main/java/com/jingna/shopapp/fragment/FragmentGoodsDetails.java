package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.RichTextBean;
import com.jingna.shopapp.util.HtmlFromUtils;
import com.jingna.shopapp.util.StringUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentGoodsDetails extends Fragment {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tv_rl2)
    TextView tvRl2;

    private String id = "";

    public static FragmentGoodsDetails newInstance(String id) {
        FragmentGoodsDetails newFragment = new FragmentGoodsDetails();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_details, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);

        initData();

        return view;
    }

    private void initData() {

        ViseHttp.GET("/AppGoodsShop/richText")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                RichTextBean textBean = gson.fromJson(data, RichTextBean.class);
                                HtmlFromUtils.setTextFromHtml(getActivity(), tvRl2, textBean.getData().getDetailMobileHtml());
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

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv1:
                tv1.setTextColor(Color.parseColor("#FF0004"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv2:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#FF0004"));
                tv3.setTextColor(Color.parseColor("#686868"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.GONE);
                break;
            case R.id.tv3:
                tv1.setTextColor(Color.parseColor("#686868"));
                tv2.setTextColor(Color.parseColor("#686868"));
                tv3.setTextColor(Color.parseColor("#FF0004"));
                ll1.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
                rl3.setVisibility(View.VISIBLE);
                break;
        }
    }

}
