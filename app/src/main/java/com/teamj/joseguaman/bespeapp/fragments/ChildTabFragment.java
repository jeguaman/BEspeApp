package com.teamj.joseguaman.bespeapp.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.view.ViewGroup;
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
import com.teamj.joseguaman.bespeapp.webService.LugaresRestClient;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 08/07/2017.
 */

public class ChildTabFragment extends Fragment {


    @BindView(R.id.reclyclerViewLugares)
    RecyclerView mRecyclerView;

    private View view;
    private Unbinder unbinder;
    private LugarAdapter mMainAdapter;
    private ConnectionDetector mConnectionDetector;
    private int idLugarSeleccionado;
    private Area areaSeleccionada;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_child, container, false);
        Bundle bundle = getArguments();
        areaSeleccionada = (Area) bundle.getSerializable(Constants.$BUNDLE_AREA);
        unbinder = ButterKnife.bind(this, view);
        mConnectionDetector = new ConnectionDetector(getActivity());
        setHasOptionsMenu(true);
        initLayout();
        loadData(areaSeleccionada.getAreaId());
        return view;
    }


    public void initLayout() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Lugar lugar = mMainAdapter.getItem(position);
                        idLugarSeleccionado = lugar.getLugarId();
                        //TODO: consultar por id en un nuevo metodo

                        DialogInformacion dialogInfo = new DialogInformacion();
                        dialogInfo.setImage(R.drawable.ic_menu_camera);
//                        dialogInfo.setMensaje(getResources().getString(R.string.metodologia_aviso_siembra));
                        dialogInfo.setMensaje(lugar.getDescripcion());
                        dialogInfo.setTitulo(lugar.getTitulo() + idLugarSeleccionado);
                        EventBus.getDefault().postSticky(dialogInfo);
                        DialogFragment dialog = new LugaresInfoDialog();
                        dialog.setCancelable(false);//evita que se cierre al presionar el back button
                        dialog.show(ChildTabFragment.this.getChildFragmentManager(), ChildTabFragment.class.getSimpleName());
                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {

                    }
                })
        );
    }

    private void loadInfoDialogo() {
        //getImagenPorIdLugar
    }

    public void loadData(int idArea) {
        LugaresRestClient lrc = new LugaresRestClient(getActivity());
        lrc.getLugaresPorArea(String.valueOf(idArea), new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                //TODO: Juanillo aqui es donde toca probar que se cargue los lugares por id de area
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

            }
        });


    }

    private void loadInfo(List<Lugar> listaLugar) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera);
        for (int i = 0; i < listaLugar.size(); i++) {
            listaLugar.get(i).setImagen(bm);
        }
//        for (int i = 0; i < idArea; i++) {
//            listaLugar.add(new Lugar(i + 1, new Area(), "descripcion" + (i + 1), bm, "titulo" + (i + 1)));
//        }
        mMainAdapter = new LugarAdapter(listaLugar);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
