package com.example.cuongvvph18550_asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.URL.BaseURL;
import com.example.cuongvvph18550_asm_mob403.api.ResponseApi;
import com.example.cuongvvph18550_asm_mob403.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_dn, btn_dk;
    private EditText ed_username, ed_password, ed_repassword, ed_fullname, ed_email;
    private List<User> list = new ArrayList<User>();
    private Boolean isSuccessful;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_dk = findViewById(R.id.btn_dk);
        btn_dn = findViewById(R.id.btn_dn);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        ed_repassword = findViewById(R.id.ed_repassword);
        ed_fullname = findViewById(R.id.ed_fullname);
        ed_email = findViewById(R.id.ed_email);
        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    private void register() {
        String passWordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if(ed_username.getText().toString().trim().isEmpty()
                || ed_password.getText().toString().trim().isEmpty()
                || ed_fullname.getText().toString().trim().isEmpty()
                || ed_email.getText().toString().trim().isEmpty()
                || ed_repassword.getText().toString().trim().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Không để trống các trường dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches()){
            Toast.makeText(this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Pattern.compile(passWordPattern).matcher(ed_password.getText().toString()).matches() && ed_password.getText().toString().length() < 6){
            Toast.makeText(this, "Mật khẩu Không chứa ký tự đặc biệt và phải từ 6 ký tự trở lên", Toast.LENGTH_SHORT).show();
            return;
        }else if (!ed_password.getText().toString().equals(ed_repassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            return;
        }else {
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
                        list = response.body();
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
            for (int i = 0; i < list.size(); i++) {
                user = list.get(i);
                if (ed_username.getText().toString().equals(user.getUserName())) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            user = new User(ed_username.getText().toString(),
                    ed_password.getText().toString(),
                    ed_email.getText().toString(),
                    ed_fullname.getText().toString());
            Call<User> postUser = responseApi.postUser(user);
            postUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("TAG", "onResponse: " + response.body().toString() );
                    if (response.isSuccessful()) {
                        User user = response.body();
                        Log.d("TAG", "onResponse: post" + user.getUserName());
                            Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t);
                    Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}