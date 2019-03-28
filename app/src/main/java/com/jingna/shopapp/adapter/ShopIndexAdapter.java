package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.ShopIndexGoodsBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class ShopIndexAdapter extends RecyclerView.Adapter<ShopIndexAdapter.ViewHolder> {
    private Context context;
    private List<ShopIndexGoodsBean.DataBean> data;
    public ShopIndexAdapter(List<ShopIndexGoodsBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shop_index_goods, parent, false);
        ShopIndexAdapter.ViewHolder holder = new ShopIndexAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getGoodsPic()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getGoodsName());
        holder.tvPrice.setText("¥"+data.get(position).getGoodsPrice());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvTitle;
        private TextView tvPrice;
        public ViewHolder(View itemView)
        {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
