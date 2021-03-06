package com.teamj.joseguaman.bespeapp.fragments;

import android.app.ProgressDialog;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.ViewPagerAdapter;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.utils.Tools;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 06/07/2017.
 */

public class ParentTabFragment extends Fragment {

    private static final String TAG = ParentTabFragment.class.getSimpleName();

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

    private Unbinder unbinder;
    private ViewPagerAdapter mAdapter;
    private View view;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_tab_parent, container, false);
        unbinder = ButterKnife.bind(this, view);
        getIDs();
        obtenerInformacionServidor();
        return view;
    }

    private void getIDs() {
        mAdapter = new ViewPagerAdapter(getFragmentManager(), getActivity());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void loadInfo(final List<Area> listaAreas) {
        for (int i = 0; i < listaAreas.size(); i++) {
            addPage(listaAreas.get(i));
        }
        mAdapter.notifyDataSetChanged();
        //if (mAdapter.getCount() > 0)

        setupTabLayout();
        setEvents();

    }

    private void obtenerInformacionServidor() {
        AreaRestClient lrc = new AreaRestClient(getContext());
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
        mProgressDialog = ProgressDialog.show(getContext(), title, message, true, false);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
