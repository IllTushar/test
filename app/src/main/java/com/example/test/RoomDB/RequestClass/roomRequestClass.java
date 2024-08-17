package com.example.test.RoomDB.RequestClass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.test.RoomDB.Entity.entityClass;
import com.example.test.RoomDB.RoomInterface.RoomInterface;

@Database(entities = entityClass.class, exportSchema = false, version = 1)
public abstract class roomRequestClass extends RoomDatabase {
    private static final String DB_NAME = "Test eka.care";
    private static roomRequestClass requestModel;

    public static synchronized roomRequestClass getRequestModel(Context context) {
        if (requestModel == null) {
            requestModel = Room.databaseBuilder(context,roomRequestClass.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return requestModel;
    }

    public abstract RoomInterface roomInterface();
}
