package com.benja.restauranteapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {

    // Insertar nuevo platillo
    @Insert
    void insert(Food food);

    // Actualizar platillo existente
    @Update
    void update(Food food);

    // Eliminar platillo
    @Delete
    void delete(Food food);

    // Eliminar por ID
    @Query("DELETE FROM food WHERE id = :id")
    void deleteById(int id);

    // Obtener todos los platillos
    @Query("SELECT * FROM food")
    List<Food> getAll();

    // Obtener platillos de un restaurante específico
    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId")
    List<Food> getFoodsByRestaurant(int restaurantId);

    // Obtener solo tipo comida
    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'comida'")
    List<Food> getOnlyFood(int restaurantId);

    // Obtener solo tipo bebida
    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'bebida'")
    List<Food> getOnlyDrinks(int restaurantId);

    // Obtener solo tipo complemento
    @Query("SELECT * FROM food WHERE restaurantId = :restaurantId AND type = 'complemento'")
    List<Food> getOnlyComplements(int restaurantId);

    // Obtener por tipo dinámico
    @Query("SELECT * FROM food WHERE type = :type AND restaurantId = :restaurantId")
    List<Food> getByTypeAndRestaurant(String type, int restaurantId);

    // Obtener un platillo por su ID
    @Query("SELECT * FROM food WHERE id = :id")
    Food getById(int id);
}