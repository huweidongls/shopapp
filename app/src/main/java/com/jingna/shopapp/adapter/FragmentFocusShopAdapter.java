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
import com.jingna.shopapp.bean.UserFollowShopListBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/21.
 */

public class FragmentFocusShopAdapter extends RecyclerView.Adapter<FragmentFocusShopAdapter.ViewHolder> {

    private Context context;
    private List<UserFollowShopListBean.DataBean> data;

    public FragmentFocusShopAdapter(List<UserFollowShopListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_focus_shop, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getAppSellerLogo()).into(holder.shopimg);
        holder.tv_title.setText(data.get(position).getSellerName());
        holder.tv_shopnum.setText(data.get(position).getFollowNum()+"人关注");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView shopimg;
        private TextView tv_title;
        private TextView tv_shopnum;
        public ViewHolder(View itemView) {
            super(itemView);
            shopimg = itemView.findViewById(R.id.shopimg);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_shopnum = itemView.findViewById(R.id.tv_shopnum);
        }
    }

}
