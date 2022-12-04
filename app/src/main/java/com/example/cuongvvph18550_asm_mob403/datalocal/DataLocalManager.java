package com.example.cuongvvph18550_asm_mob403.datalocal;

import android.content.Context;

import com.example.cuongvvph18550_asm_mob403.model.User;
import com.google.gson.Gson;

public class DataLocalManager {

    private static final String PREF_USER = "PREF_USER";
    private static DataLocalManager instance;
    private MySharedPreferences preferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.preferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setUser(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        DataLocalManager.getInstance().preferences.putStringValue(PREF_USER, json);
    }

    public static User getuser(){
        String json = DataLocalManager.getInstance().preferences.getStringValue(PREF_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        return user;
    }
}
