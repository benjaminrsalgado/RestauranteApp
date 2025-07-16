package com.benja.restauranteapp.models;


public class Comida {
    private String nombre;
    private double precio;
    private String descripcion;
    private int imagenResId;

    public Comida(String nombre, double precio, String descripcion, int imagenResId) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
