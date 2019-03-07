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

import java.util.List;

/**
 * Created by Administrator on 2019/3/7.
 */

public class FragmentShenqingjiluAdapter extends RecyclerView.Adapter<FragmentShenqingjiluAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public FragmentShenqingjiluAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shenqingjilu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data.get(position).equals("0")){
            Glide.with(context).load(R.mipmap.huanhuo).into(holder.ivType);
            holder.tvType.setText("换货");
        }else if(data.get(position).equals("1")){
            Glide.with(context).load(R.mipmap.tuihuo).into(holder.ivType);
            holder.tvType.setText("退货");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivType;
        private TextView tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }

}
