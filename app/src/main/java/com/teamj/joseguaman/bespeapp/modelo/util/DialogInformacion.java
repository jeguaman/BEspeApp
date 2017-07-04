package com.teamj.joseguaman.bespeapp.modelo.util;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 03/07/2017.
 */

public class DialogInformacion implements Serializable {

    private String titulo;
    private int image;
    private String mensaje;

    public DialogInformacion() {
    }

    public DialogInformacion(String titulo, int image, String mensaje) {
        this.titulo = titulo;
        this.image = image;
        this.mensaje = mensaje;
    }

    public String getTitulo() {

        return titulo;
    }

    public int getImage() {
        return image;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImage(int image) {
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
