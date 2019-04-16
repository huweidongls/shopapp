package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentYiwanchengPicListAdapter extends RecyclerView.Adapter<FragmentYiwanchengPicListAdapter.ViewHolder> {

    private Context context;
    private List<OrderDaifukuanBean.DataBean.ListBean> data;

    public FragmentYiwanchengPicListAdapter(List<OrderDaifukuanBean.DataBean.ListBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_yiwancheng_pic_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String pic = data.get(position).getGoodPic();
        if(!TextUtils.isEmpty(pic)){
            Glide.with(context).load(Const.BASE_URL+pic).into(holder.iv);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
