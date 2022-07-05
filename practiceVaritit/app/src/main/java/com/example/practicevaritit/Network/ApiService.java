package com.example.practicevaritit.Network;

import com.example.practicevaritit.Model.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
//https://randomuser.me/api?results=10
public interface ApiService {
    @GET("api?results=10")
    Call<JsonResponse> getUsers();
}
