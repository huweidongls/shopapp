package com.jingna.shopapp.pages;

import android.content.Context;
import android.os.Bundle;

import com.jingna.shopapp.R;
import com.jingna.shopapp.base.BaseActivity;

import butterknife.ButterKnife;

public class EditReceiveActivity extends BaseActivity {

    private Context context = EditReceiveActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_receive);

        ButterKnife.bind(EditReceiveActivity.this);

    }
}
