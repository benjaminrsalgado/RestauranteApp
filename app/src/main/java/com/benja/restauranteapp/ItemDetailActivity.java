package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.benja.restauranteapp.R;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView nombreTextView, precioTextView, descripcionTextView;
    private ImageView imagenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail); // Este layout lo vamos a crear en el paso 2

        // Conectamos las vistas
        nombreTextView = findViewById(R.id.nombreComida);
        precioTextView = findViewById(R.id.precioComida);
        descripcionTextView = findViewById(R.id.descripcionComida);
        imagenView = findViewById(R.id.imagenComida);

        // Recibimos los datos del Intent
        String nombre = getIntent().getStringExtra("nombre");
        double precio = getIntent().getDoubleExtra("precio", 0);
        String descripcion = getIntent().getStringExtra("descripcion");
        int imagenResId = getIntent().getIntExtra("imagenResId", R.drawable.placeholder); // imagen por defecto

        // Mostramos los datos
        nombreTextView.setText(nombre);
        precioTextView.setText("$" + precio);
        descripcionTextView.setText(descripcion);
        imagenView.setImageResource(imagenResId);
    }
}
