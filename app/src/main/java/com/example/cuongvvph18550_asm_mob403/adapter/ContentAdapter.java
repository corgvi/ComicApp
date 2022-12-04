package com.example.cuongvvph18550_asm_mob403.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.model.ImageContent;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder>{

    private List<String> listcontent;
    private Context context;

    public ContentAdapter(List<String> listcontent, Context context) {
        this.listcontent = listcontent;
        this.context = context;
    }

    public void setData(List<String> listcontent){
        this.listcontent = listcontent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContentAdapter.ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentAdapter.ContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ContentViewHolder holder, int position) {
        Glide.with(context)
                .load(listcontent.get(position))
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_file_download_off_24)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listcontent.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
