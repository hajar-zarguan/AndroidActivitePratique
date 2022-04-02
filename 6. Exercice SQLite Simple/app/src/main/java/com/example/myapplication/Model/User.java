package com.example.myapplication.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private  int ID ;
    @ColumnInfo(name = "Name")
    private  String Name;

    public User(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
