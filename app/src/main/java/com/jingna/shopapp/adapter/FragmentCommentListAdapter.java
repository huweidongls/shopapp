package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingna.shopapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentCommentListAdapter extends RecyclerView.Adapter<FragmentCommentListAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public FragmentCommentListAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_comment_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.llXing.removeAllViews();
        ImageView imageView;
        for (int i = 0; i<5; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                holder.llXing.addView(imageView);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 10;
                holder.llXing.addView(imageView, layoutParams);
            }
        }
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        FragmentCommentListRvAdapter rvAdapter = new FragmentCommentListRvAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        holder.rvPic.setLayoutManager(manager);
        holder.rvPic.setAdapter(rvAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llXing;
        private RecyclerView rvPic;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            rvPic = itemView.findViewById(R.id.rv_pic);
        }
    }

}
