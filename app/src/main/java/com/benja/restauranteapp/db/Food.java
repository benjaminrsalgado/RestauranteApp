package com.benja.restauranteapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public double price;
    public String description;
    public String type;
    public String restaurantName; // puedes mantenerlo si lo usas para mostrar
    public int restaurant_id;     // 👈 ESTE es el que faltaba

    public Food(String name, double price, String description, String type, String restaurantName, int restaurant_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.restaurantName = restaurantName;
        this.restaurant_id = restaurant_id;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getRestaurantName() { return restaurantName; }
    public int getRestaurant_id() { return restaurant_id; }
}