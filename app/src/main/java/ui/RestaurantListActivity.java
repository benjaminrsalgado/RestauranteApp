package com.benja.restauranteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;

public class RestaurantListActivity extends AppCompatActivity {

    String[] restaurantes = {"Restaurante A", "Restaurante B", "Restaurante C", "Restaurante D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        ListView listView = findViewById(R.id.restaurantListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                restaurantes
        );

        listView.setAdapter(adapter);

        // 👉 Aquí detectamos cuando el usuario toca un restaurante
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(RestaurantListActivity.this, RestaurantMenuActivity.class);
            startActivity(intent);
        });
    }
}
