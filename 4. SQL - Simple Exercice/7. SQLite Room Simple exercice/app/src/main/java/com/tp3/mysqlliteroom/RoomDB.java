package com.tp3.mysqlliteroom;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//ajouter les entité de la bdd
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //créer les instances de la bdd
    private static RoomDB database;
    //définir le nom de la bdd
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        //vérifier les condtions
        if(database == null){
            //quand la bdd est null
            //initialiser la bdd
            database= Room.databaseBuilder(context.getApplicationContext()
            ,RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //reoutner la bdd
        return database;
    }
    //créer Dao
    public abstract MainDao mainDao();
}
