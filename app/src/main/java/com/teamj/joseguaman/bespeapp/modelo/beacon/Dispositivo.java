package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.util.Date;

/**
 * Created by Juan on 24/7/2017.
 */

public class Dispositivo {

    private Integer dispositivoId;
    private String imei;
    private String descripción;
    private Date inserted;
    private Date updated;
    private Boolean deleted;

    public Integer getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Integer dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
