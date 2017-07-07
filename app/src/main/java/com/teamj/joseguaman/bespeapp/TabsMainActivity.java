package com.teamj.joseguaman.bespeapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.teamj.joseguaman.bespeapp.adapter.ViewPagerAdapter;
import com.teamj.joseguaman.bespeapp.modelo.Ejemplo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alterbios on 06-Jul-17.
 */

public class TabsMainActivity extends AppCompatActivity {

    private static final String TAG = TabsMainActivity.class.getSimpleName();

    List<Ejemplo> listaEjemplo = new ArrayList<>();
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
