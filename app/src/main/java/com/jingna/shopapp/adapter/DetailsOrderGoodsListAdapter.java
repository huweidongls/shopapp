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
import com.jingna.shopapp.bean.DetailsOrderBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */

public class DetailsOrderGoodsListAdapter extends RecyclerView.Adapter<DetailsOrderGoodsListAdapter.ViewHolder> {

    private Context context;
    private List<DetailsOrderBean.DataBean.ListBean> data;

    public DetailsOrderGoodsListAdapter(List<DetailsOrderBean.DataBean.ListBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_details_order_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getGoodPic()).into(holder.ivTitle);
        holder.tvGoodsName.setText(data.get(position).getGoodsName());
        holder.tvGoodsNum.setText("数量："+data.get(position).getGoodsNumber());
        holder.tvGoodsPrice.setText("¥"+data.get(position).getGoodsPrice());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivTitle;
        private TextView tvGoodsName;
        private TextView tvGoodsNum;
        private TextView tvGoodsPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
            tvGoodsPrice = itemView.findViewById(R.id.tv_goods_price);
        }
    }

}
