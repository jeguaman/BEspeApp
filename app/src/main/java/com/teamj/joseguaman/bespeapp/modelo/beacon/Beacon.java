package com.teamj.joseguaman.bespeapp.modelo.beacon;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class Beacon implements Serializable {
    private int beaconId;
    private String uuid;
    private String major;
    private String minor;
    private String descripcion;
    private Bitmap imagen;

    public Beacon() {
    }

    public Beacon(int beaconId, String uuid, String major, String minor, String descripcion, Bitmap imagen) {
        this.beaconId = beaconId;
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(int beaconId) {
        this.beaconId = beaconId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
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

    @Override
    public String toString() {
        return "Beacon{" +
                "beaconId=" + beaconId +
                ", uuid='" + uuid + '\'' +
                ", major='" + major + '\'' +
                ", minor='" + minor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
