package com.example.cuongvvph18550_asm_mob403.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.activity.DetailActivity;
import com.example.cuongvvph18550_asm_mob403.model.Truyen;

import java.util.ArrayList;
import java.util.List;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.TruyenViewHolder> {

    List<Truyen> listTruyen = new ArrayList<Truyen>();
    Context context;

    public TruyenAdapter(List<Truyen> listTruyen, Context context) {
        this.listTruyen = listTruyen;
        this.context = context;
    }

    public void setData(List<Truyen> listTruyen){
        this.listTruyen = listTruyen;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TruyenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenViewHolder holder, int position) {
        Truyen truyen = listTruyen.get(position);
        Glide.with(context)
                .load(truyen.getAnhBia())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_file_download_off_24)
                .into(holder.imgTruyen);
        holder.tvTacGia.setText(truyen.getTenTacGia());
        holder.tvTenTruyen.setText(truyen.getTenTruyen());
        holder.layoutTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("truyen", truyen);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    class TruyenViewHolder extends RecyclerView.ViewHolder {
        CardView layoutTruyen;
        ImageView imgTruyen;
        TextView tvTenTruyen, tvTacGia;
        public TruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutTruyen = itemView.findViewById(R.id.layout_truyen);
            imgTruyen = itemView.findViewById(R.id.img_truyen);
            tvTenTruyen = itemView.findViewById(R.id.tv_name);
            tvTacGia = itemView.findViewById(R.id.tv_tac_gia);
        }
    }
}
