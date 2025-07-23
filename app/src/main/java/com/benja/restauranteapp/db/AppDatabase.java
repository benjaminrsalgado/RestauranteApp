package com.benja.restauranteapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {Restaurant.class, Food.class},
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public abstract RestaurantDao restaurantDao();
    public abstract FoodDao foodDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "restaurante_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}