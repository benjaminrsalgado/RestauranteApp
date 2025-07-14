package com.benja.restauranteapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benja.restauranteapp.R;
import com.benja.restauranteapp.models.Comida;

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
        holder.nombreText.setText(comida.getNombre());
        holder.precioText.setText("$" + comida.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaComida.size();
    }

    public static class ComidaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreText, precioText;

        public ComidaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.nombreComida);
            precioText = itemView.findViewById(R.id.precioComida);
        }
    }
}
