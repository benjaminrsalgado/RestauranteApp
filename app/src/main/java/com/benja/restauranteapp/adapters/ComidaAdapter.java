package com.benja.restauranteapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.db.Food;
import com.benja.restauranteapp.ui.ItemDetailActivity;
import com.benja.restauranteapp.ui.EditarPlatilloActivity;

import java.util.ArrayList;
import java.util.List;

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder> {

    private final List<Food> listaComida;
    private final List<Food> listaCompleta;

    public ComidaAdapter(List<Food> listaComida) {
        this.listaComida   = listaComida;
        this.listaCompleta = new ArrayList<>(listaComida);
    }

    static class ComidaViewHolder extends RecyclerView.ViewHolder {
        final TextView txtNombreComida;
        final TextView txtPrecioComida;

        ComidaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreComida = itemView.findViewById(R.id.txtNombreComida);
            txtPrecioComida = itemView.findViewById(R.id.txtPrecioComida);
        }
    }

    @NonNull
    @Override
    public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comida, parent, false);
        return new ComidaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {
        Food comida = listaComida.get(position);

        holder.txtNombreComida.setText(comida.getName());
        holder.txtPrecioComida.setText(String.format("$ %.2f", comida.getPrice()));


        holder.itemView.setOnClickListener(v -> {
            Context ctx = v.getContext();
            Intent intent = new Intent(ctx, ItemDetailActivity.class);
            intent.putExtra("nombre",       comida.getName());
            intent.putExtra("precio",       comida.getPrice());
            intent.putExtra("descripcion",  comida.getDescription());
            intent.putExtra("tipo",         comida.getType());
            intent.putExtra("imagenResId",  R.drawable.placeholder);
            ctx.startActivity(intent);
        });


        holder.itemView.setOnLongClickListener(v -> {
            Context ctx = v.getContext();

            try {
                Intent intent = new Intent(ctx, EditarPlatilloActivity.class);
                intent.putExtra("id",             comida.getId());
                intent.putExtra("nombre",         comida.getName());
                intent.putExtra("precio",         comida.getPrice());
                intent.putExtra("descripcion",    comida.getDescription());
                intent.putExtra("tipo",           comida.getType());
                intent.putExtra("restaurantId",   comida.getRestaurantId());
                intent.putExtra("restaurantName", comida.getRestaurantName());

                ctx.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(ctx, "Error al abrir el editor: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaComida.size();
    }

    public void filtrar(String texto) {
        texto = texto.toLowerCase();
        listaComida.clear();

        if (texto.isEmpty()) {
            listaComida.addAll(listaCompleta);
        } else {
            for (Food f : listaCompleta) {
                if (f.getName().toLowerCase().contains(texto)) {
                    listaComida.add(f);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void actualizarLista(List<Food> nuevaLista) {
        listaComida.clear();
        listaComida.addAll(nuevaLista);

        listaCompleta.clear();
        listaCompleta.addAll(nuevaLista);

        notifyDataSetChanged();
    }
}