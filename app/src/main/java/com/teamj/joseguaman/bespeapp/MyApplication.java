package com.teamj.joseguaman.bespeapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.estimote.sdk.EstimoteSDK;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamj.joseguaman.bespeapp.estimote.BeaconEstimote;
import com.teamj.joseguaman.bespeapp.estimote.BeaconID;
import com.teamj.joseguaman.bespeapp.estimote.BeaconNotificationsManager;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.BeaconRestClient;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.VolleyRequest;

import java.util.List;

;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    private static final String TAG = MyApplication.class.getSimpleName();
    private Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        EstimoteSDK.initialize(getApplicationContext(), "beacon-tesis-ama", "422a5e24ee58a76c013dac074146f4b7");
        VolleyRequest.init(this);
        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }


    //TODO: debería ir en el main activity todos los metodos hacia abajo
    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) {
            Log.i("pinche TAG", "paso aca");
            return;
        }
        Log.i("pinche TAG", "va a a la consulta");
        traerBeaconsServidor();
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }


    public void traerBeaconsServidor() {
        BeaconRestClient lrc = new BeaconRestClient(this);
        lrc.obtenerTodosBeaconsAsociadosEstimote(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                TypeToken<List<BeaconEstimote>> token = new TypeToken<List<BeaconEstimote>>() {
                };
                List<BeaconEstimote> beacons = gson.fromJson(response.getJsonEntity(), token.getType());
                if (beacons != null && !beacons.isEmpty()) {
                    agregarNotificacionesEnBeacons(beacons);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
    }

    private void agregarNotificacionesEnBeacons(List<BeaconEstimote> estimotes) {
        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this, estimotes);
        for (BeaconEstimote b : estimotes) {
            beaconNotificationsManager.addNotification(
                    new BeaconID(b.getBeaconId(), b.getProximityUUID(), b.getMajor(), b.getMinor()),
                    b.getEnterMessage(),
                    b.getExitMessage());
        }
        beaconNotificationsManager.startMonitoring();


        beaconNotificationsEnabled = true;
    }
}
