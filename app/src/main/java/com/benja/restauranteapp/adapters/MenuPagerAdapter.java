package com.benja.restauranteapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benja.restauranteapp.ui.ComidaFragment;

public class MenuPagerAdapter extends FragmentStateAdapter {

    public MenuPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Por ahora solo tenemos un fragmento
        switch (position) {
            case 0:
                return new ComidaFragment();
            default:
                return new ComidaFragment(); // fallback
        }
    }

    @Override
    public int getItemCount() {
        return 1; // Cambia este número si agregas más tabs
    }
}
