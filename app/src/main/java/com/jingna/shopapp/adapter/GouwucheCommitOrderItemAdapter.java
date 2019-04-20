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
import com.jingna.shopapp.bean.FragmentGouwucheBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/4/20.
 */

public class GouwucheCommitOrderItemAdapter extends RecyclerView.Adapter<GouwucheCommitOrderItemAdapter.ViewHolder> {

    private Context context;
    private List<FragmentGouwucheBean.DataBean.ShopGoodsBean> data;

    public GouwucheCommitOrderItemAdapter(List<FragmentGouwucheBean.DataBean.ShopGoodsBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_gouwuche_commit_order_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getAppPic()).into(holder.ivTitle);
        holder.tvGoodsName.setText(data.get(position).getGoodsName());
        holder.tvGoodsNum.setText("数量: "+data.get(position).getGoodsNum());
        holder.tvGoodsPrice.setText("¥"+data.get(position).getPrice());
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
