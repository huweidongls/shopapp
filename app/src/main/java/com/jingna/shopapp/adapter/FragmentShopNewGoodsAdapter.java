package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.ShopGoodsBean;
import com.jingna.shopapp.pages.GoodsDetailsActivity;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/4/4.
 */

public class FragmentShopNewGoodsAdapter extends RecyclerView.Adapter<FragmentShopNewGoodsAdapter.ViewHolder>{
    private Context context;
    private List<ShopGoodsBean.DataBean> data;
    public FragmentShopNewGoodsAdapter(List<ShopGoodsBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public FragmentShopNewGoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_shop_newgoods, parent, false);
        FragmentShopNewGoodsAdapter.ViewHolder holder = new FragmentShopNewGoodsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentShopNewGoodsAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getGoodsPic()).into(holder.img);
        holder.title.setText(data.get(position).getGoodsName());
        holder.date.setText(data.get(position).getCreateTime());
        holder.price.setText("¥"+data.get(position).getGoodsPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GoodsDetailsActivity.class);
                intent.putExtra("id", data.get(position).getGoodsId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price;
        private TextView date;
        private ImageView img;
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
        }
    }
}
