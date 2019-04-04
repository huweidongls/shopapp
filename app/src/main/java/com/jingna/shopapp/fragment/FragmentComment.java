package com.jingna.shopapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.FragmentCommentListAdapter;
import com.jingna.shopapp.bean.FragmentCommentBean;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentComment extends Fragment {

    @BindView(R.id.rv_comment)
    RecyclerView recyclerView;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;

    private FragmentCommentListAdapter adapter;
    private List<FragmentCommentBean.DataBean> mList;

    private String id = "";

    public static FragmentComment newInstance(String id) {
        FragmentComment newFragment = new FragmentComment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, null);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        ViseHttp.GET("/AppGoodsShop/getComment")
                .addParam("goodsId", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                Gson gson = new Gson();
                                FragmentCommentBean bean = gson.fromJson(data, FragmentCommentBean.class);
                                mList = bean.getData();
                                if(mList!=null&&mList.size()>0){
                                    tvCommentNum.setText("评价("+bean.getData().get(0).getGoodsComment()+"条）");
                                }
                                adapter = new FragmentCommentListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
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

}
