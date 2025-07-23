package com.benja.restauranteapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double price;
    private String description;
    private String type;
    private String restaurantName;
    private int restaurantId;


    public Food() {
    }


    public Food(String name, double price, String description, String type, String restaurantName, int restaurantId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
    }


    public Food(int id, String name, double price, String description, String type, String restaurantName, int restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}