package com.example.practicevaritit.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users",indices = @Index(value = {"userId"},unique = true))
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name = "UserName")
    private UserName name;

    @ColumnInfo(name = "UserEmail")
    private String email;

    @ColumnInfo(name = "UserDob")
    private UserDob dob;

    @ColumnInfo(name = "UserPic")
    private UserProfileImage picture;

    public User(UserName name, String email, UserDob dob, UserProfileImage picture) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.picture = picture;
    }

    public int getUserId() {
        return userId;
    }

    public UserName getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserDob getDob() {
        return dob;
    }

    public UserProfileImage getPicture() {
        return picture;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", picture=" + picture +
                '}';
    }
}
