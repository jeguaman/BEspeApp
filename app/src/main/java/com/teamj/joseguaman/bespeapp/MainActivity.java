package com.teamj.joseguaman.bespeapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.teamj.joseguaman.bespeapp.fragments.MainFragment;
import com.teamj.joseguaman.bespeapp.fragments.ParentTabFragment;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.utils.Tools;
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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        VolleyRequest.init(this);
        getIDs();
        setEvents();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
//        buttonAddPage = (Button) findViewById(R.id.buttonAddPage);
        fragmentParent = (ParentTabFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
        //textView = (TextView) findViewById(R.id.editTextPageName);
    }

    private void setEvents() {
        //agregar a la lista
        List<Lugar> listaLugar = new ArrayList<>();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera);
        listaLugar.add(new Lugar(1, new Area(), "descripciom1", bm, "titulo1"));
        listaLugar.add(new Lugar(2, new Area(), "descripciom2", bm, "titulo2"));
        listaLugar.add(new Lugar(3, new Area(), "descripciom3", bm, "titulo3"));
        listaLugar.add(new Lugar(4, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(5, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(6, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(7, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(8, new Area(), "descripciom4", bm, "titulo4"));

        for (int i = 0; i < listaLugar.size(); i++) {
            fragmentParent.addPage(listaLugar.get(i).getTitulo() + "");
        }
//        buttonAddPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!textView.getText().toString().equals("")) {
//                    fragmentParent.addPage(textView.getText() + "");
//                    textView.setText("");
//                } else {
//                    Toast.makeText(MainActivity.this, "Page name is empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}
