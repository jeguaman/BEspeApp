package com.teamj.joseguaman.bespeapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.teamj.joseguaman.bespeapp.fragments.LugarFragment;
import com.teamj.joseguaman.bespeapp.modelo.Ejemplo;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;

import java.util.List;


/**
 * Created by alterbios on 06-Jul-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    List<List<Area>> data;

    public PagerAdapter(FragmentManager fm, int numOfTabs, List<List<Area>> data) {
        super(fm);
        mNumOfTabs = numOfTabs;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return LugarFragment.newInstance(data.get(position),0);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
