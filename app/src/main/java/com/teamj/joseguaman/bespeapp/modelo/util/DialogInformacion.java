package com.teamj.joseguaman.bespeapp.modelo.util;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 03/07/2017.
 */

public class DialogInformacion implements Serializable {

    private String titulo;
    private Bitmap image;
    private String mensaje;

    public DialogInformacion() {
    }

    public DialogInformacion(String titulo, Bitmap image, String mensaje) {
        this.titulo = titulo;
        this.image = image;
        this.mensaje = mensaje;
    }

    public String getTitulo() {

        return titulo;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "DialogInformacion{" +
                "titulo='" + titulo + '\'' +
                ", image=" + image +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
