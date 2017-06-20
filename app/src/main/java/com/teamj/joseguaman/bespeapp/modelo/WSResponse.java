package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class WSResponse implements Serializable{
    private boolean state;
    private Object objectResponse;

    public WSResponse(boolean state, Object objectResponse) {
        this.state = state;
        this.objectResponse = objectResponse;
    }

    public WSResponse() {

    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }

    @Override
    public String toString() {
        return "WSResponse{" +
                "state=" + state +
                ", objectResponse=" + objectResponse +
                '}';
    }
}
