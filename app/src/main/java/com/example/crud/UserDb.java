package com.example.crud;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDb {
    @Query("SELECT * FROM UserData")
    List<UserData> getAll();

    @Query("SELECT * FROM UserData WHERE uid = :uid")
    UserData getUserById(int uid);

    @Update
    void update(UserData userData);

    @Insert
    void insertAll(UserData... userData);

    @Delete
    void delete(UserData userData);
}