package com.teamj.joseguaman.bespeapp.utils;

import android.Manifest;

/**
 * Created by alterbios on 05-Jul-17.
 */

public class Constants {

    //private static final String $URL = "http://192.168.2.83:8080";
    //private static final String $URL = "http://127.0.0.1:8080";
    //private static final String $URL = "http://192.168.2.58:8080";
    //casa
    //private static final String $URL = "http://192.168.2.58:8080";
    //biblioteca
    private static final String $URL = "http://192.168.100.69:8080";
    private static String WEB_PATH = "/BeaconWS/webresources/ws";
    private static String APP_NAME = "/BeaconWS/";


    private static final String $PATH_REGISTRO_NUEVO_MATCH = "/BeaconWS/webresources/ws/registrarAreaDispositivo";
    private static final String $PATH_NOTIFICACION_POR_BEACON_TIPO = "/BeaconWS/webresources/ws/traerNotificacionPorBeaconTipo";
    private static final String $PATH_NOTIFICACION_POR_AREA_TIPO = "/BeaconWS/webresources/ws/traerNotificacionPorAreaTipo";
    private static final String $PATH_LUGARES_POR_ID_AREA_NO_BYTES = "/BeaconWS/webresources/ws/traerLugaresPorIdAreaNoBytes";
    private static final String $PATH_AREA_POR_ID = "/BeaconWS/webresources/ws/traerAreaPorId";
    private static final String $PATH_AREAS_NO_IMAGEN = "/BeaconWS/webresources/ws/traerAreasNoImagen";
    private static final String $PATH_AREAS = "/BeaconWS/webresources/ws/traerAreas";
    private static final String $PATH_LUGAR_POR_ID = "/BeaconWS/webresources/ws/traerLugarPorId";
    private static final String $PATH_LUGARES_POR_UUID = "/BeaconWS/webresources/ws/traerLugaresPorUUIDBeacon";
    private static final String $PATH_AREAS_POR_UUID = "/BeaconWS/webresources/ws/traerAreasPorUUIDBeacon";
    private static final String $PATH_IMAGEN_LUGAR_POR_ID = "/BeaconWS/webresources/ws/traerImagenPorIdLugar";
    private static final String $PATH_ICONO_LUGAR_POR_ID = "/BeaconWS/webresources/ws/traerIconoPorIdLugar";
    private static final String $PATH_IMAGEN_AREA_POR_ID = "/BeaconWS/webresources/ws/traerImagenPorIdArea";
    private static final String $PATH_BEACONS_NO_IMAGEN = "/BeaconWS/webresources/ws/traerBeacons";
    private static final String $PATH_AREA_BEACON_IDS_BEACON_IDS = "/BeaconWS/webresources/ws/traerAreaBeaconListaPorIdsBeacon";
    private static final String $PATH_AREA_BEACON_ID_BEACON_ID = "/BeaconWS/webresources/ws/traerAreaBeaconPorIdBeacon";
    private static final String $PATH_IMAGES = "/media/";

    public static String[] ALL_REQUIRED_PERMISSION = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH
    };

    public static final int MY_PERMISSIONS_REQUEST = 311;

    public static String getHostUrlAppNAME() {
        return $URL + APP_NAME;
    }

    public static final String $BUNDLE_AREA = "area";

    public static final boolean DEV_MODE = true;

    public static String getURLRegistroAreaDispositivo() {
        return $URL + $PATH_REGISTRO_NUEVO_MATCH;
    }

    public static String getURLNotificacionesAreaTipo() {
        return $URL + $PATH_NOTIFICACION_POR_AREA_TIPO;
    }

    public static String getURLNotificacionesBeaconTipo() {
        return $URL + $PATH_NOTIFICACION_POR_BEACON_TIPO;
    }

    public static String getURLLugaresPorIdAreaNoImagenesBytes() {
        return $URL + $PATH_LUGARES_POR_ID_AREA_NO_BYTES;
    }

    public static String getURLAreaPorId() {
        return $URL + $PATH_AREA_POR_ID;
    }

    public static String getURLLugarPorId() {
        return $URL + $PATH_LUGAR_POR_ID;
    }

    public static String getURLLuagresPorUUID() {
        return $URL + $PATH_LUGARES_POR_UUID;
    }

    public static String getURLAreasPorUUID() {
        return $URL + $PATH_AREAS_POR_UUID;
    }

    public static String getURLImagenes() {
        return $URL + $PATH_IMAGES;
    }

    public static String getURLAreasSinImagen() {
        return $URL + $PATH_AREAS_NO_IMAGEN;
    }

    public static String getURLTodasAreas() {
        return $URL + $PATH_AREAS;
    }

    public static String getURLImagenLugar() {
        return $URL + $PATH_IMAGEN_LUGAR_POR_ID;
    }

    public static String getURLImagenArea() {
        return $URL + $PATH_IMAGEN_AREA_POR_ID;
    }

    public static String getURLIconoLugar() {
        return $URL + $PATH_ICONO_LUGAR_POR_ID;
    }

    public static String getURLBeaconsSinImagen() {
        return $URL + $PATH_BEACONS_NO_IMAGEN;
    }

    public static String getURLTraerAreaBeaconListaPorIdsBeacon() {
        return $URL + $PATH_AREA_BEACON_IDS_BEACON_IDS;
    }
    public static String getURLTraerAreaBeaconPorIdBeacon() {
        return $URL + $PATH_AREA_BEACON_ID_BEACON_ID;
    }
}

