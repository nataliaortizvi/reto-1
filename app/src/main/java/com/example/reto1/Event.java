package com.example.reto1;

import android.graphics.Bitmap;
import android.view.View;

public class Event {

    private String nombreEvent;
    private String nombreRestaurant;
    private String horaInicio;
    private String horaFin;
    private String direction;
    private Bitmap imageUrl;

    public Event() {}

    public Event(String nombreEvent, String nombreRestaurant, String horaInicio, String horaFin, Bitmap imageUrl) {
        this.nombreEvent = nombreEvent;
        this.nombreRestaurant = nombreRestaurant;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.imageUrl = imageUrl;
    }

    public Bitmap getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Bitmap imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNombreEvent() {
        return nombreEvent;
    }

    public void setNombreEvent(String nombreEvent) {
        this.nombreEvent = nombreEvent;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getNombreRestaurant() {
        return nombreRestaurant;
    }

    public void setNombreRestaurant(String nombreRestaurant) {
        this.nombreRestaurant = nombreRestaurant;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
