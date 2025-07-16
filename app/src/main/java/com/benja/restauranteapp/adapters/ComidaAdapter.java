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
import com.benja.restauranteapp.models.Comida;
import com.benja.restauranteapp.ui.ItemDetailActivity; // 👈 Asegúrate de importar esto

import java.util.List;

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder> {

    private List<Comida> listaComida;

    public ComidaAdapter(List<Comida> listaComida) {
        this.listaComida = listaComida;
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
        Comida comida = listaComida.get(position);
        holder.txtNombreComida.setText(comida.getNombre());
        holder.txtPrecioComida.setText("$" + comida.getPrecio());

        // 🎯 Abrir detalle al tocar el platillo
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);

            intent.putExtra("nombre", comida.getNombre());
            intent.putExtra("precio", comida.getPrecio());
            intent.putExtra("descripcion", comida.getDescripcion());
            intent.putExtra("imagenResId", comida.getImagenResId());

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
}
