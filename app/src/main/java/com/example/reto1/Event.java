package com.example.reto1;

public class Event {

    private String nombreEvent;
    private String nombreRestaurant;
    private String horaInicio;
    private String horaFin;

    public Event() {}

    public Event(String nombreEvent, String nombreRestaurant, String horaInicio, String horaFin) {
        this.nombreEvent = nombreEvent;
        this.nombreRestaurant = nombreRestaurant;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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
