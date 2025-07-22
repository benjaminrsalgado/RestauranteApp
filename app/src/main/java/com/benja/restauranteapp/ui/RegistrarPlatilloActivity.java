package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;

import java.util.concurrent.Executors;

public class RegistrarPlatilloActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etDescripcion;
    private Spinner spinnerTipo;
    private Button btnRegistrar;
    private AppDatabase db;
    private int restaurantId;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_platillo);

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombrePlatillo);
        etPrecio = findViewById(R.id.etPrecioPlatillo);
        etDescripcion = findViewById(R.id.etDescripcionPlatillo);
        spinnerTipo = findViewById(R.id.spinnerTipoPlatillo);
        btnRegistrar = findViewById(R.id.btnRegistrarPlatillo);

        db = AppDatabase.getInstance(this);

        // Obtener datos del intent
        restaurantId = getIntent().getIntExtra("restaurantId", -1);
        restaurantName = getIntent().getStringExtra("restaurantName");

        // Mostrar opciones bonitas en el Spinner
        String[] opcionesVisibles = {"Comida", "Bebidas", "Complementos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesVisibles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String precioStr = etPrecio.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if (nombre.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Asignar valor interno correcto
            String[] valoresInternos = {"comida", "bebida", "complemento"};
            int selectedIndex = spinnerTipo.getSelectedItemPosition();
            String tipo = valoresInternos[selectedIndex];

            Food nuevo = new Food(nombre, precio, descripcion, tipo, restaurantName, restaurantId);

            Executors.newSingleThreadExecutor().execute(() -> {
                db.foodDao().insert(nuevo);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Platillo registrado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}