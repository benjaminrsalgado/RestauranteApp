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
                listaComida.add(new Comida("Tlacoyos", 35, "Platillo mexicano", R.drawable.placeholder));
                listaComida.add(new Comida("Panza", 200, "Platillo típico", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Papas Fritas", 40, "Papas crujientes", R.drawable.placeholder));
                listaComida.add(new Comida("Onion Rings", 45, "Aros de cebolla", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pizza Margherita", 80, "Pizza clásica", R.drawable.placeholder));
                listaComida.add(new Comida("Lasagna", 90, "Lasaña de carne", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sushi", 100, "Rollos de sushi", R.drawable.placeholder));
                listaComida.add(new Comida("Ramen", 110, "Sopa japonesa", R.drawable.placeholder));
            }
        } else if (tipo.equals("bebida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Coca-Cola", 45, "Refresco", R.drawable.placeholder));
                listaComida.add(new Comida("Cerveza", 80, "Cerveza artesanal", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Root Beer", 50, "Refresco clásico", R.drawable.placeholder));
                listaComida.add(new Comida("Lemonade", 35, "Limonada fresca", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Vino Tinto", 120, "Vino de la casa", R.drawable.placeholder));
                listaComida.add(new Comida("Agua con gas", 25, "Agua mineral", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sake", 130, "Licor de arroz", R.drawable.placeholder));
                listaComida.add(new Comida("Té Verde", 30, "Té tradicional", R.drawable.placeholder));
            }
        } else if (tipo.equals("complemento")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Guacamole", 30, "Aguacate machacado", R.drawable.placeholder));
                listaComida.add(new Comida("Totopos", 40, "Tortillas fritas", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Ketchup", 10, "Salsa de tomate", R.drawable.placeholder));
                listaComida.add(new Comida("Mayonesa", 10, "Salsa blanca", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pan de ajo", 25, "Pan horneado", R.drawable.placeholder));
                listaComida.add(new Comida("Queso Parmesano", 15, "Queso rallado", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Wasabi", 10, "Picante japonés", R.drawable.placeholder));
                listaComida.add(new Comida("Jengibre", 10, "Laminado y encurtido", R.drawable.placeholder));
            }
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void filtrarPorTexto(String texto) {
        if (adapter != null) {
            adapter.filtrar(texto);
        }
    }
}