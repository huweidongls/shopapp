package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.AppCouponBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/30.
 */

public class FragmentNotUseCouponsAdapter extends RecyclerView.Adapter<FragmentNotUseCouponsAdapter.ViewHolder> {

    private Context context;
    private List<AppCouponBean.DataBean> data;

    public FragmentNotUseCouponsAdapter(List<AppCouponBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_not_use_coupons, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data.get(position).getType().equals("0")){
            holder.tvRmb.setVisibility(View.VISIBLE);
            holder.tvPrice.setVisibility(View.VISIBLE);
            holder.tvDiscount.setVisibility(View.GONE);
            holder.tvPrice.setText(data.get(position).getSumDiscount());
            holder.tvMaxPrice.setText("满"+data.get(position).getMaxMoney()+"元可用");
        }else if(data.get(position).getType().equals("1")){
            holder.tvRmb.setVisibility(View.GONE);
            holder.tvPrice.setVisibility(View.GONE);
            holder.tvDiscount.setVisibility(View.VISIBLE);
            holder.tvDiscount.setText(Double.valueOf(data.get(position).getParameter())*10+"折");
            holder.tvMaxPrice.setText("最高抵"+data.get(position).getSumDiscount()+"元");
        }
        holder.tvName.setText(data.get(position).getCouponName());
        holder.tvTime.setText(data.get(position).getValidTime()+"-"+data.get(position).getOverDueTime());
        String usageMode = data.get(position).getUsageMode();
        if(usageMode.equals("0")){
            holder.tvType.setText("全场通用");
        }else if(usageMode.equals("1")){
            holder.tvType.setText("指定分类");
        }else if(usageMode.equals("2")){
            holder.tvType.setText("指定商品");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvPrice;
        private TextView tvMaxPrice;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvType;
        private TextView tvRmb;
        private TextView tvDiscount;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvMaxPrice = itemView.findViewById(R.id.tv_max_price);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvType = itemView.findViewById(R.id.tv_type);
            tvRmb = itemView.findViewById(R.id.tv_rmb);
            tvDiscount = itemView.findViewById(R.id.tv_discount);
        }
    }

}
