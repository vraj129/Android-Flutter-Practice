package com.example.practicevaritit.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class UserProfileImage {
    private String thumbnail;

    public UserProfileImage(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public static class TypeConverterUserProfileImage {
        Gson gson = new Gson();

        @TypeConverter
        public UserProfileImage stringToObject(String data) {
            if(data == null){
                return null;
            }
            Type listType = new TypeToken<UserProfileImage>(){}.getType();
            return gson.fromJson(data,listType);
        }

        @TypeConverter
        public String objectToString(UserProfileImage userProfileImage) {
            return gson.toJson(userProfileImage);
        }


    }
}
