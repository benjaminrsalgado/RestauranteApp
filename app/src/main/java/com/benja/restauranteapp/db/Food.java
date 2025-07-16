package com.benja.restauranteapp.db;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Entidad que representa una comida, bebida o complemento.
 */
@Entity(
        tableName = "food",
        foreignKeys = @ForeignKey(
                entity = Restaurant.class,
                parentColumns = "id",
                childColumns = "restaurant_id",
                onDelete = CASCADE
        )
)
public class Food {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "restaurant_id", index = true)
    public int restaurantId;

    @NonNull
    public String name;

    public double price;

    public String description;

    @NonNull
    public FoodType type;  // Esto usa un enum que crearemos enseguida
}
