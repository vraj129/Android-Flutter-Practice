package com.example.practicevaritit.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practicevaritit.Dao.UserDao;
import com.example.practicevaritit.Database.UserDatabase;
import com.example.practicevaritit.Model.JsonResponse;
import com.example.practicevaritit.Model.User;

import java.util.List;

public class UserRepository {
    private UserDatabase userDatabase;
    public UserRepository(Application application) {
        userDatabase = UserDatabase.getInstance(application);
    }
    public LiveData<List<User>> getAllRecords() {
        return userDatabase.userDao().getAllUsers();
    }
    public LiveData<List<User>> searchDatabase(String searchQuery){
        return userDatabase.userDao().getUsers(searchQuery);
    }
    public void deletAllUser(){
        new DeleteAsyncTask(userDatabase).execute();
    }
    public void insert(User user){
        new InsertAsyncTask(userDatabase).execute(user);
    }

    class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public InsertAsyncTask(UserDatabase userDatabase) {
            userDao = userDatabase.userDao();
        }


        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        public DeleteAsyncTask(UserDatabase userDatabase) {
            userDao = userDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
