package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.MessagePreferentialAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.MessageCenterBean;
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

public class MessagePreferentialActivity extends BaseActivity {

    private Context context = MessagePreferentialActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private MessagePreferentialAdapter adapter;
    private List<MessageCenterBean.DataBean> mList;
    private String typeid = "";//类型ID
    private String TypeName ="";//类型昵称
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_preferential);
        typeid = getIntent().getStringExtra("id");//类型ID
        TypeName = getIntent().getStringExtra("TypeName");//类型昵称
        StatusBarUtils.setStatusBar(MessagePreferentialActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(MessagePreferentialActivity.this);
        initData();
    }
    private void initData() {

        tv_title.setText(TypeName);//页面载入加载类型昵称
        mList = new ArrayList<>();//消息列表接口
        ViseHttp.GET("MemberPushMsg/queryListByMemberId")
                .addParam("memberId", SpUtils.getUserId(context))
                .addParam("msgType",typeid)
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
                                adapter = new MessagePreferentialAdapter(mList);
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
