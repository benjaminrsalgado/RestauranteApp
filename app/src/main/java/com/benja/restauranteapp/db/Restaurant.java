package com.benja.restauranteapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;


    public Restaurant(String name) {
        this.name = name;
    }


    public Restaurant() {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}