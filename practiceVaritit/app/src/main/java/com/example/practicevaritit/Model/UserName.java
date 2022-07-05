package com.example.practicevaritit.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class UserName {
    private String first;
    private String last;

    public UserName(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public static class TypeConverterUserName {
        Gson gson = new Gson();

        @TypeConverter
        public UserName stringToObject(String data) {
            if(data == null){
                return null;
            }
            Type listType = new TypeToken<UserName>(){}.getType();
            return gson.fromJson(data,listType);
        }

        @TypeConverter
        public String objectToString(UserName userName) {
            return gson.toJson(userName);
        }


    }
}
