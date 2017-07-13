package com.teamj.joseguaman.bespeapp.modelo.beacon;

import java.io.Serializable;

/**
 * Created by Jose Guaman on 20/06/2017.
 */

public class WSResponse implements Serializable {
    private boolean state;
    private String jsonEntity;

    public WSResponse(boolean state, String jsonEntity) {
        this.state = state;
        this.jsonEntity = jsonEntity;
    }

    public WSResponse() {

    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getJsonEntity() {
        return jsonEntity;
    }

    public void setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
    }

    @Override
    public String toString() {
        return "WSResponse{" +
                "state=" + state +
                ", jsonEntity='" + jsonEntity + '\'' +
                '}';
    }
}
