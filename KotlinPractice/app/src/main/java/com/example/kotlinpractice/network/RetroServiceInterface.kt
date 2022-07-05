package com.example.kotlinpractice.network

import com.example.kotlinpractice.model.RepositoryData
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {
    @GET("countries")
    fun getDataFromAPI() : Call<List<RepositoryData>>
}