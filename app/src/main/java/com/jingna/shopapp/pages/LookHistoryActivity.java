package com.jingna.shopapp.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.LookHistoryAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.LookHistoryBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookHistoryActivity extends BaseActivity {

    private Context context = LookHistoryActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private LookHistoryAdapter adapter;
    private List<LookHistoryBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_history);

        StatusBarUtils.setStatusBar(LookHistoryActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(LookHistoryActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.GET("/MemberBrowse/queryList")
                .addParam("memberId", SpUtils.getUserId(context))
                .addParam("pageNum", "1")
                .addParam("pageSize", "10")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                LookHistoryBean historyBean = gson.fromJson(data, LookHistoryBean.class);
                                mList = historyBean.getData();
                                adapter = new LookHistoryAdapter(mList);
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

    @OnClick({R.id.rl_back, R.id.rl_clear})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_clear:
                DialogCustom dialogCustom = new DialogCustom(context, "是否清空浏览记录", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        clear();
                    }
                });
                dialogCustom.show();
                break;
        }
    }

    /**
     * 清空浏览记录
     */
    private void clear() {

        ViseHttp.POST("/MemberBrowse/toDeleteAll")
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                mList.clear();
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

}
