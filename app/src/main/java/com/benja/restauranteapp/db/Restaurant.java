package com.benja.restauranteapp.db;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
}