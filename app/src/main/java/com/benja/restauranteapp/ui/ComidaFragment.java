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
import com.benja.restauranteapp.db.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ComidaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private final List<Food> listaComida = new ArrayList<>();

    private String tipo = "comida";
    private String nombreRestaurante = "";

    public ComidaFragment() {

    }

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

    @Override
    public void onResume() {
        super.onResume();
        cargarComidaDesdeDB();
    }

    private void cargarComidaDesdeDB() {
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(requireContext());

            Restaurant restaurante = db.restaurantDao().getByName(nombreRestaurante);
            if (restaurante == null) return;

            List<Food> resultado = db.foodDao().getByTypeAndRestaurant(tipo, restaurante.id);

            requireActivity().runOnUiThread(() -> {
                adapter.actualizarLista(resultado);
            });
        });
    }


    public void recargarDesdeDB() {
        cargarComidaDesdeDB();
    }

    public void filtrarPorTexto(String texto) {
        if (adapter != null) {
            adapter.filtrar(texto);
        }
    }
}