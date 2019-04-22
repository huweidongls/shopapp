package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentHaveCommentAdapter;
import com.jingna.shopapp.adapter.FragmentToCommentAdapter;
import com.jingna.shopapp.base.BaseFragment;
import com.jingna.shopapp.bean.TobeEvaluatedBean;
import com.jingna.shopapp.util.SpUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentHaveComment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private String id = "";
    private int page=1;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    public static FragmentHaveComment newInstance(String id) {
        FragmentHaveComment newFragment = new FragmentHaveComment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    private FragmentHaveCommentAdapter adapter;
    private List<TobeEvaluatedBean.DataBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_have_comment, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        EvaluateList(page);
        mList = new ArrayList<>();
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                EvaluateList(page);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                ViseHttp.GET("AppComment/queryList")
                        .addParam("pageNum",page+"")
                        .addParam("pageSize","10")
                        .addParam("type","1")
                        .addParam("memberId", SpUtils.getUserId(getContext()))
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                page = page + 1;
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        Gson gson = new Gson();
                                        TobeEvaluatedBean tobeEvaluatedBean = gson.fromJson(data,TobeEvaluatedBean.class);
                                        mList.addAll(tobeEvaluatedBean.getData());
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
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

    }
    public void EvaluateList(int p){
        ViseHttp.GET("AppComment/queryList")
                .addParam("pageNum","1")
                .addParam("pageSize","10")
                .addParam("type","1")
                .addParam("memberId", SpUtils.getUserId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            // Logger.e("11111111",data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                TobeEvaluatedBean tobeEvaluatedBean = gson.fromJson(data,TobeEvaluatedBean.class);
                                mList.clear();
                                mList.addAll(tobeEvaluatedBean.getData());
                                //adapter.notifyDataSetChanged();
                                adapter = new FragmentHaveCommentAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                page = 2;
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
