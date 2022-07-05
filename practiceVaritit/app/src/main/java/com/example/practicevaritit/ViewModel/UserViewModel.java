package com.example.practicevaritit.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> getAllUsers;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        getAllUsers = userRepository.getAllRecords();
    }

    public LiveData<List<User>> searchDatabase(String searchQuery){
        return  userRepository.searchDatabase(searchQuery);
    }

    public LiveData<List<User>> getAllUsers(){
        return getAllUsers;
    }
}
