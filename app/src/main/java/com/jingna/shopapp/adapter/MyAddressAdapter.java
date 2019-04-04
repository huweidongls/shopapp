package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.AddressBean;
import com.jingna.shopapp.pages.EditReceiveActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/27.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {

    private Context context;
    private List<AddressBean.DataBean> data;
    private String type;
    private ItemClickListener listener;

    public MyAddressAdapter(List<AddressBean.DataBean> data, String type, ItemClickListener listener) {
        this.data = data;
        this.type = type;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_address_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getConsignee());
        holder.tvPhoneNum.setText(data.get(position).getConsigneeTel());
        holder.tvAddress.setText(data.get(position).getLocation()+data.get(position).getAdress());
        if(data.get(position).getAcquiescentAdress().equals("1")){
            holder.tvMoren.setVisibility(View.VISIBLE);
        }else {
            holder.tvMoren.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("wode")){
                    Intent intent = new Intent();
                    intent.setClass(context, EditReceiveActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }else if(type.equals("order")){
                    listener.onClick(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvPhoneNum;
        private TextView tvAddress;
        private TextView tvMoren;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phonenum);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvMoren = itemView.findViewById(R.id.tv_moren);
        }
    }

    public interface ItemClickListener{
        void onClick(AddressBean.DataBean bean);
    }

}
