package com.example.practicesolvative.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practicesolvative.model.ContactModel;
import com.example.practicesolvative.model.model;
import com.example.practicesolvative.network.ApiService;
import com.example.practicesolvative.network.RetroInstance;
import com.example.practicesolvative.room.ContactsDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListViewModel extends ViewModel {
    private MutableLiveData<model> contactList;
    private ContactsDatabase database;

    public ContactListViewModel() {
        contactList = new MutableLiveData<>();
    }

    public MutableLiveData<model> getContactList() {
        return contactList;
    }

    public void makeCall() {
        ApiService service = RetroInstance.getRetrofitClient().create(ApiService.class);
        Call<model> call = service.getContacts();
//        call.enqueue(new Callback<List<ContactModel>>() {
//            @Override
//            public void onResponse(Call<List<ContactModel>> call, Response<List<ContactModel>> response) {
//                Log.d("RESULT",response.body().toString());
//                contactList.postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<ContactModel>> call, Throwable t) {
//                Log.d("RESULT","FAILED");
//                contactList.postValue(null);
//            }
//        });
        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                Log.d("RESULT",response.body().toString());
                Log.d("RESULT","hello");
                contactList.postValue(response.body());
                database.dao().insert((ContactModel) response.body().getContacts());
            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                Log.d("RESULT",t.toString());
            }
        });
     }
}
