package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/4/2.
 */

public class TocommentAdapter extends RecyclerView.Adapter<TocommentAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public TocommentAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public TocommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_comment_list_evaluation, parent, false);
        TocommentAdapter.ViewHolder holder = new TocommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TocommentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
