package com.benja.restauranteapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {

    // Insertar una nueva comida
    @Insert
    void insert(Food food);

    // Obtener TODAS las comidas (sin importar el restaurante o tipo)
    @Query("SELECT * FROM food")
    List<Food> getAll();

    // Obtener todas las comidas de un restaurante
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId")
    List<Food> getFoodsByRestaurant(int restaurantId);

    // Obtener solo comidas (tipo = 'food')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'food'")
    List<Food> getOnlyFood(int restaurantId);

    // Obtener solo bebidas (tipo = 'drink')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'drink'")
    List<Food> getOnlyDrinks(int restaurantId);

    // Obtener solo complementos (tipo = 'complement')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'complement'")
    List<Food> getOnlyComplements(int restaurantId);
}
