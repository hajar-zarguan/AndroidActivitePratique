package com.example.myapplication.DatabaseServices;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.myapplication.Model.User;

import java.util.List;

@Database(entities = {User.class}, version = 1)
public class AppDataBase extends RoomDatabase {

    //Create DAO
    public abstract Daoo UserDAO();

    // create database instance
    private static AppDataBase database;

    // Define database name
    private static String DATABASE_NAME="db";

    public synchronized static AppDataBase getInstance(Context context)
    {
        // check condition
        if(database==null)
        {
            // when database is null
            // Initialize database
            database= Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        // Return database
        return database;
    }


    @NonNull
    @androidx.annotation.NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
