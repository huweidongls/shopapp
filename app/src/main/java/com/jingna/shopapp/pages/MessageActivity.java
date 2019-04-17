package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.MessageAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.MessageCenterBean;
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.util.SpUtils;
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

public class MessageActivity extends BaseActivity {

    private Context context = MessageActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MessageAdapter adapter;
    private List<MessageCenterBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        StatusBarUtils.setStatusBar(MessageActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MessageActivity.this);
        initData();

    }

    private void initData() {
        mList = new ArrayList<>();
        ViseHttp.GET("MemberPushMsg/queryListByMemberId")
                .addParam("memberId", SpUtils.getUserId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                MessageCenterBean MessageCenterBean = gson.fromJson(data,MessageCenterBean.class);
                                mList.clear();
                                mList.addAll(MessageCenterBean.getData());
                                adapter = new MessageAdapter(mList);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
