package com.teamj.joseguaman.bespeapp;

import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamj.joseguaman.bespeapp.fragments.ParentTabFragment;
import com.teamj.joseguaman.bespeapp.fragments.dialog.InfoAppDialog;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.VolleyRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ParentTabFragment fragmentParent;
    private ProgressDialog mProgressDialog;

    public final static String EXTRA_MESSAGE = "msg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VolleyRequest.init(this);
        getIDs();
        obtenerInformacionServidor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, "mensaje");
            startActivity(intent);
            return true;
        } else if (id == R.id.action_us) {
            DialogFragment dialog = new InfoAppDialog();
            dialog.setCancelable(false);//evita que se cierre al presionar el back button
            dialog.show(getSupportFragmentManager(), InfoAppDialog.class.getSimpleName());
        } else if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }


        return super.onOptionsItemSelected(item);
    }

    private void getIDs() {
        fragmentParent = (ParentTabFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
    }

    private void obtenerInformacionServidor() {
        AreaRestClient lrc = new AreaRestClient(this);
        showProgressDialog("Beacons", "Cargando Información...");
        lrc.obtenerTodasAreasSinImagen(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                List<Area> listaAreas = new ArrayList<>();
                Gson gson = new Gson();
                TypeToken<List<Area>> token = new TypeToken<List<Area>>() {
                };
                listaAreas = gson.fromJson(response.getJsonEntity(), token.getType());
                loadInfo(listaAreas);
                hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressDialog();
                Log.e(TAG, error.toString());
            }
        });

    }

    private void loadInfo(final List<Area> listaAreas) {
        for (int i = 0; i < listaAreas.size(); i++) {
            fragmentParent.addPage(listaAreas.get(i));
        }
    }

    public void showProgressDialog(String title, String message) {
        mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
