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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.ViewPagerAdapter;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.utils.Tools;

import java.sql.SQLOutput;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 06/07/2017.
 */

public class ParentTabFragment extends Fragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.image_right)
    Button btnViewSiguiente;

    @BindView(R.id.image_left)
    Button btnViewAtras;

    @BindView(R.id.btn_imei)
    Button btnImei;

    private Unbinder unbinder;
    private ViewPagerAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_parent, container, false);
        unbinder = ButterKnife.bind(this, view);
        getIDs();
        setEvents();
        return view;
    }

    private void getIDs() {
        mAdapter = new ViewPagerAdapter(getFragmentManager(), getActivity());
        mViewPager.setAdapter(mAdapter);
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

    @OnClick(R.id.btn_imei)//@BindView(R.id.btn_imei)
    public void obtenerImei() {
        //Toast.makeText(view.getContext(), "el imei es: " + Tools.getIMEI(view.getContext()), Toast.LENGTH_SHORT).show();
    }

    private void verificarBotonesSigAnt() {
        int selectedTabPosition = mViewPager.getCurrentItem();
        System.out.println("adalter count es : " + mAdapter.getCount());
        System.out.println("selec position count es : " + selectedTabPosition);
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

    public void addPage(Area area) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.$BUNDLE_AREA, area);
        ChildTabFragment fragmentChild = new ChildTabFragment();
        fragmentChild.setArguments(bundle);
        mAdapter.addNewFragment(fragmentChild, area.getTitulo());
        mAdapter.notifyDataSetChanged();
        if (mAdapter.getCount() > 0) mTabLayout.setupWithViewPager(mViewPager);
        setupTabLayout();
    }


    public void setupTabLayout() {
        //selectedTabPosition = mViewPager.getCurrentItem();
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
