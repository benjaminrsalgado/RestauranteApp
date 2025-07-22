package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.benja.restauranteapp.R;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView nombreTextView, precioTextView, descripcionTextView, tipoTextView;
    private ImageView imagenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // 🟦 Configurar toolbar con botón de regreso
        Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // 🟦 Referencias UI
        nombreTextView      = findViewById(R.id.nombreComida);
        precioTextView      = findViewById(R.id.precioComida);
        descripcionTextView = findViewById(R.id.descripcionComida);
        tipoTextView        = findViewById(R.id.tipoComida);
        imagenView          = findViewById(R.id.imagenComida);

        // 🟦 Obtener datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre      = extras.getString("nombre", "Sin nombre");
            double precio      = extras.getDouble("precio", 0);
            String descripcion = extras.getString("descripcion", "Sin descripción");
            String tipo        = extras.getString("tipo", "Sin tipo");
            int imagenResId    = extras.getInt("imagenResId", R.drawable.placeholder);

            // 🟦 Mostrar en pantalla
            nombreTextView.setText(nombre);
            precioTextView.setText(String.format("$%.2f", precio));
            descripcionTextView.setText(descripcion);
            tipoTextView.setText("Tipo: " + tipo);
            imagenView.setImageResource(imagenResId);
        }
    }
}