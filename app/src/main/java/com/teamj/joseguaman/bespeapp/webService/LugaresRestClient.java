package com.teamj.joseguaman.bespeapp.webService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
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


    public void getCicloProductoresSincroIds(String usuarioId, String equipoId, String perfil, String cicloId,
                                             String listaSincroIdPorComas, Response.Listener<WSResponse> listener,
                                             Response.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        params.put("key", "");
        params.put("usuarioId", usuarioId);
        params.put("equipoId", equipoId);
        params.put("perfil", perfil);
        params.put("cicloId", cicloId);
        params.put("listaSincroIdPorComas", listaSincroIdPorComas);

        GsonRequest<WSResponse> request = new GsonRequest<>(Request.Method.POST, "URL", WSResponse.class, header, params, listener, errorListener);
        executeRequest(request);

    }


}

