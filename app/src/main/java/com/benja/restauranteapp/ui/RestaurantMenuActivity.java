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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantMenuActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MenuPagerAdapter pagerAdapter;
    private String nombreRestaurante;
    private int restaurantId = -1;
    private FloatingActionButton fab;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        // 🔹 Recuperar nombre del intent
        nombreRestaurante = getIntent().getStringExtra("nombreRestaurante");

        // 🔹 Toolbar con flechita
        Toolbar toolbar = findViewById(R.id.toolbarRestaurante);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(nombreRestaurante);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 🔹 Inicializar views
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab = findViewById(R.id.fabAgregarPlatillo);
        fab.setEnabled(false); // Desactivar botón hasta tener el ID

        // 🔹 Adapter de las pestañas
        pagerAdapter = new MenuPagerAdapter(this, nombreRestaurante);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0: tab.setText("Comida"); break;
                case 1: tab.setText("Bebidas"); break;
                case 2: tab.setText("Complementos"); break;
            }
        }).attach();

        // 🔹 Obtener restaurantId y configurar botón flotante
        executor.execute(() -> {
            Restaurant r = AppDatabase.getInstance(this).restaurantDao().getByName(nombreRestaurante);
            if (r != null) {
                restaurantId = r.id;

                // ✅ Configurar botón flotante ya que se obtuvo el ID
                runOnUiThread(() -> {
                    fab.setEnabled(true);
                    fab.setOnClickListener(v -> {
                        Intent intent = new Intent(this, RegistrarPlatilloActivity.class);
                        intent.putExtra("restaurantId", restaurantId);
                        intent.putExtra("restaurantName", nombreRestaurante);
                        startActivity(intent);
                    });
                });
            }
        });
    }

    // 🔁 Al volver de otra pantalla, recargar la pestaña actual
    @Override
    protected void onResume() {
        super.onResume();
        if (pagerAdapter != null && viewPager != null) {
            int pos = viewPager.getCurrentItem();
            pagerAdapter.recargarFragment(pos);
        }
    }

    // 🔍 Menú de búsqueda
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_busqueda, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Buscar platillo...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                filtrarTexto(query);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                filtrarTexto(newText);
                return true;
            }
        });

        return true;
    }

    // 🔎 Filtrar platillos según pestaña actual
    private void filtrarTexto(String texto) {
        int posicion = viewPager.getCurrentItem();
        pagerAdapter.filtrarEnFragment(posicion, texto);
    }

    // 🔙 Flechita de regreso
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 🔙 Navegación hacia atrás
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}