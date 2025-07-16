package com.benja.restauranteapp.adapters;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benja.restauranteapp.ui.ComidaFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {

    private final String nombreRestaurante;
    private final Fragment[] fragments = new Fragment[3];

    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity, String nombreRestaurante) {
        super(fragmentActivity);
        this.nombreRestaurante = nombreRestaurante;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ComidaFragment fragment = new ComidaFragment();

        Bundle args = new Bundle();
        args.putString("nombreRestaurante", nombreRestaurante);

        switch (position) {
            case 0:
                args.putString("tipo", "comida");
                break;
            case 1:
                args.putString("tipo", "bebida");
                break;
            case 2:
                args.putString("tipo", "complemento");
                break;
        }

        fragment.setArguments(args);
        fragments[position] = fragment;
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    public void filtrarEnFragment(int position, String texto) {
        Fragment fragment = fragments[position];
        if (fragment instanceof ComidaFragment) {
            ((ComidaFragment) fragment).filtrarPorTexto(texto);
        }
    }
    }