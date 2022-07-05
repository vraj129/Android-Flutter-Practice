package com.example.practicesolvative.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.practicesolvative.model.ContactModel;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactModel model);

    @Query("SELECT * FROM contacts")
    LiveData<List<ContactModel>> getAllContacts();
}
