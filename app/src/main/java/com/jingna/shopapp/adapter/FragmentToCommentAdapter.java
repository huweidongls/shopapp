package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.TobeEvaluatedBean;
import com.jingna.shopapp.pages.CommentActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.util.Const;

import java.util.List;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentToCommentAdapter extends RecyclerView.Adapter<FragmentToCommentAdapter.ViewHolder>{
    private Context context;
    private List<TobeEvaluatedBean.DataBean> data;
    public FragmentToCommentAdapter(List<TobeEvaluatedBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public FragmentToCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_comment_list_evaluation, parent, false);
        FragmentToCommentAdapter.ViewHolder holder = new FragmentToCommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FragmentToCommentAdapter.ViewHolder holder, final int position) {
        String[] pic = data.get(position).getAppPic().split(",");
        if(pic.length>0){
            Glide.with(context).load(Const.BASE_URL+pic[0]).into(holder.image);
        }
        holder.text.setText(data.get(position).getGoodsName());
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CommentActivity.class);
                intent.putExtra("orderId",data.get(position).getOrderId());
                intent.putExtra("goodsId",data.get(position).getGoodsId());
                intent.putExtra("goodsName",data.get(position).getGoodsName());
                intent.putExtra("goodspic",data.get(position).getAppPic());
                context.startActivity(intent);
            }
            });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;
        private TextView submit;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            submit = itemView.findViewById(R.id.submit);
        }
    }
}
