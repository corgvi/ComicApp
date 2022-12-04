package com.example.cuongvvph18550_asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuongvvph18550_asm_mob403.datalocal.DataLocalManager;
import com.example.cuongvvph18550_asm_mob403.model.User;
import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.api.ResponseApi;
import com.example.cuongvvph18550_asm_mob403.URL.BaseURL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btn_dn, btn_dk;
    private EditText ed_username, ed_password;
//    private ArrayList<User> listUser;
    private Boolean isSuccessful;
    String TAG = "zzzzzzzzzzzzzzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_dn = findViewById(R.id.btn_dn);
        btn_dk = findViewById(R.id.btn_dk);
        ed_password = findViewById(R.id.ed_password);
        ed_username = findViewById(R.id.ed_username);
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });
    }

    private void checkUser() {
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
                        if(ed_username.getText().toString().equals(user.getUserName())
                                && ed_password.getText().toString().equals(user.getPassword())){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            DataLocalManager.setUser(user);
                            isSuccessful = true;
                            finish();
                        }else {
                            isSuccessful = false;
                        }
                    }
                    if(isSuccessful){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }
}