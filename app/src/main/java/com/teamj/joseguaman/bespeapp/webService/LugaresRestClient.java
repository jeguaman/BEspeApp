package com.teamj.joseguaman.bespeapp.webService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.GsonRequest;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.RestClientBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jose Guaman on 04/07/2017.
 */

public class LugaresRestClient extends RestClientBase {

    public LugaresRestClient(Context context) {
        super(context);
    }


    public void getLugaresPorArea(String idArea, Response.Listener<WSResponse> listener,
                                  Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("id_area", idArea);

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLLugaresPorIdAreaNoImagenesBytes(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);

    }

    public void getImagenPorIdLugar(String idLugar, Response.Listener<WSResponse> listener,
                                    Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("id_lugar", idLugar);

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLImagenLugar(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);
    }

    public void getIconoPorIdLugar(String idLugar, Response.Listener<WSResponse> listener,
                                   Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("id_lugar", idLugar);

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, Constants.getURLIconoLugar(), WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);
    }

}

