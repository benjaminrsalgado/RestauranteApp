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

        // 🔍 Obtenemos el nombre del restaurante y el tipo (comida, bebida, complemento)
        String nombreRestaurante = getArguments() != null ? getArguments().getString("nombreRestaurante") : "";
        String tipo = getArguments() != null ? getArguments().getString("tipo") : "comida";

        listaComida = new ArrayList<>();

        // 💡 Cargamos según tipo y restaurante
        if (tipo.equals("comida")) {
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
            }
        } else if (tipo.equals("bebida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Coca-Cola", 45));
                listaComida.add(new Comida("Cerveza", 80));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Diet-Coke", 50));
                listaComida.add(new Comida("Malteada", 90));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Vino Tinto", 100));
                listaComida.add(new Comida("Vino blanco", 90));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Te Matcha", 44));
                listaComida.add(new Comida("Coca-Cola", 40));
            }
        } else if (tipo.equals("complemento")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Guacamole", 30));
                listaComida.add(new Comida("Totopos", 40));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Papas Extra", 80));
                listaComida.add(new Comida("Queso", 20));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pasta", 60));
                listaComida.add(new Comida("Queso parmesano", 40));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Wasabi", 40));
                listaComida.add(new Comida("Jengibre", 30));
            }
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return vista;
    }
}