package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.LookHistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/11.
 */

public class LookHistoryAdapter extends RecyclerView.Adapter<LookHistoryAdapter.ViewHolder> {

    private Context context;
    private List<LookHistoryBean.DataBean> data;

    public LookHistoryAdapter(List<LookHistoryBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_lookhistory, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final List<LookHistoryBean.DataBean.GoodsbeanBean> list = data.get(position).getGoodsbean();
        final LookHistoryItemAdapter itemAdapter = new LookHistoryItemAdapter(list, new LookHistoryItemAdapter.ClickListener() {
            @Override
            public void onClear() {
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.tvTime.setText(data.get(position).getDatetime());
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(itemAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTime;
        private RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            rv = itemView.findViewById(R.id.item_rv);
        }
    }

}
