package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demo_app.adapter.Adapter;
import com.example.demo_app.model.Feed;
import com.example.demo_app.model.Other_mandi;
import com.example.demo_app.network.APIService;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private static final String TAG ="MainActivity";

    public  ArrayList<Other_mandi> other_mandis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RetrofitActivity.this));
        Button button = findViewById(R.id.button);
        other_mandis = PrefConfig.readListFromPref(this);
        if(other_mandis != null)
        {
            adapter = new Adapter(RetrofitActivity.this,other_mandis);
            recyclerView.setAdapter(adapter);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJSON();
            }
        });
        //https://thekrishi.com/test/mandi?lat=28.44108136&lon=77.0526054&ver=89&lang=hi&crop_id=10
    }
    private void loadJSON(){
        //Toast.makeText(this,"Here",Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://thekrishi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiClass = retrofit.create(APIService.class);
        Call<Feed> call = apiClass.getData();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG,"onResponse : Server Response : " + response.toString());
                Log.d(TAG,"onResponse: received information: "+ response.body().toString());
                other_mandis = response.body().getData().getOther_mandis();
                PrefConfig.writeListInPref(getApplicationContext(),other_mandis);
                adapter = new Adapter(RetrofitActivity.this,other_mandis);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
            }
        });
    }
}