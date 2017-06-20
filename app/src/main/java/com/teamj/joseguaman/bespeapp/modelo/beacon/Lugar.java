package com.teamj.joseguaman.bespeapp.modelo.beacon;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class Lugar implements Serializable {

    private int lugarId;
    private Area area;
    private String descripcion;
    private Bitmap imagen;
    private String titulo;

    public Lugar() {
    }

    public Lugar(int lugarId, Area area, String descripcion, Bitmap imagen, String titulo) {
        this.lugarId = lugarId;
        this.area = area;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    public int getLugarId() {
        return lugarId;
    }

    public void setLugarId(int lugarId) {
        this.lugarId = lugarId;
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

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "lugarId=" + lugarId +
                ", area=" + area +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + imagen +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
