package com.example.practicevaritit.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserDob {
    private String date;

    public UserDob(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public static class TypeConverterUserDob {
        Gson gson = new Gson();

        @TypeConverter
        public UserDob stringToObject(String data) {
            if(data == null){
                return null;
            }
            Type listType = new TypeToken<UserDob>(){}.getType();
            return gson.fromJson(data,listType);
        }

        @TypeConverter
        public String objectToString(UserDob userDob) {
            return gson.toJson(userDob);
        }
    }


}
