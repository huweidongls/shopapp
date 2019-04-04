package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.MyAddressAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.bean.AddressBean;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {

    private Context context = AddressActivity.this;

    @BindView(R.id.rv)
    SwipeMenuRecyclerView recyclerView;

    private MyAddressAdapter adapter;
    private List<AddressBean.DataBean> mList;

    private String userId = "";

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        type = getIntent().getStringExtra("type");
        userId = SpUtils.getUserId(context);
        StatusBarUtils.setStatusBar(AddressActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(AddressActivity.this);
        initData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mList != null){
            String url = "/MemAdress/queryList?memberId="+ userId;
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    Gson gson = new Gson();
                                    AddressBean bean = gson.fromJson(data, AddressBean.class);
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
    }

    private void initData() {

        String url = "/MemAdress/queryList?memberId="+ userId;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                AddressBean bean = gson.fromJson(data, AddressBean.class);
                                mList = bean.getData();
                                adapter = new MyAddressAdapter(mList, type, new MyAddressAdapter.ItemClickListener() {
                                    @Override
                                    public void onClick(AddressBean.DataBean bean) {
                                        Intent intent = new Intent();
                                        intent.putExtra("address", bean);
                                        setResult(100, intent);
                                        finish();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
                                recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
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

    @OnClick({R.id.rl_back, R.id.btn_insert_address})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert_address:
                intent.setClass(context, InsertReceiveActivity.class);
                startActivity(intent);
                break;
        }
    }

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem sendItem = new SwipeMenuItem(context)
                    .setBackgroundColor(Color.parseColor("#F6F6F6"))
                    .setText("设为默认")
                    .setTextColor(Color.parseColor("#2D2D2D"))
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(sendItem);// 添加菜单到右侧。

            SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                switch (menuPosition){
                    case 0:
                        //设为默认
                        String url = "/MemAdress/setDefault";
                        ViseHttp.POST(url)
                                .addParam("id", mList.get(adapterPosition).getId()+"")
                                .addParam("memberId", userId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "设置成功");
                                                for (int i = 0; i<mList.size(); i++){
                                                    if(i == adapterPosition){
                                                        mList.get(i).setAcquiescentAdress("1");
                                                    }else {
                                                        mList.get(i).setAcquiescentAdress("0");
                                                    }
                                                }
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
                        break;
                    case 1:
                        //删除
                        String url1 = "/MemAdress/toDelete";
                        ViseHttp.POST(url1)
                                .addParam("id", mList.get(adapterPosition).getId()+"")
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "删除成功");
                                                mList.remove(adapterPosition);
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
                        break;
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

}
