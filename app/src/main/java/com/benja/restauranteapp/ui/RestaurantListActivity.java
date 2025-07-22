package com.benja.restauranteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;
import com.benja.restauranteapp.db.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class RestaurantListActivity extends AppCompatActivity {

    private AppDatabase db;
    private ArrayAdapter<String> adapter;
    private List<Restaurant> listaRestaurantes = new ArrayList<>();

    private ListView listView;
    private SearchView searchView;
    private FloatingActionButton fabAgregar;

    private static final String TAG = "RestaurantListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        db = AppDatabase.getInstance(this);

        listView   = findViewById(R.id.restaurantListView);
        searchView = findViewById(R.id.searchViewRestaurantes);
        fabAgregar = findViewById(R.id.fabAgregarRestaurante);

        // ✅ Importante: NO usar lista externa, usa lista interna del adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>()
        );
        listView.setAdapter(adapter);

        fabAgregar.setOnClickListener(v ->
                startActivity(new Intent(this, RegistrarRestauranteActivity.class)));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String q) { return false; }

            @Override public boolean onQueryTextChange(String txt) {
                filtrarEnLista(txt);
                return true;
            }
        });

        listView.setOnItemClickListener((p, v, pos, id) -> {
            String nombre = adapter.getItem(pos);
            if (nombre != null) {
                Intent i = new Intent(this, RestaurantMenuActivity.class);
                i.putExtra("nombreRestaurante", nombre);
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener((p, v, pos, id) -> {
            String nombre = adapter.getItem(pos);
            if (nombre == null) return false;

            // Buscar restaurante por nombre directamente
            for (Restaurant r : listaRestaurantes) {
                if (r.name.equals(nombre)) {
                    Intent i = new Intent(this, EditarRestauranteActivity.class);
                    i.putExtra("restaurantId", r.id);
                    i.putExtra("restaurantName", r.name);
                    startActivity(i);
                    return true;
                }
            }

            Toast.makeText(this, "Restaurante no encontrado", Toast.LENGTH_SHORT).show();
            return true;
        });

        sembrarDatosDemo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarRestaurantes();
    }

    private void cargarRestaurantes() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Restaurant> lista = db.restaurantDao().getAll();
            Log.d(TAG, "Total restaurantes: " + lista.size());

            listaRestaurantes.clear();
            listaRestaurantes.addAll(lista);

            runOnUiThread(() -> {
                adapter.clear();
                for (Restaurant r : listaRestaurantes) {
                    adapter.add(r.name);
                }
                adapter.notifyDataSetChanged();

                if (listaRestaurantes.isEmpty()) {
                    Toast.makeText(this, "No hay restaurantes registrados", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void filtrarEnLista(String texto) {
        adapter.clear();
        for (Restaurant r : listaRestaurantes) {
            if (r.name.toLowerCase().contains(texto.toLowerCase())) {
                adapter.add(r.name);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void sembrarDatosDemo() {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (!db.restaurantDao().getAll().isEmpty()) return;

            db.restaurantDao().insert(new Restaurant("Mexican Restaurant"));
            db.restaurantDao().insert(new Restaurant("Japan Restaurant"));
            db.restaurantDao().insert(new Restaurant("Italian Restaurant"));

            int idMex = db.restaurantDao().getByName("Mexican Restaurant").id;
            int idJap = db.restaurantDao().getByName("Japan Restaurant").id;
            int idIta = db.restaurantDao().getByName("Italian Restaurant").id;

            db.foodDao().insert(new Food("Tlacoyos", 35, "Platillo mexicano", "food", "Mexican Restaurant", idMex));
            db.foodDao().insert(new Food("Panza", 200, "Platillo típico", "food", "Mexican Restaurant", idMex));
            db.foodDao().insert(new Food("Sushi", 100, "Rollos de sushi", "food", "Japan Restaurant", idJap));
            db.foodDao().insert(new Food("Coca-Cola", 45, "Refresco", "drink", "Mexican Restaurant", idMex));
            db.foodDao().insert(new Food("Guacamole", 30, "Aguacate", "complement", "Mexican Restaurant", idMex));
            db.foodDao().insert(new Food("Vino Tinto", 120, "Vino de la casa", "drink", "Italian Restaurant", idIta));

            runOnUiThread(this::cargarRestaurantes);
        });
    }
}