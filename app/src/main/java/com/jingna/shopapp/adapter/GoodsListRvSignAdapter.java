package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;

/**
 * Created by Administrator on 2019/3/27.
 */

public class GoodsListRvSignAdapter extends RecyclerView.Adapter<GoodsListRvSignAdapter.ViewHolder> {

    private Context context;
    private String[] data;

    public GoodsListRvSignAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_goods_list_rv_sign, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSign.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvSign;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSign = itemView.findViewById(R.id.tv_sign);
        }
    }

}
