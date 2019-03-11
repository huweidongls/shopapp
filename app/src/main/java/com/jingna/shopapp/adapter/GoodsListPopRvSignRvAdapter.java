package com.jingna.shopapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.app.MyApplication;
import com.jingna.shopapp.bean.ChoiceMenuSignBean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class GoodsListPopRvSignRvAdapter extends RecyclerView.Adapter<GoodsListPopRvSignRvAdapter.ViewHolder> {

    private Context context;
    private List<ChoiceMenuSignBean> data;
    private int pos;

    public GoodsListPopRvSignRvAdapter(List<ChoiceMenuSignBean> data, int pos) {
        this.data = data;
        this.pos = pos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_goods_list_pop_rv_sign_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getSign());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getIsSelete() == 0){
                    holder.tv.setTextColor(Color.parseColor("#FF0004"));
                    holder.tv.setBackgroundResource(R.drawable.bg_ff0004_16dp_bord);
                    data.get(position).setIsSelete(1);
                    MyApplication.signMap.put(pos+"", data);
                    notifyDataSetChanged();
                }else {
                    holder.tv.setTextColor(Color.parseColor("#333333"));
                    holder.tv.setBackgroundResource(R.drawable.bg_f5f5f5_16dp);
                    data.get(position).setIsSelete(0);
                    MyApplication.signMap.put(pos+"", data);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
