package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.TobeEvaluatedBean;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.DensityTool;

import java.util.List;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentHaveCommentAdapter extends RecyclerView.Adapter<FragmentHaveCommentAdapter.ViewHolder> {

    private Context context;
    private List<TobeEvaluatedBean.DataBean> data;

    public FragmentHaveCommentAdapter(List<TobeEvaluatedBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_have_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] pic = data.get(position).getAppPic().split(",");
        if(pic.length>0){
            Glide.with(context).load(Const.BASE_URL+pic[0]).into(holder.images);
        }
        holder.llXing.removeAllViews();
        int a = DensityTool.dp2px(context, 15);
        ImageView imageView;
        for (int i = 0; i<data.get(position).getCommentLevel(); i++){
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
        holder.tv_title.setText(data.get(position).getGoodsName());
        holder.tv_comment.setText(data.get(position).getGoodsComment());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout llXing;
        private ImageView images;
        private TextView tv_title;
        private TextView tv_comment;
        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
            images = itemView.findViewById(R.id.images);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }

}
