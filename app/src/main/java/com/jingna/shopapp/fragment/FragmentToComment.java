package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentChulizhongAdapter;
import com.jingna.shopapp.adapter.TocommentAdapter;
import com.jingna.shopapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentToComment extends BaseFragment {
    @BindView(R.id.ceshi)
    RecyclerView recyclerView;

    private TocommentAdapter adapter;
    private List<String> mList;
    private String id = "";

    public static FragmentToComment newInstance(String id) {
        FragmentToComment newFragment = new FragmentToComment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_comment, null);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();
        Bundle args = getArguments();
        initData();
        if (args != null) {
            id = args.getString("id");
        }
        return view;
    }
    public void initData() {
        mList.add("");
        mList.add("");
        adapter = new TocommentAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
