package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Created by Administrator on 2019/4/16.
 */

public class FragmentAllOrderAdapter extends RecyclerView.Adapter<FragmentAllOrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderDaifukuanBean.DataBean> data;
    private ClickListener listener;

    public FragmentAllOrderAdapter(List<OrderDaifukuanBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_all_order, parent, false);
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
        String orderStatus = data.get(position).getOrderStatus();
        if(orderStatus.equals("0")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#FF0004"));
            holder.tvOrderStatus.setText("等待付款");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.VISIBLE);
            holder.tvToPay.setVisibility(View.VISIBLE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.GONE);
        }else if(orderStatus.equals("1")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#FF0004"));
            holder.tvOrderStatus.setText("待收货");
            holder.tvCheckLogistics.setVisibility(View.VISIBLE);
            holder.tvBuyAgain.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.VISIBLE);
            holder.tvDeleteOrder.setVisibility(View.GONE);
        }else if(orderStatus.equals("2")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#FF0004"));
            holder.tvOrderStatus.setText("待收货");
            holder.tvCheckLogistics.setVisibility(View.VISIBLE);
            holder.tvBuyAgain.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.VISIBLE);
            holder.tvDeleteOrder.setVisibility(View.GONE);
        }else if(orderStatus.equals("3")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#FF0004"));
            holder.tvOrderStatus.setText("待收货");
            holder.tvCheckLogistics.setVisibility(View.VISIBLE);
            holder.tvBuyAgain.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.GONE);
        }else if(orderStatus.equals("4")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#333333"));
            holder.tvOrderStatus.setText("已完成");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.GONE);
        }else if(orderStatus.equals("5")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#333333"));
            holder.tvOrderStatus.setText("已完成");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.VISIBLE);
        }else if(orderStatus.equals("6")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#333333"));
            holder.tvOrderStatus.setText("已完成");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.VISIBLE);
        }else if(orderStatus.equals("7")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#333333"));
            holder.tvOrderStatus.setText("已取消");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.VISIBLE);
        }else if(orderStatus.equals("8")){
            holder.tvOrderStatus.setTextColor(Color.parseColor("#333333"));
            holder.tvOrderStatus.setText("已退款");
            holder.tvCheckLogistics.setVisibility(View.GONE);
            holder.tvBuyAgain.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvReturnPrice.setVisibility(View.GONE);
            holder.tvDeleteOrder.setVisibility(View.VISIBLE);
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
        holder.tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPay(position);
            }
        });
        holder.tvReturnPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onReturnPrice(position);
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
        private TextView tvOrderStatus;
        private TextView tvCheckLogistics;
        private TextView tvBuyAgain;
        private TextView tvCancelOrder;
        private TextView tvToPay;
        private TextView tvReturnPrice;
        private TextView tvDeleteOrder;
        private LinearLayout ll;

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
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            tvCheckLogistics = itemView.findViewById(R.id.tv_check_logistics);
            tvBuyAgain = itemView.findViewById(R.id.tv_buy_again);
            tvCancelOrder = itemView.findViewById(R.id.tv_cancel_order);
            tvToPay = itemView.findViewById(R.id.tv_to_pay);
            tvReturnPrice = itemView.findViewById(R.id.tv_return_price);
            tvDeleteOrder = itemView.findViewById(R.id.tv_delete_order);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    public interface ClickListener{
        void onPay(int pos);
        void onReturnPrice(int pos);
    }

}
