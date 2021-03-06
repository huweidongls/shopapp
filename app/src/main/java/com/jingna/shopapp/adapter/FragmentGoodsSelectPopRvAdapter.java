package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.ChoiceMenuBean;
import com.jingna.shopapp.bean.ChoiceMenuSignBean;
import com.jingna.shopapp.bean.FragmentGoodsSelectPopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentGoodsSelectPopRvAdapter extends RecyclerView.Adapter<FragmentGoodsSelectPopRvAdapter.ViewHolder> {

    private Context context;
    private List<FragmentGoodsSelectPopBean.DataBean> data;
    private ClickListener listener;

    public FragmentGoodsSelectPopRvAdapter(List<FragmentGoodsSelectPopBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_goods_list_pop_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getAttributeName());
        List<ChoiceMenuSignBean> list = new ArrayList<>();
        String[] sign = data.get(position).getInputList().split(",");
        for (int i = 0; i<sign.length; i++){
            list.add(new ChoiceMenuSignBean(sign[i], 0));
        }
        FragmentGoodsSelectPopRvSignRvAdapter rvAdapter = new FragmentGoodsSelectPopRvSignRvAdapter(list, data.get(position).getAttrId(), data.get(position).getAttrType(), new FragmentGoodsSelectPopRvSignRvAdapter.ClickListener() {
            @Override
            public void onClick(String pos, String i) {
                listener.onClick(pos, i);
            }
        });
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rvSign.setLayoutManager(manager);
        holder.rvSign.setAdapter(rvAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvSign;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            rvSign = itemView.findViewById(R.id.rv_sign);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface ClickListener{
        void onClick(String pos, String i);
    }

}
