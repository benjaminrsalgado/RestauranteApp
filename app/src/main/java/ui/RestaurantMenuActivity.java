package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.adapters.MenuPagerAdapter;
import com.benja.restauranteapp.ui.ComidaFragment;
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

    // ✅ Inflamos menú con SearchView
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

    // ✅ Maneja clics del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // ← cerrar esta pantalla (regresar)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ✅ Método para filtrar desde el fragmento visible
    private void filtrarTexto(String texto) {
        int posicion = viewPager.getCurrentItem();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + posicion);
        if (fragment instanceof ComidaFragment) {
            ((ComidaFragment) fragment).filtrarPorTexto(texto);
        }
    }

    // ✅ Asegura que la flecha de regreso funcione
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}