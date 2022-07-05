package com.example.practicesolvative.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "contacts")
public class ContactModel {

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    private String gender;
//    private PhoneModel phoneModel;

    public ContactModel(int id, String name, String email, String gender) {

        this.name = name;
        this.email = email;
        this.gender = gender;
        //this.phoneModel = phoneModel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

//    public PhoneModel getPhoneModel() {
//        return phoneModel;
//    }
}
