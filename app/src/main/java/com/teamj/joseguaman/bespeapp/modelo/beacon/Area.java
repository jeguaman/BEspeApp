package com.teamj.joseguaman.bespeapp.modelo.beacon;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class Area implements Serializable {

    private int areaId;
    private String titulo;
    private String descripcion;
    private byte[] imagen;

    public Area() {
    }

    public Area(int areaId, String titulo, String descripcion, byte[] imagen) {
        this.areaId = areaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
