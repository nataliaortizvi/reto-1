package com.example.reto1;

public class Restaurante {

    private String nombreRestaurante;
    private String descripRestaurante;

    public Restaurante() {
    }

    public Restaurante(String nombreRestaurante, String descripRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
        this.descripRestaurante = descripRestaurante;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getDescripRestaurante() {
        return descripRestaurante;
    }

    public void setDescripRestaurante(String descripRestaurante) {
        this.descripRestaurante = descripRestaurante;
    }
}
