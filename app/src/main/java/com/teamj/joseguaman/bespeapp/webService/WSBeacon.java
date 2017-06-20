package com.teamj.joseguaman.bespeapp.webService;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.teamj.joseguaman.bespeapp.constantes.Constant;

import java.io.IOException;

/**
 * Created by Jose Guaman on 19/06/2017.
 */

public class WSBeacon {

    public static String registroAreaEnDispositivo(String idArea, String imeiDispositivo) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("nickname", idArea)
                .add("correo", imeiDispositivo).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_REGISTRO_NUEVO_MATCH)
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return null;
        }
    }

}
