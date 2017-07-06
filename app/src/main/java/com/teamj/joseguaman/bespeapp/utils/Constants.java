package com.teamj.joseguaman.bespeapp.utils;

/**
 * Created by alterbios on 05-Jul-17.
 */

public class Constants {

    private static final String $URL = "http://192.168.2.83:8080";

    private static final String $PATH_REGISTRO_NUEVO_MATCH = "/BeaconWS/webresources/ws/registrarAreaDispositivo";
    private static final String $PATH_NOTIFICACION_POR_AREA_TIPO = "/BeaconWS/webresources/ws/traerNotificacionPorAreaTipo";
    private static final String $PATH_LUGARES_POR_ID_AREA_NO_BYTES = "/BeaconWS/webresources/ws/traerLugaresPorIdAreaNoBytes";
    private static final String $PATH_AREA_POR_ID = "/BeaconWS/webresources/ws/traerAreaPorId";
    private static final String $PATH_LUGAR_POR_ID = "/BeaconWS/webresources/ws/traerLugarPorId";
    private static final String $PATH_LUGARES_POR_UUID = "/BeaconWS/webresources/ws/traerLugaresPorUUIDBeacon";
    private static final String $PATH_AREAS_POR_UUID = "/BeaconWS/webresources/ws/traerAreasPorUUIDBeacon";
    private static final String $PATH_IMAGES = "/media/";

    public static final boolean DEV_MODE = true;

    public static String getURLRegistroAreaDispositivo() {
        return $URL + $PATH_REGISTRO_NUEVO_MATCH;
    }


}

