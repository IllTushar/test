package com.example.test.RoomDB.RoomInterface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.*;

import com.example.test.RoomDB.Entity.entityClass;

@Dao
public interface RoomInterface {
    @Insert
    void insert(entityClass data);


    @Delete
    void delete(entityClass user);

    @Query("SELECT * FROM UserInfo")
    LiveData<List<entityClass>> getAllItems();


}
