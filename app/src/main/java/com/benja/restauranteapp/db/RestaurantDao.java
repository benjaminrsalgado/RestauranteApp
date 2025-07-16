package com.benja.restauranteapp.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Insert
    void insert(Restaurant restaurant);

    @Query("SELECT * FROM Restaurant")
    List<Restaurant> getAll();
}