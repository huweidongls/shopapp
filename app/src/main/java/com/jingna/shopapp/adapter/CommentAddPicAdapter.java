package com.jingna.shopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingna.shopapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/3/8.
 */

public class CommentAddPicAdapter extends RecyclerView.Adapter<CommentAddPicAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    private static final int TYPE_ADD = 1;
    private static final int TYPE_PIC = 2;
    private static final int MAX_SIZE = 9;

    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public CommentAddPicAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_comment_add_pic, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (data.size() >= MAX_SIZE) {
            //最多9张
            holder.llAdd.setVisibility(View.GONE);
        } else {
            holder.rlPic.setVisibility(View.VISIBLE);
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.llAdd.setVisibility(View.VISIBLE);
        }
        if (getItemViewType(position) == TYPE_ADD) {
            holder.llAdd.setVisibility(View.VISIBLE);
            holder.rlPic.setVisibility(View.GONE);
            holder.ivDelete.setVisibility(View.GONE);
        } else {
            holder.llAdd.setVisibility(View.GONE);
            holder.rlPic.setVisibility(View.VISIBLE);
            holder.ivDelete.setVisibility(View.VISIBLE);
            Glide.with(context).load(data.get(position)).into(holder.ivPic);
        }
        holder.llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addImg();
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteImg(position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_ADD;
        } else {
            return TYPE_PIC;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llAdd;
        private ImageView ivDelete;
        private RelativeLayout rlPic;
        private ImageView ivPic;

        public ViewHolder(View itemView) {
            super(itemView);
            llAdd = itemView.findViewById(R.id.ll_add);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            rlPic = itemView.findViewById(R.id.rl_pic);
            ivPic = itemView.findViewById(R.id.iv_pic);
        }
    }

    public interface ClickListener{
        void addImg();
        void deleteImg(int i);
    }

}
