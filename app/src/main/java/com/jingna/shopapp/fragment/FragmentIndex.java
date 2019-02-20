package com.jingna.shopapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.IndexAdapter;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.widget.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/2/15.
 */

public class FragmentIndex extends Fragment {

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_saoyisao)
    RelativeLayout rlSaoyisao;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.iv_saoyisao)
    ImageView ivSaoyisao;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_small_search)
    ImageView ivSmallSearch;
    @BindView(R.id.tv_search_text)
    TextView tvSearchText;

    private IndexAdapter adapter;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        StatusBarUtils.setStatusBarTransparent(getActivity());
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        setMargins(rlSearch, 0, result, 0, 0);

        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    rlSearch.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
                    StatusBarUtils.setStatusBarTransparent(getActivity());
                    llSearch.setBackgroundResource(R.drawable.bg_index_search);
                    rlSaoyisao.setBackgroundResource(R.drawable.bg_42000000_round);
                    rlMessage.setBackgroundResource(R.drawable.bg_42000000_round);
                    Glide.with(getContext()).load(R.mipmap.saoyisao).into(ivSaoyisao);
                    Glide.with(getContext()).load(R.mipmap.message).into(ivMessage);
                    Glide.with(getContext()).load(R.mipmap.search).into(ivSmallSearch);
                    tvSearchText.setTextColor(Color.parseColor("#9C9C9C"));
                } else if (y > 0 && y <= 888) {
                    float scale = (float) y / 888;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    rlSearch.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) alpha, 255, 255, 255));
                } else {
                    rlSearch.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) 255, 255, 255, 255));
                    llSearch.setBackgroundResource(R.drawable.bg_index_search_dark);
                    rlSaoyisao.setBackgroundResource(R.drawable.bg_ffffff_round);
                    rlMessage.setBackgroundResource(R.drawable.bg_ffffff_round);
                    Glide.with(getContext()).load(R.mipmap.saoyisao_dark).into(ivSaoyisao);
                    Glide.with(getContext()).load(R.mipmap.message_dark).into(ivMessage);
                    Glide.with(getContext()).load(R.mipmap.search_light).into(ivSmallSearch);
                    tvSearchText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new IndexAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
