package com.example.cuongvvph18550_asm_mob403.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.URL.BaseURL;
import com.example.cuongvvph18550_asm_mob403.activity.LoginActivity;
import com.example.cuongvvph18550_asm_mob403.activity.MainActivity;
import com.example.cuongvvph18550_asm_mob403.api.ResponseApi;
import com.example.cuongvvph18550_asm_mob403.datalocal.DataLocalManager;
import com.example.cuongvvph18550_asm_mob403.model.Comment;
import com.example.cuongvvph18550_asm_mob403.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private List<Comment> listComment;
    private Context context;

    public CommentAdapter(List<Comment> listComment, Context context) {
        this.listComment = listComment;
        this.context = context;
    }

    public void setData(List<Comment> listComment){
        this.listComment = listComment;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = listComment.get(position);
        holder.tvComment.setText(comment.getNoiDung());

        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ResponseApi responseApi = retrofit.create(ResponseApi.class);
        Call<List<User>> users= responseApi.getUsers();
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    ArrayList<User> list = (ArrayList<User>) response.body();
                    for(User user :list){
                        if(comment.getIdUser().equals(user.getId())){
                            holder.tvName.setText(user.getFullName());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvComment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvComment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
