package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.FragmentGouwucheBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/20.
 */

public class GouwucheCommitOrderAdapter extends RecyclerView.Adapter<GouwucheCommitOrderAdapter.ViewHolder> {

    private Context context;
    private List<FragmentGouwucheBean.DataBean> data;

    public GouwucheCommitOrderAdapter(List<FragmentGouwucheBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_gouwuche_commit_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSellerName.setText(data.get(position).getSellerName());
        GouwucheCommitOrderItemAdapter itemAdapter = new GouwucheCommitOrderItemAdapter(data.get(position).getShopGoods());
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llSeller;
        private TextView tvSellerName;
        private RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            llSeller = itemView.findViewById(R.id.ll_seller);
            tvSellerName = itemView.findViewById(R.id.tv_seller_title);
            rv = itemView.findViewById(R.id.rv);
        }
    }

}
