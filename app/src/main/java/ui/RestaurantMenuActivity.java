package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.adapters.MenuPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RestaurantMenuActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MenuPagerAdapter pagerAdapter;
    private String nombreRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        // ✅ Obtenemos el nombre del restaurante
        nombreRestaurante = getIntent().getStringExtra("nombreRestaurante");

        // ✅ Configuramos Toolbar como ActionBar
        Toolbar toolbar = findViewById(R.id.toolbarRestaurante);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(nombreRestaurante);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // ← muestra la flecha
        }

        // ✅ Conectamos vistas
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // ✅ Creamos adaptador con nombre del restaurante
        pagerAdapter = new MenuPagerAdapter(this, nombreRestaurante);
        viewPager.setAdapter(pagerAdapter);

        // ✅ Configuramos pestañas
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Comida");
                    break;
                case 1:
                    tab.setText("Bebidas");
                    break;
                case 2:
                    tab.setText("Complementos");
                    break;
            }
        }).attach();
    }

    // ✅ Inflamos menú (donde estará el ícono de búsqueda)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
        return true;
    }

    // ✅ Acciones al hacer clic en ítems del menú (ej: la lupa)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // ← cerrar esta pantalla (regresar)
            return true;
        } else if (item.getItemId() == R.id.action_search) {
            // Aquí luego puedes abrir un cuadro de búsqueda o un nuevo fragment
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ✅ Asegura que la flecha de regreso funcione siempre
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // ← equivalente a presionar el botón de regresar
        return true;
    }
}