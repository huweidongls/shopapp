package com.jingna.shopapp.pages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.GoodsListAdapter;
import com.jingna.shopapp.adapter.GoodsListPopRvAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.GoodsListBean;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListActivity extends BaseActivity {

    private Context context = GoodsListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private GoodsListAdapter adapter;
    private List<GoodsListBean.DataBean> mList;

    private String id = "";

    private PopupWindow popupWindow;
    private View popView;

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
        ViseHttp.GET("/AppGoodsShop/queryList")
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .addParam("categoryId", id)
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

    private void initRight() {

        popView = LayoutInflater.from(context).inflate(R.layout.popupwindow_goods_list_right_layout, null);
        RecyclerView rvPop = popView.findViewById(R.id.rv_pop);
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        GoodsListPopRvAdapter popRvAdapter = new GoodsListPopRvAdapter(data);
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

    @OnClick({R.id.rl_back, R.id.rl_right_shaixuan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_right_shaixuan:
                showRight();
                break;
        }
    }

}
