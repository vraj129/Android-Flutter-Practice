package com.example.practicesolvative.model;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class model {
    @SerializedName("contacts")
    @Expose
    List<ContactModel> contacts;

    public model(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    public List<ContactModel> getContacts() {
        return contacts;
    }
}
