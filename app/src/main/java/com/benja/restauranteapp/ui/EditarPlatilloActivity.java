package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;

import java.util.concurrent.Executors;

public class EditarPlatilloActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etDescripcion;
    private Spinner spinnerTipo;
    private Button btnActualizar, btnEliminar;

    private int foodId, restaurantId;
    private String restaurantName;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_platillo);


        Toolbar toolbar = findViewById(R.id.toolbarEditarPlatillo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());


        etNombre = findViewById(R.id.etNombrePlatillo);
        etPrecio = findViewById(R.id.etPrecioPlatillo);
        etDescripcion = findViewById(R.id.etDescripcionPlatillo);
        spinnerTipo = findViewById(R.id.spinnerTipoPlatillo);
        btnActualizar = findViewById(R.id.btnActualizarPlatillo);
        btnEliminar = findViewById(R.id.btnEliminarPlatillo);


        db = AppDatabase.getInstance(this);


        String[] tipos = {"comida", "bebida", "complemento"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);


        foodId = getIntent().getIntExtra("id", -1);
        restaurantId = getIntent().getIntExtra("restaurantId", -1);
        restaurantName = getIntent().getStringExtra("restaurantName");


        if (foodId == -1 || restaurantId == -1 || restaurantName == null) {
            Toast.makeText(this, "Error al cargar el platillo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        etNombre.setText(getIntent().getStringExtra("nombre"));
        etPrecio.setText(String.valueOf(getIntent().getDoubleExtra("precio", 0)));
        etDescripcion.setText(getIntent().getStringExtra("descripcion"));


        String tipo = getIntent().getStringExtra("tipo");
        if (tipo != null) {
            int index = adapter.getPosition(tipo.toLowerCase());
            if (index >= 0) spinnerTipo.setSelection(index);
        }


        btnActualizar.setOnClickListener(v -> {
            String nuevoNombre = etNombre.getText().toString().trim();
            String precioStr = etPrecio.getText().toString().trim();
            String nuevaDescripcion = etDescripcion.getText().toString().trim();
            String nuevoTipo = spinnerTipo.getSelectedItem().toString().toLowerCase();

            if (nuevoNombre.isEmpty() || precioStr.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double nuevoPrecio;
            try {
                nuevoPrecio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            Food actualizado = new Food(foodId, nuevoNombre, nuevoPrecio, nuevaDescripcion, nuevoTipo, restaurantName, restaurantId);

            Executors.newSingleThreadExecutor().execute(() -> {
                db.foodDao().update(actualizado);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Platillo actualizado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });


        btnEliminar.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                db.foodDao().deleteById(foodId);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Platillo eliminado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}