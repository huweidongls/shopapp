package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.SearchHistoryAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.StatusBarUtils;
import com.jingna.shopapp.widget.FlowLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    private Context context = SearchActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;

    private SearchHistoryAdapter adapter;
    private String[] mList;

    private InputMethodManager manager;//输入法管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        StatusBarUtils.setStatusBar(SearchActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(SearchActivity.this);
        initData();
        manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        search();

    }

    private void search() {
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //先隐藏键盘
                    if (manager.isActive()) {
                        manager.hideSoftInputFromWindow(etSearch.getApplicationWindowToken(), 0);
                    }
                    //自己需要的操作
                    String search = etSearch.getText().toString();
                    if(!TextUtils.isEmpty(search)){
                        String old = SpUtils.getSearchHistory(context);
                        String newStr = old+search+",";
                        SpUtils.setSearchHistory(context, newStr);
                        mList = newStr.split(",");
                        adapter = new SearchHistoryAdapter(mList);
                        recyclerView.setAdapter(adapter);
                    }
                }
                //记得返回false
                return false;
            }
        });
    }

    private void initData() {

        FlowLayoutManager manager = new FlowLayoutManager();
        recyclerView.setLayoutManager(manager);
        String sign = SpUtils.getSearchHistory(context);
        if(TextUtils.isEmpty(sign)){

        }else {
            mList = sign.split(",");
        }
        adapter = new SearchHistoryAdapter(mList);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.tv_cancel, R.id.iv_del})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.iv_del:
                SpUtils.setSearchHistory(context, "");
                mList = null;
                adapter = new SearchHistoryAdapter(mList);
                recyclerView.setAdapter(adapter);
                break;
        }
    }

}
