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
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.IndexGoodsBean;
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
 * Created by Administrator on 2019/2/26.
 */

public class FragmentMyTuijianAdapter extends RecyclerView.Adapter<FragmentMyTuijianAdapter.ViewHolder> {

    private Context context;
    private List<IndexGoodsBean.DataBean> data;

    public FragmentMyTuijianAdapter(List<IndexGoodsBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_my_tuijian, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.price.setText(data.get(position).getPrice()+"");
        holder.goodsNmae.setText(data.get(position).getGoodsName());
        Glide.with(context).load(Const.BASE_URL+data.get(position).getAppPic()).into(holder.images);
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
        private TextView price;
        private TextView goodsNmae;
        private ImageView images;
        private ImageView addcart;
        public ViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
            goodsNmae = itemView.findViewById(R.id.goodsNmae);
            price = itemView.findViewById(R.id.price);
            addcart = itemView.findViewById(R.id.addcart);
        }
    }

}
