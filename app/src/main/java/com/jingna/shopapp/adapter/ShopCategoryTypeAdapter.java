package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.ChoiceMenuSignBean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 */

public class ShopCategoryTypeAdapter extends RecyclerView.Adapter<ShopCategoryTypeAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public ShopCategoryTypeAdapter(List<String> data) {
        this.data = data;
    }

    public ShopCategoryTypeAdapter() {

    }

    @Override
    public ShopCategoryTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shop_type, parent, false);
        ShopCategoryTypeAdapter.ViewHolder holder = new ShopCategoryTypeAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ShopCategoryTypeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
