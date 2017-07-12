package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class WSResponse implements Serializable {
    private boolean state;
    private Object entity;

    public WSResponse(boolean state, Object entity) {
        this.state = state;
        this.entity = entity;
    }

    public WSResponse() {

    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "WSResponse{" +
                "state=" + state +
                ", entity=" + entity +
                '}';
    }
}
