package com.example.practicesolvative.network;

import com.example.practicesolvative.model.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("contacts")
    Call<model> getContacts();
}
