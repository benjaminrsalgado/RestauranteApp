package com.benja.restauranteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.adapters.MenuPagerAdapter;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.concurrent.Executors;

public class RestaurantMenuActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MenuPagerAdapter pagerAdapter;
    private String nombreRestaurante;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        // Obtener el nombre del restaurante desde el intent
        nombreRestaurante = getIntent().getStringExtra("nombreRestaurante");

        // Configurar la Toolbar con flecha de regreso
        Toolbar toolbar = findViewById(R.id.toolbarRestaurante);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(nombreRestaurante);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Buscar restaurantId en la base de datos
        Executors.newSingleThreadExecutor().execute(() -> {
            Restaurant r = AppDatabase.getInstance(this).restaurantDao().getByName(nombreRestaurante);
            if (r != null) {
                restaurantId = r.id;
            }
        });

        // Configurar tabs y ViewPager2
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new MenuPagerAdapter(this, nombreRestaurante);
        viewPager.setAdapter(pagerAdapter);

        // Asignar títulos a las pestañas
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

        // Agregar botón flotante para registrar platillo
        FloatingActionButton fab = findViewById(R.id.fabAgregarPlatillo);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistrarPlatilloActivity.class);
            intent.putExtra("restaurantId", restaurantId);
            intent.putExtra("restaurantName", nombreRestaurante);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Buscar platillo...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarTexto(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarTexto(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Maneja la flecha de regreso en la Toolbar
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Aplica filtro al fragmento actual
    private void filtrarTexto(String texto) {
        int posicion = viewPager.getCurrentItem();
        pagerAdapter.filtrarEnFragment(posicion, texto);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}