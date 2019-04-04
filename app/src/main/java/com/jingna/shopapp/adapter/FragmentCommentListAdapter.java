package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.FragmentCommentBean;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.DensityTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FragmentCommentListAdapter extends RecyclerView.Adapter<FragmentCommentListAdapter.ViewHolder> {

    private Context context;
    private List<FragmentCommentBean.DataBean> data;

    public FragmentCommentListAdapter(List<FragmentCommentBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_comment_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getHeadPhoto()).into(holder.ivAvatar);
        holder.tvName.setText(data.get(position).getMemName());
        holder.tvGoodsComment.setText(data.get(position).getGoodsComment());
        holder.tvGoodsName.setText(data.get(position).getGoodsName());
        holder.tvCommentTime.setText(data.get(position).getCommentTime());
        holder.llXing.removeAllViews();
        int commentLevel = data.get(position).getCommentLevel();
        int a = DensityTool.dp2px(context, 9);
        ImageView imageView;
        for (int i = 0; i<commentLevel; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.llXing.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 10;
                holder.llXing.addView(imageView, layoutParams);
            }
        }
        String pic = data.get(position).getGoodsCommentPic();
        if(!TextUtils.isEmpty(pic)){
            FragmentCommentListRvAdapter rvAdapter = new FragmentCommentListRvAdapter(pic.split(","));
            GridLayoutManager manager = new GridLayoutManager(context, 3);
            holder.rvPic.setLayoutManager(manager);
            holder.rvPic.setAdapter(rvAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llXing;
        private RecyclerView rvPic;
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvGoodsComment;
        private TextView tvGoodsName;
        private TextView tvCommentTime;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            rvPic = itemView.findViewById(R.id.rv_pic);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvGoodsComment = itemView.findViewById(R.id.tv_goods_comment);
            tvGoodsName = itemView.findViewById(R.id.tv_goods_name);
            tvCommentTime = itemView.findViewById(R.id.tv_comment_time);
        }
    }

}
