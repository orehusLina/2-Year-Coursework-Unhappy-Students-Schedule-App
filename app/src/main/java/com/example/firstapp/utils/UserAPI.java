package com.example.firstapp.utils;

import com.example.firstapp.model.ResitRequest;
import com.example.firstapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {
    @Headers("Content-Type: application/json")
    @POST("/api/login")
    Call<Void> login(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("/api/signup")
    Call<Void> signUp(@Body User user);

    @Headers("Content-Type: application/json")
    @GET("/api/user")
    Call<User> getUser(@Body User user);

    @GET("/api/resits")
    Call<List<ResitRequest>> getResits();

}
