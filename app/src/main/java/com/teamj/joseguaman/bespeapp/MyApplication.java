package com.teamj.joseguaman.bespeapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.estimote.sdk.EstimoteSDK;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamj.joseguaman.bespeapp.estimote.BeaconID;
import com.teamj.joseguaman.bespeapp.estimote.BeaconNotificationsManager;
import com.teamj.joseguaman.bespeapp.modelo.beacon.AreaBeacon;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Beacon;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Notificacion;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.AreaBeaconRestClient;
import com.teamj.joseguaman.bespeapp.webService.BeaconRestClient;
import com.teamj.joseguaman.bespeapp.webService.NotificacionRestClient;

import java.util.ArrayList;
import java.util.List;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    private static final String TAG = MyApplication.class.getSimpleName();
    private List<BeaconID> beaconIDEstimote = new ArrayList<>();
    private Context context;
    private BeaconNotificationsManager beaconNotificationsManager;
    private List<Notificacion> notificaciones=new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        EstimoteSDK.initialize(getApplicationContext(), "beacon-tesis-ama", "422a5e24ee58a76c013dac074146f4b7");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }

    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) {
            return;
        }
        traerBeaconsServidor();
    }

    //Original
/*
    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        beaconNotificationsManager.addNotification(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 13701, 9068),
                "Hola Mundo, world.",
                "Goodbye, world.");
        beaconNotificationsManager.startMonitoring();

        beaconNotificationsEnabled = true;
    }
*/

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }


    public void traerBeaconsServidor() {
        BeaconRestClient lrc = new BeaconRestClient(this);
        lrc.obtenerTodosBeaconsSinImagen(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                TypeToken<List<Beacon>> token = new TypeToken<List<Beacon>>() {
                };
                List<Beacon> beacons = gson.fromJson(response.getJsonEntity(), token.getType());
                beacons.toString();
                cargarListaBeaconEstimote(beacons);
                beaconNotificationsManager = new BeaconNotificationsManager(context);
                for (BeaconID b : beaconIDEstimote
                        ) {
                    agregarNotificaciones(b);
                }

                beaconNotificationsManager.startMonitoring();

                beaconNotificationsEnabled = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        /*areaBeaconRestClient.traerAreaBeaconPorIdsBeacon(beaconListString.toString(),new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                TypeToken<List<AreaBeacon>> token = new TypeToken<List<AreaBeacon>>() {
                };
                areaBeaconList = gson.fromJson(response.getJsonEntity(), token.getType());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        System.out.println("AREABEACONLIST:");
        areaBeaconList.toString();*/
    }

    private void cargarListaBeaconEstimote(List<Beacon> beaconsBase) {
        BeaconID beaconID;
        for (Beacon b : beaconsBase) {
            beaconID = new BeaconID(b.getBeaconId(), b.getUuid(), Integer.parseInt(b.getMajor()), Integer.parseInt(b.getMinor()));
            beaconIDEstimote.add(beaconID);
        }
    }

    private void agregarNotificaciones(final BeaconID beaconId) {

        NotificacionRestClient notificacionRestClient= new NotificacionRestClient(context);
        notificacionRestClient.obtenerNotificacionBeacon(String.valueOf(beaconId.getClaveBase()), new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                TypeToken<List<Notificacion>> token = new TypeToken<List<Notificacion>>() {
                };
                notificaciones = gson.fromJson(response.getJsonEntity(), token.getType());
                if (notificaciones!=null && !notificaciones.isEmpty()){
                    setearNotificaciones(beaconId,notificaciones);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

        /*beaconNotificationsManager.addNotification(beaconId,
                "Hola Mundo, world.",
                "Goodbye, world.");*/

    }

    private void setearNotificaciones(BeaconID beaconId,List<Notificacion> notificaciones){
        Notificacion entrada;
        Notificacion salida;
        entrada= notificaciones.get(0);
        salida=notificaciones.get(1);
        beaconNotificationsManager.addNotification(beaconId,
                entrada.getDescripcion(),
                salida.getDescripcion());
    }
}
