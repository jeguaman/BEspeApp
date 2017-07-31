package com.teamj.joseguaman.bespeapp.webService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.GsonRequest;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.RestClientBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jose Guaman on 04/07/2017.
 */

public class BeaconRestClient extends RestClientBase {

    public BeaconRestClient(Context context) {
        super(context);
    }


    public void obtenerTodosBeaconsSinImagen(Response.Listener<WSResponse> listener, Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLBeaconsSinImagen(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);
    }

    public void obtenerTodosBeaconsAsociadosSinImagen(Response.Listener<WSResponse> listener, Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLBeaconsAsociadosSinImagen(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);
    }

    public void obtenerTodosBeaconsAsociadosEstimote(Response.Listener<WSResponse> listener, Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLBeaconsAsociadosEstimote(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);
    }
}
