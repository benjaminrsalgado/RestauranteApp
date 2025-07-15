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
import com.benja.restauranteapp.models.Comida;

import java.util.ArrayList;
import java.util.List;

public class ComidaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private List<Comida> listaComida;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_comida, container, false);

        recyclerView = vista.findViewById(R.id.recyclerComida);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // ✅ Recuperamos el nombre del restaurante desde el Bundle
        String nombreRestaurante = getArguments() != null ? getArguments().getString("nombreRestaurante") : "";

        // ✅ Creamos una lista diferente para cada restaurante
        listaComida = new ArrayList<>();

        if (nombreRestaurante.equals("Mexican Restaurant")) {
            listaComida.add(new Comida("Tlacoyos", 35));
            listaComida.add(new Comida("Panza", 200));
        } else if (nombreRestaurante.equals("USA Restaurant")) {
            listaComida.add(new Comida("Hamburguesa", 350));
            listaComida.add(new Comida("Papas Fritas", 110));
        } else if (nombreRestaurante.equals("Italian Restaurant")) {
            listaComida.add(new Comida("Pasta Alfredo", 500));
            listaComida.add(new Comida("Gelato", 200));
        } else if (nombreRestaurante.equals("Japan Restaurant")) {
            listaComida.add(new Comida("Sushi", 200));
            listaComida.add(new Comida("Ramen", 470));
        } else {
            // Por si acaso
            listaComida.add(new Comida("Comida genérica", 50.00));
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return vista;
    }
}