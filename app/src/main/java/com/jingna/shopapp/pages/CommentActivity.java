package com.jingna.shopapp.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jingna.shopapp.R;
import com.jingna.shopapp.adapter.CommentAddPicAdapter;
import com.jingna.shopapp.base.BaseActivity;
import com.jingna.shopapp.util.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity {

    private Context context = CommentActivity.this;

    @BindView(R.id.rv_pic)
    RecyclerView recyclerView;
    @BindView(R.id.ll_add_pic)
    LinearLayout llAddPic;

    private CommentAddPicAdapter adapter;
    private List<String> mList;

    private int REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        StatusBarUtils.setStatusBar(CommentActivity.this, getResources().getColor(R.color.statusbar_color));
        ButterKnife.bind(CommentActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        adapter = new CommentAddPicAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CommentAddPicAdapter.ClickListener() {
            @Override
            public void addImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9-mList.size()) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(CommentActivity.this, REQUEST_CODE); // 打开相册
            }

            @Override
            public void deleteImg(int i) {
                mList.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.ll_add_pic})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_add_pic:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            llAddPic.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            mList.addAll(images);
            adapter.notifyDataSetChanged();

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

}
