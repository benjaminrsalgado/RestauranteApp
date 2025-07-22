package com.benja.restauranteapp.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benja.restauranteapp.ui.ComidaFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {

    private final String nombreRestaurante;
    private final ComidaFragment[] fragments = new ComidaFragment[3];

    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity, String nombreRestaurante) {
        super(fragmentActivity);
        this.nombreRestaurante = nombreRestaurante;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String tipo;
        switch (position) {
            case 0:
                tipo = "comida";
                break;
            case 1:
                tipo = "bebida";
                break;
            case 2:
                tipo = "complemento";
                break;
            default:
                tipo = "comida";
        }

        ComidaFragment fragment = new ComidaFragment();
        Bundle args = new Bundle();
        args.putString("nombreRestaurante", nombreRestaurante);
        args.putString("tipo", tipo);
        fragment.setArguments(args);

        fragments[position] = fragment;
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    // ✅ Para filtrar texto en la pestaña actual
    public void filtrarEnFragment(int position, String texto) {
        if (position >= 0 && position < fragments.length && fragments[position] != null) {
            fragments[position].filtrarPorTexto(texto);
        }
    }

    // ✅ Para recargar datos del fragmento actual (al volver del registro de platillo)
    public void recargarFragment(int position) {
        if (position >= 0 && position < fragments.length && fragments[position] != null) {
            fragments[position].recargarDesdeDB();
        }
    }
}