package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentYiwanchengAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentYiwancheng extends Fragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private String id;
    private FragmentYiwanchengAdapter adapter;
    private List<String> mList;
    public static FragmentYiwancheng newInstance(String id) {
        FragmentYiwancheng newFragment = new FragmentYiwancheng();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yiwancheng, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        adapter = new FragmentYiwanchengAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

}
