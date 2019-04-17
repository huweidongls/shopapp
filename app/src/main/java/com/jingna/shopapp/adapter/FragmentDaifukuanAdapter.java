package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentDaifukuanAdapter extends RecyclerView.Adapter<FragmentDaifukuanAdapter.ViewHolder> {

    private Context context;
    private List<OrderDaifukuanBean.DataBean> data;

    public FragmentDaifukuanAdapter(List<OrderDaifukuanBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_daifukuan, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSellerTitle.setText(data.get(position).getSellerName());
        List<OrderDaifukuanBean.DataBean.ListBean> list = data.get(position).getList();
        if(list.size() == 1){
            holder.rl1.setVisibility(View.VISIBLE);
            holder.rl2.setVisibility(View.GONE);
            Glide.with(context).load(Const.BASE_URL+list.get(0).getGoodPic()).into(holder.ivTitle);
            holder.tvTitle.setText(list.get(0).getGoodsName());
            holder.tvPrice.setText("¥"+list.get(0).getGoodsPrice());
        }else {
            holder.rl1.setVisibility(View.GONE);
            holder.rl2.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rvGoodsList.setLayoutManager(manager);
            FragmentYiwanchengPicListAdapter listAdapter = new FragmentYiwanchengPicListAdapter(list);
            holder.rvGoodsList.setAdapter(listAdapter);
            holder.tvGoodsNum.setText("共"+list.size()+"件商品 应付款：");
            int price = 0;
            for (OrderDaifukuanBean.DataBean.ListBean bean : list){
                price = price + bean.getGoodsPrice();
            }
            holder.tvPrice.setText("¥"+price);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvSellerTitle;
        private ImageView ivTitle;
        private TextView tvTitle;
        private TextView tvPrice;
        private RelativeLayout rl1;
        private RelativeLayout rl2;
        private RecyclerView rvGoodsList;
        private TextView tvGoodsNum;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSellerTitle = itemView.findViewById(R.id.tv_seller_title);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            rl1 = itemView.findViewById(R.id.rl1);
            rl2 = itemView.findViewById(R.id.rl2);
            rvGoodsList = itemView.findViewById(R.id.rv_goods_list);
            tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
        }
    }

}
