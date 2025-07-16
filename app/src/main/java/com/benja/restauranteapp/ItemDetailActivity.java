package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benja.restauranteapp.R;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView nombreTextView, precioTextView, descripcionTextView;
    private ImageView imagenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);


        Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> finish());


        nombreTextView = findViewById(R.id.nombreComida);
        precioTextView = findViewById(R.id.precioComida);
        descripcionTextView = findViewById(R.id.descripcionComida);
        imagenView = findViewById(R.id.imagenComida);


        String nombre = getIntent().getStringExtra("nombre");
        double precio = getIntent().getDoubleExtra("precio", 0);
        String descripcion = getIntent().getStringExtra("descripcion");
        int imagenResId = getIntent().getIntExtra("imagenResId", R.drawable.placeholder);


        nombreTextView.setText(nombre);
        precioTextView.setText("$" + precio);
        descripcionTextView.setText(descripcion);
        imagenView.setImageResource(imagenResId);
    }
}
