package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.MessageCenterBean;
import com.jingna.shopapp.pages.MessagePreferentialActivity;
import com.jingna.shopapp.pages.Message_contentActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.StringUtils;

import java.util.List;

import retrofit2.http.POST;

/**
 * Created by Administrator on 2019/3/25.
 */

public class MessagePreferentialAdapter extends RecyclerView.Adapter<MessagePreferentialAdapter.ViewHolder> {

    private Context context;
    private List<MessageCenterBean.DataBean> data;

    public MessagePreferentialAdapter(List<MessageCenterBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message_preferential, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_time.setText(StringUtils.friendly_time(data.get(position).getSendTime()));
        Glide.with(context).load(Const.BASE_URL+data.get(position).getMsgPic()).into(holder.tv_img);//更换图片
        holder.tv_title.setText(data.get(position).getMsgTitle());
        holder.tv_miaoshu.setText(data.get(position).getMsgAbstract());
        if(data.get(position).getIsImgOrText().equals('1')){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//跳转详情
                    Intent intent = new Intent();
                    intent.setClass(context, Message_contentActivity.class);
                    intent.putExtra("id", data.get(position).getPId());
                    context.startActivity(intent);
                }
            });
        }else{
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(data.get(position).getMsgUrl());//此处填链接
            intent.setData(content_url);
            context.startActivity(intent);
        }

    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_time;
        private ImageView tv_img;
        private TextView tv_title;
        private TextView tv_miaoshu;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_img = itemView.findViewById(R.id.tv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_miaoshu = itemView.findViewById(R.id.tv_miaoshu);
        }
    }

}
