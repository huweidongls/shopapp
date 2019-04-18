package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/4/16.
 */

public class FragmentReturnPriceAdapter extends RecyclerView.Adapter<FragmentReturnPriceAdapter.ViewHolder> {

    private Context context;
    private List<OrderDaifukuanBean.DataBean> data;

    public FragmentReturnPriceAdapter(List<OrderDaifukuanBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_return_price, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvSellerTitle.setText(data.get(position).getSellerName());
        List<OrderDaifukuanBean.DataBean.ListBean> list = data.get(position).getList();
        if(list.size() == 1){
            holder.rl1.setVisibility(View.VISIBLE);
            holder.rl2.setVisibility(View.GONE);
            Glide.with(context).load(Const.BASE_URL+list.get(0).getGoodPic()).into(holder.ivTitle);
            holder.tvTitle.setText(list.get(0).getGoodsName());
            holder.tvPrice.setText("¥"+list.get(0).getGoodsPrice());
        }else {
            holder.rl1.setVisibility(View.GONE);
            holder.rl2.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rvGoodsList.setLayoutManager(manager);
            FragmentYiwanchengPicListAdapter listAdapter = new FragmentYiwanchengPicListAdapter(list);
            holder.rvGoodsList.setAdapter(listAdapter);
            holder.tvGoodsNum.setText("共"+list.size()+"件商品 应付款：");
            int price = 0;
            for (OrderDaifukuanBean.DataBean.ListBean bean : list){
                price = price + bean.getGoodsPrice();
            }
            holder.tvPrice.setText("¥"+price);
        }
        holder.tvDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "是否删除订单", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.POST("/AppOrder/toDelete")
                                .addParam("goodsOrderId", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String str) {
                                        try {
                                            Log.e("123123", str);
                                            JSONObject jsonObject = new JSONObject(str);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "删除订单成功");
                                                data.remove(position);
                                                notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                });
                dialogCustom.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvSellerTitle;
        private ImageView ivTitle;
        private TextView tvTitle;
        private RelativeLayout rl1;
        private RelativeLayout rl2;
        private TextView tvGoodsNum;
        private TextView tvPrice;
        private RecyclerView rvGoodsList;
        private TextView tvDeleteOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSellerTitle = itemView.findViewById(R.id.tv_seller_title);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rl1 = itemView.findViewById(R.id.rl1);
            rl2 = itemView.findViewById(R.id.rl2);
            tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
            tvPrice = itemView.findViewById(R.id.tv_price);
            rvGoodsList = itemView.findViewById(R.id.rv_goods_list);
            tvDeleteOrder = itemView.findViewById(R.id.tv_delete_order);
        }
    }

}
