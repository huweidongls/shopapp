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
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/13.
 */

public class ShopGoodsAdapter extends RecyclerView.Adapter<ShopGoodsAdapter.ViewHolder>{
    private Context context;
    private List<ShopGoodsBean.DataBean> data;
    private  String shopid;
    public ShopGoodsAdapter(List<ShopGoodsBean.DataBean> data , String shopid) {
        this.data = data;
        this.shopid = shopid;
    }
    @Override
    public ShopGoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shop_goods_list, parent, false);
        ShopGoodsAdapter.ViewHolder holder = new ShopGoodsAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ShopGoodsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getGoodsPic()).into(holder.iv);
        holder.tv.setText(data.get(position).getGoodsName());
        holder.tv_price.setText("¥"+data.get(position).getGoodsPrice());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        private TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
