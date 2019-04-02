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

public class FragmentToCommentAdapter extends RecyclerView.Adapter<FragmentToCommentAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public FragmentToCommentAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FragmentToCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_comment_list_evaluation, parent, false);
        FragmentToCommentAdapter.ViewHolder holder = new FragmentToCommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentToCommentAdapter.ViewHolder holder, int position) {

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
