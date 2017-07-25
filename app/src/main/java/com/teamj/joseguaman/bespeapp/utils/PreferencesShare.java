package com.teamj.joseguaman.bespeapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jose Guaman on 23/07/2017.
 */

public class PreferencesShare {


    private Context context;
    private SharedPreferences sharedPreferences;

    public static final String USER_PROFILE_ID = "com.teamj.joseguaman.bespeapp.USER_PROFILE_ID";
    public static final String USER_PROFILE_NAME = "com.teamj.joseguaman.bespeapp.USER_PROFILE_NAME";
    public static final String EQUIPO_NAME = "com.teamj.joseguaman.bespeapp.EQUIPO_NAME";
    public static final String SOUND = "com.teamj.joseguaman.bespeapp.SOUND";
    public static final String MODE_NIGHT = "com.teamj.joseguaman.bespeapp.MODE_NIGHT";
    private static final String PREFERENCE_NAME = "com.teamj.joseguaman.bespeapp.MAIN_PREF";


    public PreferencesShare(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setEquipoName(String equipoName) {
        sharedPreferences.edit().putString(EQUIPO_NAME, equipoName).apply();
    }

    public String getEquipoName() {
        return sharedPreferences.getString(EQUIPO_NAME, "");
    }

    public void setSoundApp(Boolean sonido) {
        sharedPreferences.edit().putBoolean(SOUND, sonido).apply();
    }

    public Boolean getSoundApp() {
        return sharedPreferences.getBoolean(SOUND, Boolean.TRUE);
    }

    public void setModeNightApp(Boolean sonido) {
        sharedPreferences.edit().putBoolean(MODE_NIGHT, sonido).apply();
    }

    public Boolean getModeNightApp() {
        return sharedPreferences.getBoolean(MODE_NIGHT, Boolean.TRUE);
    }
}
