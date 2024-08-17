package com.example.test.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.test.RoomDB.Entity.entityClass;
import com.example.test.RoomDB.RequestClass.roomRequestClass;
import com.example.test.RoomDB.RoomInterface.RoomInterface;

import java.util.List;

public class viewModelClass extends AndroidViewModel {
    private RoomInterface roomInterface;
    private LiveData<List<entityClass>> allItems;

    public viewModelClass(@NonNull Application application) {
        super(application);
        roomRequestClass database = roomRequestClass.getRequestModel(application);
        this.roomInterface = database.roomInterface();
        this.allItems = roomInterface.getAllItems();
    }
    public void insert(entityClass user) {
        new Thread(() -> roomInterface.insert(user)).start();
    }

    public LiveData<List<entityClass>> getAllUsers() {
        return allItems;
    }

    public void delete(entityClass user) {
        new Thread(() -> roomInterface.delete(user)).start();
    }
}
