package com.example.cuongvvph18550_asm_mob403.api;

import com.example.cuongvvph18550_asm_mob403.model.Comment;
import com.example.cuongvvph18550_asm_mob403.model.Truyen;
import com.example.cuongvvph18550_asm_mob403.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ResponseApi {
    @GET("getUsers")
    Call<List<User>> getUsers();

    @GET("getTruyens")
    Call<List<Truyen>> getTruyens();

    @GET("getComments")
    Call<List<Comment>> getComments();

    @POST("createComment")
    Call<Comment> postComment(@Body Comment comment);

    @POST("createUser")
    Call<User> postUser(@Body User user);
}
