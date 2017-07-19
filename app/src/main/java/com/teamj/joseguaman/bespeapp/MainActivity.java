package com.teamj.joseguaman.bespeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.VolleyRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ParentTabFragment fragmentParent;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getIDs() {
        fragmentParent = (ParentTabFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
    }

    private void obtenerInformacionServidor() {
        AreaRestClient lrc = new AreaRestClient(this);
        lrc.obtenerTodasAreasSinImagen(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                List<Area> listaAreas = new ArrayList<>();
                Gson gson = new Gson();
                TypeToken<List<Area>> token = new TypeToken<List<Area>>() {
                };
                listaAreas = gson.fromJson(response.getJsonEntity(), token.getType());
                loadInfo(listaAreas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });

    }

    private void loadInfo(final List<Area> listaAreas) {

//        for (int i = 0; i < listaAreas.size(); i++) {
//            AreaRestClient lrc = new AreaRestClient(this);
//            final int finalI = i;
//            lrc.obtenerImagenPorArea(String.valueOf(listaAreas.get(i).getAreaId()), new Response.Listener<WSResponse>() {
//                @Override
//                public void onResponse(WSResponse response) {
//                    Gson gson = new Gson();
//                    Area a = gson.fromJson(response.getJsonEntity(), Area.class);
//                    /*InputStream inputStream = new ByteArrayInputStream(a.getImagen());
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    //de bitmap convertir a bytes[]
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                    byte[] byteArray = stream.toByteArray();*/
//                    listaAreas.get(finalI).setImagen(a.getImagen());
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//        }

        for (int i = 0; i < listaAreas.size(); i++) {
            fragmentParent.addPage(listaAreas.get(i));
        }
    }
}
