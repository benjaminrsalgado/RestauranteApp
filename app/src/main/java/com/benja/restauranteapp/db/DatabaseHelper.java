package com.benja.restauranteapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "restaurantes.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE restaurant (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL)");


        db.execSQL("CREATE TABLE food (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "restaurant_id INTEGER NOT NULL," +
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "description TEXT," +
                "type TEXT CHECK(type IN ('food', 'drink', 'complement')) NOT NULL," +
                "FOREIGN KEY(restaurant_id) REFERENCES restaurant(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);
    }
}
