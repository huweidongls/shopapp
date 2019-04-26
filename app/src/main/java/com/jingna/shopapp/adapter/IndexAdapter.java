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
import com.jingna.shopapp.bean.IndexGoodsBean;
import com.jingna.shopapp.pages.GoodsDetailsActivity;
import com.jingna.shopapp.pages.GoodsListActivity;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/2/15.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<IndexGoodsBean.DataBean> data;

    public IndexAdapter(List<IndexGoodsBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String[] pic = data.get(position).getAppPic().split(",");
        if(pic.length>0){
            Glide.with(context).load(Const.BASE_URL+pic[0]).into(holder.image);
        }
       // Glide.with(context).load(Const.BASE_URL+data.get(position).getAppPic()).into(holder.image);
        holder.text_goods.setText(data.get(position).getGoodsName());
        holder.price.setText(data.get(position).getPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GoodsDetailsActivity.class);
                intent.putExtra("id", data.get(position).getGoodsId()+"");
                context.startActivity(intent);
            }
        });
        holder.kxs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GoodsListActivity.class);
                intent.putExtra("id", data.get(position).getCategoryId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView text_goods;
        private TextView price;
        private TextView kxs;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text_goods = itemView.findViewById(R.id.text_goods);
            price = itemView.findViewById(R.id.price);
            kxs = itemView.findViewById(R.id.kxs);
        }
    }

}
