package com.example.demo_app.network;

import com.example.demo_app.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("test/mandi?lat=28.44108136&lon=77.0526054&ver=89&lang=hi&crop_id=10")
    Call<Feed> getData();
}
