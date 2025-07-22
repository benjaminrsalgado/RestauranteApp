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

public class EditarRestauranteActivity extends AppCompatActivity {

    private EditText etEditarNombre;
    private Button btnActualizar, btnEliminar;
    private AppDatabase db;
    private int restaurantId;
    private String oldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_restaurante);

        db = AppDatabase.getInstance(this);

        // Toolbar con botón de regreso
        Toolbar toolbar = findViewById(R.id.toolbarEditar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Referencias UI
        etEditarNombre = findViewById(R.id.etEditarNombre);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        // Recibir datos del intent
        restaurantId = getIntent().getIntExtra("restaurantId", -1);
        oldName = getIntent().getStringExtra("restaurantName");
        etEditarNombre.setText(oldName);

        // Botón actualizar
        btnActualizar.setOnClickListener(v -> {
            String nuevoNombre = etEditarNombre.getText().toString().trim();
            if (nuevoNombre.isEmpty()) {
                Toast.makeText(this, "Escribe un nuevo nombre", Toast.LENGTH_SHORT).show();
                return;
            }

            Executors.newSingleThreadExecutor().execute(() -> {
                Restaurant r = new Restaurant();
                r.id = restaurantId;
                r.name = nuevoNombre;
                db.restaurantDao().update(r);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Restaurante actualizado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });

        // Botón eliminar
        btnEliminar.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                Restaurant r = new Restaurant();
                r.id = restaurantId;
                r.name = oldName;
                db.restaurantDao().delete(r);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Restaurante eliminado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}