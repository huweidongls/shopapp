package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.OrderDaifukuanBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.pages.DetailsOrderActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/3/6.
 */

public class FragmentDaifukuanAdapter extends RecyclerView.Adapter<FragmentDaifukuanAdapter.ViewHolder> {

    private Context context;
    private List<OrderDaifukuanBean.DataBean> data;
    private ClickListener listener;

    public FragmentDaifukuanAdapter(List<OrderDaifukuanBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_daifukuan, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
            double price = 0;
            for (OrderDaifukuanBean.DataBean.ListBean bean : list){
                price = price + bean.getGoodsPrice();
            }
            holder.tvPrice.setText("¥"+price);
            holder.rvGoodsList.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        holder.itemView.performClick();  //模拟父控件的点击
                    }
                    return false;
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, DetailsOrderActivity.class);
                intent.putExtra("orderId", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "是否取消订单", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.GET("/AppOrder/cancellationOrder")
                                .addParam("goodsOrderId", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String str) {
                                        try {
                                            Log.e("123123", str);
                                            JSONObject jsonObject = new JSONObject(str);
                                            if(jsonObject.optString("status").equals("200")){
                                                ToastUtil.showShort(context, "取消订单成功");
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
        holder.tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPay(position);
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
        private TextView tvPrice;
        private RelativeLayout rl1;
        private RelativeLayout rl2;
        private RecyclerView rvGoodsList;
        private TextView tvGoodsNum;
        private TextView tvCancelOrder;
        private TextView tvToPay;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSellerTitle = itemView.findViewById(R.id.tv_seller_title);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            rl1 = itemView.findViewById(R.id.rl1);
            rl2 = itemView.findViewById(R.id.rl2);
            rvGoodsList = itemView.findViewById(R.id.rv_goods_list);
            tvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
            tvCancelOrder = itemView.findViewById(R.id.tv_cancel_order);
            tvToPay = itemView.findViewById(R.id.tv_to_pay);
        }
    }

    public interface ClickListener{
        void onPay(int pos);
    }

}
