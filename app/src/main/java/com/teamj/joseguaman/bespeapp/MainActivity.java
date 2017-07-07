package com.teamj.joseguaman.bespeapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.teamj.joseguaman.bespeapp.fragments.MainFragment;
import com.teamj.joseguaman.bespeapp.utils.Tools;
import com.teamj.joseguaman.bespeapp.webService.restClientBase.VolleyRequest;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        VolleyRequest.init(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_principal);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getFragmentManager();

        switch (id) {

            case R.id.nav_principal: {
                setTitle(Tools.parseTitle(item.getTitle().toString()));
                Fragment fragment = new MainFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        //.addToBackStack(null) //se añade al backstack, es decir cuando aplaste el boton de atras
                        .commit();
                break;
            }

            case R.id.nav_entrada: {
                setTitle(Tools.parseTitle(item.getTitle().toString()));
                Fragment fragment = new EntradaFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        //.addToBackStack(null) //se añade al backstack, es decir cuando aplaste el boton de atras
                        .commit();
                break;
            }

            case R.id.nav_primer_piso: {
                setTitle(Tools.parseTitle(item.getTitle().toString()));
                Fragment fragment = new PrimerPisoFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        //.addToBackStack(null) //se añade al backstack, es decir cuando aplaste el boton de atras
                        .commit();
                break;
            }

            case R.id.nav_segundo_piso: {
                setTitle(Tools.parseTitle(item.getTitle().toString()));
                Fragment fragment = new SegundoPisoFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        //.addToBackStack(null) //se añade al backstack, es decir cuando aplaste el boton de atras
                        .commit();
                break;
            }


            case R.id.nav_tercer_piso: {
                setTitle(Tools.parseTitle(item.getTitle().toString()));
                Fragment fragment = new TercerPisoFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        //.addToBackStack(null) //se añade al backstack, es decir cuando aplaste el boton de atras
                        .commit();
                break;
            }

            default:
                Log.e(TAG, "Opción no válida");
                return super.onOptionsItemSelected(item);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
