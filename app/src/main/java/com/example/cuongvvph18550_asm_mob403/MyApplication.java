package com.example.cuongvvph18550_asm_mob403;

import android.app.Application;

import com.example.cuongvvph18550_asm_mob403.datalocal.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
