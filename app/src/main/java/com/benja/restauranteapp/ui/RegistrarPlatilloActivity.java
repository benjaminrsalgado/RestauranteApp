package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;

import java.util.concurrent.Executors;

public class RegistrarPlatilloActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etDescripcion, etTipo;
    private Button btnRegistrar;
    private AppDatabase db;
    private int restaurantId;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_platillo);

        etNombre     = findViewById(R.id.etNombrePlatillo);
        etPrecio     = findViewById(R.id.etPrecioPlatillo);
        etDescripcion = findViewById(R.id.etDescripcionPlatillo);
        etTipo       = findViewById(R.id.etTipoPlatillo);
        btnRegistrar = findViewById(R.id.btnRegistrarPlatillo);
        db = AppDatabase.getInstance(this);

        // Obtener datos del intent
        restaurantId   = getIntent().getIntExtra("restaurantId", -1);
        restaurantName = getIntent().getStringExtra("restaurantName");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String precioStr = etPrecio.getText().toString().trim();
                String descripcion = etDescripcion.getText().toString().trim();
                String tipo = etTipo.getText().toString().trim();

                if (nombre.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty() || tipo.isEmpty()) {
                    Toast.makeText(RegistrarPlatilloActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                double precio;
                try {
                    precio = Double.parseDouble(precioStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(RegistrarPlatilloActivity.this, "Precio inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                Food nuevo = new Food(nombre, precio, descripcion, tipo, restaurantName, restaurantId);

                Executors.newSingleThreadExecutor().execute(() -> {
                    db.foodDao().insert(nuevo);
                    runOnUiThread(() -> {
                        Toast.makeText(RegistrarPlatilloActivity.this, "Platillo registrado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                });
            }
        });
    }
}