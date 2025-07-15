package com.benja.restauranteapp.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benja.restauranteapp.ui.ComidaFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {

    private final String nombreRestaurante;

    // ✅ Ahora recibimos el nombre del restaurante en el constructor
    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity, String nombreRestaurante) {
        super(fragmentActivity);
        this.nombreRestaurante = nombreRestaurante;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // ✅ Creamos el fragmento y le pasamos el nombre del restaurante
        if (position == 0) {
            ComidaFragment fragment = new ComidaFragment();

            Bundle args = new Bundle();
            args.putString("nombreRestaurante", nombreRestaurante);
            fragment.setArguments(args);

            return fragment;
        }

        return new ComidaFragment(); // fallback
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
