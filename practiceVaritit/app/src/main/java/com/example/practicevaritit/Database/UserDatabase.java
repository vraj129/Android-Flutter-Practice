package com.example.practicevaritit.Database;

import android.animation.TypeConverter;
import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.practicevaritit.Dao.UserDao;
import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.Model.UserDob;
import com.example.practicevaritit.Model.UserName;
import com.example.practicevaritit.Model.UserProfileImage;


@Database(entities = {User.class}, version = 2,exportSchema = false)
@TypeConverters({UserName.TypeConverterUserName.class,UserDob.TypeConverterUserDob.class,UserProfileImage.TypeConverterUserProfileImage.class})
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "UserDatabase";

    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (UserDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,UserDatabase.class,DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

   static Callback callback = new Callback() {
       @Override
       public void onCreate(@NonNull SupportSQLiteDatabase db) {
           super.onCreate(db);
           new PopulateAsyncTask(INSTANCE);
       }
   };

    static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private UserDao userDao;
         PopulateAsyncTask(UserDatabase userDatabase) {
            userDao=userDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
             userDao.deleteAllUsers();
            return null;
        }
    }
}
