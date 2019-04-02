package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jingna.shopapp.R;
import com.jingna.shopapp.util.DensityTool;

import java.util.List;

/**
 * Created by Administrator on 2019/4/2.
 */

public class FragmentHaveCommentAdapter extends RecyclerView.Adapter<FragmentHaveCommentAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public FragmentHaveCommentAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_have_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.llXing.removeAllViews();
        int a = DensityTool.dp2px(context, 15);
        ImageView imageView;
        for (int i = 0; i<4; i++){
            if(i == 0){
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                holder.llXing.addView(imageView, layoutParams);
            }else {
                imageView = new ImageView(context);
                imageView.setImageResource(R.mipmap.xingxing_red);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
                layoutParams.leftMargin = 10;
                holder.llXing.addView(imageView, layoutParams);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llXing;

        public ViewHolder(View itemView) {
            super(itemView);
            llXing = itemView.findViewById(R.id.ll_xing);
        }
    }

}
