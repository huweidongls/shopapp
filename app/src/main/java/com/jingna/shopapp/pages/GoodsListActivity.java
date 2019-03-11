package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsListAdapter;
import com.jingna.shopapp.adapter.GoodsListPopRvAdapter;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.ChoiceMenuBean;
import com.jingna.shopapp.bean.ChoiceMenuSignBean;
import com.jingna.shopapp.bean.GoodsListBean;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity {

    private Context context = GoodsListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;

    private GoodsListAdapter adapter;
    private List<GoodsListBean.DataBean> mList;

    private String id = "";

    private PopupWindow popupWindow;
    private View popView;

    private PopupWindow popupWindowZonghe;
    private View popViewZonghe;

    private String sign = "";
    private String minPrice = "";
    private String maxPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(GoodsListActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(GoodsListActivity.this);
        initData();
        initRight();

    }

    private void initData() {

        mList = new ArrayList<>();
        ViseHttp.POST("/AppGoodsShop/queryList")
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .addParam("categoryId", id)
                .addParam("orderBy", "b.price asc")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GoodsListBean bean = gson.fromJson(data, GoodsListBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter = new GoodsListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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
     * 显示综合筛选排序pop
     */
    private void showPopZonghe(){

        popViewZonghe = LayoutInflater.from(context).inflate(R.layout.popupwindow_goods_list_zonghe, null);

        popupWindowZonghe = new PopupWindow(popViewZonghe, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindowZonghe.setTouchable(true);
        popupWindowZonghe.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindowZonghe.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindowZonghe.setOutsideTouchable(true);
//        popupWindowZonghe.showAtLocation(getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
        popupWindowZonghe.showAsDropDown(llShaixuan);
        // 设置popWindow的显示和消失动画
//        popupWindowZonghe.setAnimationStyle(R.style.mypopwindow_anim_style);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindowZonghe.update();

        popupWindowZonghe.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });

    }

    private void initRight() {

        popView = LayoutInflater.from(context).inflate(R.layout.popupwindow_goods_list_right_layout, null);
        final RecyclerView rvPop = popView.findViewById(R.id.rv_pop);
        TextView tvSure = popView.findViewById(R.id.tv_sure);
        final EditText etMin = popView.findViewById(R.id.et_min);
        final EditText etMax = popView.findViewById(R.id.et_max);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, List<ChoiceMenuSignBean>> map = MyApplication.signMap;
                String signN = "";
                for (int i = 0;;i++){
                    if(map.get(i+"") == null){
                        break;
                    }else {
                        List<ChoiceMenuSignBean> list = map.get(i+"");
                        for (int a = 0; a<list.size(); a++){
                            if(list.get(a).getIsSelete() == 1){
                                signN = signN + list.get(a).getSign() + ",";
                            }
                        }
                    }
                }
                sign = signN;
                Log.e("123123", sign);
                String min = etMin.getText().toString();
                String max = etMax.getText().toString();
                if(!TextUtils.isEmpty(min)&&!TextUtils.isEmpty(max)){
                    if(Integer.valueOf(min)<Integer.valueOf(max)){
                        minPrice = min;
                        maxPrice = max;
                        onReGet();
                        popupWindow.dismiss();
                    }else if(Integer.valueOf(min)>=Integer.valueOf(max)){
                        ToastUtil.showShort(context, "最低价需小于最高价");
                    }
                }else if(TextUtils.isEmpty(min)&&TextUtils.isEmpty(max)){
                    onReGet();
                    popupWindow.dismiss();
                }else {
                    ToastUtil.showShort(context, "最低价或最高价不能为空");
                }
            }
        });

        ViseHttp.GET("/AppGoodsShop/choiceMenu")
                .addParam("id", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                ChoiceMenuBean menuBean = gson.fromJson(data, ChoiceMenuBean.class);
                                List<ChoiceMenuBean.DataBean> list = new ArrayList<>();
                                list.addAll(menuBean.getData());
                                GoodsListPopRvAdapter popRvAdapter = new GoodsListPopRvAdapter(list);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvPop.setLayoutManager(manager);
                                rvPop.setAdapter(popRvAdapter);
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
     * 根据筛选条件刷新列表
     */
    private void onReGet() {

        ViseHttp.POST("/AppGoodsShop/queryList")
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .addParam("categoryId", id)
                .addParam("goodsAttributes", sign)
                .addParam("minPrice", minPrice)
                .addParam("maxPrice", maxPrice)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                GoodsListBean bean = gson.fromJson(data, GoodsListBean.class);
                                mList.clear();
                                mList.addAll(bean.getData());
                                adapter.notifyDataSetChanged();
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
     * 显示右侧筛选
     */
    private void showRight(){

        popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.rl_right_shaixuan, R.id.rl_zonghe})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_right_shaixuan:
                showRight();
                break;
            case R.id.rl_zonghe:
                showPopZonghe();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.signMap.clear();
    }
}
