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

    private String tipo = "comida"; // lo puedes actualizar según pestaña
    private String nombreRestaurante = "Mexican Restaurant"; // actualiza desde argumentos

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comida, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewComida);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaComida = new ArrayList<>();

        // 📌 Carga el nombre del restaurante y el tipo desde los argumentos
        if (getArguments() != null) {
            tipo = getArguments().getString("tipo", "comida");
            nombreRestaurante = getArguments().getString("nombreRestaurante", "");
        }

        // 🍽️ Cargar datos según restaurante y tipo
        if (tipo.equals("comida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Tlacoyos", 35, "Deliciosos tlacoyos rellenos.", R.drawable.placeholder));
                listaComida.add(new Comida("Panza", 200, "Panza guisada con chile rojo.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Papas Fritas", 40, "Papas crujientes servidas con salsa.", R.drawable.placeholder));
                listaComida.add(new Comida("Onion Rings", 45, "Aros de cebolla empanizados.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pizza Margherita", 80, "Pizza con queso, tomate y albahaca.", R.drawable.placeholder));
                listaComida.add(new Comida("Lasagna", 90, "Pasta horneada con carne y queso.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sushi", 100, "Rollo de arroz con pescado.", R.drawable.placeholder));
                listaComida.add(new Comida("Ramen", 110, "Sopa japonesa con fideos.", R.drawable.placeholder));
            }
        } else if (tipo.equals("bebida")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Coca-Cola", 45, "Refresco clásico.", R.drawable.placeholder));
                listaComida.add(new Comida("Cerveza", 80, "Cerveza artesanal mexicana.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Root Beer", 50, "Refresco con sabor único.", R.drawable.placeholder));
                listaComida.add(new Comida("Lemonade", 35, "Limonada fresca con hielo.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Vino Tinto", 120, "Vino italiano tradicional.", R.drawable.placeholder));
                listaComida.add(new Comida("Agua con gas", 25, "Agua con burbujas refrescante.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Sake", 130, "Bebida alcohólica japonesa.", R.drawable.placeholder));
                listaComida.add(new Comida("Té Verde", 30, "Té japonés caliente.", R.drawable.placeholder));
            }
        } else if (tipo.equals("complemento")) {
            if (nombreRestaurante.equals("Mexican Restaurant")) {
                listaComida.add(new Comida("Guacamole", 30, "Dip de aguacate con limón.", R.drawable.placeholder));
                listaComida.add(new Comida("Totopos", 40, "Tortillas crujientes para acompañar.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("USA Restaurant")) {
                listaComida.add(new Comida("Ketchup", 10, "Salsa de tomate.", R.drawable.placeholder));
                listaComida.add(new Comida("Mayonesa", 10, "Salsa cremosa americana.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Italian Restaurant")) {
                listaComida.add(new Comida("Pan de ajo", 25, "Pan horneado con ajo y mantequilla.", R.drawable.placeholder));
                listaComida.add(new Comida("Queso Parmesano", 15, "Rallado para acompañar.", R.drawable.placeholder));
            } else if (nombreRestaurante.equals("Japan Restaurant")) {
                listaComida.add(new Comida("Wasabi", 10, "Pasta picante verde.", R.drawable.placeholder));
                listaComida.add(new Comida("Jengibre", 10, "Láminas de jengibre encurtido.", R.drawable.placeholder));
            }
        }

        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return view;
    }
}