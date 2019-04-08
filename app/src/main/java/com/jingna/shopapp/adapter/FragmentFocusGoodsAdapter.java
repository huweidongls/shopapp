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
import com.jingna.shopapp.bean.Attention_Goods_listBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 */

public class FragmentFocusGoodsAdapter extends RecyclerView.Adapter<FragmentFocusGoodsAdapter.ViewHolder> {

    private Context context;
    private List<Attention_Goods_listBean.DataBean> data;

    public FragmentFocusGoodsAdapter(List<Attention_Goods_listBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_focus_goods, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getAppPic()).into(holder.g_pic);
        holder.tv_title.setText(data.get(position).getGoodsName());
        holder.tv_price.setText("¥"+data.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView g_pic;
        private TextView tv_title;
        private TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            g_pic = itemView.findViewById(R.id.g_pic);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }

}
