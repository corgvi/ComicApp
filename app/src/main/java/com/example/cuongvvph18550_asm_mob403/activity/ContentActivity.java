package com.example.cuongvvph18550_asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cuongvvph18550_asm_mob403.R;
import com.example.cuongvvph18550_asm_mob403.adapter.ContentAdapter;
import com.example.cuongvvph18550_asm_mob403.model.ImageContent;
import com.example.cuongvvph18550_asm_mob403.model.Truyen;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView listView;
    private ContentAdapter contentAdapter;
    private TextView tvNoidung;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvNoidung = findViewById(R.id.tv_noidung);
        Truyen truyen = (Truyen) getIntent().getSerializableExtra("content");
        tvNoidung.setText(truyen.getTenTruyen());
        listView = findViewById(R.id.lv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        contentAdapter = new ContentAdapter(truyen.getDanhSachTruyen(), this);
        contentAdapter.setData(truyen.getDanhSachTruyen());
        listView.setAdapter(contentAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}