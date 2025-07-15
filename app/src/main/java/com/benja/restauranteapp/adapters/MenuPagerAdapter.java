package com.benja.restauranteapp.adapters;

import android.os.Bundle; // ✅ FALTABA ESTE IMPORT

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benja.restauranteapp.ui.ComidaFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {

    private final String nombreRestaurante;

    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity, String nombreRestaurante) {
        super(fragmentActivity);
        this.nombreRestaurante = nombreRestaurante;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ComidaFragment fragment = new ComidaFragment();

        Bundle args = new Bundle(); // ✅ Ya no marcará error
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
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3; // comida, bebida, complemento
    }
}
