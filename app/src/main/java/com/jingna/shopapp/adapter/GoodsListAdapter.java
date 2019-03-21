package com.jingna.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.GoodsListBean;
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
 * Created by Administrator on 2019/2/28.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {

    private Context context;
    private List<GoodsListBean.DataBean> data;

    public GoodsListAdapter(List<GoodsListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_goods_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String pic = data.get(position).getPic();
        if(!TextUtils.isEmpty(pic)){
            Glide.with(context).load(Const.BASE_URL+pic.split(",")[0]).into(holder.iv);
        }
        holder.tvTitle.setText(data.get(position).getProductName());
        holder.tvPrice.setText("¥"+data.get(position).getPrice());
        holder.tvShopName.setText(data.get(position).getSellerName()+" >");
        holder.ivAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SpUtils.getUserId(context).equals("0")){
                    Intent intent = new Intent();
                    intent.setClass(context, SMSLoginActivity.class);
                    context.startActivity(intent);
                }else {
                    ViseHttp.POST("/ShopCart/toUpdate")
                            .addParam("userid", SpUtils.getUserId(context))
                            .addParam("goodsid", data.get(position).getGoodsId()+"")
                            .addParam("sellerId", data.get(position).getSellerId()+"")
                            .addParam("goodsNum", "1")
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
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

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvPrice;
        private TextView tvShopName;
        private ImageView ivAddCar;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvShopName = itemView.findViewById(R.id.tv_shop_name);
            ivAddCar = itemView.findViewById(R.id.iv_add_shop_car);
        }
    }

}
