package com.tp3.mysqlliteroom;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// définir le nom de la table
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    //créer la clonne de l'id
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //créer la colonne du texte
    @ColumnInfo(name = "text")
    private String text;

    //générer les getters et setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
