package com.benja.restauranteapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.Food;
import com.benja.restauranteapp.ItemDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder> {

    private List<Food> listaComida;
    private List<Food> listaCompleta;

    public ComidaAdapter(List<Food> listaComida) {
        this.listaComida = listaComida;
        this.listaCompleta = new ArrayList<>(listaComida);
    }

    @NonNull
    @Override
    public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comida, parent, false);
        return new ComidaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {
        Food comida = listaComida.get(position);
        holder.txtNombreComida.setText(comida.getName());
        holder.txtPrecioComida.setText("$" + comida.getPrice());

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("nombre", comida.getName());
            intent.putExtra("precio", comida.getPrice());
            intent.putExtra("descripcion", comida.getDescription());
            intent.putExtra("imagenResId", R.drawable.placeholder); // usa imagen por defecto
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaComida.size();
    }

    public static class ComidaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreComida, txtPrecioComida;

        public ComidaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreComida = itemView.findViewById(R.id.txtNombreComida);
            txtPrecioComida = itemView.findViewById(R.id.txtPrecioComida);
        }
    }

    public void filtrar(String texto) {
        texto = texto.toLowerCase();
        listaComida.clear();

        if (texto.isEmpty()) {
            listaComida.addAll(listaCompleta);
        } else {
            for (Food comida : listaCompleta) {
                if (comida.getName().toLowerCase().contains(texto)) {
                    listaComida.add(comida);
                }
            }
        }

        notifyDataSetChanged();
    }
}