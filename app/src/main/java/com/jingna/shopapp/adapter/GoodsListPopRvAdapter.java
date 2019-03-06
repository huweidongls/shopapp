package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class GoodsListPopRvAdapter extends RecyclerView.Adapter<GoodsListPopRvAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public GoodsListPopRvAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_goods_list_pop_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsListPopRvSignRvAdapter rvAdapter = new GoodsListPopRvSignRvAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rvSign.setLayoutManager(manager);
        holder.rvSign.setAdapter(rvAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvSign;

        public ViewHolder(View itemView) {
            super(itemView);
            rvSign = itemView.findViewById(R.id.rv_sign);
        }
    }

}
