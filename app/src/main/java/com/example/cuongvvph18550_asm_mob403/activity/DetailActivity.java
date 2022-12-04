package com.example.cuongvvph18550_asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.URL.BaseURL;
import com.example.cuongvvph18550_asm_mob403.adapter.CommentAdapter;
import com.example.cuongvvph18550_asm_mob403.api.ResponseApi;
import com.example.cuongvvph18550_asm_mob403.datalocal.DataLocalManager;
import com.example.cuongvvph18550_asm_mob403.model.Comment;
import com.example.cuongvvph18550_asm_mob403.model.Truyen;
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

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnDoc;
    private TextView tvName, tvMota, tvTacGia;
    private RecyclerView rcvComment;
    private ImageView img, btnSend;
    private Truyen truyen;
    private User user;
    private CommentAdapter commentAdapter;
    private List<Comment> listComment = new ArrayList<>();
    private EditText edComment;
    String TAG = "nnnnnnnnnnnnnnn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        btnDoc = findViewById(R.id.btn_doc);
        img = findViewById(R.id.img_truyen);
        tvName = findViewById(R.id.tv_name);
        tvTacGia = findViewById(R.id.tv_tac_gia);
        tvMota = findViewById(R.id.tv_mo_ta);
        rcvComment = findViewById(R.id.rcv_comment);
        edComment = findViewById(R.id.ed_mess);
        btnSend = findViewById(R.id.img_send);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvComment.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(listComment, this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        user = DataLocalManager.getuser();
        truyen = (Truyen) getIntent().getSerializableExtra("truyen");
        tvName.setText(truyen.getTenTruyen());
        tvTacGia.setText(truyen.getTenTacGia());
        tvMota.setText(truyen.getMoTa());
        Glide.with(this)
                .load(truyen.getAnhBia())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_file_download_off_24)
                .into(img);
        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("content", truyen);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        getComment();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postComment(new Comment(truyen.getId(), user.getId(), edComment.getText().toString()));
                getComment();
                edComment.setText("");
            }
        });
    }

    private void getComment(){
        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ResponseApi responseApi = retrofit.create(ResponseApi.class);
        Call<List<Comment>> comments = responseApi.getComments();
        comments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                listComment.clear();
                List<Comment> list = response.body();
                Log.d(TAG, "onResponse: " + list.size());
                truyen = (Truyen) getIntent().getSerializableExtra("truyen");
                user = DataLocalManager.getuser();
                for (Comment comment : list){
                    if(truyen.getId().equals(comment.getIdTruyen())){
                        listComment.add(comment);
                    }
                }
                commentAdapter.setData(listComment);
                rcvComment.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void postComment(Comment comment){
        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ResponseApi responseApi = retrofit.create(ResponseApi.class);
        Call<Comment> postComment = responseApi.postComment(comment);
        postComment.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Log.d("TAG", "comment: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}