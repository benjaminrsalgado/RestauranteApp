package com.benja.restauranteapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.adapters.MenuPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RestaurantMenuActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MenuPagerAdapter pagerAdapter;
    private TextView restaurantTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        // ✅ Recuperamos el nombre del restaurante que se pasó desde RestaurantListActivity
        String nombreRestaurante = getIntent().getStringExtra("nombreRestaurante");

        // Enlazamos vistas
        restaurantTitle = findViewById(R.id.restaurantTitle);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // ✅ Mostramos el nombre del restaurante en pantalla
        restaurantTitle.setText(nombreRestaurante);

        // ✅ Pasamos el nombre del restaurante al adaptador de tabs
        pagerAdapter = new MenuPagerAdapter(this, nombreRestaurante);
        viewPager.setAdapter(pagerAdapter);

        // Conectamos los tabs con el ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Comida");
                            break;
                        // Puedes agregar más tabs después si quieres
                    }
                }).attach();
    }
}