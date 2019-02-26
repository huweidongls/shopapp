package com.jingna.shopapp.fragment;

import android.content.Intent;
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
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentMyTuijianAdapter;
import com.jingna.shopapp.pages.PersonInformationActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/2/15.
 */

public class FragmentWode extends Fragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_num)
    LinearLayout llNum;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;

    private FragmentMyTuijianAdapter adapter;
    private List<String> mList;

    private String userId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, null);

        userId = SpUtils.getUserId(getContext());
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        if(userId.equals("0")){
            tvName.setVisibility(View.GONE);
            llNum.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
        }else {
            tvName.setVisibility(View.VISIBLE);
            llNum.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
        }

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
        adapter = new FragmentMyTuijianAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){

        }else {
            StatusBarUtils.setStatusBarTransparent(getActivity());
        }
    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl4, R.id.rl5, R.id.iv_avatar, R.id.ll_login})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl1:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl2:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl3:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl4:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl5:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_avatar:
                if(userId.equals("0")){
                    intent.setClass(getContext(), SMSLoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_login:
                intent.setClass(getContext(), SMSLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

}
