package com.teamj.joseguaman.bespeapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.ViewPagerAdapter;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;

/**
 * Created by Jose Guaman on 06/07/2017.
 */

public class ParentTabFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private int selectedTabPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_parent, container, false);
        getIDs(view);
        setEvents();
        return view;
    }

    private void getIDs(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mAdapter = new ViewPagerAdapter(getFragmentManager(), getActivity(), mViewPager, mTabLayout);
        mViewPager.setAdapter(mAdapter);
    }

    private void setEvents() {
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                mViewPager.setCurrentItem(tab.getPosition());
                selectedTabPosition = mViewPager.getCurrentItem();
                Log.d("el objeto es : ", "Objeto " + mAdapter.getItem(tab.getPosition()).toString());
                Log.d("Selected", "Selected " + tab.getPosition());
//                Toast.makeText(getActivity(), tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                Log.d("Unselected", "Unselected " + tab.getPosition());
            }
        });
    }

    public void addPage(Area area) {
        Bundle bundle = new Bundle();
        bundle.putString("data", area.getTitulo());
        ChildTabFragment fragmentChild = new ChildTabFragment();
        fragmentChild.setArguments(bundle);
        mAdapter.addNewFragment(fragmentChild, area.getTitulo());
        mAdapter.notifyDataSetChanged();
        if (mAdapter.getCount() > 0) mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(mAdapter.getCount() - 1);
        setupTabLayout();
    }

    public void setupTabLayout() {
        selectedTabPosition = mViewPager.getCurrentItem();
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
    }
}
