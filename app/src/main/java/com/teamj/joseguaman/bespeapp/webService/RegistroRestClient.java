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
 * Created by Jose Guaman on 21/07/2017.
 */

public class RegistroRestClient extends RestClientBase {

    public RegistroRestClient(Context mContext) {
        super(mContext);
    }

    /**
     * @param idArea        id en el que se encuentra el dispositivo
     * @param imei          identificador unico del dispositivo movil
     * @param tipo          valor que identifica si es de entrada o salida de la área.
     * @param listener
     * @param errorListener
     */
    public void registroAreaDispositivo(String idArea, String imei, String tipo, Response.Listener<WSResponse> listener,
                                        Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("id_area", idArea);
        params.put("imei", imei);
        params.put("tipo", tipo);

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLRegistroAreaDispositivo(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);

    }
}
