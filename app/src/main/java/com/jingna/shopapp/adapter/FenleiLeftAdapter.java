package com.jingna.shopapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.FeileiLeftListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/2/20.
 */

public class FenleiLeftAdapter extends RecyclerView.Adapter<FenleiLeftAdapter.ViewHolder> {

    private Context context;
    private List<FeileiLeftListBean.DataBean> data;
    private int select = 0;
    private ItemClickListener listener;

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public FenleiLeftAdapter(List<FeileiLeftListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fenlei_left, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(position == select){
            holder.iv.setVisibility(View.VISIBLE);
            holder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            TextPaint paint = holder.tv.getPaint();
            paint.setFakeBoldText(true);
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else {
            holder.iv.setVisibility(View.GONE);
            holder.tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            TextPaint paint = holder.tv.getPaint();
            paint.setFakeBoldText(false);
            holder.itemView.setBackgroundColor(Color.parseColor("#f6f6f6"));
        }
        holder.tv.setText(data.get(position).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select != position){
                    select = position;
                    notifyDataSetChanged();
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_left);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int i);
    }

}
