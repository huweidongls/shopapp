package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;
import com.jingna.shopapp.bean.LookHistoryBean;
import com.jingna.shopapp.dialog.DialogCustom;
import com.jingna.shopapp.util.Const;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/4/12.
 */

public class LookHistoryItemAdapter extends RecyclerView.Adapter<LookHistoryItemAdapter.ViewHolder> {

    private Context context;
    private List<LookHistoryBean.DataBean.GoodsbeanBean> data;
    private ClickListener listener;

    public LookHistoryItemAdapter(List<LookHistoryBean.DataBean.GoodsbeanBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_lookhistory_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(Const.BASE_URL+data.get(position).getAppPic()).into(holder.ivTitle);
        holder.tvTitle.setText(data.get(position).getGoodsName());
        holder.tvPrice.setText("¥"+data.get(position).getPrice());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogCustom dialogCustom = new DialogCustom(context, "是否删除该条浏览记录", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        ViseHttp.POST("/MemberBrowse/toDelete")
                                .addParam("id", data.get(position).getBrowseId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        try {
                                            Log.e("123123", s);
                                            JSONObject jsonObject = new JSONObject(s);
                                            if(jsonObject.optString("status").equals("200")){
                                                data.remove(position);
                                                if(data.size() == 0){
                                                    listener.onClear();
                                                }
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
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivTitle;
        private TextView tvTitle;
        private TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

    public interface ClickListener{
        void onClear();
    }

}
