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
import com.google.gson.annotations.Until;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.ShopIndexGoodsBean;
import com.jingna.shopapp.pages.GoodsDetailsActivity;
import com.jingna.shopapp.pages.SMSLoginActivity;
import com.jingna.shopapp.util.Const;
import com.jingna.shopapp.util.SpUtils;
import com.jingna.shopapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/3/12.
 */

public class ShopIndexAdapter extends RecyclerView.Adapter<ShopIndexAdapter.ViewHolder> {
    private Context context;
    private List<ShopIndexGoodsBean.DataBean> data;
    private String shopid;
    public ShopIndexAdapter(List<ShopIndexGoodsBean.DataBean> data,String shopid) {
        this.data = data;
        this.shopid = shopid;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shop_index_goods, parent, false);
        ShopIndexAdapter.ViewHolder holder = new ShopIndexAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getGoodsPic()).into(holder.iv);
        holder.tvTitle.setText(data.get(position).getGoodsName());
        holder.tvPrice.setText("¥"+data.get(position).getGoodsPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GoodsDetailsActivity.class);
                intent.putExtra("id", data.get(position).getGoodsId()+"");
                context.startActivity(intent);
            }
        });
        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SpUtils.getUserId(context).equals("0")){//检查是否登录
                    Intent intent = new Intent();
                    intent.setClass(context, SMSLoginActivity.class);
                    context.startActivity(intent);
                }else{
                    ViseHttp.POST("/ShopCart/toUpdate")
                            .addParam("userid", SpUtils.getUserId(context))
                            .addParam("goodsid", data.get(position).getGoodsId()+"")
                            .addParam("sellerId", shopid)
                            .addParam("goodsNum", "1")
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if (jsonObject.optString("status").equals("200")){
                                            ToastUtil.showShort(context, "已添加到购物车");
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView addcart;
        public ViewHolder(View itemView)
        {
            super(itemView);
            addcart = itemView.findViewById(R.id.addcart);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
