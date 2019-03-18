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
import com.jingna.shopapp.bean.FragmentGoodsBean;
import com.jingna.shopapp.util.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/25.
 */

public class FragmentGoodsDetailsCommentListAdapter extends RecyclerView.Adapter<FragmentGoodsDetailsCommentListAdapter.ViewHolder> {

    private Context context;
    private List<FragmentGoodsBean.DataBean.CommentListBean> data;

    public FragmentGoodsDetailsCommentListAdapter(List<FragmentGoodsBean.DataBean.CommentListBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_goods_details_comment_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getMemName());
        Glide.with(context).load(Const.BASE_URL+data.get(position).getHeadPhoto()).into(holder.ivAvatar);
        holder.tvComment.setText(data.get(position).getGoodsComment());
        holder.llXing.removeAllViews();
        ImageView imageView;
        for (int i = 0; i<5; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                holder.llXing.addView(imageView);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 10;
                holder.llXing.addView(imageView, layoutParams);
            }
        }
        //加载评论图片
        if(!TextUtils.isEmpty(data.get(position).getGoodsCommentPic())){
            String[] pic = data.get(position).getGoodsCommentPic().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i<pic.length; i++){
                list.add(Const.BASE_URL+pic[i]);
            }
            FragmentGoodsDetailsCommentListRvPicAdapter picAdapter = new FragmentGoodsDetailsCommentListRvPicAdapter(list);
            GridLayoutManager manager = new GridLayoutManager(context, 4){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            holder.rvPic.setLayoutManager(manager);
            holder.rvPic.setAdapter(picAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llXing;
        private RecyclerView rvPic;
        private TextView tvName;
        private ImageView ivAvatar;
        private TextView tvComment;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            rvPic = itemView.findViewById(R.id.rv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvComment = itemView.findViewById(R.id.tv_comment);
        }
    }

}
