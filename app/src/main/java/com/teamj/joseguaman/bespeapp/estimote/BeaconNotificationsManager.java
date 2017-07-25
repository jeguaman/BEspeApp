package com.teamj.joseguaman.bespeapp.estimote;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.google.gson.Gson;
import com.teamj.joseguaman.bespeapp.MainActivity;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Notificacion;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Registro;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.utils.Tools;
import com.teamj.joseguaman.bespeapp.webService.NotificacionRestClient;
import com.teamj.joseguaman.bespeapp.webService.RegistroRestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BeaconNotificationsManager {

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;

    private List<Region> regionsToMonitor = new ArrayList<>();
    private HashMap<String, String> enterMessages = new HashMap<>();
    private HashMap<String, String> exitMessages = new HashMap<>();
    private List<Notificacion> notifiaciones = new ArrayList<>();
    private List<InformacionBeacon> beacons = new ArrayList();

    private Context context;

    private int notificationID = 0;

    public BeaconNotificationsManager(final Context context) {
        this.context = context;
        beaconManager = new BeaconManager(context);
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.d(TAG, "onEnteredRegion: " + region.getIdentifier());
                String message = enterMessages.get(region.getIdentifier());
                if (message != null) {
                    showNotification(message);
                    Registro registroIngreso = new Registro();
                    RegistroRestClient restClient = new RegistroRestClient(context);
                    restClient.registroAreaDispositivo("1", Tools.getIMEI(context), "E", new Response.Listener<WSResponse>() {
                        @Override
                        public void onResponse(WSResponse response) {
                            Gson gson = new Gson();
                            Registro a = gson.fromJson(response.getJsonEntity(), Registro.class);
                            System.out.println("RESGISRO:"+a.toString());
                            //hideProgressDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //hideProgressDialog();
                            Log.e(TAG, "ERROR " + error);
                        }
                    });
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
                String message = exitMessages.get(region.getIdentifier());
                if (message != null) {
                    showNotification(message);
                    //TODO: Poner el registro de salida
                }
            }
        });
    }


    public void addNotification(BeaconID beaconID, List<Notificacion> notificaciones) {
        Region region = beaconID.toBeaconRegion();
        recuperarNotificacion();
        for (Notificacion notificacion : notificaciones
                ) {
            if (notificacion.getTipo().compareTo("E") == 0) {
                enterMessages.put(region.getIdentifier(), notificacion.getDescripcion());
            } else if (notificacion.getTipo().compareTo("S") == 0) {
                exitMessages.put(region.getIdentifier(), notificacion.getDescripcion());
            }
        }

        regionsToMonitor.add(region);
    }

    //Original
    public void addNotification(BeaconID beaconID, String enterMessage, String exitMessage) {
        Region region = beaconID.toBeaconRegion();
        enterMessages.put(region.getIdentifier(), enterMessage);
        exitMessages.put(region.getIdentifier(), exitMessage);
        regionsToMonitor.add(region);
    }

    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (Region region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });
    }

    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Beacon Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }

    private void recuperarNotificacion(){
        notifiaciones= new ArrayList<>();
        NotificacionRestClient notificacionRestClient= new NotificacionRestClient(context);

    }
}
