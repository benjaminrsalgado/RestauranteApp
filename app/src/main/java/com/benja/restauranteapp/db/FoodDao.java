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

    // Obtener TODAS las comidas
    @Query("SELECT * FROM food")
    List<Food> getAll();

    // Obtener todas las comidas de un restaurante específico
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId")
    List<Food> getFoodsByRestaurant(int restaurantId);

    // Obtener solo comidas (type = 'comida')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'comida'")
    List<Food> getOnlyFood(int restaurantId);

    // Obtener solo bebidas (type = 'bebida')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'bebida'")
    List<Food> getOnlyDrinks(int restaurantId);

    // Obtener solo complementos (type = 'complemento')
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId AND type = 'complemento'")
    List<Food> getOnlyComplements(int restaurantId);

    // Obtener comida por tipo y restaurante (dinámico)
    @Query("SELECT * FROM food WHERE type = :type AND restaurant_id = :restaurantId")
    List<Food> getByTypeAndRestaurant(String type, int restaurantId);
}