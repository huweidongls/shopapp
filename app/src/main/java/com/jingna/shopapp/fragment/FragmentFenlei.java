package com.jingna.shopapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FenleiLeftAdapter;
import com.jingna.shopapp.adapter.FenleiTuijianAdapter;
import com.jingna.shopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/2/15.
 */

public class FragmentFenlei extends Fragment {

    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rv_fenlei)
    RecyclerView rvFenlei;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;

    private FenleiLeftAdapter leftAdapter;
    private List<String> mList;
    private FenleiTuijianAdapter tuijianAdapter;
    private List<String> mList1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fenlei, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        setMargins(rlSearch, 0, result, 0, 0);
        mList = new ArrayList<>();
        mList.add("推荐分类");
        mList.add("百货超市");
        mList.add("男装");
        mList.add("女装");
        mList.add("男鞋");
        mList.add("女鞋");
        mList.add("内衣配饰");
        mList.add("食品生鲜");
        mList.add("酒水饮料");
        mList.add("家居厨具");
        mList.add("家具家装");
        mList.add("推荐分类");
        mList.add("百货超市");
        mList.add("男装");
        mList.add("女装");
        mList.add("男鞋");
        mList.add("女鞋");
        mList.add("内衣配饰");
        mList.add("食品生鲜");
        mList.add("酒水饮料");
        mList.add("家居厨具");
        mList.add("家具家装");
        leftAdapter = new FenleiLeftAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFenlei.setLayoutManager(manager);
        rvFenlei.setAdapter(leftAdapter);

        mList1 = new ArrayList<>();
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        mList1.add("");
        tuijianAdapter = new FenleiTuijianAdapter(mList1);
        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvTuijian.setLayoutManager(manager1);
        rvTuijian.setAdapter(tuijianAdapter);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else {
            StatusBarUtils.setStatusBar(getActivity(), Color.parseColor("#ffffff"));
        }
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
