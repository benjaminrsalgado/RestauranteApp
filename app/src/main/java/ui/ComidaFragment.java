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
                listaComida.add(new Comida(
                        "Tlacoyos",
                        35,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.",
                        R.drawable.placeholder
                ));
                listaComida.add(new Comida(
                        "Panza",
                        200,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.",
                        R.drawable.placeholder
                ));
            }
            // 👉 Repite esto para los otros restaurantes
        } else if (tipo.equals("bebida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Coca-Cola", 45, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.",R.drawable.placeholder));
                listaComida.add(new Comida("Cerveza", 80, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.", R.drawable.placeholder));
            }
            // Y así con los demás...
        } else if (tipo.equals("complemento")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Guacamole", 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.", R.drawable.placeholder));
                listaComida.add(new Comida("Totopos", 40, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.", R.drawable.placeholder));
            }
            // Y así con los demás...
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return vista;
    }
}