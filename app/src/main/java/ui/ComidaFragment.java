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

    private String tipo = "comida";
    private String nombreRestaurante = "Mexican Restaurant";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comida, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewComida);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaComida = new ArrayList<>();


        if (getArguments() != null) {
            tipo = getArguments().getString("tipo", "comida");
            nombreRestaurante = getArguments().getString("nombreRestaurante", "");
        }


        if (tipo.equals("comida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Tlacoyos", 35, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Panza", 200, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Papas Fritas", 40, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Onion Rings", 45, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pizza Margherita", 80, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Lasagna", 90, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sushi", 100, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Ramen", 110, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            }
        } else if (tipo.equals("bebida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Coca-Cola", 45, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Cerveza", 80, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Root Beer", 50, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Lemonade", 35, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Vino Tinto", 120, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Agua con gas", 25, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sake", 130, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Té Verde", 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            }
        } else if (tipo.equals("complemento")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Guacamole", 30, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Totopos", 40, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Ketchup", 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Mayonesa", 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pan de ajo", 25, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Queso Parmesano", 15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Wasabi", 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
                listaComida.add(new Comida("Jengibre", 10, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.", R.drawable.placeholder));
            }
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return view;
    }
}