package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentFocusGoodsAdapter;
import com.jingna.shopapp.adapter.FragmentFocusGoodsTuijianAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.Attention_Goods_listBean;
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.util.SpUtils;
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

/**
 * Created by Administrator on 2019/3/21.
 */

public class FragmentFocusGoods extends BaseFragment {

    @BindView(R.id.rv)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;

    private FragmentFocusGoodsAdapter goodsAdapter;
    private List<Attention_Goods_listBean.DataBean> mList;
    private FragmentFocusGoodsTuijianAdapter tuijianAdapter;
    private List<String> mTuijianList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus_goods, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        mList = new ArrayList<>();//商品关注列表接口
        ViseHttp.GET("AppGoodsMember/queryList")
                .addParam("memberId",SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                Attention_Goods_listBean Attention_Goods_listBean = gson.fromJson(data,Attention_Goods_listBean.class);
                                mList.clear();
                                mList.addAll(Attention_Goods_listBean.getData());
                                goodsAdapter = new FragmentFocusGoodsAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext()){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
                                recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
                                recyclerView.setAdapter(goodsAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });
        mTuijianList = new ArrayList<>();//店铺关注列表接口
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        mTuijianList.add("");
        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvTuijian.setLayoutManager(manager1);
        tuijianAdapter = new FragmentFocusGoodsTuijianAdapter(mTuijianList);
        rvTuijian.setAdapter(tuijianAdapter);

    }

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                    .setBackgroundColor(Color.RED)
                    .setText("取消关注")
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
                        //取消关注
                        ViseHttp.POST("/AppGoodsShop/isFollow")
                                .addParam("goodsId",mList.get(adapterPosition).getGoodsId()+"")
                                .addParam("memberId",SpUtils.getUserId(getContext()))
                                .addParam("type","0")
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.optString("status").equals("200")){
                                                mList.remove(adapterPosition);
                                                goodsAdapter.notifyDataSetChanged();
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
