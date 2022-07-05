package com.example.kotlinpractice.network

import androidx.lifecycle.LiveData
import com.example.kotlinpractice.database.AppDao
import com.example.kotlinpractice.model.RepositoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface,
                                            private val appDao: AppDao){

    fun getAllRecords() : LiveData<List<RepositoryData>> {
         return appDao.getAllRecords()
    }

    fun insertRecord(repositoryData: RepositoryData){
        appDao.insertRecords(repositoryData)
    }

    fun makeApiCall(){
        val call:Call<List<RepositoryData>> = retroServiceInterface.getDataFromAPI()
        call.enqueue(object :Callback<List<RepositoryData>>{
            override fun onResponse(
                call: Call<List<RepositoryData>>,
                response: Response<List<RepositoryData>>
            ) {
                if(response.isSuccessful){
                    appDao.deleteAllRecords()
                    response.body()?.forEach{
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<RepositoryData>>, t: Throwable) {

            }

        })
    }
}