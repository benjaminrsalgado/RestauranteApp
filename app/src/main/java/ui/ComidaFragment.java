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
        // Inflamos el layout del fragmento
        View vista = inflater.inflate(R.layout.fragment_comida, container, false);

        // Referencia al RecyclerView
        recyclerView = vista.findViewById(R.id.recyclerComida);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Datos de prueba
        listaComida = new ArrayList<>();
        listaComida.add(new Comida("Tacos al Pastor", 35.00));
        listaComida.add(new Comida("Enchiladas Verdes", 45.00));
        listaComida.add(new Comida("Pozole", 60.00));
        listaComida.add(new Comida("Quesadillas", 25.00));

        // Adapter
        adapter = new ComidaAdapter(listaComida);
        recyclerView.setAdapter(adapter);

        return vista;
    }
}