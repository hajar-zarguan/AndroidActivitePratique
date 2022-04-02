package com.example.myapplication.DatabaseServices;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.myapplication.Model.User;
import android.arch.persistence.room.Dao;
import java.util.List;
@Dao
public interface Daoo {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insert(User contact);

    @Query("SELECT * FROM User WHERE Name LIKE :Name")
    List<User> findByName(String Name);

    @Query("SELECT * FROM User WHERE ID= :ID")
    User findByID(int ID);

    @Query("DELETE FROM User WHERE ID= :ID")
    void delete(int ID);

    @Update
    void update(User contact);

    @Query("DELETE FROM User")
    void clear();


}
