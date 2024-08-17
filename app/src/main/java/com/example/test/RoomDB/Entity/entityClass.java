package com.example.test.RoomDB.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserInfo")
public class entityClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Name")
    String name;
    @ColumnInfo(name = "Age")
    int age;
    @ColumnInfo(name = "DOB")
    String dob;
    @ColumnInfo(name = "Address")
    String address;

    public entityClass() {
    }

    @Ignore
    public entityClass(String name, int age, String dob, String address) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
