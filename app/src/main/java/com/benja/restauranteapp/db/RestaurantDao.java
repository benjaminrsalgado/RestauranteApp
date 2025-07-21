package com.benja.restauranteapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Insert
    void insert(Restaurant restaurant);

    @Query("SELECT * FROM restaurant")
    List<Restaurant> getAll();

    @Query("SELECT * FROM restaurant WHERE name = :nombre LIMIT 1")
    Restaurant getByName(String nombre);

    // 🔥 Nuevo método para borrar todo
    @Query("DELETE FROM restaurant")
    void deleteAll();
}