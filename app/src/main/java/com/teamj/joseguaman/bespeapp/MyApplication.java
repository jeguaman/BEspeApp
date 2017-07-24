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
    private List<BeaconID> beaconIDEstimote= new ArrayList<>();
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= getApplicationContext();
        EstimoteSDK.initialize(getApplicationContext(), "beacon-tesis-ama", "422a5e24ee58a76c013dac074146f4b7");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }

    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }
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


    public void traerBeaconsServidor(){
        BeaconRestClient lrc = new BeaconRestClient(this);
        NotificacionRestClient notificacionRestClient= new NotificacionRestClient(this);
        AreaBeaconRestClient areaBeaconRestClient= new AreaBeaconRestClient(this);
        lrc.obtenerTodosBeaconsSinImagen(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                TypeToken<List<Beacon>> token = new TypeToken<List<Beacon>>() {
                };
                List<Beacon> beacons=gson.fromJson(response.getJsonEntity(), token.getType());
                System.out.println("BEACONSSSSSSSSSSSSSS");
                beacons.toString();
                cargarListaBeaconEstimote(beacons);
                BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(context);
                for (BeaconID b:beaconIDEstimote
                        ) {
                    beaconNotificationsManager.addNotification(b,
                            "Hola Mundo, world.",
                            "Goodbye, world.");
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

    private void cargarListaBeaconEstimote(List<Beacon> beaconsBase){
        BeaconID beaconID;
        for(Beacon b:beaconsBase){
            beaconID= new BeaconID(b.getBeaconId(),b.getUuid(), Integer.parseInt(b.getMajor()), Integer.parseInt(b.getMinor()));
            beaconIDEstimote.add(beaconID);
        }
    }
}
