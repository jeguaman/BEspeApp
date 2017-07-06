package com.teamj.joseguaman.bespeapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.teamj.joseguaman.bespeapp.adapter.PagerAdapter;
import com.teamj.joseguaman.bespeapp.modelo.Ejemplo;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alterbios on 06-Jul-17.
 */

public class TabsMainActivity extends AppCompatActivity {

    private static final String TAG = TabsMainActivity.class.getSimpleName();

    List<Ejemplo> listaEjemplo = new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        listaEjemplo.add(new Ejemplo(1, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(2, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(3, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(4, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(5, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(6, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(7, "tab", "descripcion"));
        listaEjemplo.add(new Ejemplo(8, "tab", "descripcion"));

        for (int i = 0; i < listaEjemplo.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(listaEjemplo.get(i).getNombre() + (i + 1)));
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            //allProducts.add(cat.getProductsList());
        }

        List<Area> listaArea = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.pager);
        //adapter = new PagerAdapter
        //        (getSupportFragmentManager(), tabLayout.getTabCount(), listaArea);
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("ViewPager ", " getCurrentItem() " + viewPager.getCurrentItem());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
