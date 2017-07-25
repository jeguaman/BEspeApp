package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 21/07/2017.
 */

public class Registro implements Serializable {

    private String imei;
    private String descripcion;
    private Area areaId;
    private Dispositivo dispositivoId;

    public Registro() {
    }

    public Registro(int dispositivoId, String imei, String descripcion) {
        this.imei = imei;
        this.descripcion = descripcion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Dispositivo getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Dispositivo dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "dispositivoId=" + dispositivoId +
                ", imei='" + imei + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
