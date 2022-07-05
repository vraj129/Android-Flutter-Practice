package com.example.practicesolvative.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practicesolvative.model.ContactModel;

@Database(entities = {ContactModel.class} , version = 1)
public abstract class ContactsDatabase extends RoomDatabase {
    private static ContactsDatabase instance;
    public abstract RoomDao dao();
    public static synchronized ContactsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ContactsDatabase.class,
                            "contactsDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
