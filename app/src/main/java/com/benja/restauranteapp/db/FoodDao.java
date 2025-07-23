package com.benja.restauranteapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {


    @Insert
    void insert(Food food);


    @Update
    void update(Food food);


    @Delete
    void delete(Food food);


    @Query("DELETE FROM food WHERE id = :id")
    void deleteById(int id);


    @Query("SELECT * FROM food")
    List<Food> getAll();


    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId")
    List<Food> getFoodsByRestaurant(int restaurantId);


    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'comida'")
    List<Food> getOnlyFood(int restaurantId);


    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'bebida'")
    List<Food> getOnlyDrinks(int restaurantId);


    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'complemento'")
    List<Food> getOnlyComplements(int restaurantId);


    @Query("SELECT * FROM food WHERE type = :type AND restaurantId = :restaurantId")
    List<Food> getByTypeAndRestaurant(String type, int restaurantId);


    @Query("SELECT * FROM food WHERE id = :id")
    Food getById(int id);
}