package com.benja.restauranteapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    // Constructor necesario para insertar fácilmente
    public Restaurant(String name) {
        this.name = name;
    }

    // Constructor vacío requerido por Room (si usas uno personalizado)
    public Restaurant() {
    }

    // Getters opcionales si los necesitas
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}