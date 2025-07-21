package com.benja.restauranteapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView nombreTextView, precioTextView, descripcionTextView;
    private ImageView imagenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // 🟩 Toolbar con flecha de regreso
        Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // 🟩 Referencias de UI
        nombreTextView = findViewById(R.id.nombreComida);
        precioTextView = findViewById(R.id.precioComida);
        descripcionTextView = findViewById(R.id.descripcionComida);
        imagenView = findViewById(R.id.imagenComida);

        // 🟩 Obtener extras del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre", "Sin nombre");
            double precio = extras.getDouble("precio", 0);
            String descripcion = extras.getString("descripcion", "Sin descripción");
            int imagenResId = extras.getInt("imagenResId", R.drawable.placeholder);

            nombreTextView.setText(nombre);
            precioTextView.setText(String.format("$%.2f", precio));
            descripcionTextView.setText(descripcion);
            imagenView.setImageResource(imagenResId);
        }
    }
}