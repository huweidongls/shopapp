package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity {

    private Context context = CommentActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        StatusBarUtils.setStatusBar(CommentActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(CommentActivity.this);
        initData();

    }

    private void initData() {



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
