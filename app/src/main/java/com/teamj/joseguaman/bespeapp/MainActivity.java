package com.teamj.joseguaman.bespeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.teamj.joseguaman.bespeapp.fragments.ParentTabFragment;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;
import com.teamj.joseguaman.bespeapp.webService.LugaresRestClient;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.VolleyRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ParentTabFragment fragmentParent;
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VolleyRequest.init(this);
        getIDs();
        setEvents();

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

    private void setEvents() {
        List<Area> listaAreas = new ArrayList<>();
        AreaRestClient lrc = new AreaRestClient(this);
        lrc.obtenerTodasAreas(new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        List<Area> listaArea = new ArrayList<>();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera);
        listaArea.add(new Area(1, "Area 1", "", bm));
        listaArea.add(new Area(2, "Area 2", "", bm));
        listaArea.add(new Area(3, "Area 3", "", bm));
        listaArea.add(new Area(4, "Area 4", "", bm));
        listaArea.add(new Area(5, "Area 5", "", bm));

        for (int i = 0; i < listaArea.size(); i++) {
            fragmentParent.addPage(listaArea.get(i));
        }

    }
}
