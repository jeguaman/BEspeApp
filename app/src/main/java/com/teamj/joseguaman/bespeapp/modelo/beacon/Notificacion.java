package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class Notificacion implements Serializable {

    private Area area;
    private String descripcion;
    private String tipo;

    public Notificacion() {
    }

    public Notificacion(Area area, String descripcion, String tipo) {
        this.area = area;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "area=" + area +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
