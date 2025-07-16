package com.benja.restauranteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    private List<String> listaRestaurantesOriginal;
    private List<String> listaRestaurantesFiltrada;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        ListView listView = findViewById(R.id.restaurantListView);
        SearchView searchView = findViewById(R.id.searchViewRestaurantes);


        listaRestaurantesOriginal = Arrays.asList(
                "Mexican Restaurant",
                "USA Restaurant",
                "Italian Restaurant",
                "Japan Restaurant"
        );


        listaRestaurantesFiltrada = new ArrayList<>(listaRestaurantesOriginal);


        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaRestaurantesFiltrada
        );

        listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarRestaurantes(newText);
                return true;
            }
        });


        listView.setOnItemClickListener((parent, view, position, id) -> {
            String restauranteSeleccionado = listaRestaurantesFiltrada.get(position);

            Intent intent = new Intent(RestaurantListActivity.this, RestaurantMenuActivity.class);
            intent.putExtra("nombreRestaurante", restauranteSeleccionado);
            startActivity(intent);
        });
    }


    private void filtrarRestaurantes(String texto) {
        listaRestaurantesFiltrada.clear();

        if (texto.isEmpty()) {
            listaRestaurantesFiltrada.addAll(listaRestaurantesOriginal);
        } else {
            String filtro = texto.toLowerCase();
            for (String restaurante : listaRestaurantesOriginal) {
                if (restaurante.toLowerCase().contains(filtro)) {
                    listaRestaurantesFiltrada.add(restaurante);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}
