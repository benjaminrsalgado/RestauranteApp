package com.benja.restauranteapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RestaurantDao {


    @Insert
    void insert(Restaurant restaurant);


    @Update
    void update(Restaurant restaurant);


    @Delete
    void delete(Restaurant restaurant);

    @Query("DELETE FROM restaurant")
    void deleteAll();


    @Query("SELECT * FROM restaurant")
    List<Restaurant> getAll();

    @Query("SELECT * FROM restaurant WHERE name = :nombre LIMIT 1")
    Restaurant getByName(String nombre);
}