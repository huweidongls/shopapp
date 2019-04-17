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
import com.jingna.shopapp.bean.MessageCenterBean;
import com.jingna.shopapp.pages.GoodsDetailsActivity;
import com.jingna.shopapp.pages.MessagePreferentialActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/3/25.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<MessageCenterBean.DataBean> data;

    public MessageAdapter(List<MessageCenterBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getMsgPic()).into(holder.message_pic);//更换图片
        holder.tv_title.setText(data.get(position).getTypeName());//类型
        holder.meaages_user.setText(data.get(position).getMsgAbstract());//描述
        holder.week.setText(StringUtils.friendly_time(data.get(position).getSendTime()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转详情
                Intent intent = new Intent();
                intent.setClass(context, MessagePreferentialActivity.class);
                intent.putExtra("id", data.get(position).getTypeId());
                intent.putExtra("TypeName", data.get(position).getTypeName());
                context.startActivity(intent);
            }
        });
    }
    private String getWeek(String pTime) {
        String Week = "";
        //pTime=pTime.substring(10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView message_pic;
        private TextView tv_title;
        private TextView meaages_user;
        private TextView week;
        public ViewHolder(View itemView) {
            super(itemView);
            message_pic = itemView.findViewById(R.id.message_pic);
            tv_title = itemView.findViewById(R.id.tv_title);
            meaages_user = itemView.findViewById(R.id.meaages_user);
            week = itemView.findViewById(R.id.week);
        }
    }

}
