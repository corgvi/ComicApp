package com.example.cuongvvph18550_asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.URL.BaseURL;
import com.example.cuongvvph18550_asm_mob403.adapter.TruyenAdapter;
import com.example.cuongvvph18550_asm_mob403.api.ResponseApi;
import com.example.cuongvvph18550_asm_mob403.datalocal.DataLocalManager;
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

public class MainActivity extends AppCompatActivity {
    List<Truyen> listTruyen = new ArrayList<Truyen>();
    RecyclerView recyclerView;
    TruyenAdapter truyenAdapter;
    ImageView img;
    TextView tvName, tvEmail;
    SearchView searchView;
    String TAG = "xxxxxxxxxxxxxxxx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        searchView = findViewById(R.id.search);
        getListTruyen();
        User user = DataLocalManager.getuser();
        Log.d("TAG", "onCreate: " + user.getId());
        tvName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        recyclerView = findViewById(R.id.rcv_truyen);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        truyenAdapter = new TruyenAdapter(listTruyen, this);
    }

    private void getListTruyen(){
        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BaseURL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ResponseApi responseApi = retrofit.create(ResponseApi.class);
        Call<List<Truyen>> truyens= responseApi.getTruyens();
        truyens.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                listTruyen = response.body();
                truyenAdapter.setData(listTruyen);
                recyclerView.setAdapter(truyenAdapter);
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {

            }
        });
    }
}