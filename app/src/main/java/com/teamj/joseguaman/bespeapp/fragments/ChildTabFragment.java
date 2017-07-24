package com.teamj.joseguaman.bespeapp.fragments;


import android.app.Application;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.LugarAdapter;
import com.teamj.joseguaman.bespeapp.fragments.dialog.LugaresInfoDialog;
import com.teamj.joseguaman.bespeapp.listener.RecyclerItemClickListener;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.modelo.util.DialogInformacion;
import com.teamj.joseguaman.bespeapp.utils.ConnectionDetector;
import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.utils.Tools;
import com.teamj.joseguaman.bespeapp.webService.AreaRestClient;
import com.teamj.joseguaman.bespeapp.webService.LugaresRestClient;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 08/07/2017.
 */

public class ChildTabFragment extends Fragment {

    private static final String TAG = ChildTabFragment.class.getSimpleName();

    @BindView(R.id.reclyclerViewLugares)
    RecyclerView mRecyclerView;

    @BindView(R.id.image_area)
    ImageView mImageView;

    private View view;
    private Unbinder unbinder;
    private LugarAdapter mMainAdapter;
    private ConnectionDetector mConnectionDetector;
    private int idLugarSeleccionado;
    private Area areaSeleccionada;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_child, container, false);
        Bundle bundle = getArguments();
        areaSeleccionada = (Area) bundle.getSerializable(Constants.$BUNDLE_AREA);
        unbinder = ButterKnife.bind(this, view);
        loadIMageArea(areaSeleccionada.getAreaId());
        mConnectionDetector = new ConnectionDetector(getActivity());
        setHasOptionsMenu(true);
        initLayout();
        loadData(areaSeleccionada.getAreaId());
        return view;
    }

    private void loadIMageArea(Integer idArea) {
        //showProgressDialog("Beacon", "Cargando Imagen 1...");
        AreaRestClient arc = new AreaRestClient(getActivity());
        arc.obtenerImagenPorArea(String.valueOf(idArea), new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                Area a = gson.fromJson(response.getJsonEntity(), Area.class);
                InputStream inputStream = new ByteArrayInputStream(a.getImagen());
//                showProgressDialog("Area", "Cargando Información...");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_account_balance);
                }
                mImageView.setImageBitmap(bitmap);
                //hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //hideProgressDialog();
                Log.e(TAG, "ERROR " + error);
            }
        });
    }

    public void initLayout() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final Lugar lugar = mMainAdapter.getItem(position);
                        idLugarSeleccionado = lugar.getLugarId();
                        showProgressDialog("Beacon", "Cargando Información...");
                        LugaresRestClient lugaresRestClient = new LugaresRestClient(getActivity());
                        lugaresRestClient.getImagenPorIdLugar(String.valueOf(idLugarSeleccionado), new Response.Listener<WSResponse>() {
                            @Override
                            public void onResponse(WSResponse response) {
                                DialogInformacion dialogInfo = new DialogInformacion();
                                Gson gson = new Gson();
                                Lugar l = gson.fromJson(response.getJsonEntity(), Lugar.class);
                                InputStream inputStream = new ByteArrayInputStream(l.getImagen());
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                dialogInfo.setImage(bitmap);
                                dialogInfo.setMensaje(lugar.getDescripcion());
                                dialogInfo.setTitulo(lugar.getTitulo());
                                EventBus.getDefault().postSticky(dialogInfo);
                                DialogFragment dialog = new LugaresInfoDialog();
                                dialog.setCancelable(false);//evita que se cierre al presionar el back button
                                dialog.show(ChildTabFragment.this.getChildFragmentManager(), ChildTabFragment.class.getSimpleName());
                                hideProgressDialog();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                hideProgressDialog();
                                Log.e(TAG, "Error es: " + error);
                            }
                        });
                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {

                    }
                })
        );
    }

    public void loadData(int idArea) {
        LugaresRestClient lrc = new LugaresRestClient(getActivity());
        lrc.getLugaresPorArea(String.valueOf(idArea), new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                List<Lugar> listaLugar = new ArrayList<>();
                Gson gson = new Gson();
                TypeToken<List<Lugar>> token = new TypeToken<List<Lugar>>() {
                };
                listaLugar = gson.fromJson(response.getJsonEntity(), token.getType());
                loadInfo(listaLugar);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
    }

    private void loadInfo(List<Lugar> listaLugar) {
        mMainAdapter = new LugarAdapter(listaLugar, getActivity());//TODO: verificar si es el contexto correcto.
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showProgressDialog(String title, String message) {
        mProgressDialog = ProgressDialog.show(getActivity(), title, message, true, false);
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
