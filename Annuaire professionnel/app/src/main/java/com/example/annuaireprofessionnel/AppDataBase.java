package com.example.annuaireprofessionnel;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
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

    //Create DAO
    public abstract ContactDAO contactDAO();
}
