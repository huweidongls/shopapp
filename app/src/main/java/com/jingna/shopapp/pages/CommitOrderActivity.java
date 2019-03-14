package com.jingna.shopapp.pages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitOrderActivity extends BaseActivity {

    private Context context = CommitOrderActivity.this;

    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;

    private int goodsNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_order);

        StatusBarUtils.setStatusBar(CommitOrderActivity.this, Color.parseColor("#ffffff"));
        ButterKnife.bind(CommitOrderActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_jianhao, R.id.rl_jiahao})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_jianhao:
                if(goodsNum > 1){
                    goodsNum = goodsNum - 1;
                    tvGoodsNum.setText(goodsNum+"");
                }
                break;
            case R.id.rl_jiahao:
                goodsNum = goodsNum + 1;
                tvGoodsNum.setText(goodsNum+"");
                break;
        }
    }

}
