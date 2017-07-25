package com.teamj.joseguaman.bespeapp.estimote;

/**
 * Created by Juan on 24/7/2017.
 */

public class InformacionBeacon {
    private Integer idArea;
    private Integer idBeacon;

    public InformacionBeacon() {
    }

    public InformacionBeacon(Integer idArea, Integer idBeacon) {
        this.idArea = idArea;
        this.idBeacon = idBeacon;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getIdBeacon() {
        return idBeacon;
    }

    public void setIdBeacon(Integer idBeacon) {
        this.idBeacon = idBeacon;
    }
}
