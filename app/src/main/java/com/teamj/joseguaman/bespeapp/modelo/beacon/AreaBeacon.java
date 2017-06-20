package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class AreaBeacon implements Serializable {
    private int areaBeaconId;
    private Area area;
    private Beacon beacon;
    private boolean estado;

    public AreaBeacon(int areaBeaconId, Area area, Beacon beacon, boolean estado) {
        this.areaBeaconId = areaBeaconId;
        this.area = area;
        this.beacon = beacon;
        this.estado = estado;
    }

    public AreaBeacon() {

    }

    public int getAreaBeaconId() {
        return areaBeaconId;
    }

    public void setAreaBeaconId(int areaBeaconId) {
        this.areaBeaconId = areaBeaconId;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AreaBeacon{" +
                "areaBeaconId=" + areaBeaconId +
                ", area=" + area +
                ", beacon=" + beacon +
                ", estado=" + estado +
                '}';
    }
}
