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
        RequestBody formBody = new FormEncodingBuilder().add("id_area", idArea)
                .add("imei", imeiDispositivo).build();
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

    public static String traerNotificacionesPorAreaTipo(String idArea, String tipo) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("id_area", idArea)
                .add("tipo", tipo).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_NOTIFICACION_POR_AREA_TIPO)
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

    public static String traerLugaresPorAreaNoBytes(String idArea) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("id_area", idArea).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_LUGARES_POR_ID_AREA_NO_BYTES)
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

    public static String traerAreaPorId(String idArea) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("id_area", idArea).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_AREA_POR_ID)
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

    public static String traerLugarPorId(String idLugar) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("id_lugar", idLugar).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_LUGAR_POR_ID)
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

    public static String traerLugaresPorUUID(String uuid) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("uuid", uuid).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_LUGARES_POR_UUID)
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

    public static String traerAreasPorUUID(String uuid) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder().add("uuid", uuid).build();
        Request request = new Request.Builder()
                .url(Constant.$URL + Constant.$PATH_AREAS_POR_UUID)
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
