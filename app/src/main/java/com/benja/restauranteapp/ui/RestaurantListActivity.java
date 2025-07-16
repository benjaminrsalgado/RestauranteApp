package com.benja.restauranteapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;
import com.benja.restauranteapp.db.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RestaurantListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ArrayAdapter<String> adapter;
    private List<String> nombresRestaurantes = new ArrayList<>();

    private ListView listView;
    private EditText etNuevoRestaurante;
    private Button btnRegistrar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        db = AppDatabase.getInstance(this);

        listView = findViewById(R.id.restaurantListView);
        etNuevoRestaurante = findViewById(R.id.etNuevoRestaurante);
        btnRegistrar = findViewById(R.id.btnRegistrarRestaurante);
        searchView = findViewById(R.id.searchViewRestaurantes);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombresRestaurantes);
        listView.setAdapter(adapter);

        cargarRestaurantes();

        // 👇 Insertar datos de ejemplo si no hay platillos en la tabla Food
        Executors.newSingleThreadExecutor().execute(() -> {
            if (db.foodDao().getAll().isEmpty()) {
                db.foodDao().insert(new Food("Tlacoyos", 35.0, "Platillo mexicano", "food", "Mexican Restaurant"));
                db.foodDao().insert(new Food("Panza", 200.0, "Platillo típico", "food", "Mexican Restaurant"));
                db.foodDao().insert(new Food("Sushi", 100.0, "Rollos de sushi", "food", "Japan Restaurant"));
                db.foodDao().insert(new Food("Coca-Cola", 45.0, "Refresco", "drink", "Mexican Restaurant"));
                db.foodDao().insert(new Food("Guacamole", 30.0, "Aguacate machacado", "complement", "Mexican Restaurant"));
                db.foodDao().insert(new Food("Vino Tinto", 120.0, "Vino de la casa", "drink", "Italian Restaurant"));
            }
        });

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNuevoRestaurante.getText().toString().trim();
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Escribe un nombre", Toast.LENGTH_SHORT).show();
                return;
            }

            Executors.newSingleThreadExecutor().execute(() -> {
                Restaurant nuevo = new Restaurant();
                nuevo.name = nombre;
                db.restaurantDao().insert(nuevo);

                runOnUiThread(() -> {
                    etNuevoRestaurante.setText("");
                    Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    cargarRestaurantes(); // actualiza la lista
                });
            });
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> filtrados = new ArrayList<>();
                for (String nombre : nombresRestaurantes) {
                    if (nombre.toLowerCase().contains(newText.toLowerCase())) {
                        filtrados.add(nombre);
                    }
                }
                adapter.clear();
                adapter.addAll(filtrados);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String nombreSeleccionado = adapter.getItem(position);
            if (nombreSeleccionado != null) {
                Intent intent = new Intent(RestaurantListActivity.this, RestaurantMenuActivity.class);
                intent.putExtra("nombreRestaurante", nombreSeleccionado);
                startActivity(intent);
            }
        });
    }

    private void cargarRestaurantes() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Restaurant> lista = db.restaurantDao().getAll();
            nombresRestaurantes.clear();
            for (Restaurant r : lista) {
                nombresRestaurantes.add(r.name);
            }

            runOnUiThread(() -> {
                adapter.clear();
                adapter.addAll(nombresRestaurantes);
                adapter.notifyDataSetChanged();
            });
        });
    }
}
