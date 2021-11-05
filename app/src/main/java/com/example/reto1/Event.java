package com.example.reto1;

public class Event {

    private String nombreEvent;
    private String horaInicio;
    private String horaFin;

    public Event() {}

    public Event(String nombreEvent, String horaInicio, String horaFin) {
        this.nombreEvent = nombreEvent;
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

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
