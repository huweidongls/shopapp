package com.jingna.shopapp.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.LookHistoryAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookHistoryActivity extends BaseActivity {

    private Context context = LookHistoryActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private LookHistoryAdapter adapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_history);

        StatusBarUtils.setStatusBar(LookHistoryActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(LookHistoryActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new LookHistoryAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

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
