package com.tp3.mysqlliteroom;


import static androidx.room.OnConflictStrategy.REPLACE;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MainDao {
    //Insert query
    @Insert(onConflict  = REPLACE)
    public abstract void insert(MainData mainData);

    //Delete query
    @Delete
    public abstract void delete(MainData mainData);

    //Delete all query
    @Delete
    public abstract void reset(List<MainData> mainData);

    //update query
    @Query("UPDATE table_name SET text = :sText WHERE ID = :sID")
    public abstract void update(int sID, String sText);

    //Get all data query
    @Query("SELECT * FROM table_name")
    public abstract List<MainData> getAll();


}
