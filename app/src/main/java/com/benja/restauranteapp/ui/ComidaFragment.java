package com.benja.restauranteapp.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.adapters.ComidaAdapter;
import com.benja.restauranteapp.db.AppDatabase;
import com.benja.restauranteapp.db.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ComidaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private List<Food> listaComida = new ArrayList<>();

    private String tipo = "comida";
    private String nombreRestaurante = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comida, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewComida);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            tipo = getArguments().getString("tipo", "comida");
            nombreRestaurante = getArguments().getString("nombreRestaurante", "");
        }

        cargarComidaDesdeDB();

        return view;
    }

    private void cargarComidaDesdeDB() {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(requireContext());
            List<Food> resultado = db.foodDao().getByType(tipo);  // ← usamos tu FoodDao

            requireActivity().runOnUiThread(() -> {
                listaComida.clear();
                listaComida.addAll(resultado);
                adapter.notifyDataSetChanged();
            });
        });
    }

    public void filtrarPorTexto(String texto) {
        if (adapter != null) {
            adapter.filtrar(texto);
        }
    }
}