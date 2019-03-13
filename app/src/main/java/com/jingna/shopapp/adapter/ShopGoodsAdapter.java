package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/13.
 */

public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public ShopGoodsAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public ShopGoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_goods_list, parent, false);
        ShopGoodsAdapter.ViewHolder holder = new ShopGoodsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopGoodsAdapter.ViewHolder holder, int position) {

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
