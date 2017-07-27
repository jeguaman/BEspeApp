package com.teamj.joseguaman.bespeapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.estimote.sdk.SystemRequirementsChecker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamj.joseguaman.bespeapp.adapter.ViewPagerAdapter;
import com.teamj.joseguaman.bespeapp.fragments.ChildTabFragment;
import com.teamj.joseguaman.bespeapp.fragments.dialog.InfoAppDialog;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.utils.PreferencesShare;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PreferencesShare sharedPreferences;

    @Nullable
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Nullable
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Nullable
    @BindView(R.id.image_right)
    Button btnViewSiguiente;

    @Nullable
    @BindView(R.id.image_left)
    Button btnViewAtras;

    private ViewPagerAdapter mAdapter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sharedPreferences = new PreferencesShare(this);
        verificarModoNocturno();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIDs();
        obtenerInformacionServidor();
        verificarBotonesSigAnt();
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
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setEvents() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                mViewPager.setCurrentItem(tab.getPosition());
                verificarBotonesSigAnt();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication app = (MyApplication) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "No se puede escanear Beacons, algunos permisos no estan autorizados.");
            Log.e(TAG, "Leer más acerca de que es necesario en: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "Si esto es arreglado, usted podrá ver un dialogo sobre la pantalla de su aplicación ahora, si desea pregunte sobre el correcto funcionamiento.");
        } else if (!app.isBeaconNotificationsEnabled()) {
            Log.d(TAG, "Habilitando notificaciones beacon.......");
            app.enableBeaconNotifications();
            //app.enableBeaconNotifications();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    private void verificarModoNocturno() {
        if (sharedPreferences.getModeNightApp()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
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

    public void showProgressDialog(String title, String message) {
        mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void loadInfo(final List<Area> listaAreas) {
        for (int i = 0; i < listaAreas.size(); i++) {
            addPage(listaAreas.get(i));
        }
        mAdapter.notifyDataSetChanged();
        setupTabLayout();
        setEvents();

    }

    public void addPage(Area area) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.$BUNDLE_AREA, area);
        ChildTabFragment fragmentChild = new ChildTabFragment();
        fragmentChild.setArguments(bundle);
        mAdapter.addNewFragment(fragmentChild, area.getTitulo());

    }


    public void setupTabLayout() {
        //selectedTabPosition = mViewPager.getCurrentItem();
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
    }


    private void verificarBotonesSigAnt() {
        int selectedTabPosition = mViewPager.getCurrentItem();
        if (selectedTabPosition == 0) {
            btnViewAtras.setVisibility(View.GONE);
            btnViewSiguiente.setVisibility(View.VISIBLE);
        } else if (selectedTabPosition == mAdapter.getCount() - 1) {
            btnViewAtras.setVisibility(View.VISIBLE);
            btnViewSiguiente.setVisibility(View.GONE);
        } else {
            btnViewAtras.setVisibility(View.VISIBLE);
            btnViewSiguiente.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.image_right)
    public void tabSiguiente() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        verificarBotonesSigAnt();
    }

    @OnClick(R.id.image_left)
    public void tabAnterior() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        verificarBotonesSigAnt();
    }
}


