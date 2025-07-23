package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Restaurant;

import java.util.concurrent.Executors;

public class RegistrarRestauranteActivity extends AppCompatActivity {

    private EditText etNuevoRestaurante;
    private Button btnRegistrar;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());


        etNuevoRestaurante = findViewById(R.id.etNuevoRestaurante);
        btnRegistrar = findViewById(R.id.btnRegistrarRestaurante);
        db = AppDatabase.getInstance(this);


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
                    Toast.makeText(this, "Restaurante registrado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}