package com.example.kotlinpractice.dependencyinjection

import android.content.Context
import com.example.kotlinpractice.database.AppDao
import com.example.kotlinpractice.database.AppDatabase
import com.example.kotlinpractice.network.RetroServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Context):AppDatabase{
        return AppDatabase.getAppDBInstance(context)
    }
    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDatabase):AppDao{
        return appDatabase.getAppDao()
    }
    var BASE_URL = "https://disease.sh/v2/";

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit):RetroServiceInterface {
        return retrofit.create(RetroServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}